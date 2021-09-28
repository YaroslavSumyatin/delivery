package com.delivery.servlets;

import com.delivery.database.dao.ReportDAO;
import com.delivery.database.entities.report.Report;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(urlPatterns = "/manage/report")
public class ReportServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ReportServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(req.getParameter("date1"));
        } catch (ParseException e) {
            log.error("Can't parse date 1");
        }
        Date date1 = new Date(parsed.getTime());
        try {
            parsed = format.parse(req.getParameter("date2"));
        } catch (ParseException e) {
            log.error("Can't parse date 2");
        }
        Date date2 = new Date(parsed.getTime());
        ReportDAO reportDAO = ReportDAO.getInstance();
        List<Report> report = null;
        try {
            report = reportDAO.formReport(date1, date2);
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        req.setAttribute("report", report);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/report.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
