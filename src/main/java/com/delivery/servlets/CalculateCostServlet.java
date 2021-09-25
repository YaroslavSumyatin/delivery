package com.delivery.servlets;

import com.delivery.database.dao.CityDAO;
import com.delivery.database.dao.DepartmentDAO;
import com.delivery.database.dao.TariffDAO;
import com.delivery.database.entities.City;
import com.delivery.database.entities.Department;
import com.delivery.database.entities.Tariff;
import com.delivery.exceptions.DBException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/calculation"})
public class CalculateCostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departmentList = null;
        DepartmentDAO departmentDAO = new DepartmentDAO();
        try {
            departmentList = departmentDAO.findAll();
        } catch (DBException e) {
            e.printStackTrace();
        }
        req.setAttribute("departments", departmentList);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/calculateCostPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float weight = Float.parseFloat(req.getParameter("weight"));
        req.setAttribute("weight", weight);
        float length = Float.parseFloat(req.getParameter("length"));
        req.setAttribute("length", length);
        float width = Float.parseFloat(req.getParameter("width"));
        req.setAttribute("width", width);
        float height = Float.parseFloat(req.getParameter("height"));
        req.setAttribute("height", height);
        int size = (int) (length * width * height);
        int departmentFromId = Integer.parseInt(req.getParameter("departmentFrom"));
        req.setAttribute("idFrom", departmentFromId);
        int departmentToId = Integer.parseInt(req.getParameter("departmentTo"));
        req.setAttribute("idTo", departmentToId);
        DepartmentDAO departmentDAO = new DepartmentDAO();
        CityDAO cityDAO = new CityDAO();
        TariffDAO tariffDAO = new TariffDAO();
        Department departmentFrom;
        Department departmentTo;
        City cityFrom = null;
        City cityTo = null;
        try {
            departmentFrom = departmentDAO.findById(departmentFromId);
            departmentTo = departmentDAO.findById(departmentToId);
            cityFrom = cityDAO.findById(departmentFrom.getCityId());
            cityTo = cityDAO.findById(departmentTo.getCityId());
        } catch (DBException e) {
            e.printStackTrace();
        }
        String distance = Tariff.DISTANCE_COUNTRY;
        if (cityFrom != null && cityTo != null) {
            if (cityFrom.getRegion().equals(cityTo.getRegion())) distance = Tariff.DISTANCE_REGION;
            if (cityFrom.getId() == cityTo.getId()) distance = Tariff.DISTANCE_CITY;
        }
        Tariff tariff = null;
        try {
            tariff = tariffDAO.getCalculatedTariff(size, weight, distance);
        } catch (DBException e) {
            e.printStackTrace();
        }
        if (tariff != null) {
            req.setAttribute("cost", tariff.getDeliveryCost());
        }
        doGet(req, resp);
    }
}
