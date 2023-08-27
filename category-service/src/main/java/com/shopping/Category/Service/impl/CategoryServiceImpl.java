package com.shopping.Category.Service.impl;

import com.shopping.Category.DTO.CategoryRequest;
import com.shopping.Category.DTO.CategoryResponse;
import com.shopping.Category.Entity.Category;
import com.shopping.Category.Repository.CategoryRepository;
import com.shopping.Category.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> addCategory(CategoryRequest categoryRequest) {
        try{
            String s= UUID.randomUUID().toString();
            Category category=Category.builder()
                    .categoryId(s)
                    .name(categoryRequest.getName())
                    .description(categoryRequest.getDescription())
                    .build();
            Category category1 = this.categoryRepository.save(category);
            CategoryResponse categoryResponse=CategoryResponse.builder()
                    .categoryId(category1.getCategoryId())
                    .description(category1.getDescription())
                    .name(category1.getName())
                    .build();
            return ResponseEntity.ok().body(categoryResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Internal Server Error");
        }
    }

    @Override
    public ResponseEntity<?> getCatgeory(String categoryId) {

        if(this.categoryRepository.existsById(categoryId)){
            Category category=this.categoryRepository.findById(categoryId).get();
            CategoryResponse categoryResponse=CategoryResponse.builder()
                    .name(category.getName())
                    .description(category.getDescription())
                    .categoryId(category.getCategoryId())
                    .build();
            return ResponseEntity.ok().body(categoryResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<?>> getCategories() {
        try{
            List<Category> categories=this.categoryRepository.findAll();
            List<CategoryResponse> categoryResponses=categories.stream().map(category -> {
                return CategoryResponse.builder()
                        .categoryId(category.getCategoryId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .build();
            }).collect(Collectors.toList());
            return ResponseEntity.ok().body(categoryResponses);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<?> deleteCategory(String categoryId) {
        if(this.categoryRepository.existsById(categoryId)){
            this.categoryRepository.deleteById(categoryId);
            return ResponseEntity.ok("Category deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Category> updateCategory(CategoryResponse categoryResponse) {

        if(this.categoryRepository.existsById(categoryResponse.getCategoryId())){
            Category category=Category.builder()
                    .categoryId(categoryResponse.getCategoryId())
                    .name(categoryResponse.getName())
                    .description(categoryResponse.getDescription())
                    .build();
            return ResponseEntity.ok().body(this.categoryRepository.save(category));
        }

        return ResponseEntity.badRequest().build();
    }
}
