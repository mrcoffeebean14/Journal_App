package com.mrcoffeebean.journalapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrcoffeebean.journalapp.entity.UserEntity;
import com.mrcoffeebean.journalapp.services.UserEntityService;

@RestController
@RequestMapping("/api/user")
public class UserEntryController {

    @Autowired
    private UserEntityService userEntityService;

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return userEntityService.getAllUsers();
    }

    // Add single user
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserEntity user) {
        return userEntityService.addUser(user);
    }

    // Add multiple users
    @PostMapping("/addUser")
    public ResponseEntity<?> addAllUsers(@RequestBody List<UserEntity> users) {
        return userEntityService.addAllUser(users);
    }

    // Update user
    @PutMapping("/{username}")
    public ResponseEntity<?> changeUserEntity(
            @PathVariable String username,
            @RequestBody UserEntity newUser) {

        return userEntityService.changeUserEntity(username, newUser);
    }

    // Delete user
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUserByUserName(
            @PathVariable String username) {

        return userEntityService.deleteUserByUserName(username);
    }
}