package com.blank04.universitycms.controller;

import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.model.user.User;
import com.blank04.universitycms.model.user.impl.BasicUser;
import com.blank04.universitycms.repository.BasicUserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String REDIRECT="redirect:/admin";
    private final BasicUserRepository basicUserRepository;

    public AdminController(BasicUserRepository basicUserRepository) {
        this.basicUserRepository = basicUserRepository;
    }

    @GetMapping
    public String getAdminPanel(Model model, Authentication authentication) {
        List<BasicUser> basicUsers = basicUserRepository.findAll();
        basicUsers.sort(Comparator.comparingLong(BasicUser::getId));
        model.addAttribute("users", basicUsers);
        model.addAttribute("user", authentication);
        return "admin/admin-panel";
    }

    @GetMapping("/passwordUpdate/{username}")
    public String updateAudienceForm(@PathVariable("username") String username, Model model) {
        Optional<BasicUser> user = basicUserRepository.findByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("password", user.get().getPassword());
            return "admin/password-update";
        } else {
            return "exception";
        }
    }

    @PostMapping("/passwordUpdate")
    public String updateAudience(@RequestParam("username") String username, @RequestParam("password") String password) {
        Optional<BasicUser> userOptional = basicUserRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            BasicUser user = userOptional.get();
            user.setPassword(password);
            basicUserRepository.save(user);
            return REDIRECT;
        } else {
            return "exception";
        }
    }
}
