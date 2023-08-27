package com.shopping.Product.DTO;

import com.shopping.Product.Entitty.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private String productId;
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private String imageUrl;
    private boolean availability;
    private String createdBy;
    private Date createdDate;
    private String categoryId;

    public static Product productResponseToProduct(ProductResponse productResponse){
        return Product.builder()
                .productId(productResponse.getProductId())
                .productName(productResponse.getProductName())
                .productDescription(productResponse.getProductDescription())
                .price(productResponse.getPrice())
                .categoryId(productResponse.getCategoryId())
                .createdBy(productResponse.getCreatedBy())
                .createdDate(productResponse.getCreatedDate())
                .availability(productResponse.isAvailability())
                .imageUrl(productResponse.getImageUrl())
                .build();
    }

    public static ProductResponse productToProductResponse(Product product){
        return ProductResponse.builder()
                .productId(product.getProductId())
                .categoryId(product.getCategoryId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .price(product.getPrice())
                .availability(product.isAvailability())
                .createdBy(product.getCreatedBy())
                .createdDate(product.getCreatedDate())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
