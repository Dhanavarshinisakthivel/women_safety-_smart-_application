package com.womensafety.dto;

public class ProfileUpdateRequest {

    private String fullName;
    private String phone;
    private String address;
    private String photoUrl;

    // Default Constructor
    public ProfileUpdateRequest() {
    }

    // Parameterized Constructor
    public ProfileUpdateRequest(String fullName, String phone,
                                String address, String photoUrl) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.photoUrl = photoUrl;
    }

    // Getters and Setters

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

    // Optional: toString()

    @Override
    public String toString() {
        return "ProfileUpdateRequest{" +
                "fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}

//package com.womensafety.dto;
//
//import lombok.Data;
//
//@Data
//public class ProfileUpdateRequest {
//    private String fullName;
//    private String phone;
//    private String address;
//    private String photoUrl;
//}
