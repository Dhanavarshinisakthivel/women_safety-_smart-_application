//package com.womensafety.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDateTime;
//
///**
// * MODEL / ENTITY LAYER
// * ---------------------
// * An "Entity" is a Java class that mirrors one table in the database.
// * Each object of this class = one row in the "users" table.
// * Spring automatically converts this class into SQL for us (that's the
// * "ORM" - Object Relational Mapping - magic of @Entity).
// */
//@Entity
//@Table(name = "users")
//@Data // Lombok: auto-generates getters, setters, toString, equals, hashCode
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    @Column(nullable = false)
//    private String password; // stored as a BCrypt hash, never plain text
//
//    @Column(nullable = false)
//    private String fullName;
//
//    private String phone;
//
//    private String address;
//
//    private String photoUrl;
//
//    // ROLE decides admin vs normal user (this doubles as our "admin table"
//    // in a beginner-friendly way - see schema.sql comments for details)
//    @Enumerated(EnumType.STRING)
//    private Role role = Role.USER;
//
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    public enum Role {
//        USER, ADMIN
//    }
//}


package com.womensafety.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * MODEL / ENTITY LAYER
 * ---------------------
 * An "Entity" is a Java class that mirrors one table in the database.
 * Each object of this class = one row in the "users" table.
 * Spring automatically converts this class into SQL for us (that's the
 * "ORM" - Object Relational Mapping - magic of @Entity).
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // stored as a BCrypt hash, never plain text

    @Column(nullable = false)
    private String fullName;

    private String phone;

    private String address;

    private String photoUrl;

    // ROLE decides admin vs normal user
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Role {
        USER, ADMIN
    }

    // Default Constructor
    public User() {
    }

    // Parameterized Constructor
    public User(Long id, String email, String password, String fullName,
                String phone, String address, String photoUrl,
                Role role, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.photoUrl = photoUrl;
        this.role = role;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                '}';
    }
}