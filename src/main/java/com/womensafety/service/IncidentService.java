package com.womensafety.service;

import com.womensafety.dto.IncidentRequest;
import com.womensafety.model.Incident;
import com.womensafety.model.User;
import com.womensafety.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final AiFeatureService aiFeatureService;

    public IncidentService(IncidentRepository incidentRepository, AiFeatureService aiFeatureService) {
        this.incidentRepository = incidentRepository;
        this.aiFeatureService = aiFeatureService;
    }

    public Incident createIncident(User user, IncidentRequest request) {
        Incident incident = new Incident();
        incident.setUser(user);
        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setPhotoUrl(request.getPhotoUrl());
        incident.setLatitude(request.getLatitude());
        incident.setLongitude(request.getLongitude());

        // AI FEATURE 5: turn raw notes into a structured report
        incident.setAiGeneratedReport(aiFeatureService.generateIncidentReport(request.getDescription()));

        // AI FEATURE 10: predict urgency
        incident.setRiskLevel(aiFeatureService.predictRiskLevel(request.getDescription()));

        return incidentRepository.save(incident);
    }

    public List<Incident> getIncidentsForUser(User user) {
        return incidentRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }
}
