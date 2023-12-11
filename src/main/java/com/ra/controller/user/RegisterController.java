package com.ra.controller.user;

import com.ra.model.entity.User;
import com.ra.model.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class RegisterController {
    @Autowired
    UserService userService;
    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user/register";
    }
    @PostMapping("/register")
    public String handleRegister(@Valid @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
            return "user/register";
        }else {
            if (userService.saveOrUpDate(user)){
                return "user/login";
            }
        }
        return "redirect:user/register";
    }
}
