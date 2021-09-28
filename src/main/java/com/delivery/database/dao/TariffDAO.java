package com.delivery.database.dao;

import com.delivery.database.DBUtils;
import com.delivery.database.entities.Tariff;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TariffDAO {

    private static final Logger log = Logger.getLogger(TariffDAO.class);

    private static final String SQL_FIND_ALL_TARIFF_SIZE = "SELECT * FROM tariff_size ORDER BY cost";
    private static final String SQL_FIND_ALL_TARIFF_WEIGHT = "SELECT * FROM tariff_weight ORDER BY cost";
    private static final String SQL_FIND_ALL_TARIFF_DISTANCE = "SELECT * FROM tariff_distance ORDER BY cost";

    private static final String SQL_TARIFF_SIZE_COST = "SELECT cost FROM tariff_size WHERE size>=? ORDER BY size LIMIT 1";
    private static final String SQL_TARIFF_WEIGHT_COST = "SELECT cost FROM tariff_weight WHERE weight>=? ORDER BY weight LIMIT 1";
    private static final String SQL_TARIFF_DISTANCE_COST = "SELECT cost FROM tariff_distance WHERE distance=?";

    private static TariffDAO instance;

    private TariffDAO() {
    }

    public static TariffDAO getInstance() {
        if (instance == null) {
            instance = new TariffDAO();
        }
        return instance;
    }

    /*
     * Calculate which tariffs would be used based on size, weight and distance of delivery
     */
    public Tariff getCalculatedTariff(int size, float weight, String distance) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Tariff tariff = new Tariff();
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_TARIFF_SIZE_COST);
            pstmt.setInt(1, size);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                tariff.setSizeCost(resultSet.getFloat("cost") * size);
            }
            pstmt = con.prepareStatement(SQL_TARIFF_WEIGHT_COST);
            pstmt.setFloat(1, weight);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                tariff.setWeightCost(resultSet.getFloat("cost") * weight);
            }
            pstmt = con.prepareStatement(SQL_TARIFF_DISTANCE_COST);
            pstmt.setString(1, distance);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                tariff.setDistanceCost(resultSet.getFloat("cost"));
            }
            tariff.calculateCost();
        } catch (SQLException e) {
            String message = "Can't find tariffs";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return tariff;
    }

    /*
     * Find all tariffs by size
     */
    public List<Tariff> findAllSize() throws DBException {
        List<Tariff> tariffList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(SQL_FIND_ALL_TARIFF_SIZE);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Tariff tariff = new Tariff();
                tariff.setSize(resultSet.getInt("size"));
                tariff.setSizeCost(resultSet.getFloat("cost"));
                tariffList.add(tariff);
            }
        } catch (SQLException e) {
            String message = "Can't find all size tariffs";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }
        return tariffList;
    }

    /*
     * Find all tariffs by weight
     */
    public List<Tariff> findAllWeight() throws DBException {
        List<Tariff> tariffList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(SQL_FIND_ALL_TARIFF_WEIGHT);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Tariff tariff = new Tariff();
                tariff.setWeight(resultSet.getFloat("weight"));
                tariff.setWeightCost(resultSet.getFloat("cost"));
                tariffList.add(tariff);
            }
        } catch (SQLException e) {
            String message = "Can't find all weight tariffs";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }
        return tariffList;
    }

    /*
     * Find all tariffs by distance
     */
    public List<Tariff> findAllDistance() throws DBException {
        List<Tariff> tariffList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(SQL_FIND_ALL_TARIFF_DISTANCE);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Tariff tariff = new Tariff();
                tariff.setDistance(resultSet.getString("distance"));
                tariff.setDistanceCost(resultSet.getFloat("cost"));
                tariffList.add(tariff);
            }
        } catch (SQLException e) {
            String message = "Can't find all weight tariffs";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }
        return tariffList;
    }

}
