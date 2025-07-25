package com.mart.Agrimart.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequestDto {

    @NotNull(message = "product name must be there")
    private String name;
    @NotNull(message = "decsription for the product should be there")
    private String description;
    @NotNull(message = "price is mandatory")
    private Double price;
    @NotNull(message = "quantity is needed")
    private int quantity;
    @NotNull(message = "select the category")
    private String category;
}

