package com.dgmf.controller;

import com.dgmf.dto.UserDto;
import com.dgmf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

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

    // Handler Method for User Registration Form Submit Request
    // @ModelAttribute() is Used to Get the Model Object (Here, userDto)
    // Containing Form Data
    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserDto userDto) {
        userService.saveUser(userDto);

        // Returns the Same Registration Form with Success
        // Message ("success" parameter)
        return "redirect:/register?success";
    }
}
