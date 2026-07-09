package com.womensafety.dto;

public class IncidentRequest {

    private String title;
    private String description;
    private String photoUrl;
    private Double latitude;
    private Double longitude;

    // Default Constructor
    public IncidentRequest() {
    }

    // Parameterized Constructor
    public IncidentRequest(String title, String description, String photoUrl, Double latitude, Double longitude) {
        this.title = title;
        this.description = description;
        this.photoUrl = photoUrl;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for photoUrl
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
}


//package com.womensafety.dto;
//
//import lombok.Data;
//
//@Data
//public class IncidentRequest {
//    private String title;
//    private String description;
//    private String photoUrl;
//    private Double latitude;
//    private Double longitude;
//}
