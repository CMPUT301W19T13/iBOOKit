package com.example.ibookit.Model;

/**
 * @author zijun wu
 *
 * @version 1.0
 */
public class GeoLocation {

    private Double latitude;
    private Double longitude;

    /**
     * Constructor
     *
     * @param latitude
     * @param longitude
     */
    public GeoLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * default Constructor
     */
    public GeoLocation(){}

    /**
     * getter and setter
     */
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
