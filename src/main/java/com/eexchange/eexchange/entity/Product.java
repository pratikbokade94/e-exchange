package com.eexchange.eexchange.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // PHONE or LAPTOP
    @Column(nullable = false)
    private String category;

    private String brand;

    @Column(nullable = false)
    private Double price;

    // Like New, Good, Fair
    private String itemCondition;

    @Column(length = 2000)
    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String imageUrl;

    private String sellerName;
    private String sellerEmail;
    private String sellerPhone;

    private LocalDateTime createdAt = LocalDateTime.now();
}