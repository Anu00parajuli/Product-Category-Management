package com.application.productcategorymanagement.service;

import com.application.productcategorymanagement.entity.Product;
import com.application.productcategorymanagement.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        try {
            productRepository.save(product);
            log.info("Product {} saved in database successfully", product.getName());
        } catch (Exception e) {
            log.error("Failed to save Product {} in database" + product.getName(), e.getMessage(), e);
        }
        return product;
    }

    public void deleteProduct(UUID productId) {
        if (productRepository.existsById(productId)) {
            try {
                productRepository.deleteById(productId);
                log.info("Product with ProductId: {} deleted in database successfully", productId);
            } catch (Exception e) {
                log.error("Failed to delete Product with ProductId {} in database" + productId, e.getMessage(), e);
            }
        } else {
            log.info("Product with productId: {} not found", productId);
        }
    }

    public List<Product> findAllProducts(Pageable pageable){
        Page<Product> productsPage = productRepository.findAll(pageable);
        return productsPage.getContent();
    }

    public Optional<Product> findProductById(UUID productId){
        if (productRepository.existsById(productId)){
            return productRepository.findById(productId);
        }
        else {
            log.info("Product with productId: {} not found", productId);
            return null;
        }
    }
    public Optional<Product> updateProduct(UUID id, Product updatedProduct) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product existingProduct = productOpt.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStatus(updatedProduct.getStatus());
            existingProduct.setCreatedAt(updatedProduct.getCreatedAt());
            productRepository.save(existingProduct);
            return productOpt;
        } else {
            log.error("Product not found");
            return null;
        }

    }

}
