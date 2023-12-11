package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProfileController {
    @RequestMapping("/profile")
    public String page() {
        return "user/profile";
    }
}
