package com.example.howtoquery.repository;

import com.example.howtoquery.enumeration.ProductType;
import com.example.howtoquery.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>,
    QuerydslPredicateExecutor<Product> {

    Page<Product> findByProductType(ProductType productType, Pageable pageable);
}
