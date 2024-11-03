package com.example.homecloud.service.impl;

import com.example.homecloud.entity.Role;
import com.example.homecloud.entity.User;
import com.example.homecloud.repository.UserRepository;
import com.example.homecloud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public void addUser(User user)
    {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new RuntimeException("User with username " + user.getUsername() + " is already exist");

        user.setRole(Role.USER);

        userRepository.save(user);
    }

    @Override
    public User getUserById(int id) {
        User user = userRepository.findById(id);
        if (user == null)
            throw new RuntimeException("No such user with id: " + id);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null)
            throw new RuntimeException("No such user with username: " + username);
        return user;
    }

    @Override
    public void editUserById(int id, User user)
    {
        User oldUser = userRepository.findById(id);
        if (oldUser == null)
            throw new RuntimeException("No such user with id: " + id);

        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());

        userRepository.save(oldUser);
    }

    @Override
    public void editUserByUsername(String username, User user) {
        User oldUser = userRepository.findByUsername(username).orElse(null);
        if (oldUser == null)
            throw new RuntimeException("No such user with username: " + username);

        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());

        userRepository.save(oldUser);
    }

    @Override
    public void deleteUserById(int id)
    {
        User user = userRepository.findById(id);
        if (user == null)
            throw new RuntimeException("No such user with id: " + id);

        userRepository.delete(user);
    }

    @Override
    public List<User> getAll()
    {
        return userRepository.findAll();
    }
}
