package com.dgmf.controller;

import com.dgmf.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    // Handler Method for Homepage Request
    @GetMapping({"/", "home", "index"})
    public String home() {
        return "index";
    }

    // Handler Method for User Registration Form Request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Empty Model Object to Store Form Data
        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "register";
    }
}
