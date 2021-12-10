package com.example.webserrvice.entity;

//import org.springframework.security.core.GrantedAuthority;

public enum Role {
    ROLE_ADMIN, ROLE_CLIENT;

    public String getAuthority() {
        return name();
    }

}

