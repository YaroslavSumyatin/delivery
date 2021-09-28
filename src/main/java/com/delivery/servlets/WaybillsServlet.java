package com.delivery.servlets;

import com.delivery.database.dao.ApplicationDAO;
import com.delivery.database.dao.UserDAO;
import com.delivery.database.dao.WaybillDAO;
import com.delivery.database.entities.Application;
import com.delivery.database.entities.User;
import com.delivery.database.entities.Waybill;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/manage/waybills"})
public class WaybillsServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(WaybillsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WaybillDAO waybillDAO = WaybillDAO.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        ApplicationDAO appDAO = ApplicationDAO.getInstance();
        List<Waybill> waybills = null;
        List<Application> applications = null;
        List<User> users = null;
        try {
            waybills = waybillDAO.findAll();
            applications = appDAO.findAll();
            users = userDAO.findAll();
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        req.setAttribute("waybills", waybills);
        req.setAttribute("apps", applications);
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/waybillsPage.jsp");
        dispatcher.forward(req, resp);
    }
}
