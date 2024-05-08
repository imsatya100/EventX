package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		return "signup";
	}
	@GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/logout")
    public String logout() {
        // Add logout logic here
        return "redirect:/"; 
    }

    @GetMapping("/help")
    public String help() {
        return "help"; 
    }

    @GetMapping("/about")
    public String about() {
        return "about"; 
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact"; 
    }
}
