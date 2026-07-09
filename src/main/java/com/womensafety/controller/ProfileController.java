package com.womensafety.controller;

import com.womensafety.dto.ProfileUpdateRequest;
import com.womensafety.model.User;
import com.womensafety.service.CurrentUserService;
import com.womensafety.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final CurrentUserService currentUserService;

    public ProfileController(ProfileService profileService, CurrentUserService currentUserService) {
        this.profileService = profileService;
        this.currentUserService = currentUserService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMyProfile() {
        return ResponseEntity.ok(currentUserService.getCurrentUser());
    }

    @PutMapping("/me")
    public ResponseEntity<User> updateMyProfile(@RequestBody ProfileUpdateRequest request) {
        User updated = profileService.updateProfile(currentUserService.getCurrentUser(), request);
        return ResponseEntity.ok(updated);
    }
}
