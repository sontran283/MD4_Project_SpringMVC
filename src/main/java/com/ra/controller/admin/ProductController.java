package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.entity.User;
import com.ra.model.service.Category.CategoryService;
import com.ra.model.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product/{id}")
    public String index(@PathVariable("id") Integer id, Model model) {
        List<Product> productList = productService.paginater(id);
        model.addAttribute("hello", categoryService.getTotalPage());
        model.addAttribute("productList", productList);
        return "admin/product/index";
    }

    @GetMapping("/add-product")
    public String add(Model model) {
        Product product = new Product();
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);
        return "admin/product/add-product";
    }

    @PostMapping("/add-product")
    public String create(@ModelAttribute("product") Product product) {
        productService.saveOrUpDate(product);
        return "redirect:/admin/product/1";
    }

    @GetMapping("/product-edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);
        return "/admin/product/edit-product";
    }

    @PostMapping("/product-edit")
    public String update(@ModelAttribute("product") Product product) {
        productService.saveOrUpDate(product);
        return "redirect:product/1";
    }

    @GetMapping("/product-change/{id}")
    public String changeStatus(@PathVariable("id") Integer id) {
        productService.delete(id);
        return "redirect:/admin/product/1";
    }
}
