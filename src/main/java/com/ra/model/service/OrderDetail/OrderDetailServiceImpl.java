package com.ra.model.service.OrderDetail;

import com.ra.model.dao.OrderDetail.OrderDetailDAO;
import com.ra.model.entity.Order;
import com.ra.model.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    OrderDetailDAO orderDetailDAO;

    @Override
    public List<OrderDetail> paginater(Integer noPage) {
        return orderDetailDAO.paginater(noPage);
    }

    @Override
    public Integer getTotalPage() {
        return orderDetailDAO.getTotalPage();
    }

    @Override
    public void create(OrderDetail orderDetail) {
        orderDetailDAO.create(orderDetail);
    }

    @Override
    public List<OrderDetail> findByOrderId(Integer orderId) {
        return orderDetailDAO.findByOrderId(orderId);
    }
}
