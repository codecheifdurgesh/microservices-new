package com.shopping.Product.Entitty;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private String imageUrl;
    private boolean availability;
    private String createdBy;
    private Date createdDate;
    private String categoryId;
}
