package com.hqdat.ecommerce.service.impl;

import com.hqdat.ecommerce.dto.CategoryDTO;
import com.hqdat.ecommerce.dto.ProductDTO;
import com.hqdat.ecommerce.model.Category;
import com.hqdat.ecommerce.model.Product;
import com.hqdat.ecommerce.repository.CategoryRepository;
import com.hqdat.ecommerce.repository.ProductRepository;
import com.hqdat.ecommerce.service.CategoryService;
import com.hqdat.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public Product convertDTO(ProductDTO productDTO) {
        Category existingCategory = categoryService.getCategoryByID(productDTO.getCategoryID());

        return Product.builder()
                .description(productDTO.getDescription())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .category(existingCategory)
                .build();
    }
    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product newProduct = convertDTO(productDTO);

        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductByID(Long productID) {

        return productRepository.findById(productID).orElseThrow(() -> new RuntimeException("Product not found!!"));
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getProductsByCategory(Pageable pageable, Long categoryID) {
        Category existingCategory = categoryService.getCategoryByID(categoryID);

        return productRepository.findByCategory(pageable, existingCategory);
    }

    @Override
    public Product updateProduct(Long productID, ProductDTO productDTO) {

        Product existingProduct = getProductByID(productID);


        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setThumbnail(productDTO.getThumbnail());

//        Update category of product
        if (existingProduct.getCategory() != null && productDTO.getCategoryID() != existingProduct.getCategory().getId()) {
            Category newCategory = categoryService.getCategoryByID(productDTO.getCategoryID());
            existingProduct.setCategory(newCategory);
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long productID) {
        Product existingProduct = getProductByID(productID);

        if(existingProduct != null) {
            productRepository.delete(existingProduct);
        }
    }
}
