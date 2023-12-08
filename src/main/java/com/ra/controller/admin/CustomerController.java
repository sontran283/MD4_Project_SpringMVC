package com.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CustomerController {
    @RequestMapping("/user")
    public String customer(){
        return "admin/user/index";
    }
}
