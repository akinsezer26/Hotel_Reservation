package com.mobilproje.ogu.otelrezervasyon;

import java.util.Date;

public class Reservation {
    private Long reservationID;
    private int reservationPrice;
    private Date reservationStartDate;
    private Date reservationEndDate;
    private Long consumerID;
    private Long roomID;

    public Reservation(Long reservationID, int reservationPrice, Date reservationStartDate, Date reservationEndDate, Long consumerID, Long roomID) {
        this.reservationID = reservationID;
        this.reservationPrice = reservationPrice;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.consumerID = consumerID;
        this.roomID = roomID;
    }

    public Reservation() {
    }

    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public int getReservationPrice() {
        return reservationPrice;
    }

    public void setReservationPrice(int reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

    public Date getReservationStartDate() {
        return reservationStartDate;
    }

    public void setReservationStartDate(Date reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
    }

    public Date getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(Date reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }

    public Long getConsumerID() {
        return consumerID;
    }

    public void setConsumerID(Long consumerID) {
        this.consumerID = consumerID;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }
}
