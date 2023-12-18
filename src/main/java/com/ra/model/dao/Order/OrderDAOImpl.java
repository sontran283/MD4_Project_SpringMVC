package com.ra.model.dao.Order;

import com.ra.model.dao.Category.CategoryDAO;
import com.ra.model.dao.User.UserDAO;
import com.ra.model.entity.*;
import com.ra.util.ConnectionDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private int LIMIT = 4;
    private int totalPage = 0;
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Order> paginater(Integer noPage) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Order> orderList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDERS_PAGINATION(?,?,?)}");
            callableStatement.setInt(1, LIMIT);
            callableStatement.setInt(2, noPage);
            callableStatement.setInt(3, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrder_id(resultSet.getInt("order_id"));
                User user = userDAO.findById(resultSet.getInt("customer_id"));
                order.setUser(user);
                order.setOrder_date(resultSet.getDate("order_date"));
                order.setTotal(resultSet.getDouble("total"));
                order.setOrderStatus(resultSet.getInt("status"));
                order.setPhone(resultSet.getString("phone"));
                order.setAddress(resultSet.getString("address"));
                order.setNote(resultSet.getString("note"));
                orderList.add(order);
            }
            this.totalPage = callableStatement.getInt(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return orderList;
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }

    @Override
    public List<Order> findAll() {
        Connection connection = ConnectionDataBase.openConnection();
        List<Order> orderList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_FY_BY_ALL()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrder_id(resultSet.getInt("order_id"));
                User user = userDAO.findById(resultSet.getInt("customer_id"));
                order.setUser(user);
                order.setOrder_date(resultSet.getDate("order_date"));
                order.setTotal(resultSet.getDouble("total"));
                order.setOrderStatus(resultSet.getInt("status"));
                order.setPhone(resultSet.getString("phone"));
                order.setAddress(resultSet.getString("address"));
                order.setNote(resultSet.getString("note"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return orderList;
    }

    @Override
    public List<Order> findByName(String name) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Order> orderList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDERS_SEARCH_BY_NAME(?)}");
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrder_id(resultSet.getInt("order_id"));
                User user = userDAO.findById(resultSet.getInt("customer_id"));
                order.setUser(user);
                order.setOrder_date(resultSet.getDate("order_date"));
                order.setTotal(resultSet.getDouble("total"));
                order.setOrderStatus(resultSet.getInt("status"));
                order.setPhone(resultSet.getString("phone"));
                order.setAddress(resultSet.getString("address"));
                order.setNote(resultSet.getString("note"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return orderList;
    }

    @Override
    public List<Order> sortByName() {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<Order> orderList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDERS_SORT_BY_DATE()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrder_id(resultSet.getInt("order_id"));
                User user = userDAO.findById(resultSet.getInt("customer_id"));
                order.setUser(user);
                order.setOrder_date(resultSet.getDate("order_date"));
                order.setTotal(resultSet.getDouble("total"));
                order.setOrderStatus(resultSet.getInt("status"));
                order.setPhone(resultSet.getString("phone"));
                order.setAddress(resultSet.getString("address"));
                order.setNote(resultSet.getString("note"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return orderList;
    }

    @Override
    public Order findById(Integer integer) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        Order order = new Order();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_FY_BY_ID(?)}");
            callableStatement.setInt(1, integer);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                order.setOrder_id(resultSet.getInt("order_id"));
                User user = userDAO.findById(resultSet.getInt("customer_id"));
                order.setUser(user);
                order.setOrder_date(resultSet.getDate("order_date"));
                order.setTotal(resultSet.getDouble("total"));
                order.setOrderStatus(resultSet.getInt("status"));
                order.setPhone(resultSet.getString("phone"));
                order.setAddress(resultSet.getString("address"));
                order.setNote(resultSet.getString("note"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return order;
    }

    @Override
    public boolean saveOrUpDate(Order order) {
        return false;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_DELETE(?)}");
            callableStatement.setInt(1, integer);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
    }

    @Override
    public int save(Order order) {
        Connection connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_ADD(?,?,?,?,?,?)}");
            callableStatement.setInt(1, order.getUser().getUserId());
            callableStatement.setDouble(2, order.getTotal());
            callableStatement.setString(3, order.getPhone());
            callableStatement.setString(4, order.getAddress());
            callableStatement.setString(5, order.getNote());
            callableStatement.setInt(6, Types.INTEGER);
            callableStatement.executeUpdate();
            return callableStatement.getInt(6);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
    }

    @Override
    public void changeStatus(Integer id, Integer status) {
        Connection connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL proc_accept_or_deny_order(?,?)}");
            callableStatement.setInt(1, id);
            callableStatement.setInt(2, status);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
    }
}
