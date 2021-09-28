package com.delivery.database.dao;

import com.delivery.database.DBUtils;
import com.delivery.database.entities.Department;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements EntityDAO<Department> {

    private static final Logger log = Logger.getLogger(DepartmentDAO.class);

    private static final String SQL_FIND_BY_ID = "SELECT * FROM department WHERE id=?";
    private static final String SQL_FIND_BY_INDEX = "SELECT * FROM department WHERE department.index=?";
    private static final String SQL_FIND_BY_CITY_ID = "SELECT * FROM department WHERE city_id=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM department ORDER BY `index`";
    private static final String SQL_INSERT = "INSERT INTO department (`index`, address, number, city_id) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE department SET `index`=?, address=?, number=?, city_id=? " +
            "WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM department WHERE id=?";
    private static final String SQL_SUBLIST = "SELECT * FROM department ORDER BY department.index LIMIT ? OFFSET ?";
    private static final String SQL_SUBLIST_CITY = "SELECT * FROM department WHERE city_id=? " +
            "ORDER BY department.index LIMIT ? OFFSET ?";


    @Override
    public Department findById(int id) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Department department = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_FIND_BY_ID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                department = initDepartment(resultSet);
            }
        } catch (SQLException e) {
            String message = "Can't find department with id=" + id;
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return department;
    }

    /*
     * Find departments by specified city
     */
    public List<Department> findByCityId(int id) throws DBException {
        List<Department> departmentList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_FIND_BY_CITY_ID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Department department = initDepartment(resultSet);
                departmentList.add(department);
            }
        } catch (SQLException e) {
            String message = "Can't find department with city_id=" + id;
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return departmentList;
    }

    public Department findByIndex(String index) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Department department = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_FIND_BY_INDEX);
            pstmt.setString(1, index);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                department = initDepartment(resultSet);
            }
        } catch (SQLException e) {
            String message = "Can't find department with index=" + index;
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return department;
    }

    private Department initDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("id"));
        department.setIndex(resultSet.getString("index"));
        department.setAddress(resultSet.getString("address"));
        department.setNumber(resultSet.getInt("number"));
        department.setCityId(resultSet.getInt("city_id"));
        return department;
    }

    @Override
    public List<Department> findAll() throws DBException {
        List<Department> departmentList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(SQL_FIND_ALL);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Department department = initDepartment(resultSet);
                departmentList.add(department);
            }
        } catch (SQLException e) {
            String message = "Can't find all departments";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }
        return departmentList;
    }

    public List<Department> findSublist(int rowscount, int firstrow) throws DBException {
        List<Department> departmentList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_SUBLIST);
            pstmt.setInt(1, rowscount);
            pstmt.setInt(2, firstrow);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Department department = initDepartment(resultSet);
                departmentList.add(department);
            }
        } catch (SQLException e) {
            String message = "Can't find departments";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return departmentList;
    }

    public List<Department> findSublist(int city_id, int rowscount, int firstrow) throws DBException {
        List<Department> departmentList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_SUBLIST_CITY);
            pstmt.setInt(1, city_id);
            pstmt.setInt(2, rowscount);
            pstmt.setInt(3, firstrow);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Department department = initDepartment(resultSet);
                departmentList.add(department);
            }
        } catch (SQLException e) {
            String message = "Can't find departments";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return departmentList;
    }

    @Override
    public boolean insert(Department department) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            prepareDepartmentForPreparedStatement(pstmt, department);
            if (pstmt.executeUpdate() != 1) return false;
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                department.setId(resultSet.getInt(1));
            }
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't insert new department";
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

    private void prepareDepartmentForPreparedStatement(PreparedStatement pstmt, Department department) throws SQLException {
        pstmt.setString(1, department.getIndex());
        pstmt.setString(2, department.getAddress());
        pstmt.setInt(3, department.getNumber());
        pstmt.setInt(4, department.getCityId());
    }

    @Override
    public boolean update(Department department) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_UPDATE);
            prepareDepartmentForPreparedStatement(pstmt, department);
            pstmt.setInt(5, department.getId());
            if (pstmt.executeUpdate() != 1) return false;
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't update department with id=" + department.getId();
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
    public boolean delete(Department department) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, department.getId());
            if (pstmt.executeUpdate() != 1) return false;
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't delete department with id=" + department.getId();
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
