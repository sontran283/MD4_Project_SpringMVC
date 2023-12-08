package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DetailController {
    @RequestMapping("/detail")
    public String detail() {
        return "user/detail";
    }
}
