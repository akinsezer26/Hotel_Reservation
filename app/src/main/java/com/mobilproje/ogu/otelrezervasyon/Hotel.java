package com.mobilproje.ogu.otelrezervasyon;

import android.graphics.Bitmap;

import java.util.UUID;

public class Hotel {
    private Long hotelID;
    private String hotelName;
    private String hotelIntroduction;
    private String hotelProvince;
    private String hotelDistrict;
    private Bitmap profilePicture;
    public Hotel(Long hotelID, String hotelName, String hotelIntroduction, String hotelProvince, String hotelDistrict) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.hotelIntroduction = hotelIntroduction;
        this.hotelProvince=hotelProvince;
        this.hotelDistrict=hotelDistrict;
    }

    public Hotel() {

    }

    public Long getHotelID() {
        return hotelID;
    }

    public void setHotelID(Long hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelIntroduction() {
        return hotelIntroduction;
    }

    public void setHotelIntroduction(String hotelIntroduction) {
        this.hotelIntroduction = hotelIntroduction;
    }

    public String getHotelProvince() {
        return hotelProvince;
    }

    public void setHotelProvince(String hotelProvince) {
        this.hotelProvince = hotelProvince;
    }

    public String getHotelDistrict() {
        return hotelDistrict;
    }

    public void setHotelDistrict(String hotelDistrict) {
        this.hotelDistrict = hotelDistrict;
    }

    public Bitmap getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Bitmap profilePicture) {
        this.profilePicture = profilePicture;
    }
}
