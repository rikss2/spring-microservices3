package com.example.webserrvice.repos;

import com.example.webserrvice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

}
