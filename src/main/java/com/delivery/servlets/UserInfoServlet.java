package com.delivery.servlets;

import com.delivery.database.dao.ApplicationDAO;
import com.delivery.database.dao.DepartmentDAO;
import com.delivery.database.dao.WaybillDAO;
import com.delivery.database.entities.Application;
import com.delivery.database.entities.Department;
import com.delivery.database.entities.User;
import com.delivery.database.entities.Waybill;
import com.delivery.exceptions.DBException;
import com.delivery.filters.Utils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/profile"})
public class UserInfoServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(UserInfoServlet.class);
    private final ApplicationDAO appDAO = ApplicationDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = Utils.getUserInSession(session);
        req.setAttribute("user", user);
        List<Application> applications = null;
        List<Department> departments = null;
        List<Waybill> waybills = null;
        try {
            applications = appDAO.findAllByUser(user.getId());
            DepartmentDAO deptDAO = DepartmentDAO.getInstance();
            departments = deptDAO.findAll();
            WaybillDAO waybillDAO = WaybillDAO.getInstance();
            waybills = waybillDAO.findAll();
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        req.setAttribute("applications", applications);
        req.setAttribute("departments", departments);
        req.setAttribute("waybills", waybills);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appId = Integer.parseInt(req.getParameter("application"));
        Application app = new Application();
        app.setId(appId);
        try {
            appDAO.delete(app);
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
