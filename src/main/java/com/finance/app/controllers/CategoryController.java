package com.finance.app.controllers;

import com.finance.app.model.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Category Controller", description = "Category API")
public interface CategoryController {
    @Operation(summary = "Get all categories", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of categories",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))})
    })
    List<CategoryDto> findAll();

    @Operation(summary = "Get category by ID", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    CategoryDto findById(Long id);

    @Operation(summary = "Get category by title", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    CategoryDto findByName(String title);

    @Operation(summary = "Create or update a category", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created or updated category",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))})
    })
    CategoryDto createOrUpdateCategory(CategoryDto categoryDto);

    @Operation(summary = "Delete category by ID", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    void deleteCategoryById(Long id);

    @Operation(summary = "Delete category by title", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    void deleteCategoryByName(String title);
}