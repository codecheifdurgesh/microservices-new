package com.shopping.Product.Service.impl;

import com.shopping.Product.DTO.ProductRequest;
import com.shopping.Product.DTO.ProductResponse;
import com.shopping.Product.Entitty.Product;
import com.shopping.Product.Repository.ProductRepository;
import com.shopping.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<?> addProduct(ProductRequest productRequest) {
        Product product=ProductRequest.productRequestToProduct(productRequest);
        product.setProductId(UUID.randomUUID().toString());
        try{
            Product product1=this.productRepository.save(product);
            return ResponseEntity.ok().body(ProductResponse.productToProductResponse(product1));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<?> getProduct(String productId) {
        if(this.productRepository.existsById(productId)){
            Product product=this.productRepository.findById(productId).get();
            return ResponseEntity.ok().body(ProductResponse.productToProductResponse(product));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> getProducts() {
        try{
            List<Product> productList=this.productRepository.findAll();
            List<ProductResponse> productResponses=productList.stream().map(ProductResponse::productToProductResponse).collect(Collectors.toList());
            return ResponseEntity.ok().body(productResponses);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> deleteProduct(String productId) {
        if(this.productRepository.existsById(productId)){
            this.productRepository.deleteById(productId);
            return ResponseEntity.ok().body("Product Deleted Successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> updateProduct(ProductResponse productResponse) {
        if(this.productRepository.existsById(productResponse.getProductId())){
            Product product=ProductResponse.productResponseToProduct(productResponse);
            Product product1=this.productRepository.save(product);
            return ResponseEntity.ok().body(ProductResponse.productToProductResponse(product1));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> getProductsByCatgeoryId(String categoryId) {
        if(this.productRepository.existsByCategoryId(categoryId)){
            List<Product> productList=this.productRepository.findByCategoryId(categoryId);
            List<ProductResponse> productResponses=productList.stream().map(ProductResponse::productToProductResponse).collect(Collectors.toList());
            return ResponseEntity.ok().body(productResponses);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> deleteProductsByCategoryId(String categoryId) {
        if(this.productRepository.existsByCategoryId(categoryId)){
            List<Product> productList=this.productRepository.findByCategoryId(categoryId);
            List<ProductResponse> productResponses=productList.stream().map(ProductResponse::productToProductResponse).collect(Collectors.toList());
            try {
                productResponses.forEach(productResponse -> {
                    this.productRepository.deleteById(productResponse.getProductId());
                });
                return ResponseEntity.ok().body("All the products belonging to this category deleted succesfully");
            }catch (Exception e){
                return ResponseEntity.internalServerError().body(e.getMessage());
            }

        }
        return ResponseEntity.notFound().build();
    }
}
