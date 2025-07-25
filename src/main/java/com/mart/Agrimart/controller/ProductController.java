package com.mart.Agrimart.controller;

import com.mart.Agrimart.dto.ProductRequestDto;
import com.mart.Agrimart.dto.ProductUpdateDto;
import com.mart.Agrimart.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("/farmer/add/product")
    public ResponseEntity<?> addProduct(
            @Valid @RequestPart(value = "product",required=true)ProductRequestDto requestDto,
            @RequestPart(value = "image",required = true)MultipartFile image,
            HttpSession session)
    {
        System.out.println(image.isEmpty());
        if(image.isEmpty())
        {
            return ResponseEntity.badRequest().body(Map.of("message","image is required to add product"));
        }
        Long userId=(Long)session.getAttribute("userId");
        return productService.createProduct(requestDto,image,userId);
    }
    @GetMapping("/get/allProducts")
    public ResponseEntity<?> getAllProducts(HttpSession session)
    {
        Long userId=(Long)session.getAttribute("userId");
        return productService.getAllProducts(userId);
    }

    @GetMapping("/farmer/get/my/allProducts")
    public ResponseEntity<?> getAllMyProducts(HttpSession session)
    {
        Long userId=(Long)session.getAttribute("userId");
        return productService.getAllMyProducts(userId);
    }

    @PatchMapping("/farmer/update/product/{id}")
    public ResponseEntity<?> updateProduct(
            @RequestPart(value="product",required = false) ProductUpdateDto updateDto,
            @RequestPart(value="image",required = false) MultipartFile image,
            @PathVariable("id") Long productId,HttpSession session)
    {
        if(updateDto==null && (image==null || image.isEmpty()))
        {
            return ResponseEntity.badRequest().body(Map.of("message","No data is given to update the product"));
        }
        Long userId=(Long)session.getAttribute("userId");
        return productService.updateProduct(productId,updateDto,image,userId);
    }

    @DeleteMapping("/farmer/delete/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id,HttpSession session)
    {
        Long userId=(Long)session.getAttribute("userId");
        return productService.deleteProduct(id,userId);
    }
}
