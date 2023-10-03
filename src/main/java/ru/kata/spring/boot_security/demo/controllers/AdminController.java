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
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String getAdminRolesAndPrincipalName(Model model, Principal principal) {
        model.addAttribute("userByPrincipalName", userService.getUserByUsername(principal.getName()));
        model.addAttribute("roles", roleService.findAllRoles());
        return "getAllUsers";
    }

    @GetMapping("/admin-info")
    public String getAdminInfoPage(Principal principal, Model model) {
        User userByPrincipalName = userService.getUserByUsername(principal.getName());
        model.addAttribute("userByPrincipalName", userByPrincipalName);
        return "adminPage";
    }
}
