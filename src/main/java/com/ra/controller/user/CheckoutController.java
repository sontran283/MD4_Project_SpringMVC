package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class CheckoutController {
    @RequestMapping("/checkout")
    public String checkout(HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/login?action=checkout";
        }
        return "user/checkout";
    }
}
