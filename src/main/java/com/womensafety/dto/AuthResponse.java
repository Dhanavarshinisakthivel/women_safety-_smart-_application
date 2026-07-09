package com.womensafety.dto;

public class AuthResponse {

    private String token;
    private String fullName;
    private String email;
    private String role;

    // Default Constructor
    public AuthResponse() {
    }

    // Parameterized Constructor
    public AuthResponse(String token, String fullName, String email, String role) {
        this.token = token;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    // Getter and Setter for token
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Getter and Setter for fullName
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


//package com.womensafety.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
///** What we send back after a successful login/register: the JWT wristband. */
//@Data
//@AllArgsConstructor
//public class AuthResponse {
//    private String token;
//    private String fullName;
//    private String email;
//    private String role;
//}
