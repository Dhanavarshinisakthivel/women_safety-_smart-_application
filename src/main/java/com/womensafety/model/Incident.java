package com.womensafety.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * A logged incident report (not necessarily an emergency in progress -
 * could be reported after the fact, e.g. "someone followed me yesterday").
 */
@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(length = 3000)
    private String description; // raw notes typed by the user

    @Column(length = 3000)
    private String aiGeneratedReport; // structured version made by Gemini

    private String photoUrl;

    private Double latitude;
    private Double longitude;

    // Filled in by the "AI Risk Level Prediction" feature: LOW / MEDIUM / HIGH
    private String riskLevel;

    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status {
        OPEN, UNDER_REVIEW, CLOSED
    }

    // Default Constructor
    public Incident() {
    }

    // Parameterized Constructor
    public Incident(Long id, User user, String title, String description,
                    String aiGeneratedReport, String photoUrl,
                    Double latitude, Double longitude,
                    String riskLevel, Status status,
                    LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.aiGeneratedReport = aiGeneratedReport;
        this.photoUrl = photoUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.riskLevel = riskLevel;
        this.status = status;
        this.createdAt = createdAt;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAiGeneratedReport() {
        return aiGeneratedReport;
    }

    public void setAiGeneratedReport(String aiGeneratedReport) {
        this.aiGeneratedReport = aiGeneratedReport;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Optional: toString()

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", aiGeneratedReport='" + aiGeneratedReport + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", riskLevel='" + riskLevel + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
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
// * A logged incident report (not necessarily an emergency in progress -
// * could be reported after the fact, e.g. "someone followed me yesterday").
// */
//@Entity
//@Table(name = "incidents")
//@Data
//public class Incident {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @Column(nullable = false)
//    private String title;
//
//    @Column(length = 3000)
//    private String description; // raw notes typed by the user
//
//    @Column(length = 3000)
//    private String aiGeneratedReport; // structured version made by Gemini
//
//    private String photoUrl;
//
//    private Double latitude;
//    private Double longitude;
//
//    // Filled in by the "AI Risk Level Prediction" feature: LOW / MEDIUM / HIGH
//    private String riskLevel;
//
//    @Enumerated(EnumType.STRING)
//    private Status status = Status.OPEN;
//
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    public enum Status {
//        OPEN, UNDER_REVIEW, CLOSED
//    }
//}
