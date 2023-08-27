package com.shopping.Category.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private String categoryId;
    private String name;
    private String description;
}
