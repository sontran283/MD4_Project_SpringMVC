package com.ra.controller.user;

import com.ra.model.dto.user.UserCheckOutDTO;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Order;
import com.ra.model.entity.StatusName;
import com.ra.model.entity.User;
import com.ra.model.service.Cart.CartService;
import com.ra.model.service.Order.OrderService;
import com.ra.model.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class CheckoutController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    HttpSession session;

    @RequestMapping("/checkout")
    public String checkout(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null) {
            // chưa đăng nhập thì chuyển hướng về trang đăng nhập
            return "redirect:/login?action=checkout";
        }
        if (cartService.isEmpty()) {
            return "redirect:/cart?emptyCart=true";
        }

        List<CartItem> cartItems = cartService.getCartItems();
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total+= cartItem.getQuantity() * cartItem.getProduct().getProductPrice();
        }
        model.addAttribute("total",total);

        User user = (User) httpSession.getAttribute("user");
        UserCheckOutDTO userCheckOutDTO = new UserCheckOutDTO();
        userCheckOutDTO.setName(user.getUserName());
        userCheckOutDTO.setEmail(user.getUserEmail());
        userCheckOutDTO.setPhone(user.getUserPhoneNumber());
        userCheckOutDTO.setAddress(user.getUserAddress());
        model.addAttribute("userCheckOutDTO", userCheckOutDTO);
        return "user/checkout";
    }

    @PostMapping("/checkout")
    public String handleCheckout(@ModelAttribute("user") UserCheckOutDTO userCheckOutDTO, HttpSession httpSession) {
        if (cartService.isEmpty()) {
            return "redirect:/cart?emptyCart=true";
        }
        List<CartItem> cartItems = cartService.getCartItems();
        session.setAttribute("cartItems",cartItems);
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total+= cartItem.getQuantity() * cartItem.getProduct().getProductPrice();
        }

        // thanh toán
        Order order = new Order();
        User user = (User) httpSession.getAttribute("user");
        order.setUser(user);
        order.setAddress(userCheckOutDTO.getAddress());
        order.setPhone(userCheckOutDTO.getPhone());
        order.setTotal(total);
        order.setNote(userCheckOutDTO.getNote());
        order.setOrderStatus(userCheckOutDTO.getStatus());
        orderService.order(order);
        cartService.clearCart();
        session.removeAttribute("cart");
        session.removeAttribute("cartItems");
        return "user/thank";
    }
}
