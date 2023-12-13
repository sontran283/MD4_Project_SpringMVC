package com.ra.controller.user;

import com.ra.model.entity.Image;
import com.ra.model.entity.Product;
import com.ra.model.service.Image.ImageService;
import com.ra.model.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class DetailController {
    @Autowired
    ProductService productService;
    @Autowired
    ImageService imageService;

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findById(id);
        List<Image> imageList = imageService.findByProductId(product.getProductId());
        model.addAttribute("product", product);
        model.addAttribute("imageList", imageList);
        return "user/detail";
    }
}
