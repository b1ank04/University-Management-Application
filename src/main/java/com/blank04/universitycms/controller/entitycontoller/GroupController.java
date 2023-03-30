package com.blank04.universitycms.controller.entitycontoller;

import com.blank04.universitycms.model.entity.Faculty;
import com.blank04.universitycms.model.entity.Group;
import com.blank04.universitycms.service.FacultyService;
import com.blank04.universitycms.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/groups")
public class GroupController {

    private static final String REDIRECT="redirect:/api/groups";
    private static final String EXCEPTION="exception";
    private final GroupService groupService;
    private final FacultyService facultyService;

    public GroupController(GroupService groupService, FacultyService facultyService) {
        this.groupService = groupService;
        this.facultyService = facultyService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Group> groups = groupService.findAll();
        groups.sort(Comparator.comparingLong(Group::getId));
        model.addAttribute("groups", groups);
        return "group/groups-list";
    }

    @GetMapping("/signup")
    public String createGroupForm() {
        return "group/group-create";
    }

    @PostMapping("/create")
    public String createGroup(@RequestParam("name") String name, @RequestParam("facultyId") Long facultyId) {
        Optional<Faculty> faculty = facultyService.findById(facultyId);
        if (faculty.isPresent()) {
            groupService.save(new Group(null, name, faculty.get()));
            return REDIRECT;
        } else {
            return EXCEPTION;
        }
    }

    @GetMapping("/update/{id}")
    public String updateGroupForm(@PathVariable("id") Long id, Model model) {
        Optional<Group> group = groupService.findById(id);
        if (group.isPresent()) {
            model.addAttribute("name", group.get().getName());
            model.addAttribute("facultyId", group.get().getFaculty().getId());
            return "group/group-update";
        } else {
            return EXCEPTION;
        }
    }

    @PostMapping("/update")
    public String updateGroup(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("facultyId") Long facultyId) {
        Optional<Faculty> faculty = facultyService.findById(facultyId);
        if (faculty.isPresent()) {
            groupService.save(new Group(id, name, faculty.get()));
            return REDIRECT;
        } else {
            return EXCEPTION;
        }
    }

    @GetMapping("delete/{id}")
    public String deleteGroup(@PathVariable("id") Long id) throws SQLException {
        groupService.deleteById(id);
        return REDIRECT;
    }
}
