package com.delivery.servlets;

import com.delivery.database.dao.ApplicationDAO;
import com.delivery.database.dao.DepartmentDAO;
import com.delivery.database.entities.Application;
import com.delivery.database.entities.Department;
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

@WebServlet(urlPatterns = "/manage/applications")
public class ManageApplicationsServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ManageApplicationsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Application> applications = null;
        List<Department> departments = null;
        try {
            ApplicationDAO appDAO = new ApplicationDAO();
            applications = appDAO.findAll();
            DepartmentDAO deptDAO = new DepartmentDAO();
            departments = deptDAO.findAll();
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        req.setAttribute("applications", applications);
        req.setAttribute("departments", departments);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/manageApplicationsPage.jsp");
        dispatcher.forward(req, resp);
    }
}
