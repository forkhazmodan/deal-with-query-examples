package com.example.howtoquery.model;

import com.example.howtoquery.converter.ProductRatingConverter;
import com.example.howtoquery.converter.ProductTypeConverter;
import com.example.howtoquery.enumeration.ProductRating;
import com.example.howtoquery.enumeration.ProductType;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Long price;

    @Convert(converter = ProductTypeConverter.class)
    private ProductType productType;

    @Convert(converter = ProductRatingConverter.class)
    private ProductRating productRating;
}
