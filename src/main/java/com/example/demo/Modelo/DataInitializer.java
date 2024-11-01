package com.example.demo.Modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Repositorio.ProductRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Component
public class DataInitializer {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    @Transactional
    public void init() {
        productRepository.save(new Product("Televisor", "Category 1", 19.99));
        productRepository.save(new Product("Pc de mesa", "Category 2", 29.99));
        productRepository.save(new Product("Iphone 16", "Category 1", 39.99));
        productRepository.save(new Product("Huawei 89", "Category 3", 49.99));
        productRepository.save(new Product("Portatil", "Category 2", 59.99));

        productRepository.save(new Product("Samsung Galaxy S30", "Category 1", 99.99));
        productRepository.save(new Product("Smartwatch X", "Category 3", 79.99));
        productRepository.save(new Product("Tablet Pro", "Category 2", 119.99));
        productRepository.save(new Product("Audífonos Bluetooth", "Category 3", 14.99));
        productRepository.save(new Product("Cámara Reflex Z", "Category 1", 499.99));
        productRepository.save(new Product("Consola de Juegos", "Category 2", 399.99));
        productRepository.save(new Product("Teclado Mecánico", "Category 2", 89.99));
        productRepository.save(new Product("Monitor Curvo", "Category 1", 199.99));
        productRepository.save(new Product("Router Wifi 6", "Category 3", 129.99));
        productRepository.save(new Product("Mouse Inalámbrico", "Category 2", 49.99));
    }
}
