package com.womensafety.controller;

import com.womensafety.dto.SosRequest;
import com.womensafety.model.AlertHistory;
import com.womensafety.model.SosAlert;
import com.womensafety.service.CurrentUserService;
import com.womensafety.service.SosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sos")
public class SosController {

    private final SosService sosService;
    private final CurrentUserService currentUserService;

    public SosController(SosService sosService, CurrentUserService currentUserService) {
        this.sosService = sosService;
        this.currentUserService = currentUserService;
    }

    /** THE big red button. Frontend calls this when the user taps SOS. */
    @PostMapping("/trigger")
    public ResponseEntity<SosAlert> triggerSos(@RequestBody SosRequest request) {
        return ResponseEntity.ok(sosService.triggerSos(currentUserService.getCurrentUser(), request));
    }

    @PostMapping("/{id}/resolve")
    public ResponseEntity<SosAlert> resolveSos(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        boolean falseAlarm = Boolean.TRUE.equals(body.get("falseAlarm"));
        return ResponseEntity.ok(sosService.resolveSos(currentUserService.getCurrentUser(), id, falseAlarm));
    }

    @GetMapping
    public ResponseEntity<List<SosAlert>> myAlerts() {
        return ResponseEntity.ok(sosService.getAlertsForUser(currentUserService.getCurrentUser()));
    }

    @GetMapping("/history")
    public ResponseEntity<List<AlertHistory>> myHistory() {
        return ResponseEntity.ok(sosService.getHistoryForUser(currentUserService.getCurrentUser()));
    }
}
