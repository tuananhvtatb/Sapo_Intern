package com.sapo.edu.ex9.repository;

import com.sapo.edu.ex9.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
