package com.ra.model.dao.OrderDetail;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.Order;
import com.ra.model.entity.OrderDetail;
import com.ra.model.entity.Product;

import java.util.List;

public interface OrderDetailDAO extends IGenericDAO<OrderDetail, Integer> {
    List<OrderDetail> paginater(Integer noPage);
    Integer getTotalPage();
    List<OrderDetail> findAll();
    void create(OrderDetail orderDetail);
    List<OrderDetail> findByOrderId(Integer orderId);
    List<OrderDetail> findByOrderDetailName(String name);
}
