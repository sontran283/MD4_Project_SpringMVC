package com.ra.model.service.OrderDetail;

import com.ra.model.entity.Order;
import com.ra.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> paginater(Integer noPage);
    Integer getTotalPage();
    void create(OrderDetail orderDetail);
    List<OrderDetail> findByOrderId(Integer orderId);
}
