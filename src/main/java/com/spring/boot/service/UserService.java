package com.spring.boot.service;


import com.spring.boot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    public User getUserById(long id);

    void addUser(User user);

    void removeUser(long id);

    void editUser(User user);
}