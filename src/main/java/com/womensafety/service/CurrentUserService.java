package com.womensafety.service;

import com.womensafety.model.User;
import com.womensafety.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Every protected endpoint needs to know "who is making this request?".
 * Once JwtAuthFilter verifies the wristband (token), Spring Security
 * stores the user's email in its "SecurityContext". This helper just
 * fetches the full User object for whoever is currently logged in.
 */
@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Logged-in user not found in database."));
    }
}
