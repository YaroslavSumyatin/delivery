package com.delivery.database.dao;

import com.delivery.database.DBUtils;
import com.delivery.database.PasswordUtils;
import com.delivery.database.entities.User;
import com.delivery.exceptions.DBException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements EntityDAO<User> {

    private static final Logger log = Logger.getLogger(UserDAO.class);

    private static final String SQL_FIND_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String SQL_FIND_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM user";
    private static final String SQL_INSERT = "INSERT INTO user (login, email, password, name, surname, role, salt) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE user SET login=?, email=?, password=?, name=?, surname=?, role=?, salt=? " +
            "WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM user WHERE id=?";

    @Override
    public User findById(int id) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_FIND_BY_ID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()) {
                user = initUser(resultSet);
            }
        } catch (SQLException e) {
            String message = "Can't find user with id=" + id;
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return user;
    }

    public User findByLogin(String login) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(SQL_FIND_BY_LOGIN);
            pstmt.setString(1, login);
            resultSet = pstmt.executeQuery();
            if(resultSet.next()) {
                user = initUser(resultSet);
            }
        } catch (SQLException e) {
            String message = "Can't find user with login=" + login;
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return user;
    }

    public User validateUser(String input, String password) throws DBException {
        User user = findByLogin(input);
        if (user == null) return null;
        if (PasswordUtils.verifyPassword(password, user.getPassword(), user.getSalt())) return user;
        return null;
    }

    @Override
    public List<User> findAll() throws DBException {
        List<User> userList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(SQL_FIND_ALL);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User user = initUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            String message = "Can't find all users";
            log.error(message + ". " + e.getMessage());
            throw new DBException(message, e);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }
        return userList;
    }

    @Override
    public boolean insert(User user) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            String salt = PasswordUtils.generateSalt(user.getPassword().length());
            String hashedPassword = PasswordUtils.hashPassword(user.getPassword(), salt);
            user.setSalt(salt);
            user.setPassword(hashedPassword);
            prepareUserForPreparedStatement(pstmt, user);
            if (pstmt.executeUpdate() != 1) return false;
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()){
                user.setId(resultSet.getInt(1));
            }
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't insert new user";
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

    @Override
    public boolean update(User user) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_UPDATE);
            prepareUserForPreparedStatement(pstmt, user);
            pstmt.setInt(8, user.getId());
            if (pstmt.executeUpdate() != 1) return false;
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't update user with id=" + user.getId();
            log.error(message, e);
            throw new DBException(message, e);
        } finally {
            DBUtils.setAutoCommit(con, true);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return true;
    }

    public boolean update(User user, String newPassword) throws DBException {
        String salt = PasswordUtils.generateSalt(newPassword.length());
        String hashedPassword = PasswordUtils.hashPassword(newPassword, salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        return update(user);
    }

    @Override
    public boolean delete(User user) throws DBException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtils.getConnection();
            DBUtils.setAutoCommit(con, false);
            pstmt = con.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, user.getId());
            if (pstmt.executeUpdate() != 1) return false;
            DBUtils.commit(con);
        } catch (SQLException e) {
            DBUtils.rollback(con);
            String message = "Can't delete user with id=" + user.getId();
            log.error(message, e);
            throw new DBException(message, e);
        } finally {
            DBUtils.setAutoCommit(con, true);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return true;
    }

    private User initUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setLogin(resultSet.getString("login"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setSurname(resultSet.getString("surname"));
        user.setRole(resultSet.getString("role"));
        user.setSalt(resultSet.getString("salt"));
        return user;
    }

    private void prepareUserForPreparedStatement(PreparedStatement pstmt, User user) throws SQLException {
        pstmt.setString(1, user.getLogin());
        pstmt.setString(2, user.getEmail());
        pstmt.setString(3, user.getPassword());
        pstmt.setString(4, user.getName());
        pstmt.setString(5, user.getSurname());
        pstmt.setString(6, user.getRole());
        pstmt.setString(7, user.getSalt());
    }

}
