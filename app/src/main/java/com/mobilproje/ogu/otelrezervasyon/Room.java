package com.mobilproje.ogu.otelrezervasyon;

import java.util.UUID;

public class Room {
    private long roomID;
    private String roomType;
    private int roomPrice;
    private String roomIntroduction;

    public Room(long roomID, String roomType, int roomPrice, String roomIntroduction) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomIntroduction = roomIntroduction;
    }

    public Room() {
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
}
