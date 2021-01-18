package com.sapo.edu.ex9.repository;

import com.sapo.edu.ex9.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
