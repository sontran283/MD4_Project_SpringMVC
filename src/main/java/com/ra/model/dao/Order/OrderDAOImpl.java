package com.ra.model.dao.Order;

import com.ra.model.entity.Category;
import com.ra.model.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO{
    private int LIMIT = 4;
    private int totalPage = 0;
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
    public List<Order> paginater(Integer noPage) {
        return null;
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }
}
