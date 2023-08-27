package com.shopping.Product.Controller;

import com.shopping.Product.DTO.ProductRequest;
import com.shopping.Product.DTO.ProductResponse;
import com.shopping.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest){
        return this.productService.addProduct(productRequest);
    }

    @GetMapping("/{productId}")
    ResponseEntity<?> getProduct(@PathVariable String productId){
        return this.productService.getProduct(productId);
    }

    @GetMapping("/")
    ResponseEntity<?> getProducts(){
        return this.productService.getProducts();
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<?> deleteProduct(@PathVariable String productId){
        return this.productService.deleteProduct(productId);
    }

    @PutMapping("/")
    ResponseEntity<?> updateProduct(@RequestBody ProductResponse productResponse){
        return this.productService.updateProduct(productResponse);
    }

    @GetMapping("/category/{categoryId}")
    ResponseEntity<?> getProductsByCategoryId(@PathVariable String categoryId){
        return this.productService.getProductsByCatgeoryId(categoryId);
    }

    @DeleteMapping("/category/{categoryId}")
    ResponseEntity<?> deleteProductsByCategoryId(@PathVariable String categoryId){
        return this.productService.deleteProductsByCategoryId(categoryId);
    }

}
