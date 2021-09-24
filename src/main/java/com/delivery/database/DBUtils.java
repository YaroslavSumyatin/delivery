package com.delivery.database;

import com.delivery.exceptions.DBException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtils {

    private static final Logger log = Logger.getLogger(DBUtils.class.getName());

    private static DataSource ds;

    public static void initDataSource(DataSource dataSource) {
        ds = dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(Connection connection){
        if (connection != null){
            try{
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void close(ResultSet resultSet){
        if (resultSet != null){
            try{
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void close(Statement statement){
        if (statement != null){
            try{
                statement.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void setAutoCommit(Connection con, boolean opt) throws DBException {
        if (con == null) return;
        try {
            con.setAutoCommit(opt);
        } catch (SQLException e) {
            String message = "Can't set autoCommit=" + opt;
            log.log(Level.SEVERE, message, e);
            throw new DBException(message, e);
        }
    }

    public static void commit(Connection con) throws DBException {
        if (con == null) return;
        try {
            con.commit();
        } catch (SQLException e) {
            String message = "Can't commit";
            log.log(Level.SEVERE, message, e);
            throw new DBException(message, e);
        }
    }

    public static void rollback(Connection con) throws DBException {
        if (con == null) return;
        try {
            con.rollback();
        } catch (SQLException e) {
            String message = "Can't rollback";
            log.log(Level.SEVERE, message, e);
            throw new DBException(message, e);
        }
    }

    public static void rollback(Connection con, Savepoint sp) throws DBException {
        if (con == null) return;
        try {
            con.rollback(sp);
        } catch (SQLException e) {
            String message = "Can't rollback to savepoint=" + sp;
            log.log(Level.SEVERE, message, e);
            throw new DBException(message, e);
        }
    }

}
