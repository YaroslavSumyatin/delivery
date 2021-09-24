package com.delivery.servlets;

import com.delivery.database.PasswordUtils;
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

@WebServlet(urlPatterns = {"/profile/edit"})
public class EditUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = Utils.getUserInSession(session);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/editUserPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String role = req.getParameter("role");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String newPassword1 = req.getParameter("newPassword1");
        String newPassword2 = req.getParameter("newPassword2");
        HttpSession session = req.getSession();
        User userInSession = Utils.getUserInSession(session);
        if (!newPassword1.isEmpty() || !newPassword2.isEmpty()) {
            if (!newPassword1.equals(newPassword2)) {
                req.setAttribute("errorMessage", "passwords are not equals");
                resp.sendRedirect(req.getContextPath() + "/login");
                System.out.println("new password trouble");
                return;
            }
        }
        User newData = new User();
        UserDAO userDAO = new UserDAO();
        newData.setId(id);
        newData.setLogin(login);
        newData.setEmail(email);
        newData.setRole(role);
        newData.setName(name);
        newData.setSurname(surname);
        newData.setPassword(userInSession.getPassword());
        newData.setSalt(userInSession.getSalt());
        try{
            if(newPassword1.isEmpty()) userDAO.update(newData);
            else userDAO.update(newData, newPassword1);
        } catch (DBException e) {
            e.printStackTrace();
        }
        Utils.setUserInSession(session, newData);
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
