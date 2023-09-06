package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("userByPrincipalName", userService.getUserByUsername(principal.getName()));
        model.addAttribute("users", userService.findAllUser());
        model.addAttribute("roles", roleService.findAll());
        return "getAllUsers";
    }

    @GetMapping("/add")
    public String newUser(@ModelAttribute("user") User user, Principal principal, Model model) {
        User userByName = userService.getUserByUsername(principal.getName());
        List<Role> roles = roleService.findAll();
        model.addAttribute("userByPrincipalName", userByName);
        model.addAttribute("roles", roles);
        return "addUser";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin/";
    }

    @PatchMapping("/edit/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/";
    }

    @GetMapping("/admin-info")
    public String getAdminInfoPage(Principal principal, Model model) {
        User userByPrincipalName = userService.getUserByUsername(principal.getName());
        model.addAttribute("userByPrincipalName", userByPrincipalName);
        return "adminPage";
    }
}
