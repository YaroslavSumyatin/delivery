package com.delivery.servlets;

import com.delivery.database.dao.ApplicationDAO;
import com.delivery.database.entities.Application;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(PaymentServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appId = Integer.parseInt(req.getParameter("application"));
        try {
            ApplicationDAO appDAO = new ApplicationDAO();
            Application app = appDAO.findById(appId);
            if (!app.getState().equals(Application.STATE_WAITING_FOR_PAYMENT)) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        req.setAttribute("appId", appId);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/creditCardPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
