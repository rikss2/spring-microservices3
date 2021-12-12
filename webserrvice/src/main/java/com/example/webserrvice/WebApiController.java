package com.example.webserrvice;

import com.example.webserrvice.entity.Role;
import com.example.webserrvice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WebApiController {

    @Autowired
    UserService userService;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/userlist")
    public List<User> getUsers() {
        return userService.getUserList();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String passwd) {
        User newUser;
        newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder().encode(passwd));
        newUser.addAuthorities(Role.ROLE_USER);
        try {
            return userService.signup(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "Unsuccessful";
        }
    }

    @DeleteMapping("/user/{id}")
    public boolean deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/user")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}
