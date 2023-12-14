package com.ra.controller.user;

import com.ra.model.entity.Category;
import com.ra.model.entity.Image;
import com.ra.model.entity.Product;
import com.ra.model.entity.User;
import com.ra.model.service.Image.ImageService;
import com.ra.model.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProfileController {
    @Value("${path}")
    private String path;
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;

    @RequestMapping("/profile")
    public String page() {
        return "user/profile";
    }

    @GetMapping("/profile-edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("profile", user);
        List<User> userList = userService.findAll();
        List<Image> imageList = imageService.findByProductId(id);
        model.addAttribute("userList", userList);
        model.addAttribute("imageList", imageList);
        return "/admin/profile/edit-profile";
    }

    @PostMapping("/profile-edit")
    public String update(@ModelAttribute("profile") User user, Model model,
                         @RequestParam("images") MultipartFile file,
                         @RequestParam("fileName") MultipartFile[] files) {
        try {
            if (!file.isEmpty()) {
                String fileImgName = file.getOriginalFilename();
                File fileaaaa = new File(path + fileImgName);
                user.setUserImg(fileImgName);
                userService.saveOrUpDate(user);
                file.transferTo(fileaaaa);
            }
            for (MultipartFile multipartFile : files) {
                String fileImg = multipartFile.getOriginalFilename();
                File fileDescription = new File(path + fileImg);
                multipartFile.transferTo(fileDescription);

                Image image = new Image();
                image.setImgUrl(fileImg);
                imageService.addImage(image, user.getUserId());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/user/profile/1";
    }
}
