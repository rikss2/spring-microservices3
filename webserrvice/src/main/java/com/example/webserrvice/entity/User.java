package com.example.webserrvice.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Column(nullable = true)
    private String key = null;

    void createRoleList(){
        if (this.roles == null ) roles = new ArrayList<Role>();
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        createRoleList();
        return roles;
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

    public void addRole(Role role) {
        createRoleList();
        this.roles.add(role);
    }


    public Optional<String> getKey() {
        return Optional.ofNullable(key);
    }

    public void generateKey(){
        this.key= "123456";
    }
}
