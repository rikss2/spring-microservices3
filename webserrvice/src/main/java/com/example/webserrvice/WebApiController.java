package com.example.webserrvice;

import com.example.webserrvice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebApiController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/userlist")
    public List<User> getUsers() {
        return userService.getUserList();
    }

    @GetMapping("/user")
    public User getUser(@RequestBody int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/signup")
    public User signup(@RequestBody @Valid CredentialDTO credentials) {
        return userService.signup(credentials).orElseThrow(() -> new RuntimeException("User already exists"));
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid CredentialDTO credentials) {
        return userService.login(credentials.getUsername(), credentials.getPassword()).orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
    }

    @DeleteMapping("/user")
    public boolean deleteUser(@RequestBody int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/user")
    public boolean updateUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
