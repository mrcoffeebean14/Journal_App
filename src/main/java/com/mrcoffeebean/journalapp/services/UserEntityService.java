package com.mrcoffeebean.journalapp.services;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mrcoffeebean.journalapp.Repository.UserEntityRepo;
import com.mrcoffeebean.journalapp.entity.UserEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UserEntityService {

    private final UserEntityRepo userEntityRepo;

    // Get all users
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userEntityRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Add single user
    public ResponseEntity<?> addUser(UserEntity user) {
        try {
            userEntityRepo.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Failed to create user",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    // Add multiple users
    public ResponseEntity<?> addAllUser(List<UserEntity> users) {
        try {
            userEntityRepo.saveAll(users);
            return new ResponseEntity<>(users, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Failed to create users",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    // Delete user by ID
    public ResponseEntity<?> deleteUserById(ObjectId id) {
        try {
            if (!userEntityRepo.existsById(id)) {
                return new ResponseEntity<>(
                        "User not found",
                        HttpStatus.NOT_FOUND
                );
            }
            userEntityRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Failed to delete user",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    // Delete user by username
    public ResponseEntity<?> deleteUserByUserName(String username) {

        try {

            UserEntity user = userEntityRepo.findByUserName(username);

            if (user == null) {
                return new ResponseEntity<>(
                        "User not found",
                        HttpStatus.NOT_FOUND
                );
            }

            userEntityRepo.deleteByUserName(username);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {

            return new ResponseEntity<>(
                    "Failed to delete user",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    // Update user by ID
    public ResponseEntity<?> putUserById(
            ObjectId id,
            UserEntity newUser) {

        Optional<UserEntity> optionalUser =
                userEntityRepo.findById(id);

        if (optionalUser.isEmpty()) {

            return new ResponseEntity<>(
                    "User not found",
                    HttpStatus.NOT_FOUND
            );
        }

        UserEntity user = optionalUser.get();

        user.setUserName(newUser.getUserName());
        user.setPassWord(newUser.getPassWord());

        userEntityRepo.save(user);

        return new ResponseEntity<>(
                user,
                HttpStatus.OK
        );
    }

    // Update user by username
    public ResponseEntity<?> changeUserEntity(
            String username,
            UserEntity newUser) {

        UserEntity user =
                userEntityRepo.findByUserName(username);

        if (user == null) {

            return new ResponseEntity<>(
                    "User not found",
                    HttpStatus.NOT_FOUND
            );
        }

        user.setUserName(newUser.getUserName());
        user.setPassWord(newUser.getPassWord());

        userEntityRepo.save(user);

        return new ResponseEntity<>(
                user,
                HttpStatus.OK
        );
    }
}