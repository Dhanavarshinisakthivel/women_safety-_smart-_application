//package com.womensafety.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDateTime;
//
///** Simple contact/feedback form submissions. */
//@Entity
//@Table(name = "feedback")
//@Data
//public class Feedback {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // Nullable - feedback can be submitted by a guest too
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    private String name;
//    private String email;
//
//    @Column(nullable = false, length = 2000)
//    private String message;
//
////    private Integer rating; // 1-5 stars, optional
//
//    private LocalDateTime submittedAt = LocalDateTime.now();
//}

package com.womensafety.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/** Simple contact/feedback form submissions. */
@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nullable - feedback can be submitted by a guest too
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String email;

    @Column(nullable = false, length = 2000)
    private String message;

    private Integer rating; // 1-5 stars, optional

    private LocalDateTime submittedAt = LocalDateTime.now();

    // Default Constructor
    public Feedback() {
    }

    // Parameterized Constructor
    public Feedback(Long id, User user, String name, String email,
                    String message, Integer rating, LocalDateTime submittedAt) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.email = email;
        this.message = message;
        this.rating = rating;
        this.submittedAt = submittedAt;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    // Optional: toString()

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", rating=" + rating +
                ", submittedAt=" + submittedAt +
                '}';
    }
}