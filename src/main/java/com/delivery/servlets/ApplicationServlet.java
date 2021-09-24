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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(urlPatterns = {"/application"})
public class ApplicationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = Utils.getUserInSession(session);
        req.setAttribute("user", user);
        String indexFrom = null;
        String indexTo = null;
        if (req.getParameter("departmentFrom") != null) indexFrom = req.getParameter("departmentFrom");
        if (req.getParameter("departmentTo") != null) indexTo = req.getParameter("departmentTo");
        req.setAttribute("indexFrom", indexFrom);
        req.setAttribute("indexTo", indexTo);
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Department> depts = null;
        try {
            depts = departmentDAO.findAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        req.setAttribute("departments", depts);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/applicationPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int departmentFrom = Integer.parseInt(req.getParameter("departmentFrom"));
        int departmentTo = Integer.parseInt(req.getParameter("departmentTo"));
        int userId = Integer.parseInt(req.getParameter("user"));
        String state = Application.STATE_IN_PROCESSING;
        float length = Float.parseFloat(req.getParameter("length"));
        float width = Float.parseFloat(req.getParameter("width"));
        float height = Float.parseFloat(req.getParameter("height"));
        int size = (int) (length * width * height);
        float weight = Float.parseFloat(req.getParameter("weight"));
        String baggageType = req.getParameter("baggageType");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(req.getParameter("receiveDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date receiveDate = new Date(parsed.getTime());
        ApplicationDAO applicationDAO = new ApplicationDAO();
        try {
            Application app = new Application();
            app.setDepartment1Id(departmentFrom);
            app.setDepartment2Id(departmentTo);
            app.setUserId(userId);
            app.setState(state);
            app.setSize(size);
            app.setWeight(weight);
            app.setBaggageType(baggageType);
            app.setReceiveDate(receiveDate);
            applicationDAO.insert(app);
        } catch (DBException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/application");
    }
}
