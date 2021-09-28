package com.delivery.servlets;

import com.delivery.database.dao.*;
import com.delivery.database.entities.*;
import com.delivery.exceptions.DBException;
import com.delivery.filters.Utils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/manage/applications/formwaybill"})
public class FormWaybillServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(FormWaybillServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appId = Integer.parseInt(req.getParameter("application"));
        int managerId = Integer.parseInt(req.getParameter("manager"));
        float cost = Float.parseFloat(req.getParameter("cost"));
        String state = Waybill.STATE_WAITING_FOR_PAYMENT;
        WaybillDAO waybillDAO = new WaybillDAO();
        try{
            Waybill waybill = new Waybill();
            waybill.setApplicationId(appId);
            waybill.setUserId(managerId);
            waybill.setCost(cost);
            waybill.setState(state);
            waybillDAO.insert(waybill);
            ApplicationDAO appDAO = new ApplicationDAO();
            Application application = appDAO.findById(appId);
            application.setState(Application.STATE_WAITING_FOR_PAYMENT);
            appDAO.update(application);
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/manage/applications");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int applicationId = Integer.parseInt(req.getParameter("application"));
        ApplicationDAO applicationDAO = new ApplicationDAO();
        Application app = null;
        try {
            app = applicationDAO.findById(applicationId);
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        DepartmentDAO departmentDAO = new DepartmentDAO();
        CityDAO cityDAO = new CityDAO();
        TariffDAO tariffDAO = new TariffDAO();
        Department departmentFrom = new Department();
        Department departmentTo = new Department();
        City cityFrom = new City();
        City cityTo = new City();
        try {
            departmentFrom = departmentDAO.findById(app.getDepartment1Id());
            departmentTo = departmentDAO.findById(app.getDepartment2Id());
            cityFrom = cityDAO.findById(departmentFrom.getCityId());
            cityTo = cityDAO.findById(departmentTo.getCityId());
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        String distance = Tariff.DISTANCE_COUNTRY;
        if (cityFrom.getRegion().equals(cityTo.getRegion())) distance = Tariff.DISTANCE_REGION;
        if (cityFrom.getId() == cityTo.getId()) distance = Tariff.DISTANCE_CITY;
        Tariff tariff = null;
        try {
            tariff = tariffDAO.getCalculatedTariff(app.getSize(), app.getWeight(), distance);
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        req.setAttribute("manager", Utils.getUserInSession(req.getSession()));
        req.setAttribute("tariff", tariff);
        req.setAttribute("app", app);
        req.setAttribute("addressFrom",
                departmentFrom.getIndex() + ", " + departmentFrom.getAddress()
                        + ", " + cityFrom.getName() + ", " + cityFrom.getRegion() + " обл.");
        req.setAttribute("addressTo",
                departmentTo.getIndex() + ", " + departmentTo.getAddress()
                        + ", " + cityTo.getName() + ", " + cityTo.getRegion() + " обл.");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/formWaybillPage.jsp");
        dispatcher.forward(req, resp);
    }
}
