package com.delivery.database.dao;

import com.delivery.database.DBUtils;
import com.delivery.database.entities.Waybill;
import com.delivery.exceptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class WaybillDAO implements EntityDAO<Waybill> {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM waybill WHERE id=?";
    private static final String SQL_FIND_BY_APPLICATION = "SELECT * FROM waybill WHERE application_id=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM waybill";
    private static final String SQL_INSERT = "INSERT INTO waybill (user_id, application_id, state, cost) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE waybill SET user_id=?, application_id=?, state=?, cost=? " +
            "WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM application WHERE id=?";

    @Override
    public Waybill findById(int id) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Waybill waybill = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_FIND_BY_ID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()) {
                waybill = initWaybill(resultSet);
            }
        } catch (SQLException e) {
            String message = "Can't find waybill with id=" + id;
            log.log(Level.SEVERE, message, e);
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return waybill;
    }

    private Waybill initWaybill(ResultSet resultSet) throws SQLException {
        Waybill waybill = new Waybill();
        waybill.setId(resultSet.getInt("id"));
        waybill.setUserId(resultSet.getInt("user_id"));
        waybill.setCreateDate(resultSet.getTimestamp("create_date"));
        waybill.setApplicationId(resultSet.getInt("application_id"));
        waybill.setState(resultSet.getString("state"));
        waybill.setCost(resultSet.getFloat("cost"));
        return waybill;
    }

    public Waybill findByApplication(int application_id) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Waybill waybill = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_FIND_BY_APPLICATION);
            pstmt.setInt(1, application_id);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()) {
                waybill = initWaybill(resultSet);
            }
        } catch (SQLException e) {
            String message = "Can't find waybill with application_id=" + application_id;
            log.log(Level.SEVERE, message, e);
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return waybill;
    }

    @Override
    public List<Waybill> findAll() throws DBException {
        List<Waybill> waybillList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(SQL_FIND_ALL);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Waybill waybill = initWaybill(resultSet);
                waybillList.add(waybill);
            }
        } catch (SQLException e) {
            String message = "Can't find all waybills";
            log.log(Level.SEVERE, message, e);
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }
        return waybillList;
    }

    @Override
    public boolean insert(Waybill waybill) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            prepareWaybillForPreparedStatement(pstmt, waybill);
            if (pstmt.executeUpdate() != 1) return false;
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()){
                waybill.setId(resultSet.getInt(1));
            }
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't insert new waybill";
            log.log(Level.SEVERE, message, e);
            throw new DBException(message, e);
        } finally {
            DBUtils.setAutoCommit(con, true);
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return true;
    }

    private void prepareWaybillForPreparedStatement(PreparedStatement pstmt, Waybill waybill) throws SQLException {
        pstmt.setInt(1, waybill.getUserId());
        pstmt.setInt(2, waybill.getApplicationId());
        pstmt.setString(3, waybill.getState());
        pstmt.setFloat(4, waybill.getCost());
    }

    @Override
    public boolean update(Waybill waybill) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_UPDATE);
            prepareWaybillForPreparedStatement(pstmt, waybill);
            pstmt.setInt(5, waybill.getId());
            if (pstmt.executeUpdate() != 1) return false;
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't update waybill with id=" + waybill.getId();
            log.log(Level.SEVERE, message, e);
            throw new DBException(message, e);
        } finally {
            DBUtils.setAutoCommit(con, true);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return true;
    }

    @Override
    public boolean delete(Waybill waybill) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, waybill.getId());
            if (pstmt.executeUpdate() != 1) return false;
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't delete waybill with id=" + waybill.getId();
            log.log(Level.SEVERE, message, e);
            throw new DBException(message, e);
        } finally {
            DBUtils.setAutoCommit(con, true);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return true;
    }
}
