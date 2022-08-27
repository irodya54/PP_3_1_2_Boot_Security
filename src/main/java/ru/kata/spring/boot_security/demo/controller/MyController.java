package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class MyController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String getAllUsers(Model model) {
        var users = service.getAllUsers();
        model.addAttribute("allUsers", users);
        return "/show-users";
    }
    @GetMapping("/addUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "info-user";
    }
    @PostMapping("")
    public String saveUser(@ModelAttribute("user") User user) {
        System.out.println(user.getId());
        if (user.getId() == 0) {
            service.addUser(user);
            System.out.println("saveuser");
        } else {
            service.updateUser(user);
            System.out.println("update");
        }

        return "redirect:/";
    }
    @GetMapping("edit/{id}")
    public String returnUser(@PathVariable("id") int id, Model model) {
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        System.out.println(user.getId());
        return "info-user";

    }
    @RequestMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        service.deleteUser(id);
        return "redirect:/";
    }
}
