package com.ra.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

@Controller
@PropertySource("classpath:/config.properties")
public class UploadController {
    @Value("${path}")
    private String path;
    @Autowired
    private ServletContext servletContext;

    @GetMapping("/upload")
    public String upload() {
        return "form-upload";
    }

    @PostMapping("/upload")
    public String postUpload(@RequestParam("img") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File destination = new File(path + fileName);
        try {
            Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "form-upload";
    }
}