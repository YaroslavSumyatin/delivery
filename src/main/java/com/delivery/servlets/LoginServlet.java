package com.delivery.servlets;

import com.delivery.database.dao.UserDAO;
import com.delivery.database.entities.User;
import com.delivery.exceptions.DBException;
import com.delivery.filters.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/loginPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = null;
        boolean hasError = false;
        String errorMessage = null;

        try {
            UserDAO userDAO = new UserDAO();
            user = userDAO.validateUser(login, password);
            if (user == null) {
                hasError = true;
                errorMessage = "login or password are invalid";
            }
        } catch (DBException e) {
            errorMessage = e.getMessage();
            hasError = true;
            log(e.getMessage(), e);
        }

        if (hasError) {
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("user", user);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/loginPage.jsp");
            dispatcher.forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            Utils.setUserInSession(session, user);
            Utils.deleteUserCookie(resp);
        }

        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
