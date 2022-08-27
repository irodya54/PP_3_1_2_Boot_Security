package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RolesService {
    public List<Role> getAllRoles();
    public List<String> getRolesNames();
}
