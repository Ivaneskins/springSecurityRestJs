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
import java.util.ArrayList;
import java.util.Collection;
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
        List<User> users = userService.getAllUsers();
        User user1 = userService.getUserByName(principal.getName());

        model.addAttribute("userByPrincipalName", user1);
        model.addAttribute("users", users);
        model.addAttribute("roles", roleService.getRoles());

        return "getAllUsers";
    }

    @GetMapping("/add")
    public String newUser(@ModelAttribute("user") User user, Principal principal, Model model) {

        StringBuilder sb = new StringBuilder();
        User userFromBd = userService.getUserByName(principal.getName());
        //Получение ролей в виде строки
        for (Role role : userFromBd.getRoles()) {
            sb.append(role.getName());
            sb.append(" ");
        }

        model.addAttribute("userByPrincipalName", userFromBd);

        model.addAttribute("roles", sb.toString());
        return "addUser";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("role") int role) {
        System.out.println(role);
        userService.addUser(user);

        User findNewUserForId = userService.getUserByName(user.getName());
        roleService.addRole(role, findNewUserForId.getId());

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
        System.out.println(user.getName());
//        userService.addUser(user);
        return "redirect:/admin/";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
