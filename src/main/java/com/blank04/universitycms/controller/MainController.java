package com.blank04.universitycms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String home(Model model) {
        model.addAttribute("appName", appName);
        return "home-page";
    }
}
