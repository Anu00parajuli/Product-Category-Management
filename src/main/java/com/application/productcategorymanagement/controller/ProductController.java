package com.application.productcategorymanagement.controller;

import com.application.productcategorymanagement.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductController {
    @Operation(summary = "Get All Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class)))}),
            @ApiResponse(responseCode = "400", description = "Record not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{\"ErrorCode\":400,\"errorDescription\":\"Record Not Found\"}"))
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{\"ErrorCode\":500,\"errorDescription\":\"Internal Server Error\"}"))
                    }
            )
    })
  public List<Product> getAllProducts(Pageable pageable);

    @Operation(summary = "Get Product with ProductId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class)))}),
            @ApiResponse(responseCode = "400", description = "Record not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{\"ErrorCode\":400,\"errorDescription\":\"Record Not Found\"}"))
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{\"ErrorCode\":500,\"errorDescription\":\"Internal Server Error\"}"))
                    }
            )
    })
    public Optional<Product> getProductById(UUID productId);




}
