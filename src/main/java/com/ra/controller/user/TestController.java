package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "user/test";
    }

    @PostMapping("/hello")
    public String helloHelo(@RequestParam("img")MultipartFile file){
        String fileName = file.getOriginalFilename();
        String url = "D:\\MD4-JAVA-DATABASE\\Project_Module4_WebFruit\\src\\main\\webapp\\uploads\\images\\";
        File file1 = new File(url + fileName );
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "user/test";
    }
}
