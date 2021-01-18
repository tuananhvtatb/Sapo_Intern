package com.sapo.ex7restfulapispring.repository;

import com.sapo.ex7restfulapispring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
