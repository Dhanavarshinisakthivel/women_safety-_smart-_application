package com.womensafety.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Every SOS alert, once it is resolved (or dismissed), gets copied here
 * as a permanent record the user can look back through -
 * "Emergency Alert History" feature.
 */
@Entity
@Table(name = "alert_history")
public class AlertHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Long originalAlertId;

    private Double latitude;
    private Double longitude;

    @Column(length = 1000)
    private String message;

    private String finalStatus; // RESOLVED / FALSE_ALARM

    private LocalDateTime triggeredAt;
    private LocalDateTime archivedAt = LocalDateTime.now();


    // Default Constructor
    public AlertHistory() {
    }


    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    // Getter and Setter for user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // Getter and Setter for originalAlertId
    public Long getOriginalAlertId() {
        return originalAlertId;
    }

    public void setOriginalAlertId(Long originalAlertId) {
        this.originalAlertId = originalAlertId;
    }


    // Getter and Setter for latitude
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    // Getter and Setter for longitude
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    // Getter and Setter for message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    // Getter and Setter for finalStatus
    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }


    // Getter and Setter for triggeredAt
    public LocalDateTime getTriggeredAt() {
        return triggeredAt;
    }

    public void setTriggeredAt(LocalDateTime triggeredAt) {
        this.triggeredAt = triggeredAt;
    }


    // Getter and Setter for archivedAt
    public LocalDateTime getArchivedAt() {
        return archivedAt;
    }

    public void setArchivedAt(LocalDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }
}


//package com.womensafety.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDateTime;
//
///**
// * Every SOS alert, once it is resolved (or dismissed), gets copied here
// * as a permanent record the user can look back through -
// * "Emergency Alert History" feature.
// */
//@Entity
//@Table(name = "alert_history")
//@Data
//public class AlertHistory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    private Long originalAlertId;
//
//    private Double latitude;
//    private Double longitude;
//
//    @Column(length = 1000)
//    private String message;
//
//    private String finalStatus; // RESOLVED / FALSE_ALARM
//
//    private LocalDateTime triggeredAt;
//    private LocalDateTime archivedAt = LocalDateTime.now();
//}
