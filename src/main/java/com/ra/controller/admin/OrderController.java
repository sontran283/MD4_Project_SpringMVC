package com.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @RequestMapping("/order")
    public String index() {
        return "admin/order/index";
    }

    @GetMapping("/add-order")
    public String add(Model model) {
        return "admin/order/add-order";
    }
    @GetMapping("/edit-order")
    public String edit(Model model) {
        return "admin/order/edit-order";
    }

    @GetMapping("/delete-order")
    public String delete(Model model) {
        return "admin/order/delete-order";
    }
}
