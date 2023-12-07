package com.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @GetMapping("/order/index")
    public String index(Model model) {
        return "admin/order/index";
    }
    @GetMapping("/order/add-order")
    public String add(Model model) {
        return "admin/order/add-order";
    }
    @GetMapping("/order/edit-order")
    public String edit(Model model) {
        return "admin/order/edit-order";
    }

    @GetMapping("/order/delete-order")
    public String delete(Model model) {
        return "admin/order/delete-order";
    }
}
