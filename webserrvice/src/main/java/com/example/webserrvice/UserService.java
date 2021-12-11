package com.example.webserrvice;
import com.example.webserrvice.entity.User;
import com.example.webserrvice.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList(){
        return userRepository.findAll();
    }

    public String signup(User user) throws Exception {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(user.getPassword());
            userRepository.save(user);
            return user.getUsername();
        } else {
            throw new Exception("Username is already in use");
        }
    }

    public void deleteUser(String userName){
        userRepository.deleteByUsername(userName);
    }

    @Transactional()
    public void updateUser(User user) {
        user.generateSecret();
        userRepository.save(user);
    }
}
