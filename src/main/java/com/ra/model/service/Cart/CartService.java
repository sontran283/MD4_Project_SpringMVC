package com.ra.model.service.Cart;

import com.ra.model.entity.CartItem;
import com.ra.model.entity.Product;
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
    private List<Product> cartItems = new ArrayList<>();
    private String message;


    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = httpSession.getAttribute("cart") != null
                ? (List<CartItem>) httpSession.getAttribute("cart")
                : new ArrayList<>();
        return cartItems;
    }

    public void addToCart(CartItem cartItem) {
        List<CartItem> cartItems = getCartItems();
        boolean spDaTonTai = false;
        // Kiểm tra số lượng sản phẩm trong giỏ hàng và giới hạn số lượng khi thêm mới
        for (CartItem item : cartItems) {
            if (item.getProduct().getProductId() == cartItem.getProduct().getProductId()) {
                // Sản phẩm đã tồn tại trong giỏ hàng, kiểm tra số lượng mới
                int newQuantity = item.getQuantity() + cartItem.getQuantity();
                int maxQuantity = item.getProduct().getQuantity();
                if (newQuantity <= maxQuantity) {
                    // Nếu số lượng mới không vượt quá giới hạn, cập nhật số lượng
                    item.setQuantity(newQuantity);
                } else {
                    // Nếu vượt quá giới hạn, cập nhật số lượng tối đa
                    item.setQuantity(maxQuantity);
                }
                spDaTonTai = true;
                break;
            }
        }
        if (!spDaTonTai) {
            // Sản phẩm chưa tồn tại trong giỏ hàng, thêm vào
            int maxQuantity = cartItem.getProduct().getQuantity();
            if (cartItem.getQuantity() > maxQuantity) {
                // Nếu vượt quá giới hạn, set số lượng tối đa
                cartItem.setQuantity(maxQuantity);
            }
            cartItems.add(cartItem);
        }
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

    public boolean isEmpty() {
        return getCartItems().isEmpty();
    }

    public void clearCart() {
        httpSession.removeAttribute("cart");
    }

    public double calculateTotal() {
        List<CartItem> cartItems = getCartItems();
        double total = 0.0;

        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
