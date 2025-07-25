package com.mart.Agrimart.mapper;

import com.mart.Agrimart.dto.ProductRequestDto;
import com.mart.Agrimart.entity.Product;

public class ProductRequestToProduct {

    public static Product productRequestToProduct(ProductRequestDto requestDto)
    {
        Product product=new Product();
        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setQuantity(requestDto.getQuantity());
        product.setCategory(requestDto.getCategory());
        return product;
    }
}
