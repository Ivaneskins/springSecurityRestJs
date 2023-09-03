package ru.kata.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {
    private EntityManager entityManager;
    @Autowired
    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addRole(int roleId, int userId) {
        Query query = entityManager.createNativeQuery("INSERT INTO users_roles (user_id, role_id) VALUES (:userId, :roleId)");
        query.setParameter("userId", userId);
        query.setParameter("roleId", roleId);
        query.executeUpdate();
    }

    @Override
    public List<Role> getRoles() {
        String jpql = "SELECT e FROM Role e";
        TypedQuery<Role> query = entityManager.createQuery(jpql, Role.class);
        return query.getResultList();
    }
}
