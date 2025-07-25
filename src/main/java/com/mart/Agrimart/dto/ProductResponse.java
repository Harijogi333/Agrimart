package com.mart.Agrimart.dto;


import com.mart.Agrimart.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private Long ownerId;
}

