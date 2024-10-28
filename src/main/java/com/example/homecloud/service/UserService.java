package com.example.homecloud.service;

import com.example.homecloud.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    void editUser(int id, User user);
    void deleteUserById(int id);
    List<User> getAll();
}
