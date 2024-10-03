package com.application.productcategorymanagement.entity;

import com.application.productcategorymanagement.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;
    @Column(name = "name", length = 255)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "status")
    private Status status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
