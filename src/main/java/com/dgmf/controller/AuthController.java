package com.dgmf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    // Handler Method for Homepage Request
    @GetMapping(path = {"/", "home", "index"})
    public String home() {
        return "index";
    }
}
