package com.womensafety.model;

import jakarta.persistence.*;

/**
 * A trusted contact belonging to one user (e.g. mother, best friend, brother).
 * These are the people who get notified when SOS is triggered.
 */
@Entity
@Table(name = "emergency_contacts")
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which user does this contact belong to?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    private String email;

    private String relationship; // e.g. "Mother", "Friend", "Colleague"

    // Priority order: 1 = notify first, 2 = notify second, etc.
    // Used by the "AI Emergency Contact Recommendation" feature.
    private Integer priority = 1;

    // Default Constructor
    public EmergencyContact() {
    }

    // Parameterized Constructor
    public EmergencyContact(Long id, User user, String name, String phone,
                            String email, String relationship, Integer priority) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.relationship = relationship;
        this.priority = priority;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    // Optional: toString()

    @Override
    public String toString() {
        return "EmergencyContact{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", relationship='" + relationship + '\'' +
                ", priority=" + priority +
                '}';
    }
}


//package com.womensafety.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
///**
// * A trusted contact belonging to one user (e.g. mother, best friend, brother).
// * These are the people who get notified when SOS is triggered.
// */
//@Entity
//@Table(name = "emergency_contacts")
//@Data
//public class EmergencyContact {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // Which user does this contact belong to?
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private String phone;
//
//    private String email;
//
//    private String relationship; // e.g. "Mother", "Friend", "Colleague"
//
//    // Priority order: 1 = notify first, 2 = notify second, etc.
//    // Used by the "AI Emergency Contact Recommendation" feature.
//    private Integer priority = 1;
//}
