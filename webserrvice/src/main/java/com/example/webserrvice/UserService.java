package com.example.webserrvice;

import com.example.webserrvice.entity.Role;
import com.example.webserrvice.entity.User;
import com.example.webserrvice.repos.UserRepository;
import com.example.webserrvice.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    Logger logger = LoggerFactory.getLogger(WebApiController.class);

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public Optional<User> signup(CredentialDTO credentials) {
        if (userRepository.findByUsername(credentials.getUsername()).isEmpty()) {
            return Optional.of(userRepository.save
                    (new User(credentials.getUsername(),
                            passwordEncoder.encode(credentials.getPassword()))));
        }
        return Optional.empty();
    }


    public boolean deleteUser(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional()
    public void updateUser(User user) {
        user.generateSecret();
        userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name).orElseThrow();
    }

    public Optional<String> login(String username, String password) {
        Optional<String> token = Optional.empty();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                token = Optional.of(jwtProvider.createToken(username, (List<Role>) user.get().getAuthorities()));
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }
        return token;
    }
}
