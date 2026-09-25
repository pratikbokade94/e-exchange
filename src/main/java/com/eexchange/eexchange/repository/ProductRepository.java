package com.eexchange.eexchange.repository;

import com.eexchange.eexchange.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryIgnoreCaseOrderByCreatedAtDesc(String category);

    List<Product> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String title);

    List<Product> findAllByOrderByCreatedAtDesc();

    List<Product> findBySellerEmailOrderByCreatedAtDesc(String sellerEmail);
}