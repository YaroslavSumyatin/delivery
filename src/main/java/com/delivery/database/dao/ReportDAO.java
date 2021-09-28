package com.delivery.database.dao;

import com.delivery.database.DBUtils;
import com.delivery.database.entities.report.Report;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    private static final Logger log = Logger.getLogger(ReportDAO.class);

    private static final String SQL_REPORT = "SELECT q.adr1, q.adr2, q.`type`, q.`date`, w.cost " +
            "FROM (SELECT a.id AS id, CONCAT(q1.index, ', ', q1.adr, ', ', q1.city, ', ', q1.reg) AS adr1, " +
            "CONCAT(q2.index, ', ', q2.adr, ', ', q2.city, ', ', q2.reg) AS adr2, a.receive_date as `date`, a.baggage_type AS `type` " +
            "FROM (SELECT a.id AS id, d.`index` AS `index`, d.address AS adr, c.`name` AS city, c.region AS reg " +
            "FROM application a INNER JOIN department d ON a.department1_id = d.id " +
            "INNER JOIN city c ON d.city_id = c.id) AS q1 INNER JOIN application a ON a.id = q1.id INNER JOIN " +
            "(SELECT a.id AS id, d.`index` AS `index`, d.address AS adr, c.`name` AS city, c.region AS reg " +
            "FROM application a INNER JOIN department d ON a.department2_id = d.id " +
            "INNER JOIN city c ON d.city_id = c.id) AS q2 ON a.id = q2.id) " +
            "AS q INNER JOIN waybill w ON q.id = w.application_id " +
            "WHERE q.`date` BETWEEN ? AND ?";

    private static ReportDAO instance;

    private ReportDAO() {
    }

    public static ReportDAO getInstance() {
        if (instance == null) {
            instance = new ReportDAO();
        }
        return instance;
    }

    /*
     * Form a report between two dates
     */
    public List<Report> formReport(Date date1, Date date2) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<Report> reports = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_REPORT);
            pstmt.setDate(1, date1);
            pstmt.setDate(2, date2);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Report rep = initReport(resultSet);
                reports.add(rep);
            }
        } catch (SQLException e) {
            String message = "Can't form report between" + date1 + " and " + date2;
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return reports;
    }

    private Report initReport(ResultSet resultSet) throws SQLException {
        Report rep = new Report();
        rep.setAddress1(resultSet.getString(1));
        rep.setAddress2(resultSet.getString(2));
        rep.setBaggageType(resultSet.getString(3));
        rep.setDate(resultSet.getDate(4));
        rep.setCost(resultSet.getFloat(5));
        return rep;
    }

}
