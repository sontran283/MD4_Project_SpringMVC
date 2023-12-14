package com.ra.model.service.Cart;

import com.ra.model.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private HttpSession httpSession;

    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = httpSession.getAttribute("cart") != null
                ? (List<CartItem>) httpSession.getAttribute("cart")
                : new ArrayList<>();
        return cartItems;
    }

    public void addToCart(CartItem cartItem) {
        List<CartItem> cartItems = getCartItems();
        cartItems.add(cartItem);
        httpSession.setAttribute("cart", cartItems);
    }

    public void update(Integer quantity, Integer productId) {
        List<CartItem> cartItems = getCartItems();
        for (CartItem item : cartItems) {
            if (item.getProduct().getProductId() == productId) {
                item.setQuantity(quantity);
                break;
            }
        }
        httpSession.setAttribute("cart", cartItems);
    }

    public void delete(Integer productId) {
        List<CartItem> cartItems = getCartItems();
        Iterator<CartItem> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getProductId() == productId) {
                iterator.remove();
                break;
            }
        }
        httpSession.setAttribute("cart", cartItems);
    }
}
