package com.womensafety.dto;

public class ContactRequest {

    private String name;
    private String phone;
    private String email;
    private String relationship;
    private Integer priority;

    // Default Constructor
    public ContactRequest() {
    }

    // Parameterized Constructor
    public ContactRequest(String name, String phone, String email,
                          String relationship, Integer priority) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.relationship = relationship;
        this.priority = priority;
    }

    // Getters and Setters

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
        return "ContactRequest{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", relationship='" + relationship + '\'' +
                ", priority=" + priority +
                '}';
    }
}

//package com.womensafety.dto;
//
//import lombok.Data;
//
//@Data
//public class ContactRequest {
//    private String name;
//    private String phone;
//    private String email;
//    private String relationship;
//    private Integer priority;
//}
