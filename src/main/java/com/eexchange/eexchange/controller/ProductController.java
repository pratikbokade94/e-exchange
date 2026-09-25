package com.eexchange.eexchange.controller;

import com.eexchange.eexchange.entity.Product;
import com.eexchange.eexchange.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Sell: naya product add karo
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        if (product.getTitle() == null || product.getTitle().isBlank()
                || product.getCategory() == null || product.getCategory().isBlank()
                || product.getPrice() == null) {
            return ResponseEntity.badRequest().body("Title, category and price are required");
        }

        product.setId(null);
        product.setCategory(product.getCategory().trim().toUpperCase());
        return ResponseEntity.ok(productRepository.save(product));
    }

    // Buy: list, category filter aur search
    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String category,
                                     @RequestParam(required = false) String q) {
        if (q != null && !q.isBlank()) {
            return productRepository.findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(q.trim());
        }
        if (category != null && !category.isBlank()) {
            return productRepository.findByCategoryIgnoreCaseOrderByCreatedAtDesc(category.trim());
        }
        return productRepository.findAllByOrderByCreatedAtDesc();
    }

    // Ek product ki detail (seller ka contact bhi isi mein hai)
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Product not found"));
    }

    // Mere listings
    @GetMapping("/my")
    public List<Product> myProducts(@RequestParam String email) {
        return productRepository.findBySellerEmailOrderByCreatedAtDesc(email.trim().toLowerCase());
    }

    // Delete: sirf wahi user jisne item list kiya
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id, @RequestParam String email) {
        return productRepository.findById(id).map(p -> {
            if (p.getSellerEmail() == null || !p.getSellerEmail().equalsIgnoreCase(email.trim())) {
                return ResponseEntity.status(403).body("You can only delete your own items");
            }
            productRepository.delete(p);
            return ResponseEntity.ok("Deleted");
        }).orElse(ResponseEntity.status(404).body("Product not found"));
    }

    // Edit: sirf wahi user jisne item list kiya
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id,
                                                @RequestParam String email,
                                                @RequestBody Product updated) {
        return productRepository.findById(id).map(p -> {
            if (p.getSellerEmail() == null || !p.getSellerEmail().equalsIgnoreCase(email.trim())) {
                return ResponseEntity.status(403).body("You can only edit your own items");
            }
            if (updated.getTitle() == null || updated.getTitle().isBlank()
                    || updated.getCategory() == null || updated.getCategory().isBlank()
                    || updated.getPrice() == null) {
                return ResponseEntity.badRequest().body("Title, category and price are required");
            }

            p.setTitle(updated.getTitle());
            p.setCategory(updated.getCategory().trim().toUpperCase());
            p.setBrand(updated.getBrand());
            p.setPrice(updated.getPrice());
            p.setItemCondition(updated.getItemCondition());
            p.setDescription(updated.getDescription());
            p.setSellerPhone(updated.getSellerPhone());

            // photo tabhi badlo jab nayi photo aayi ho
            if (updated.getImageUrl() != null && !updated.getImageUrl().isBlank()) {
                p.setImageUrl(updated.getImageUrl());
            }

            productRepository.save(p);
            return ResponseEntity.ok("Updated");
        }).orElse(ResponseEntity.status(404).body("Product not found"));
    }
}