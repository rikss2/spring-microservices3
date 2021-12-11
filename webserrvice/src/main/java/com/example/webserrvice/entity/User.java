package com.example.webserrvice.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class User {

    @Column(updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Column(nullable = true)
    private String secret = null;

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }

    public String getUsername() {
        return username;
    }


    public Optional<String> getSecret() {
        return Optional.ofNullable(secret);
    }

    public void generateSecret() {
        this.secret = "123456";
    }

    public int getId() {
        return id;
    }

    public List<Role> getRoles() {
        if (roles == null) roles = new ArrayList<>();
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
