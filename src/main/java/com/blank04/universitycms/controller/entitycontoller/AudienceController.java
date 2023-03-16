package com.blank04.universitycms.controller.entitycontoller;

import com.blank04.universitycms.service.AudienceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/audiences")
public class AudienceController {

    private final AudienceService audienceService;

    public AudienceController(AudienceService audienceService) {
        this.audienceService = audienceService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("audiences",audienceService.findAll());
        return "audiences-list";
    }
}
