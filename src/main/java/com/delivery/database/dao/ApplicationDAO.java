package com.delivery.database.dao;

import com.delivery.database.DBUtils;
import com.delivery.database.entities.Application;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ApplicationDAO implements EntityDAO<Application>{

    private static final Logger log = Logger.getLogger(ApplicationDAO.class);

    private static final String SQL_FIND_BY_ID = "SELECT * FROM application WHERE id=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM application";
    private static final String SQL_FIND_ALL_BY_USER = "SELECT * FROM application WHERE user_id=?";
    private static final String SQL_INSERT = "INSERT INTO application (department1_id, department2_id, " +
            "user_id, state, size, weight, receive_date, baggage_type) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE application SET department1_id=?, department2_id=?, " +
            "user_id=?, state=?, size=?, weight=?, receive_date=?, baggage_type=? " +
            "WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM application WHERE id=?";

    @Override
    public Application findById(int id) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Application app = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_FIND_BY_ID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()) {
                app = initApplication(resultSet);
            }
        } catch (SQLException e) {
            String message = "Can't find application with id=" + id;
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return app;
    }

    private Application initApplication(ResultSet resultSet) throws SQLException {
        Application application = new Application();
        application.setId(resultSet.getInt("id"));
        application.setDepartment1Id(resultSet.getInt("department1_id"));
        application.setDepartment2Id(resultSet.getInt("department2_id"));
        application.setCreateDate(resultSet.getTimestamp("create_date"));
        application.setUserId(resultSet.getInt("user_id"));
        application.setState(resultSet.getString("state"));
        application.setSize(resultSet.getInt("size"));
        application.setWeight(resultSet.getFloat("weight"));
        application.setReceiveDate(resultSet.getDate("receive_date"));
        application.setBaggageType(resultSet.getString("baggage_type"));
        return application;
    }

    @Override
    public List<Application> findAll() throws DBException {
        List<Application> appList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(SQL_FIND_ALL);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Application app = initApplication(resultSet);
                appList.add(app);
            }
        } catch (SQLException e) {
            String message = "Can't find all applications";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }
        return appList;
    }

    public List<Application> findAllByUser(int id) throws DBException {
        List<Application> appList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(SQL_FIND_ALL_BY_USER);
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Application app = initApplication(resultSet);
                appList.add(app);
            }
        } catch (SQLException e) {
            String message = "Can't find all applications with user=" + id;
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }
        return appList;
    }

    @Override
    public boolean insert(Application app) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            prepareApplicationForPreparedStatement(pstmt, app);
            if (pstmt.executeUpdate() != 1) return false;
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()){
                app.setId(resultSet.getInt(1));
            }
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't insert new application";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.setAutoCommit(con, true);
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return true;
    }

    private void prepareApplicationForPreparedStatement(PreparedStatement pstmt, Application app) throws SQLException {
        pstmt.setInt(1, app.getDepartment1Id());
        pstmt.setInt(2, app.getDepartment2Id());
        pstmt.setInt(3, app.getUserId());
        pstmt.setString(4, app.getState());
        pstmt.setInt(5, app.getSize());
        pstmt.setFloat(6, app.getWeight());
        pstmt.setDate(7, app.getReceiveDate());
        pstmt.setString(8, app.getBaggageType());
    }

    @Override
    public boolean update(Application app) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_UPDATE);
            prepareApplicationForPreparedStatement(pstmt, app);
            pstmt.setInt(9, app.getId());
            if (pstmt.executeUpdate() != 1) return false;
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't update application with id=" + app.getId();
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.setAutoCommit(con, true);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return true;
    }

    @Override
    public boolean delete(Application app) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, app.getId());
            if (pstmt.executeUpdate() != 1) return false;
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't delete application with id=" + app.getId();
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.setAutoCommit(con, true);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return true;
    }
}
