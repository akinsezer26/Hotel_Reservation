package com.mobilproje.ogu.otelrezervasyon;

public class HotelAndRoom {
    private Long hotelID;
    private String hotelName;
    private String hotelIntroduction;
    private String hotelProvince;
    private String hotelDistrict;
    private long roomID;
    private String roomType;
    private int roomPrice;
    private String roomIntroduction;
    private int roomCount;
    public HotelAndRoom(Long hotelID, String hotelName, String hotelIntroduction, String hotelProvince, String hotelDistrict, long roomID, String roomType, int roomPrice, String roomIntroduction, int roomCount) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.hotelIntroduction = hotelIntroduction;
        this.hotelProvince = hotelProvince;
        this.hotelDistrict = hotelDistrict;
        this.roomID = roomID;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomIntroduction = roomIntroduction;
        this.roomCount = roomCount;
    }

    public HotelAndRoom() {
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

    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomIntroduction() {
        return roomIntroduction;
    }

    public void setRoomIntroduction(String roomIntroduction) {
        this.roomIntroduction = roomIntroduction;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }
}
