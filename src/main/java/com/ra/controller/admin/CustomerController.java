package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.entity.User;
import com.ra.model.service.Category.CategoryService;
import com.ra.model.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CustomerController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public String index(@PathVariable("id")Integer id, Model model){
        List<User> userList = userService.paginater(id);
        model.addAttribute("totalPage", userService.getTotalPage());
        model.addAttribute("userList",userList);
        return "admin/user/index";
    }

    @GetMapping("/add-user")
    public String add(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "admin/user/add-user";
    }

    @PostMapping("/add-user")
    public String create(@ModelAttribute("user") User user){
        userService.saveOrUpDate(user);
        return "redirect:/admin/user/1";
    }

    @GetMapping("/user-edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        User user= userService.findById(id);
        model.addAttribute("user",user);
        return "/admin/user/edit-user";
    }
    @PostMapping("/user-edit")
    public String update(@ModelAttribute("user") User user){
        userService.saveOrUpDate(user);
        return "redirect:/admin/user/1";
    }

    @GetMapping("/user-change/{id}")
    public String changeStatus(@PathVariable("id") Integer id){
        userService.delete(id);
        return "redirect:/admin/user/1";
    }

    @GetMapping("/sort-user")
    public String sortUser(Model model) {
        List<User> sortedUserList = userService.sortByName();
        List<User> users = userService.findAll();
        model.addAttribute("userList", sortedUserList);
        model.addAttribute("totalPage", (int) Math.ceil(users.size() / 4.f));
        return "/admin/user/index";
    }

    @GetMapping("/search-user")
    public String searchUser(@RequestParam String searchTerm, Model model) {
        List<User> searchResult = userService.findByName(searchTerm);
        List<User> users = userService.findAll();
        model.addAttribute("userList", searchResult);
        model.addAttribute("totalPage", (int) Math.ceil(users.size() / 4.f));
        return "/admin/user/index";
    }
}
