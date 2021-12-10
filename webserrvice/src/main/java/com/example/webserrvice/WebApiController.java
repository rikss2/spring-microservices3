package com.example.webserrvice;

import com.example.webserrvice.entity.Role;
import com.example.webserrvice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebApiController {

    @Autowired
    UserService userService;

    @GetMapping("/userlist")
    public List<User> getUsers() {
        return userService.getUserList();
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String passwd) {
        User newUser;
        newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwd);
        newUser.addRole(Role.ROLE_CLIENT);
        try {
            return userService.signup(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "Unsuccessful";
        }
    }

    @DeleteMapping("/user/{userName}")
    public void deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
    }

    @PostMapping("/user")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}
