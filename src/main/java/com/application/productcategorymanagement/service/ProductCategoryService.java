package com.application.productcategorymanagement.service;

import com.application.productcategorymanagement.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }
}
