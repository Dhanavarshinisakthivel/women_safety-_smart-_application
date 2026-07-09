package com.womensafety.controller;

import com.womensafety.model.Incident;
import com.womensafety.model.SosAlert;
import com.womensafety.model.User;
import com.womensafety.service.AdminService;
import com.womensafety.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/** Every endpoint here requires ROLE_ADMIN (enforced in SecurityConfig). */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final FeedbackService feedbackService;

    public AdminController(AdminService adminService, FeedbackService feedbackService) {
        this.adminService = adminService;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        return ResponseEntity.ok(adminService.getDashboardStats());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<SosAlert>> getActiveAlerts() {
        return ResponseEntity.ok(adminService.getActiveAlerts());
    }

    @GetMapping("/incidents")
    public ResponseEntity<List<Incident>> getAllIncidents() {
        return ResponseEntity.ok(adminService.getAllIncidents());
    }

    @GetMapping("/feedback")
    public ResponseEntity<?> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }
}
