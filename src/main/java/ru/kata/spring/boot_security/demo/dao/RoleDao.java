package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.ArrayList;
import java.util.List;

public interface RoleDao {

    public void addRole(int roleId, int userId);

    public List<Role> getRoles();
}
