package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserControllerUpdate {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "getAllUsers";
    }

    @GetMapping("/admin/add")
    public String newUser(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping("/admin/")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/admin/edit/{id}")
    public String editPage(@PathVariable("id") int id,
                           Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "editPage";
    }

    @PutMapping("/admin/edit")
    public String editUser(@ModelAttribute ("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";

    }
}
