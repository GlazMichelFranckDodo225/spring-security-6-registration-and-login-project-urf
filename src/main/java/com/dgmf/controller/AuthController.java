package com.dgmf.controller;

import com.dgmf.dto.UserDto;
import com.dgmf.entity.User;
import com.dgmf.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    // @Valid Enable Validation to UserDto Class
    @PostMapping("/register/save")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model
    ) {
        // Validation for User Email
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(
                existingUser != null
                        && existingUser.getEmail() != null
                        && !existingUser.getEmail().isEmpty()
        ) {
            result.rejectValue(
                    "email",
                    null,
                    "There Is Already an Account Registered with the Same Email"
            );
        }

        // Validation for Form Fields
        if(result.hasErrors()) {
            model.addAttribute("user", userDto);

            // Validation Has Failed ==> Return the Same Page
            return "/register";
        }

        userService.saveUser(userDto);

        // Returns the Same Registration Form with Success
        // Message ("success" parameter)
        return "redirect:/register?success";
    }
}
