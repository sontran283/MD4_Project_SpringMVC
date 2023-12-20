package com.ra.controller.user;

import com.ra.model.entity.Order;
import com.ra.model.entity.OrderDetail;
import com.ra.model.entity.User;
import com.ra.model.service.Order.OrderService;
import com.ra.model.service.OrderDetail.OrderDetailService;
import com.ra.model.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HistoryController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/history")
    public String history() {
        return "user/history";
    }

    @GetMapping("/history")
    public String history(Model model) {
        User user= (User) session.getAttribute("user");
       if (user != null){
           Order orderList = orderService.findByUserId(user.getUserId());
           List<OrderDetail> orderDetail = orderDetailService.findAll();
           model.addAttribute("orderDetail", orderDetail);
           model.addAttribute("orderList", orderList);
           double totalPrice = 0;
           for (OrderDetail detail : orderDetail) {
               totalPrice = totalPrice + detail.getPrice() * detail.getQuantity();
           }
           model.addAttribute("totalPrice", totalPrice);
           return "user/history";
       }
       return "redirect:/login";
    }
}
