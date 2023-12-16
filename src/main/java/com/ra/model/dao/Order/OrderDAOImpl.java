package com.ra.model.dao.Order;

import com.ra.model.dao.Category.CategoryDAO;
import com.ra.model.dao.User.UserDAO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Order;
import com.ra.model.entity.StatusName;
import com.ra.model.entity.User;
import com.ra.util.ConnectionDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public Integer getTotalPage() {
        return null;
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
                StatusName status = StatusName.valueOf(resultSet.getString("status"));
                order.setOrderStatus(status);
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
        return null;
    }

    @Override
    public List<Order> sortByName() {
        return null;
    }

    @Override
    public Order findById(Integer integer) {
        return null;
    }

    @Override
    public boolean saveOrUpDate(Order order) {
        return false;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Order save(Order order) {
        Connection connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_ADD(?,?,?,?,?)}");
            callableStatement.setInt(1, order.getUser().getUserId());
            callableStatement.setDouble(2, order.getTotal());
            callableStatement.setString(3, order.getPhone());
            callableStatement.setString(4, order.getAddress());
            callableStatement.setString(5, order.getNote());
            int check= callableStatement.executeUpdate();
            if (check>0){
                return order;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return null;
    }
}
