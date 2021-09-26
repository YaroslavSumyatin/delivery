package com.delivery.servlets;

import com.delivery.database.dao.ApplicationDAO;
import com.delivery.database.dao.WaybillDAO;
import com.delivery.database.entities.Application;
import com.delivery.database.entities.Waybill;
import com.delivery.exceptions.DBException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment/completion"})
public class PaymentCompletedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/paymentCompletedPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int appId = Integer.parseInt(req.getParameter("application"));
        WaybillDAO waybillDAO = new WaybillDAO();
        ApplicationDAO appDAO = new ApplicationDAO();
        try{
            Application app = appDAO.findById(appId);
            app.setState(Application.STATE_SENT);
            appDAO.update(app);
            Waybill waybill = waybillDAO.findByApplication(appId);
            waybill.setState(Waybill.STATE_PAID);
            waybillDAO.update(waybill);
        } catch (DBException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/payment/completion");
    }
}
