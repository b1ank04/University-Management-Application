package com.blank04.universitycms.controller.entitycontoller;

import com.blank04.universitycms.model.entity.Audience;
import com.blank04.universitycms.service.AudienceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/audiences")
public class AudienceController {

    private final AudienceService audienceService;

    private static final String REDIRECT="redirect:/api/audiences";

    public AudienceController(AudienceService audienceService) {
        this.audienceService = audienceService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Audience> audiences = new ArrayList<>(audienceService.findAll());
        audiences.sort(Comparator.comparingLong(Audience::getId));
        model.addAttribute("audiences", audiences);
        return "audience/audiences-list";
    }

    @GetMapping("/signup")
    public String createAudienceForm() {
        return "audience/audience-create";
    }

    @PostMapping("/create")
    public String createAudience(@RequestParam("floor") int floor) {
        audienceService.save(new Audience(null, floor));
        return REDIRECT;
    }

    @GetMapping("/update/{id}")
    public String updateAudienceForm(@PathVariable("id") Long id, Model model) {
        Optional<Audience> audience = audienceService.findById(id);
        if (audience.isPresent()) {
         model.addAttribute("id", audience.get().getId());
         return "audience/audience-update";
        } else {
            return "exception";
        }
    }

    @PostMapping("/update")
    public String updateAudience(@RequestParam("id") Long id, @RequestParam("floor") int floor) {
        audienceService.save(new Audience(id, floor));
        return REDIRECT;
    }

    @GetMapping("delete/{id}")
    public String deleteAudience(@PathVariable("id") Long id) throws SQLException {
        audienceService.deleteById(id);
        return REDIRECT;
    }
}
