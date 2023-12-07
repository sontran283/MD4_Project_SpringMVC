package com.ra.model.dao.User;

import com.ra.model.entity.User;
import com.ra.util.ConnectionDataBase;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> findAll() {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<User> userList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL CUSTOMER_FY_BY_ALL()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("customer_id"));
                user.setUserName(resultSet.getString("name"));
                user.setUserName(resultSet.getString("email"));
                user.setUserAddress(resultSet.getString("address"));
                user.setUserPhoneNumber(resultSet.getDouble("phone_number"));
                user.setUserPassword(resultSet.getString("password"));
                user.setRole(resultSet.getBoolean("role"));
                user.setStatus(resultSet.getBoolean("status"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return userList;
    }

    @Override
    public List<User> findByName(String name) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<User> userList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL CUSTOMER_SEARCH_BY_NAME(?)}");
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("customer_id"));
                user.setUserName(resultSet.getString("name"));
                user.setUserName(resultSet.getString("email"));
                user.setUserAddress(resultSet.getString("address"));
                user.setUserPhoneNumber(resultSet.getDouble("phone_number"));
                user.setUserPassword(resultSet.getString("password"));
                user.setRole(resultSet.getBoolean("role"));
                user.setStatus(resultSet.getBoolean("status"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return userList;
    }

    @Override
    public List<User> sortByName() {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<User> userList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL CUSTOMER_SORT_BY_NAME()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("customer_id"));
                user.setUserName(resultSet.getString("name"));
                user.setUserName(resultSet.getString("email"));
                user.setUserAddress(resultSet.getString("address"));
                user.setUserPhoneNumber(resultSet.getDouble("phone_number"));
                user.setUserPassword(resultSet.getString("password"));
                user.setRole(resultSet.getBoolean("role"));
                user.setStatus(resultSet.getBoolean("status"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return userList;
    }

    @Override
    public User findById(Integer integer) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        User user = new User();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL CUSTOMER_FY_BY_ID(?)}");
            callableStatement.setInt(1, integer);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("customer_id"));
                user.setUserName(resultSet.getString("name"));
                user.setUserName(resultSet.getString("email"));
                user.setUserAddress(resultSet.getString("address"));
                user.setUserPhoneNumber(resultSet.getDouble("phone_number"));
                user.setUserPassword(resultSet.getString("password"));
                user.setRole(resultSet.getBoolean("role"));
                user.setStatus(resultSet.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return user;
    }

    @Override
    public boolean saveOrUpDate(User user) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        try {
            if (user.getUserId() == 0) {
                CallableStatement callableStatement = connection.prepareCall("{CALL CUSTOMER_ADD(?,?,?,?,?,?,?)}");
                callableStatement.setString(1, user.getUserName());
                callableStatement.setString(2, user.getUserEmail());
                callableStatement.setString(3, user.getUserAddress());
                callableStatement.setDouble(4, user.getUserPhoneNumber());
                callableStatement.setString(5, user.getUserPassword());
                callableStatement.setBoolean(6, user.getRole());
                callableStatement.setBoolean(7, user.getStatus());
                int check = callableStatement.executeUpdate();
                if (check > 0) {
                    return true;
                }
            } else {
                CallableStatement callableStatement = connection.prepareCall("{CALL CUSTOMER_UPDATE(?,?,?,?,?)}");
                callableStatement.setInt(1, user.getUserId());
                callableStatement.setString(2, user.getUserName());
                callableStatement.setString(3, user.getUserEmail());
                callableStatement.setString(4, user.getUserAddress());
                callableStatement.setDouble(5, user.getUserPhoneNumber());
                int check = callableStatement.executeUpdate();
                if (check > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return false;
    }

    @Override
    public void delete(Integer integer) {

    }
}
