package com.mart.Agrimart.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductUpdateDto {

    private String name;
    private String description;
    private Double price;
    private Boolean isAvailable;
    private Integer quantity;
    private String category;
}
