package com.ra.controller.admin;

import com.ra.model.entity.User;
import com.ra.model.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;

    @GetMapping("/user/index")
    public String index(Model model) {
        return "admin/user/index";
    }

    @GetMapping("/user/add-user")
    public String add(Model model) {
        return "admin/user/add-user";
    }

    @GetMapping("/user/edit-user")
    public String edit(Model model) {
        return "admin/user/edit-user";
    }

    @GetMapping("/user/delete-user")
    public String delete(Model model) {
        return "admin/user/delete-user";
    }

    //--------------------------------
    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") User user, Model model) {
        User authent = userService.checkLogin(user.getUserEmail(), user.getUserPassword());
        if (authent != null) {
            model.addAttribute("user", authent);
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
