package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HistoryController {
    @RequestMapping("/history")
    public String history() {
        return "user/history";
    }
}
