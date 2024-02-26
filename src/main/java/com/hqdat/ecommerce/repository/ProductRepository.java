package com.hqdat.ecommerce.repository;

import com.hqdat.ecommerce.model.Category;
import com.hqdat.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);

    Page<Product> findByCategory(Pageable pageable, Category category);

}
