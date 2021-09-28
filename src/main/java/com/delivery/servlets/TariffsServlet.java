package com.delivery.servlets;

import com.delivery.database.dao.TariffDAO;
import com.delivery.database.entities.Tariff;
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

@WebServlet(urlPatterns = {"/tariffs"})
public class TariffsServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(TariffsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffDAO tariffDAO = TariffDAO.getInstance();
        List<Tariff> tariffsSize = null;
        List<Tariff> tariffsWeight = null;
        List<Tariff> tariffsDistance = null;
        try {
            tariffsSize = tariffDAO.findAllSize();
            tariffsWeight = tariffDAO.findAllWeight();
            tariffsDistance = tariffDAO.findAllDistance();
        } catch (DBException e) {
            log.error(e.getMessage());
        }

        req.setAttribute("size", tariffsSize);
        req.setAttribute("weight", tariffsWeight);
        req.setAttribute("distance", tariffsDistance);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/tariffsPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
