package com.delivery.servlets;

import com.delivery.database.dao.UserDAO;
import com.delivery.database.entities.User;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/registrationPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setRole(User.ROLE_USER);
        boolean hasError = false;
        String errorMessage = null;
        String errorPass = null;
        if (!password.equals(password2)) {
            hasError = true;
            errorPass = "password1 != password2";
        }
        user.setPassword(password);
        try {
            UserDAO userDAO = UserDAO.getInstance();
            userDAO.insert(user);
        } catch (DBException e) {
            hasError = true;
            errorMessage = e.getMessage();
            log.error(e.getMessage());
        }

        if (hasError) {
            req.setAttribute("user", user);
            req.setAttribute("errorPass", errorPass);
            req.setAttribute("errorMessage", errorMessage);
            doGet(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }

    }
}
