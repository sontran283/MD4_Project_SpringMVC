package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WishlistController {
    @RequestMapping("/wishlist")
    public String wishlist() {
        return "user/wishlist";
    }
}
