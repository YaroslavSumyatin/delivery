package com.delivery.servlets;

import com.delivery.database.dao.ApplicationDAO;
import com.delivery.database.dao.DepartmentDAO;
import com.delivery.database.entities.Application;
import com.delivery.database.entities.Department;
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
import java.util.List;

@WebServlet(urlPatterns = { "/profile" })
public class UserInfoServlet extends HttpServlet {

    private final ApplicationDAO appDAO = new ApplicationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = Utils.getUserInSession(session);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.setAttribute("user", user);
        List<Application> applications = null;
        List<Department> departments = null;
        try {
            applications = appDAO.findAllByUser(user.getId());
            DepartmentDAO deptDAO = new DepartmentDAO();
            departments = deptDAO.findAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        req.setAttribute("applications", applications);
        req.setAttribute("departments", departments);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appId = Integer.parseInt(req.getParameter("application"));
        Application app = new Application();
        app.setId(appId);
        try{
            appDAO.delete(app);
        } catch (DBException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
