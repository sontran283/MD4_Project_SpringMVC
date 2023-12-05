package com.ra.controller.admin;

import com.ra.model.entity.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @GetMapping("/category")
    public String index(Model model) {
        List<Category> category_list = new ArrayList<>();
        category_list.add(new Category(1, "Áo", true));
        category_list.add(new Category(2, "Quần", false));
        model.addAttribute("category_list", category_list);
        return "admin/category/index";
    }

    @GetMapping("/add-category")
    public String add(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/category/add";
    }

    @PostMapping("/add-category")
    public String create(@ModelAttribute("category") Category category) {
        System.out.println(category.getCategoryName());
        System.out.println(category.isCategoryStatus());
        return "admin/category/add";
    }
}
