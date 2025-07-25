package com.mart.Agrimart.service;

import com.mart.Agrimart.dto.ProductRequestDto;
import com.mart.Agrimart.dto.ProductResponse;
import com.mart.Agrimart.dto.ProductUpdateDto;
import com.mart.Agrimart.entity.Product;
import com.mart.Agrimart.entity.User;
import com.mart.Agrimart.exception.AccessDeniedForEditException;
import com.mart.Agrimart.mapper.ProductRequestToProduct;
import com.mart.Agrimart.mapper.ProductToProductResp;
import com.mart.Agrimart.repository.ProductRepository;
import com.mart.Agrimart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createProduct(ProductRequestDto requestDto, MultipartFile image, Long userId) {

        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found with userId "+userId));
        Product product= ProductRequestToProduct.productRequestToProduct(requestDto);
        product.setIsAvailable(true);
        String imageUrl=image.getOriginalFilename();
        product.setImageUrl(imageUrl);
        product.setOwner(user);
        productRepository.save(product);
        ProductResponse productResponse= ProductToProductResp.productToProductResponse(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);

    }

    public ResponseEntity<?> getAllProducts(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found with userId "+userId));
        List<Product> productsList=productRepository.findAll();
        if(productsList.isEmpty())
        {
            return ResponseEntity.ok().body(Map.of("message","no products found"));
        }
        List<ProductResponse> productResponseList=productsList.stream().map(product ->ProductToProductResp.productToProductResponse(product)).collect(Collectors.toList());
        return ResponseEntity.ok().body(productResponseList);

    }

    public ResponseEntity<?> updateProduct(Long productId, ProductUpdateDto updateDto, MultipartFile image, Long userId) {

        Product product=productRepository.findById(productId).
                        orElseThrow(()->new RuntimeException("product not found with id "+productId));
        if(product.getOwner().getId()!=userId)
        {
            throw new AccessDeniedForEditException("you don't have access to edit this");
        }
        boolean isUpdated=false;
        if(image!=null && !image.isEmpty())
        {
            String imageUrl=image.getOriginalFilename();
            if(!product.getImageUrl().equals(imageUrl)) {
                product.setImageUrl(imageUrl);
                isUpdated = true;
            }
        }
        if(updateDto!=null) {
            if (updateDto.getName()!=null && !product.getName().equals(updateDto.getName())) {
                product.setName(updateDto.getName());
                isUpdated = true;
            }
            if (updateDto.getIsAvailable()!=null && product.getIsAvailable() != updateDto.getIsAvailable()) {
                product.setIsAvailable(updateDto.getIsAvailable());
                isUpdated = true;
            }
            if (updateDto.getCategory()!=null && !product.getCategory().equals(updateDto.getCategory())) {
                product.setCategory(updateDto.getCategory());
                isUpdated = true;
            }
            if (updateDto.getDescription()!=null && !product.getDescription().equals(updateDto.getDescription())) {
                product.setDescription(updateDto.getDescription());
                isUpdated = true;
            }
            if (updateDto.getQuantity()!=null && product.getQuantity() != updateDto.getQuantity()) {
                product.setQuantity(updateDto.getQuantity());
                isUpdated = true;
            }
            if (updateDto.getPrice()!=null &&product.getPrice() != updateDto.getPrice()) {
                product.setPrice(updateDto.getPrice());
            }
        }

        if(!isUpdated) {
            return ResponseEntity.ok(Map.of("message", "There is no difference between existing data and your provided data"));
        }
        productRepository.save(product);
        ProductResponse response=ProductToProductResp.productToProductResponse(product);
        return ResponseEntity.ok().body(response);

    }

    public ResponseEntity<?> deleteProduct(Long id, Long userId) {

        Product product=productRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("product not found with id "+id));
        if(product.getOwner().getId()!=userId)
        {
            throw new AccessDeniedForEditException("you don't have access to delete this product");
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok().body(Map.of("message","product is deleted successfullyu"));
    }

    public ResponseEntity<?> getAllMyProducts(Long userId) {

        List<Product> myProductsList=productRepository.productsByUser(userId);
        if(myProductsList.isEmpty())
        {
            return ResponseEntity.ok().body(Map.of("message","no products found"));
        }
        List<ProductResponse> productResponses=myProductsList.stream().
                map(product -> ProductToProductResp.productToProductResponse(product))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(productResponses);
    }
}
