package com.ra.model.service.Order;

import com.ra.model.dao.Order.OrderDAO;
import com.ra.model.dao.OrderDetail.OrderDetailDAO;
import com.ra.model.dao.Product.ProductDAO;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Category;
import com.ra.model.entity.Order;
import com.ra.model.entity.OrderDetail;
import com.ra.model.service.Cart.CartService;
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
    private OrderDetailDAO orderDetailDAO;

    @Override
    public boolean order(Order order) {
        try {
            Order newOrder = orderDAO.save(order);
            List<CartItem> cartItemList = cartService.getCartItems();
            for (CartItem cartItem : cartItemList) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(newOrder);
                orderDetail.setProduct(cartItem.getProduct());
                orderDetail.setQuantity(cartItem.getQuantity());
                orderDetail.setPrice(cartItem.getProduct().getProductPrice());
                orderDetailDAO.save(orderDetail);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
