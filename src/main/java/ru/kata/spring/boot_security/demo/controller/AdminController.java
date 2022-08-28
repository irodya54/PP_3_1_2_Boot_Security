package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RolesService rolesService;

    public AdminController(UserService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        var users = userService.getAllUsers();
        model.addAttribute("allUsers", users);
        return "/show-users";
    }
    @GetMapping("/addUser")
    public String addUser(Model model) {
        User user = new User();
        Set<Role> currentRoles = rolesService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("currentRole", currentRoles);
        return "add-user";
    }
    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }

        return "redirect:/admin";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/edit/{id}")
    public String returnUser(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        Set<Role> currentRoles = rolesService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("currentRole", currentRoles);
        return "add-user";

    }
}
