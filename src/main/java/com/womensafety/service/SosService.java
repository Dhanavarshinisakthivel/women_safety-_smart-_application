package com.womensafety.service;

import com.womensafety.dto.SosRequest;
import com.womensafety.model.AlertHistory;
import com.womensafety.model.SosAlert;
import com.womensafety.model.User;
import com.womensafety.repository.AlertHistoryRepository;
import com.womensafety.repository.SosAlertRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class SosService {

    private final SosAlertRepository sosAlertRepository;
    private final AlertHistoryRepository alertHistoryRepository;
    private final AiFeatureService aiFeatureService;
    private final NotificationService notificationService;

    public SosService(SosAlertRepository sosAlertRepository,
                       AlertHistoryRepository alertHistoryRepository,
                       AiFeatureService aiFeatureService,
                       NotificationService notificationService) {
        this.sosAlertRepository = sosAlertRepository;
        this.alertHistoryRepository = alertHistoryRepository;
        this.aiFeatureService = aiFeatureService;
        this.notificationService = notificationService;
    }

    /**
     * Triggers a new SOS alert.
     * Steps: (1) simple duplicate-alert check (a basic version of the
     * "AI Fake Alert Detection" feature - full AI version comes in Phase 2),
     * (2) generate an AI message, (3) save the alert, (4) notify contacts.
     */
    public SosAlert triggerSos(User user, SosRequest request) {

        // --- Basic fake/duplicate alert guard ---
        // If this user triggered an alert in the last 60 seconds, treat this
        // as an accidental double-tap rather than creating a second alert.
        List<SosAlert> recent = sosAlertRepository.findByUserOrderByTriggeredAtDesc(user);
        if (!recent.isEmpty()) {
            SosAlert last = recent.get(0);
            long secondsSinceLast = ChronoUnit.SECONDS.between(last.getTriggeredAt(), LocalDateTime.now());
            if (last.getStatus() == SosAlert.Status.ACTIVE && secondsSinceLast < 60) {
                return last; // return the existing active alert instead of duplicating
            }
        }

        String message = (request.getCustomMessage() != null && !request.getCustomMessage().isBlank())
                ? request.getCustomMessage()
                : aiFeatureService.generateSosMessage(user, request.getLatitude(), request.getLongitude());

        SosAlert alert = new SosAlert();
        alert.setUser(user);
        alert.setLatitude(request.getLatitude());
        alert.setLongitude(request.getLongitude());
        alert.setMessage(message);
        alert.setStatus(SosAlert.Status.ACTIVE);

        SosAlert saved = sosAlertRepository.save(alert);

        notificationService.notifyContactsOfSos(user, saved);

        return saved;
    }

    public SosAlert resolveSos(User user, Long alertId, boolean falseAlarm) {
        SosAlert alert = sosAlertRepository.findById(alertId)
                .orElseThrow(() -> new IllegalArgumentException("Alert not found."));

        if (!alert.getUser().getId().equals(user.getId())) {
            throw new SecurityException("You do not have permission to modify this alert.");
        }

        alert.setStatus(falseAlarm ? SosAlert.Status.FALSE_ALARM : SosAlert.Status.RESOLVED);
        alert.setResolvedAt(LocalDateTime.now());
        SosAlert saved = sosAlertRepository.save(alert);

        // Archive into permanent history log
        AlertHistory history = new AlertHistory();
        history.setUser(user);
        history.setOriginalAlertId(alert.getId());
        history.setLatitude(alert.getLatitude());
        history.setLongitude(alert.getLongitude());
        history.setMessage(alert.getMessage());
        history.setFinalStatus(alert.getStatus().name());
        history.setTriggeredAt(alert.getTriggeredAt());
        alertHistoryRepository.save(history);

        return saved;
    }

    public List<SosAlert> getAlertsForUser(User user) {
        return sosAlertRepository.findByUserOrderByTriggeredAtDesc(user);
    }

    public List<AlertHistory> getHistoryForUser(User user) {
        return alertHistoryRepository.findByUserOrderByArchivedAtDesc(user);
    }
}
