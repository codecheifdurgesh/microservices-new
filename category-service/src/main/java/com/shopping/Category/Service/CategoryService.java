package com.shopping.Category.Service;

import com.shopping.Category.DTO.CategoryRequest;
import com.shopping.Category.DTO.CategoryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<?> addCategory(CategoryRequest categoryRequest);
    ResponseEntity<?> getCatgeory(String categoryId);
    ResponseEntity<List<?>> getCategories();
    ResponseEntity<?> deleteCategory(String categoryId);
    ResponseEntity<?> updateCategory(CategoryResponse categoryResponse);
}
