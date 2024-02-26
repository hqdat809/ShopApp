package com.hqdat.ecommerce.service;

import com.hqdat.ecommerce.dto.ProductDTO;
import com.hqdat.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);

    Product getProductByID(Long productID);

    Page<Product> getProducts(Pageable pageable);

    Page<Product> getProductsByCategory(Pageable pageable, Long categoryID);

    Product updateProduct(Long productID, ProductDTO productDTO);

    void deleteProduct(Long productID);


}
