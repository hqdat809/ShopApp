package com.hqdat.ecommerce.controller;

import com.hqdat.ecommerce.dto.CategoryDTO;
import com.hqdat.ecommerce.model.Category;
import com.hqdat.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        Category category = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(category);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryByID(@PathVariable Long id) {
        Category listCategory = categoryService.getCategoryByID(id);
        return ResponseEntity.ok(listCategory);
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> listCategory = categoryService.getCategories();
        return ResponseEntity.ok(listCategory);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDTO categoryDTO) {
        Category updatedCategory = categoryService.updateCategory(id, categoryDTO );
        return ResponseEntity.ok(updatedCategory);
    }
}
