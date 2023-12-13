package com.ra.model.service.Cart;

import com.ra.model.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private HttpSession httpSession;
    List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        // kiem tra xem trong session co ko, co thi gan vao cart item, ko co thi cho bang giong
        cartItems = httpSession.getAttribute("cart") != null ? (List<CartItem>) httpSession.getAttribute("cart") : new ArrayList<>();
        return cartItems;
    }

    public void addToCart(CartItem cartItem) {
        // day item vao cart
        cartItems.add(cartItem);
        // luu vao session
        httpSession.setAttribute("cart", cartItems);
    }

    public void update(Integer quantity, Integer productId) {

    }

    public void delete() {

    }
}
