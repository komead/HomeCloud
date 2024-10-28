package com.example.homecloud.controller;

import com.example.homecloud.entity.User;
import com.example.homecloud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;
//    private PasswordEncoder passwordEncoder;

    @PostMapping("/new")
    public User create(@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return user;
    }

    @PostMapping("/edit/{id}")
    public User updateUser(@PathVariable(value = "id") int id, @RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.editUser(id, user);
        return user;
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") int id) {
        userService.deleteUserById(id);
        return "user deleted";
    }

    @GetMapping("")
    public List<User> getAll(Model model) {
        return userService.getAll();
    }
}
