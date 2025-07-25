package com.mart.Agrimart.mapper;

import com.mart.Agrimart.dto.ProductResponse;
import com.mart.Agrimart.dto.UserResponse;
import com.mart.Agrimart.entity.Product;

public class ProductToProductResp {

    public static ProductResponse productToProductResponse(Product product)
    {
        ProductResponse productResponse=new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setCategory(product.getCategory());
        productResponse.setOwnerId(product.getOwner().getId());
        return productResponse;
    }
}
