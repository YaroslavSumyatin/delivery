package com.delivery.servlets;

import com.delivery.database.dao.UserDAO;
import com.delivery.database.entities.User;
import com.delivery.exceptions.DBException;
import com.delivery.filters.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/profile/delete"})
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = Utils.getUserInSession(session);
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.delete(user);
            resp.sendRedirect(req.getContextPath() + "/logout");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
