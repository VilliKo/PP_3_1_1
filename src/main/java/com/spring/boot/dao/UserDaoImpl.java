package com.spring.boot.dao;

import com.spring.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {}

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {

        entityManager.persist(user);
    }

    @Override
    public void removeUser(long id) {
        Query q = entityManager.createQuery("DELETE FROM User WHERE id=:id");
        q.setParameter("id", id).executeUpdate();
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }
}
