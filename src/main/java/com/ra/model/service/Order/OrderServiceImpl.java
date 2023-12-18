package com.ra.model.service.Order;

import com.ra.model.dao.Order.OrderDAO;
import com.ra.model.dao.OrderDetail.OrderDetailDAO;
import com.ra.model.dao.Product.ProductDAO;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Category;
import com.ra.model.entity.Order;
import com.ra.model.entity.OrderDetail;
import com.ra.model.service.Cart.CartService;
import com.ra.model.service.OrderDetail.OrderDetailService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public int order(Order order) {
        int order_id = orderDAO.save(order);
        List<CartItem> cartItemList = cartService.getCartItems();
        for (CartItem cartItem : cartItemList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order_id);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getProduct().getProductPrice());
            orderDetailService.create(orderDetail);
        }
        return order_id;
    }

    @Override
    public void changeStatus(Integer id, Integer status) {
        orderDAO.changeStatus(id, status);
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public List<Order> findByName(String name) {
        return orderDAO.findByName(name);
    }

    @Override
    public List<Order> sortByName() {
        return orderDAO.sortByName();
    }

    @Override
    public Order findById(Integer integer) {
        return orderDAO.findById(integer);
    }

    @Override
    public boolean saveOrUpDate(Order order) {
        return orderDAO.saveOrUpDate(order);
    }

    @Override
    public void delete(Integer integer) {
        orderDAO.delete(integer);
    }

    @Override
    public List<Order> paginater(Integer noPage) {
        return orderDAO.paginater(noPage);
    }

    @Override
    public Integer getTotalPage() {
        return orderDAO.getTotalPage();
    }
}
