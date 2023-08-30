package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getAllUsers(Model model, Principal principal) {
        List<User> users = userService.getAllUsers();
        StringBuilder sb = new StringBuilder();
        User user = userService.getUser(1);
        if (principal != null) {
            user = userService.getUserByName(principal.getName());

        }
        //Получение ролей в виде строки
        for (Role role : user.getRoles()) {
            sb.append(role.getName());
            sb.append(" ");
        }

        model.addAttribute("userByPrincipalName", user);

        model.addAttribute("users", users);

        model.addAttribute("roles", sb.toString());

        return "getAllUsers";
    }

    @GetMapping("/add")
    public String newUser(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") int id,
                           Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "editPage";
    }

    @PutMapping("/edit")
    public String editUser(@ModelAttribute ("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
