package com.shopping.Category.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CategoryRequest {

    private String name;
    private String description;

}
