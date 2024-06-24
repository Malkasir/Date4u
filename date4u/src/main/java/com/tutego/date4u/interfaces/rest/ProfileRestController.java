package com.tutego.date4u.interfaces.rest;

import com.tutego.date4u.core.profile.Profile;
import com.tutego.date4u.interfaces.DTO.ProfileDto;
import com.tutego.date4u.interfaces.mapper.ProfileMapper;
import com.tutego.date4u.repository.ProfileRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/profiles")
public class ProfileRestController {

    @Autowired
    private ProfileRepository profiles;

    private final ProfileMapper profileMapper = new ProfileMapper();

    @GetMapping
    public List<ProfileDto> profiles() {
        return profiles.findAll().stream()
                .map(profileMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto) {
        Profile profile = profileMapper.convertToEntity(profileDto);
        Profile savedProfile = profiles.save(profile);
        ProfileDto savedProfileDto = profileMapper.convertToDto(savedProfile);
        return new ResponseEntity<>(savedProfileDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a profile by its id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfileDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Profile not found",
                    content = @Content)
    })
    public ResponseEntity<?> get(@PathVariable long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().body("Invalid id");
        }

        return profiles.findById(id)
                .map(profile -> ResponseEntity.ok(profileMapper.convertToDto(profile)))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

}


