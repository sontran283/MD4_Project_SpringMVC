package com.ra.model.service.Order;

import com.ra.model.entity.Category;
import com.ra.model.entity.Order;
import com.ra.model.entity.User;
import com.ra.model.service.IGenericService;

import java.util.List;

public interface OrderService  extends IGenericService<Order,Integer> {
    List<Order> paginater(Integer noPage);
    Integer getTotalPage();
    boolean order(Order order);
}
