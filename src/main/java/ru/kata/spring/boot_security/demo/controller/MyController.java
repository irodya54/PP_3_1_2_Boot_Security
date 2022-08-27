package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class MyController {

    @Autowired
    private UserService userService;

    @Autowired
    private RolesService rolesService;

    @GetMapping("/")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        var users = userService.getAllUsers();
        model.addAttribute("allUsers", users);
        return "/show-users";
    }
    @GetMapping("/admin/addUser")
    public String addUser(Model model) {
        User user = new User();
        List<Role> currentRoles = rolesService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("currentRole", currentRoles);
        return "add-user";
    }
    @PostMapping("/admin/addUser")
    public String saveUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }

        return "redirect:/admin";
    }
    @GetMapping("/admin/edit/{id}")
    public String returnUser(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        List<Role> currentRoles = rolesService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("currentRole", currentRoles);
        System.out.println(user.getId());
        return "add-user";

    }

    @GetMapping("/user")
    public String showUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = user.getId();
        user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "info-user";

    }

    @RequestMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
