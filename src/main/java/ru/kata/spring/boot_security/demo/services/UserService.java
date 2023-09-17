package ru.kata.spring.boot_security.demo.services;



import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    void deleteUserById(Long id);

    void updateUser(User userUpdate);

    void addUser(User user);

    List<User> findAllUsers();

    User getUserByUsername(String username);

    User findUserById(Long id);
}
