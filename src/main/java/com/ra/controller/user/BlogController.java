package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BlogController {
    @RequestMapping("/blog")
    public String blog(){
        return "user/blog";
    }
}
