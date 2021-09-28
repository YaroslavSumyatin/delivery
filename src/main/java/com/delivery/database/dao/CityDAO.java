package com.delivery.database.dao;

import com.delivery.database.DBUtils;
import com.delivery.database.entities.City;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements EntityDAO<City> {

    private static final Logger log = Logger.getLogger(CityDAO.class);

    private static final String SQL_FIND_BY_ID = "SELECT * FROM city WHERE id=?";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM city WHERE name=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM city";
    private static final String SQL_INSERT = "INSERT INTO city (name, region) " +
            "VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE city SET name=?, region=? " +
            "WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM city WHERE id=?";

    private static CityDAO instance;

    private CityDAO() {
    }

    public static CityDAO getInstance() {
        if (instance == null) {
            instance = new CityDAO();
        }
        return instance;
    }

    @Override
    public City findById(int id) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        City city = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_FIND_BY_ID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                city = initCity(resultSet);
            }
        } catch (SQLException e) {
            String message = "Can't find city with id=" + id;
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return city;
    }

    /*
     * Find city by its name
     */
    public City findByName(String name) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        City city = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_FIND_BY_NAME);
            pstmt.setString(1, name);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                city = initCity(resultSet);
            }
        } catch (SQLException e) {
            String message = "Can't find city with name=" + name;
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return city;
    }

    private City initCity(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt("id"));
        city.setName(resultSet.getString("name"));
        city.setRegion(resultSet.getString("region"));
        return city;
    }

    @Override
    public List<City> findAll() throws DBException {
        List<City> cityList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(SQL_FIND_ALL);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                City city = initCity(resultSet);
                cityList.add(city);
            }
        } catch (SQLException e) {
            String message = "Can't find all cities";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }
        return cityList;
    }

    @Override
    public boolean insert(City city) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            prepareCityForPreparedStatement(pstmt, city);
            if (pstmt.executeUpdate() != 1) return false;
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                city.setId(resultSet.getInt(1));
            }
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't insert new city";
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

    private void prepareCityForPreparedStatement(PreparedStatement pstmt, City city) throws SQLException {
        pstmt.setString(1, city.getName());
        pstmt.setString(2, city.getRegion());
    }

    @Override
    public boolean update(City city) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_UPDATE);
            prepareCityForPreparedStatement(pstmt, city);
            pstmt.setInt(3, city.getId());
            if (pstmt.executeUpdate() != 1) return false;
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't update city with id=" + city.getId();
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
    public boolean delete(City city) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, city.getId());
            if (pstmt.executeUpdate() != 1) return false;
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't delete city with id=" + city.getId();
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
