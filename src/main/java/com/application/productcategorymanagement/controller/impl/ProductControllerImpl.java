package com.application.productcategorymanagement.controller.impl;

import com.application.productcategorymanagement.controller.ProductController;
import com.application.productcategorymanagement.entity.Product;
import com.application.productcategorymanagement.repository.ProductRepository;
import com.application.productcategorymanagement.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("product")
@Tag(name = "Product", description = "Product API operations")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductControllerImpl(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public List<Product> getAllProducts(Pageable pageable){
        return productService.findAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(UUID id){
        return productService.findProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(UUID id, Product updatedProduct) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product existingProduct = productOpt.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStatus(updatedProduct.getStatus());
            existingProduct.setCreatedAt(updatedProduct.getCreatedAt());
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
