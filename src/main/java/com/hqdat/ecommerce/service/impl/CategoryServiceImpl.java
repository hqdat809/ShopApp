package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.dto.CategoryDTO;
import com.hqdat.ecommerce.model.Category;
import com.hqdat.ecommerce.repository.CategoryRepository;
import com.hqdat.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category convertDTO(CategoryDTO categoryDTO) {
        return Category
                .builder()
                .name(categoryDTO.getName())
                .build();
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory = convertDTO(categoryDTO);

        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryByID(Long categoryID) {
        return categoryRepository.findById(categoryID)
                .orElseThrow(() -> new RuntimeException("Category not found!!"));
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long categoryID, CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryByID(categoryID);

        existingCategory.setName(categoryDTO.getName());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long categoryID) {
        Category existingCategory = getCategoryByID(categoryID);

        if(existingCategory != null) {
            categoryRepository.delete(existingCategory);
        }
    }
}
