//package com.ra.controller.admin;
//
//import com.ra.model.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class HomeController {
//    @Autowired
//    private HttpSession session;
//
//    @RequestMapping("/")
//    public String index(Model model) {
//        User userFromsession = (User) session.getAttribute(("user"));
//        model.addAttribute("user", userFromsession);
//        return "home";
//    }
//}
