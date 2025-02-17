package com.example.lab5_asm.repository;

import com.example.lab5_asm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category_id = :category_id")
    Page<Product> findByCategoryId(String category_id, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
}
 
