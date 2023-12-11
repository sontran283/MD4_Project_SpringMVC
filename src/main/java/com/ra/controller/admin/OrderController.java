package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.entity.Order;
import com.ra.model.entity.User;
import com.ra.model.service.Order.OrderService;
import com.ra.model.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{id}")
    public String index(@PathVariable("id")Integer id, Model model){
        List<Order> orderList = orderService.paginater(id);
        model.addAttribute("totalPage", orderService.getTotalPage());
        model.addAttribute("userList",orderList);
        return "admin/order/index";
    }

    @GetMapping("/add-order")
    public String add(Model model){
        Order order=new Order();
        model.addAttribute("order",order);
        return "admin/order/add-order";
    }

    @PostMapping("/add-order")
    public String create(@ModelAttribute("order") Order order){
        orderService.saveOrUpDate(order);
        return "redirect:/admin/order/1";
    }

    @GetMapping("/order-edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Order order= orderService.findById(id);
        model.addAttribute("order",order);
        return "/admin/order/edit-order";
    }

    @PostMapping("/order-edit")
    public String update(@ModelAttribute("user") Order order){
        orderService.saveOrUpDate(order);
        return "redirect:/admin/order/1";
    }

    @GetMapping("/order-change/{id}")
    public String changeStatus(@PathVariable("id") Integer id){
        orderService.delete(id);
        return "redirect:/admin/order/1";
    }

    @GetMapping("/sort-order")
    public String sortOrder(Model model) {
        List<Order> sortedOrderList = orderService.sortByName();
        List<Order> orders = orderService.findAll();
        model.addAttribute("orderList", sortedOrderList);
        model.addAttribute("totalPage", (int) Math.ceil(orders.size() / 4.f));
        return "/admin/order/index";
    }

    @GetMapping("/search-order")
    public String searchOrder(@RequestParam String searchTerm, Model model) {
        List<Order> searchResult = orderService.findByName(searchTerm);
        List<Order> orders = orderService.findAll();
        model.addAttribute("orderList", searchResult);
        model.addAttribute("totalPage", (int) Math.ceil(orders.size() / 4.f));
        return "/admin/order/index";
    }
}
