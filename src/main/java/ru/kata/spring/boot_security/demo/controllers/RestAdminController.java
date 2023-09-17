package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/rest")
public class RestAdminController {

    private UserService userService;
    private RoleService roleService;

    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @PatchMapping("/edit/")
    public String editUser(@RequestBody User user) {
        Optional<String> optValueUpdateUser = Optional.ofNullable(user.getUsername());
        if (optValueUpdateUser.isPresent()) {
            Optional<User> dbUser = Optional.ofNullable(userService.getUserByUsername(optValueUpdateUser.get()));
            if (dbUser.isPresent()) {
                if (optValueUpdateUser.get().equals(dbUser.get().getUsername())) {
                    userService.updateUser(user);
                    return "User updated";
                }
            }
        }
        return "Not such user in DB";
    }

    @DeleteMapping("/delete/")
    public String deleteUser(@RequestBody long id) {
        if (userService.findUserById(id) != null) {
            userService.deleteUserById(id);
            return String.format("User with id = %s was deleted", id);
        }
        return "No such user in DB";
    }
}
