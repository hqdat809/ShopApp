package com.hqdat.ecommerce.service;

import com.hqdat.ecommerce.dto.CategoryDTO;
import com.hqdat.ecommerce.model.Category;
import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category createCategory(CategoryDTO categoryDTO);

    Category getCategoryByID(Long categoryID);

    List<Category> getCategories();

    Category updateCategory(Long categoryID, CategoryDTO categoryDTO);

    void deleteCategory(Long categoryID);

}
