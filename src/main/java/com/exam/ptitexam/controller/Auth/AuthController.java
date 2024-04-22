package com.exam.ptitexam.controller.Auth;

import com.exam.ptitexam.domain.Role;
import com.exam.ptitexam.domain.User;
import com.exam.ptitexam.domain.dto.LoginDTO;
import com.exam.ptitexam.domain.dto.RegisterDTO;
import com.exam.ptitexam.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO body) {
        try {
            User newUser = this.userService.registerDTOtoUser(body);
            Role userRole = new Role();
            userRole.setId(2);
            newUser.setRole(userRole);
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            this.userService.handleSaveUser(newUser);
            return new ResponseEntity<>("User registered successfully!.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User registration failed.", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO body) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User login successfully!.", HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("User not authenticated.", HttpStatus.UNAUTHORIZED);
        }
    }
}
