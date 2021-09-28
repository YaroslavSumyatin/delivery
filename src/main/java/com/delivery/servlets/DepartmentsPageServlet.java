package com.delivery.servlets;

import com.delivery.database.dao.CityDAO;
import com.delivery.database.dao.DepartmentDAO;
import com.delivery.database.entities.City;
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

@WebServlet(urlPatterns = {"/departments"})
public class DepartmentsPageServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DepartmentsPageServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = null;
        if (req.getParameter("city") != null) {
            city = req.getParameter("city");
            req.setAttribute("city", city);
        }
        int page = 1;
        int maxRecords = 5;
        int numberOfPages = 0;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        DepartmentDAO departmentDAO = new DepartmentDAO();
        String errorMessage = null;
        List<Department> list = null;
        List<City> cities = null;
        try {
            CityDAO cityDAO = new CityDAO();
            int records = 0;
            if (city == null) {
                records = departmentDAO.findAll().size();
                list = departmentDAO.findSublist(maxRecords, (page - 1) * maxRecords);
            } else {
                City currentCity = cityDAO.findByName(city);
                records = departmentDAO.findByCityId(currentCity.getId()).size();
                list = departmentDAO.findSublist(currentCity.getId(), maxRecords, (page - 1) * maxRecords);
            }

            numberOfPages = (int) Math.ceil(records * 1.0 / maxRecords);
            if (page <= 0) page = 1;
            if (page > numberOfPages) page = numberOfPages;
            cities = cityDAO.findAll();
        } catch (DBException e) {
            errorMessage = e.getMessage();
            log.error(e.getMessage());
        }
        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("departments", list);
        req.setAttribute("cities", cities);
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("curPage", page);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/departmentListPage.jsp");
        dispatcher.forward(req, resp);
    }
}
