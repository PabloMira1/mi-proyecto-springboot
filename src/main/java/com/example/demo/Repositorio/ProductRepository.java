package com.example.demo.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Modelo.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
}

