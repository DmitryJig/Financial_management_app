package com.finance.app.controllers;

import com.finance.app.model.dto.ProfileDto;
import com.finance.app.model.dto.ProfileReq;
import com.finance.app.model.dto.ProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Profile Controller", description = "Profile API")
public interface ProfileController {
    @Operation(summary = "Get profile data", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile name, balance id, profile id and user id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProfileDto.class))}),
            @ApiResponse(responseCode = "400", description = "Profile not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "UserId and request doesn't match", content = @Content)})
    ProfileDto getProfile(Long userId, Long profileId);

    @Operation(summary = "Get list of profiles", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of profiles by userId",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponse.class))}),
            @ApiResponse(responseCode = "403", description = "UserId and request doesn't match", content = @Content)})
    ProfileResponse getProfiles(Long userId);

    @Operation(summary = "Create user profile", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create profile",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProfileDto.class))}),
            @ApiResponse(responseCode = "403", description = "UserId and request doesn't match", content = @Content)})
    ProfileDto createProfile(ProfileReq req, Long userId);

    @Operation(summary = "Delete user profile", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete profile",
                    content = @Content(mediaType = "application/json", schema = @Schema)),
            @ApiResponse(responseCode = "403", description = "UserId and request doesn't match", content = @Content)})
    void deleteProfile(Long profileId, Long userId);

}
