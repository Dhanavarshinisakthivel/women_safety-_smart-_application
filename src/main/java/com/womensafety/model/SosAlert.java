package com.womensafety.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Created every time a user presses the "SOS" button.
 * This is the most important table in the whole app.
 */
@Entity
@Table(name = "sos_alerts")
public class SosAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double latitude;
    private Double longitude;

    // The AI-generated (or manually typed) message sent to contacts
    @Column(length = 1000)
    private String message;

    // ACTIVE = just triggered, RESOLVED = user marked themselves safe,
    // FALSE_ALARM = flagged by the AI Fake Alert Detection feature
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private LocalDateTime triggeredAt = LocalDateTime.now();
    private LocalDateTime resolvedAt;

    public enum Status {
        ACTIVE, RESOLVED, FALSE_ALARM
    }

    // Default Constructor
    public SosAlert() {
    }

    // Parameterized Constructor
    public SosAlert(Long id, User user, Double latitude, Double longitude,
                    String message, Status status,
                    LocalDateTime triggeredAt, LocalDateTime resolvedAt) {
        this.id = id;
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = message;
        this.status = status;
        this.triggeredAt = triggeredAt;
        this.resolvedAt = resolvedAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getTriggeredAt() {
        return triggeredAt;
    }

    public void setTriggeredAt(LocalDateTime triggeredAt) {
        this.triggeredAt = triggeredAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    // Optional: toString()

    @Override
    public String toString() {
        return "SosAlert{" +
                "id=" + id +
                ", user=" + user +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", triggeredAt=" + triggeredAt +
                ", resolvedAt=" + resolvedAt +
                '}';
    }
}

//package com.womensafety.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDateTime;
//
///**
// * Created every time a user presses the "SOS" button.
// * This is the most important table in the whole app.
// */
//@Entity
//@Table(name = "sos_alerts")
//@Data
//public class SosAlert {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    private Double latitude;
//    private Double longitude;
//
//    // The AI-generated (or manually typed) message sent to contacts
//    @Column(length = 1000)
//    private String message;
//
//    // ACTIVE = just triggered, RESOLVED = user marked themselves safe,
//    // FALSE_ALARM = flagged by the AI Fake Alert Detection feature
//    @Enumerated(EnumType.STRING)
//    private Status status = Status.ACTIVE;
//
//    private LocalDateTime triggeredAt = LocalDateTime.now();
//    private LocalDateTime resolvedAt;
//
//    public enum Status {
//        ACTIVE, RESOLVED, FALSE_ALARM
//    }
//}
