package com.example.thi_ead.repository;

import com.example.thi_ead.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
