package com.sapo.edu.ex5dbspringboot.repository;

import com.sapo.edu.ex5dbspringboot.entities.RepositoryS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryRepo extends JpaRepository<RepositoryS, Integer> {
}
