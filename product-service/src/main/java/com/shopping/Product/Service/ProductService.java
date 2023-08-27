package com.shopping.Product.Service;

import com.shopping.Product.DTO.ProductRequest;
import com.shopping.Product.DTO.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> addProduct(ProductRequest productRequest);
    ResponseEntity<?> getProduct(String productId);
    ResponseEntity<?> getProducts();
    ResponseEntity<?> deleteProduct(String productId);
    ResponseEntity<?> updateProduct(ProductResponse productResponse);
    ResponseEntity<?> getProductsByCatgeoryId(String categoryId);
    ResponseEntity<?> deleteProductsByCategoryId(String categoryId);
}
