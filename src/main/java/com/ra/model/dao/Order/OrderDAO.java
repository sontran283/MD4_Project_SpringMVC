package com.ra.model.dao.Order;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Order;
import com.ra.model.entity.Product;

import java.util.List;

public interface OrderDAO  extends IGenericDAO<Order, Integer> {
    List<Order> paginater(Integer noPage);
    Integer getTotalPage();
    List<Order> findAll();
    int save(Order order);
    void changeStatus(Integer id,Integer status);
}
