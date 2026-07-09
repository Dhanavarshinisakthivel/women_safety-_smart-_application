package com.womensafety.service;

import com.womensafety.model.Incident;
import com.womensafety.model.SosAlert;
import com.womensafety.model.User;
import com.womensafety.repository.IncidentRepository;
import com.womensafety.repository.SosAlertRepository;
import com.womensafety.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final SosAlertRepository sosAlertRepository;
    private final IncidentRepository incidentRepository;

    public AdminService(UserRepository userRepository, SosAlertRepository sosAlertRepository,
                         IncidentRepository incidentRepository) {
        this.userRepository = userRepository;
        this.sosAlertRepository = sosAlertRepository;
        this.incidentRepository = incidentRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<SosAlert> getActiveAlerts() {
        return sosAlertRepository.findByStatus(SosAlert.Status.ACTIVE);
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Map<String, Long> getDashboardStats() {
        return Map.of(
                "totalUsers", userRepository.count(),
                "totalAlerts", sosAlertRepository.count(),
                "activeAlerts", (long) sosAlertRepository.findByStatus(SosAlert.Status.ACTIVE).size(),
                "totalIncidents", incidentRepository.count()
        );
    }
}
