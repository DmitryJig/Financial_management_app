package com.finance.app.controllers;

import com.finance.app.model.dto.RegUserDto;
import com.finance.app.model.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{id}")
@Tag(name = "User Controller", description = "User API")
public interface UserController {
    @Operation(summary = "Get all users", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))})
    })
    List<UserDto> findAll(Long id);

    @Operation(summary = "Get user by ID", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    UserDto findById(Long id);

    @Operation(summary = "Create user", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created user",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))})
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    UserDto createUser(RegUserDto regUserDto);

    @Operation(summary = "Delete user by ID", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping
    void deleteUserById(Long id);
}