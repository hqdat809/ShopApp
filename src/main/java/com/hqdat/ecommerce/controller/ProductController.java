package com.hqdat.ecommerce.controller;

import com.hqdat.ecommerce.dto.ProductDTO;
import com.hqdat.ecommerce.model.Product;
import com.hqdat.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @GetMapping("")
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort
    ) {
        PageRequest  pageRequest = PageRequest.of(page, size, Sort.by(sort).descending());

        return ResponseEntity.ok(productService.getProducts(pageRequest));
    }

    @GetMapping("/by-category")
    public ResponseEntity<Page<Product>> getProductsByCategory(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "category", defaultValue = "") Long categoryID
    ) {
        PageRequest  pageRequest = PageRequest.of(page, size, Sort.by(sort).descending());

        return ResponseEntity.ok(productService.getProductsByCategory(pageRequest, categoryID));
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Delete product success!!");
    }
}
