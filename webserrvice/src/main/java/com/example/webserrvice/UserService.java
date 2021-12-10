package com.example.webserrvice;
import com.example.webserrvice.entity.User;
import com.example.webserrvice.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return tokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }*/

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

    public void updateUser(User user)
    {
        userRepository.save(user);
    }
}
