package com.ra.model.service.Order;

import com.ra.model.dao.Order.OrderDAO;
import com.ra.model.dao.Product.ProductDAO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDAO orderDAO;
    @Override
    public List<Order> findAll() {
        return null;
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
    public List<Category> paginater(Integer noPage) {
        return null;
    }

    @Override
    public Integer getTotalPage() {
        return null;
    }
}
