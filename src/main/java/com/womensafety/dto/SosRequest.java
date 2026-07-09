package com.womensafety.dto;

public class SosRequest {

    private Double latitude;
    private Double longitude;
    private String customMessage; // optional - if blank, AI generates one

    // Default Constructor
    public SosRequest() {
    }

    // Parameterized Constructor
    public SosRequest(Double latitude, Double longitude, String customMessage) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.customMessage = customMessage;
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

    // Getter and Setter for customMessage
    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }
}


//package com.womensafety.dto;
//
//import lombok.Data;
//
//@Data
//public class SosRequest {
//    private Double latitude;
//    private Double longitude;
//    private String customMessage; // optional - if blank, AI generates one
//}
