package com.technova.product.repo;

import com.technova.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
}
