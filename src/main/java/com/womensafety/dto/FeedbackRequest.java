package com.womensafety.dto;

public class FeedbackRequest {

    private String name;
    private String email;
    private String message;
    private Integer rating;

    // Default Constructor
    public FeedbackRequest() {
    }

    // Parameterized Constructor
    public FeedbackRequest(String name, String email, String message, Integer rating) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.rating = rating;
    }

    // Getters and Setters

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

    // Optional: toString()

    @Override
    public String toString() {
        return "FeedbackRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", rating=" + rating +
                '}';
    }
}






















//package com.womensafety.dto;
//
//import lombok.Data;
//
//@Data
//public class FeedbackRequest {
//    private String name;
//    private String email;
//    private String message;
//    private Integer rating;
//}
