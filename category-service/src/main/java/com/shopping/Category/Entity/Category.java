package com.shopping.Category.Entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private String categoryId;
    private String name;
    private String description;

}
