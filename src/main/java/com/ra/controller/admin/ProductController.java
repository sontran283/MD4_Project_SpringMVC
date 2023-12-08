package com.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @GetMapping("/product")
    public String index(Model model) {
        return "admin/product/index";
    }
    @GetMapping("/add-product")
    public String add(Model model) {
        return "admin/product/add-product";
    }
    @GetMapping("/edit-product")
    public String edit(Model model) {
        return "admin/product/edit-product";
    }

    @GetMapping("/delete-product")
    public String delete(Model model) {
        return "admin/product/delete-product";
    }
}
