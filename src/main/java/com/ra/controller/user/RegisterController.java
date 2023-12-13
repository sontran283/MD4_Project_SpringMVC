package com.ra.controller.user;

import com.ra.model.entity.User;
import com.ra.model.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/")
public class RegisterController {
    @Value("${path}")
    private String path;
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/register";
    }

    @PostMapping("/register")
    public String handleRegister(@Valid @ModelAttribute("user") User user,
                                 BindingResult result,
                                 @RequestParam("files") MultipartFile files) {
        if (result.hasErrors()) {
            return "user/register";
        } else {
            if (userService.checkValidateEmail(user.getUserEmail())) {
                result.rejectValue("userEmail", "userEmail.exits", "email da ton tai");
                return "user/register";
            }
            String fileName = files.getOriginalFilename();
            File file1 = new File(path + fileName);
            try {
                files.transferTo(file1);
                user.setUserImg(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (userService.saveOrUpDate(user)) {
                return "user/login";
            }
        }
        return "redirect:user/register";
    }
}
