package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
@Repository
public class RolesDaoImpl implements RolesDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = entityManager.createQuery("from Role", Role.class).getResultList();
        return roles;
    }

    @Override
    public List<String> getRolesNames() {
        List<Role> roles = getAllRoles();
        List<String> rolesNames = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            rolesNames.add(roles.get(i).getRole());
        }
        return rolesNames;
    }

}
