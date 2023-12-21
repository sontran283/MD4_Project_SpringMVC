package com.ra.controller.admin;

import com.ra.model.dao.Image.ImageDAOImpl;
import com.ra.model.dto.product.ProductDTO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Image;
import com.ra.model.entity.Product;
import com.ra.model.service.Category.CategoryService;
import com.ra.model.service.Image.ImageService;
import com.ra.model.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@PropertySource("classpath:/config.properties")
@RequestMapping("/admin")
public class ProductController {
    @Value("${path}")
    private String path;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageDAOImpl imageDAO;

    @GetMapping("/product/{id}")
    public String index(@PathVariable("id") Integer id, Model model) {
        List<Product> productList = productService.paginater(id);
        model.addAttribute("totalPage", productService.getTotalPage());
        model.addAttribute("productList", productList);
        return "admin/product/index";
    }

    @GetMapping("/add-product")
    public String add(Model model) {
        ProductDTO productDTO = new ProductDTO();
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("product", productDTO);
        model.addAttribute("categoryList", categoryList);
        return "admin/product/add-product";
    }

    @PostMapping("/add-product")
    public String create(@Valid @ModelAttribute("product") ProductDTO productDTO,
                         BindingResult result, Model model,
                         @RequestParam("fileName") MultipartFile[] files) {
        if (result.hasErrors()) {
            List<Category> categoryList = categoryService.findAll();
            model.addAttribute("categoryList", categoryList);
            return "admin/product/add-product";
        }
        try {
            if (productService.checkNameProduct(productDTO.getProductName())) {
                result.rejectValue("productName", "productName.exits", "Product name already exists");
                return "admin/product/add-product";
            }
            MultipartFile file = productDTO.getFile();
            String fileName = file.getOriginalFilename();
            File file1 = new File(path + fileName);
            int productId = productService.saveProductId(productDTO);
            if (productId > 0) {
                file.transferTo(file1);
                for (MultipartFile multipartFile : files) {
                    String fileImgName = multipartFile.getOriginalFilename();
                    File fileDescription = new File(path + fileImgName);
                    multipartFile.transferTo(fileDescription); // chuyen du lieu

                    Image image = new Image();
                    image.setImgUrl(fileImgName);
                    imageService.addImage(image, productId);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/product/1";
    }

    @GetMapping("/product-edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        List<Category> categoryList = categoryService.findAll();
        List<Image> imageList = imageService.findByProductId(id);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("imageList", imageList);
        return "/admin/product/edit-product";
    }

    @PostMapping("/product-edit")
    public String update(@ModelAttribute("product") Product product,
                         @RequestParam("images") MultipartFile file,
                         @RequestParam("fileName") MultipartFile[] files) {
        try {
            if (!file.isEmpty()) {
                String fileImgName = file.getOriginalFilename();
                File fileaaaa = new File(path + fileImgName);
                product.setImg(fileImgName);
                file.transferTo(fileaaaa);
            }
            productService.update(product);
            imageService.delete(product.getProductId());

            for (MultipartFile multipartFile : files) {
                String fileImg = multipartFile.getOriginalFilename();
                File fileDescription = new File(path + fileImg);
                multipartFile.transferTo(fileDescription);
                Image image = new Image();
                image.setImgUrl(fileImg);
                imageService.addImage(image, product.getProductId());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/product/1";
    }   

    @GetMapping("/product-change/{id}")
    public String changeStatus(@PathVariable("id") Integer id) {
        productService.delete(id);
        return "redirect:/admin/product/1";
    }

    @GetMapping("/product-delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product/1";
    }

    @GetMapping("/sort-product")
    public String sortProduct(Model model) {
        List<Product> sortedProductList = productService.sortByName();
        List<Product> products = productService.findAll();
        model.addAttribute("productList", sortedProductList);
        model.addAttribute("totalPage", (int) Math.ceil(products.size() / 4.f));
        return "/admin/product/index";
    }

    @GetMapping("/search-product")
    public String searchCategory(@RequestParam String searchTerm, Model model) {
        List<Product> searchResult = productService.findByName(searchTerm);
        List<Product> products = productService.findAll();
        model.addAttribute("productList", searchResult);
        model.addAttribute("totalPage", (int) Math.ceil(products.size() / 4.f));
        return "/admin/product/index";
    }

    @GetMapping("delete_image_product/{id}")
    public String deleteImageProduct(@PathVariable("id") Integer id) {
        Image image = imageService.findById(id);
        int ProductId = image.getProductId();
        imageService.delete(id);
        return "redirect:/admin/product-edit/" + ProductId;
    }
}
