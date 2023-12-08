package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{id}")
    public String index(@PathVariable("id")Integer id, Model model){
        List<Category>categoryList=categoryService.paginater(id);
        model.addAttribute("hello", categoryService.getTotalPage());
        model.addAttribute("categoryList",categoryList);
        return "admin/category/index";
    }

    @GetMapping("/add-category")
    public String add(Model model){
        Category category=new Category();
        model.addAttribute("category",category);
        return "admin/category/add-category";
    }

    @PostMapping("/add-category")
    public String create(@ModelAttribute("category") Category category){
        categoryService.saveOrUpDate(category);
        return "redirect:/admin/category/1";
    }

    @GetMapping("/category-edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Category category= categoryService.findById(id);
        model.addAttribute("category",category);
        return "/admin/category/edit-category";
    }
    @PostMapping("/update-category")
    public String update(@ModelAttribute("category") Category category){
        categoryService.saveOrUpDate(category);
        return "redirect:/admin/category/1";
    }

    @GetMapping("/category-change/{id}")
    public String changeStatus(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/admin/category/1";
    }
}
