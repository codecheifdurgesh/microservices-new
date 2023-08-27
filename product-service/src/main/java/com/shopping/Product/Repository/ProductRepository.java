package com.shopping.Product.Repository;

import com.shopping.Product.Entitty.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    boolean existsByCategoryId(String categoryId);

    List<Product> findByCategoryId(String categoryId);
}
