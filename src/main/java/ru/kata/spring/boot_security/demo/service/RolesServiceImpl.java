package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RolesDao;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Service
public class RolesServiceImpl implements RolesService{
    @Autowired
    private RolesDao rolesDao;


    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return rolesDao.getAllRoles();
    }

    @Override
    public List<String> getRolesNames() {
        return rolesDao.getRolesNames();
    }
}
