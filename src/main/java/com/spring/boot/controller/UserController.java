package com.spring.boot.controller;

import com.spring.boot.model.User;
import com.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(@ModelAttribute("user") User user) {
        return "user-create";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect: /";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect: /";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id")long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PutMapping("/pages/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect: /";
    }
}
