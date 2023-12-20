package com.ra.controller.user;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.Category.CategoryService;
import com.ra.model.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class ShopController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

//    @GetMapping("/shop")
//    public String shopList(Model model) {
//        List<Product> productList = productService.findAll();
//        List<Category> categoryList = categoryService.findAll();
//        model.addAttribute("categoryList", categoryList);
//        model.addAttribute("productList", productList);
//        return "/user/shop";
//    }
    @GetMapping("/shop")
    public String shopList(Model model,@RequestParam(value = "page",defaultValue = "1") Integer id) {
        List<Product> productList = productService.paginater(id);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("totalPage", productService.getTotalPage());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productList", productList);
        return "/user/shop";
    }

    @GetMapping("/shop/{categoryId}")
    public String categoryFruit(@PathVariable Integer categoryId, Model model) {
        List<Category> categoryList = categoryService.findAll();
        List<Product> productList = productService.findByCategoryId(categoryId);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productList", productList);
        return "/user/shop";
    }

    @GetMapping("/search-product")
    public String searchProduct(@RequestParam String search, Model model) {
        List<Product> searchResult = productService.findByName(search);
        model.addAttribute("productList", searchResult);
        List<Product> products = productService.findAll();
        model.addAttribute("totalPage", (int) Math.ceil(products.size() / 4.f));
        return "/user/shop";
    }


    @GetMapping("/sort-product")
    public String sortProduct(Model model) {
        List<Product> sortResult = productService.sortByName();
        model.addAttribute("productList", sortResult);
        return "/user/shop";
    }
}
