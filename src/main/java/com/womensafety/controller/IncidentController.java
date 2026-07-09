package com.womensafety.controller;

import com.womensafety.dto.IncidentRequest;
import com.womensafety.model.Incident;
import com.womensafety.service.CurrentUserService;
import com.womensafety.service.IncidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;
    private final CurrentUserService currentUserService;

    public IncidentController(IncidentService incidentService, CurrentUserService currentUserService) {
        this.incidentService = incidentService;
        this.currentUserService = currentUserService;
    }

    @PostMapping
    public ResponseEntity<Incident> reportIncident(@RequestBody IncidentRequest request) {
        return ResponseEntity.ok(incidentService.createIncident(currentUserService.getCurrentUser(), request));
    }

    @GetMapping
    public ResponseEntity<List<Incident>> myIncidents() {
        return ResponseEntity.ok(incidentService.getIncidentsForUser(currentUserService.getCurrentUser()));
    }
}
