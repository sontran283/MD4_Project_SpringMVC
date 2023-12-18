package com.ra.model.service.OrderDetail;

import com.ra.model.entity.Order;
import com.ra.model.entity.OrderDetail;
import com.ra.model.service.IGenericService;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public interface OrderDetailService extends IGenericService<OrderDetail,Integer> {
    List<OrderDetail> paginater(Integer noPage);
    Integer getTotalPage();
    void create(OrderDetail orderDetail);
    List<OrderDetail> findByOrderId(Integer orderId);
    List<OrderDetail> findByOrderDetailName(String name);
}
