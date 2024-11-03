package com.example.homecloud.service;

import com.example.homecloud.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    void editUserById(int id, User user);
    void editUserByUsername(String username, User user);
    void deleteUserById(int id);
    List<User> getAll();
}
