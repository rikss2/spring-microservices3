package com.example.webserrvice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class User implements UserDetails {

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
    private Set<Role> authorities;

    @Column(nullable = true)
    private String secret = null;

    @Column
    private String email;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, Set<Role> authorities, String secret, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.secret = secret;
        this.email = email;
    }

    protected User() {
    }

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) authorities = new HashSet<>();
        return authorities;
    }

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

    public void setAuthorities(Set<Role> roles) {
        this.authorities = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
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

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addAuthorities(Role role) {
        if (authorities == null) authorities = new HashSet<>();
        authorities.add(role);
    }
}
