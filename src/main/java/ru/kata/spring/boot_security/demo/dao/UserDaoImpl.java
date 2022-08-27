package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
     private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        var users = entityManager.createQuery("from User", User.class).getResultList();
        return users;
    }

    @Override
    public User findByUsername(String userName) {
        User user = entityManager.createQuery(
                        "SELECT u from User u WHERE u.userName = :username", User.class).
                setParameter("username", userName).getSingleResult();

        System.out.println(user);
        return user;
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }
}