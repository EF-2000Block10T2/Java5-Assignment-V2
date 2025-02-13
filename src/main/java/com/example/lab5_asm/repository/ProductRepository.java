package com.example.lab5_asm.repository;

import com.example.lab5_asm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
