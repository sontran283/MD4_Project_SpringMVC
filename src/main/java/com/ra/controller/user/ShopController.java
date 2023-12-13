package com.ra.controller.user;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.Category.CategoryService;
import com.ra.model.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ShopController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/shop")
    public String shopList(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
        return "user/shop";
    }
}
