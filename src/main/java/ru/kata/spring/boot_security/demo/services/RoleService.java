package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleService {

    public void addRole(int roleId, int userId);
    public List<Role> getRoles();

}
