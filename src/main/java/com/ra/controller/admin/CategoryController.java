package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.Category.CategoryService;
import com.ra.model.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/category/{id}")
    public String index(@PathVariable("id")Integer id, Model model){
        List<Category>categoryList=categoryService.paginater(id);
        model.addAttribute("totalPage", categoryService.getTotalPage());
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
    public String create(@Valid @ModelAttribute("category") Category category, BindingResult result){
        if (result.hasErrors()){
            return "admin/category/add-category";
        }
        if (categoryService.checkNameCategory(category.getCategoryName())){
            result.rejectValue("categoryName", "categoryName.exits", "Category name already exists");
            return "admin/category/add-category";
        }
        categoryService.saveOrUpDate(category);
        return "redirect:/admin/category/1";
    }

    @GetMapping("/category-edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Category category= categoryService.findById(id);
        model.addAttribute("category",category);
        return "/admin/category/edit-category";
    }

    @PostMapping("/category-edit")
    public String update(@ModelAttribute("category") Category category){
        categoryService.saveOrUpDate(category);
        return "redirect:/admin/category/1";
    }

    @GetMapping("/category-change/{id}")
    public String changeStatus(@PathVariable("id") Integer id){
        Category category = categoryService.findById(id);
        if (category == null) {
            // Xử lý khi danh mục không tồn tại
            return "redirect:/admin/category/1";
        }

        // Thay đổi trạng thái của danh mục
        category.setCategoryStatus(!category.getCategoryStatus());

        // Lưu cập nhật vào cơ sở dữ liệu
        categoryService.saveOrUpDate(category);

        // Nếu trạng thái của danh mục là Close, cập nhật trạng thái của các sản phẩm thuộc danh mục
        if (!category.getCategoryStatus()) {
            List<Product> products = productService.findByCategoryId(id);
            for (Product product : products) {
                product.setProductStatus(false); // Đặt trạng thái sản phẩm thành Close
                productService.saveOrUpDate(product);
            }
        }
        return "redirect:/admin/category/1";
    }

    @GetMapping("/sort-category")
    public String sortCategory(Model model) {
        List<Category> sortedCategoryList = categoryService.sortByName();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categoryList", sortedCategoryList);
        model.addAttribute("totalPage", (int) Math.ceil(categories.size() / 4.f));
        return "/admin/category/index";
    }

    @GetMapping("/search-category")
    public String searchCategory(@RequestParam String searchTerm, Model model) {
        List<Category> searchResult = categoryService.findByName(searchTerm);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categoryList", searchResult);
        model.addAttribute("totalPage", (int) Math.ceil(categories.size() / 4.f));
        return "/admin/category/index";
    }
}
