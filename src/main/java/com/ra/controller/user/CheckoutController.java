package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CheckoutController {
    @RequestMapping("/checkout")
    public String checkout() {
        return "user/checkout";
    }
}
