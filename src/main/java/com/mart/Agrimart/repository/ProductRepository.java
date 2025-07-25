package com.mart.Agrimart.repository;

import com.mart.Agrimart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value="select * from products where owner_id=?1",nativeQuery = true)
    List<Product> productsByUser(Long userId);
}
