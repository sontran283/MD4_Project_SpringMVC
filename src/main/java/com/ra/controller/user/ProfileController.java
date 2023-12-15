package com.ra.controller.user;

import com.ra.model.entity.Category;
import com.ra.model.entity.Image;
import com.ra.model.entity.Product;
import com.ra.model.entity.User;
import com.ra.model.service.Image.ImageService;
import com.ra.model.service.User.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
    public String page(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
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
        return "/user/profile/edit-profile";
    }

    @PostMapping("/profile-edit")
    public String update(@ModelAttribute("profile") User user,
                         @RequestParam("profileImage") MultipartFile profileImage,
                         HttpSession session) {
        try {
            if (!profileImage.isEmpty()) {
                String profileImageName = profileImage.getOriginalFilename();
                File profileImageFile = new File(path + profileImageName);
                user.setUserImg(profileImageName);
                userService.saveOrUpDate(user);
                profileImage.transferTo(profileImageFile);
                session.setAttribute("user",user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/profile";
    }
}
