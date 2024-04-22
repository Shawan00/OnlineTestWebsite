package com.exam.ptitexam.controller.admin.User;

import com.exam.ptitexam.domain.User;
import com.exam.ptitexam.domain.dto.UpdateUser;
import com.exam.ptitexam.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestUserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RestUserController(UserService userService,
                              PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/api/admin/user/list")
    public List<User> getUserList() {
        return userService.getAllUser();
    }

    @PostMapping("/api/admin/user/create")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User createdUser = userService.handleSaveUser(newUser);
        if (createdUser != null) {
            // user creation successful, return user details
            return ResponseEntity.ok(createdUser);
        } else {
            // user creation failed, return an error message
            return ResponseEntity.badRequest().body("User creation failed.");


        }
    }

    @PutMapping("/api/admin/user/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody UpdateUser updatedUser) {
        User user = this.userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }
        user.setFullName(updatedUser.getFullName());
        user.setRole(updatedUser.getRole());
        User updated = this.userService.handleSaveUser(user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/api/admin/user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        User user = this.userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }
        this.userService.deleteUserById(user.getId());
        return ResponseEntity.ok("User deleted successfully.");
    }
}