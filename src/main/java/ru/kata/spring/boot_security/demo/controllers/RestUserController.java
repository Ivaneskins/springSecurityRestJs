package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/user/rest")
public class RestUserController {

    private RoleService roleService;
    private UserService userService;

    public RestUserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public User getUserInfo(Principal principal) {
        System.out.println(principal.getName());
        return userService.getUserByUsername(principal.getName());
    }
}
