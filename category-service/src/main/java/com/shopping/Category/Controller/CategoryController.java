package com.shopping.Category.Controller;

import com.shopping.Category.DTO.CategoryRequest;
import com.shopping.Category.DTO.CategoryResponse;
import com.shopping.Category.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest){
        return this.categoryService.addCategory(categoryRequest);
    }

    @GetMapping("/")
    ResponseEntity<?> getCategories(){
        return this.categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    ResponseEntity<?> getCategory(@PathVariable String categoryId){
        return this.categoryService.getCatgeory(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    ResponseEntity<?> deleteCategory(@PathVariable String categoryId){
        return this.categoryService.deleteCategory(categoryId);
    }

    @PutMapping("/")
    ResponseEntity<?> updateCategory(@RequestBody CategoryResponse categoryResponse){
        return this.categoryService.updateCategory(categoryResponse);
    }
}
