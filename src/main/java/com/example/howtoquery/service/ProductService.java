package com.example.howtoquery.service;

import com.example.howtoquery.enumeration.ProductType;
import com.example.howtoquery.model.Product;
import com.example.howtoquery.repository.ProductRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    public Page<Product> search(ProductType productType, Pageable pageable) {
        return productRepository.findByProductType(productType, pageable);
    }


}
