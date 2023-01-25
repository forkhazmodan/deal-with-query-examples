package com.example.howtoquery.converter;

import com.example.howtoquery.enumeration.ProductRating;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProductRatingConverter implements AttributeConverter<ProductRating, Integer>  {
    @Override
    public Integer convertToDatabaseColumn(ProductRating productRating) {
        if (productRating == null)
            return null;

        switch (productRating) {
            case PG:
                return 1;
            case R:
                return 2;
            case G:
                return 3;
            case PG13:
                return 4;
            default:
                throw new IllegalArgumentException(productRating + " not supported.");
        }
    }

    @Override
    public ProductRating convertToEntityAttribute(Integer integer) {
        if (integer == null)
            return null;

        switch (integer) {
            case 1:
                return ProductRating.PG;
            case 2:
                return ProductRating.R;
            case 3:
                return ProductRating.G;
            case 4:
                return ProductRating.PG13;
            default:
                throw new IllegalArgumentException(integer + " not supported.");
        }
    }
}
