package com.shopping.Product.DTO;

import com.shopping.Product.Entitty.Product;
import com.shopping.Product.ProductApplication;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private String imageUrl;
    private boolean availability;
    private String createdBy;
    private String categoryId;

    public static Product productRequestToProduct(ProductRequest productRequest){
        return Product.builder()
                .categoryId(productRequest.getCategoryId())
                .productName(productRequest.getProductName())
                .productDescription(productRequest.getProductDescription())
                .price(productRequest.getPrice())
                .availability(productRequest.isAvailability())
                .createdBy(productRequest.getCreatedBy())
                .imageUrl(productRequest.getImageUrl())
                .createdDate(new Date())
                .build();
    }

    public static ProductRequest productToProductRequest(Product product){
        return ProductRequest.builder()
                .categoryId(product.getCategoryId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .price(product.getPrice())
                .availability(product.isAvailability())
                .createdBy(product.getCreatedBy())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
