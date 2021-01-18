package com.sapo.sql_injection.repository;

import com.sapo.sql_injection.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
