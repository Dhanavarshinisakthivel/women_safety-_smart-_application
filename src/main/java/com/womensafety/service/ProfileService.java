package com.womensafety.service;

import com.womensafety.dto.ProfileUpdateRequest;
import com.womensafety.model.User;
import com.womensafety.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateProfile(User currentUser, ProfileUpdateRequest request) {
        if (request.getFullName() != null && !request.getFullName().isBlank()) {
            currentUser.setFullName(request.getFullName());
        }
        if (request.getPhone() != null) {
            currentUser.setPhone(request.getPhone());
        }
        if (request.getAddress() != null) {
            currentUser.setAddress(request.getAddress());
        }
        if (request.getPhotoUrl() != null) {
            currentUser.setPhotoUrl(request.getPhotoUrl());
        }
        return userRepository.save(currentUser);
    }
}
