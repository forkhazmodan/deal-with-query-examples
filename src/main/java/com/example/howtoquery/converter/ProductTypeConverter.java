package com.example.howtoquery.converter;

import com.example.howtoquery.enumeration.ProductType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProductTypeConverter implements AttributeConverter<ProductType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProductType productType) {
        if (productType == null)
            return null;

        switch (productType) {
            case OTHER:
                return 1;
            case FOOD:
                return 2;
            case COMPUTERS:
                return 3;
            default:
                throw new IllegalArgumentException(productType + " not supported.");
        }
    }

    @Override
    public ProductType convertToEntityAttribute(Integer integer) {
        if (integer == null)
            return null;

        switch (integer) {
            case 1:
                return ProductType.OTHER;
            case 2:
                return ProductType.FOOD;
            case 3:
                return ProductType.COMPUTERS;
            default:
                throw new IllegalArgumentException(integer + " not supported.");
        }
    }
}
