package com.ra.model.dao.OrderDetail;

import com.ra.model.dao.Order.OrderDAO;
import com.ra.model.dao.Product.ProductDAO;
import com.ra.model.dao.User.UserDAO;
import com.ra.model.entity.*;
import com.ra.model.service.Product.ProductService;
import com.ra.util.ConnectionDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO {
    private int LIMIT = 4;
    private int totalPage = 0;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    ProductService productService;

    @Override
    public List<OrderDetail> paginater(Integer noPage) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_DETAIL_PAGINATION(?,?,?)}");
            callableStatement.setInt(1, LIMIT);
            callableStatement.setInt(2, noPage);
            callableStatement.setInt(3, Types.INTEGER);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(resultSet.getInt("order_id"));
                Product product = productDAO.findById(resultSet.getInt("product_id"));
                orderDetail.setProduct(product);
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setPrice(resultSet.getDouble("price"));
                orderDetailList.add(orderDetail);
            }
            this.totalPage = callableStatement.getInt(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return orderDetailList;
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }

    @Override
    public List<OrderDetail> findAll() {
        Connection connection = ConnectionDataBase.openConnection();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_DETAIL_FY_BY_ALL()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(resultSet.getInt("order_id"));
                Product product=productService.findById(resultSet.getInt("product_id"));
                orderDetail.setProduct(product);
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setPrice(resultSet.getDouble("price"));
                orderDetailList.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return orderDetailList;
    }

    @Override
    public void create(OrderDetail orderDetail) {
        Connection connection = ConnectionDataBase.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_DETAIL_ADD(?,?,?,?)}");
            callableStatement.setInt(1, orderDetail.getOrderId());
            callableStatement.setInt(2, orderDetail.getProduct().getProductId());
            callableStatement.setInt(3, orderDetail.getQuantity());
            callableStatement.setDouble(4, orderDetail.getPrice());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
    }

    @Override
    public List<OrderDetail> findByOrderId(Integer orderId) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_DETAIL_FIND_BY_ORDER_ID(?)}");
            callableStatement.setInt(1, orderId);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(resultSet.getInt("order_id"));
                Product product = productDAO.findById(resultSet.getInt("product_id"));
                orderDetail.setProduct(product);
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setPrice(resultSet.getInt("price"));
                orderDetailList.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return orderDetailList;
    }

    @Override
    public List<OrderDetail> findByOrderDetailName(String name) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDERS_DETAIL_SEARCH_BY_NAME(?)}");
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(resultSet.getInt("order_id"));
                Product product = productDAO.findById(resultSet.getInt("product_id"));
                orderDetail.setProduct(product);
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setPrice(resultSet.getDouble("price"));
                orderDetailList.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return orderDetailList;
    }

    @Override
    public List<OrderDetail> findByName(String name) {
        return null;
    }

    @Override
    public List<OrderDetail> sortByName() {
        return null;
    }

    @Override
    public OrderDetail findById(Integer integer) {
        Connection connection = null;
        connection = ConnectionDataBase.openConnection();
        OrderDetail orderDetail = new OrderDetail();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL ORDER_DETAIL_FY_BY_ID(?)}");
            callableStatement.setInt(1, integer);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                orderDetail.setOrderId(resultSet.getInt("order_id"));
                Product product = productDAO.findById(resultSet.getInt("product_id"));
                orderDetail.setProduct(product);
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDataBase.closeConnection(connection);
        }
        return orderDetail;
    }


    @Override
    public boolean saveOrUpDate(OrderDetail orderDetail) {
        return false;
    }

    @Override
    public void delete(Integer integer) {

    }
}
