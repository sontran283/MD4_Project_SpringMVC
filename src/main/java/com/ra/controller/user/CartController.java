package com.ra.controller.user;

import com.ra.model.entity.CartItem;
import com.ra.model.entity.Product;
import com.ra.model.service.Cart.CartService;
import com.ra.model.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    ProductService productService;

    @RequestMapping("/cart")
    public String cart(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems",cartItems);
        return "/user/cart";
    }

    @PostMapping("/add-cart")
    public String addCart(@RequestParam("quantity") Integer qty, @RequestParam("productId") Integer id) {
        CartItem cartItem = new CartItem();
        Product product = productService.findById(id);
        cartItem.setProduct(product);
        cartItem.setQuantity(qty);
        cartService.addToCart(cartItem);
        return "redirect:/cart";
    }
    @PostMapping("/update-cart")
    public String updateCart(@RequestParam("quantity") Integer quantity, @RequestParam("productId") Integer productId) {
        cartService.update(quantity, productId);
        return "redirect:/cart";
    }

    @PostMapping("/delete-cart")
    public String deleteCart(@RequestParam("productId") Integer productId) {
        cartService.delete(productId);
        return "redirect:/cart";
    }
}
