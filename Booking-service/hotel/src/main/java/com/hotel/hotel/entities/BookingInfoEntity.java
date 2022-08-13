package com.hotel.hotel.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

//entity class to save the data to the database

public class BookingInfoEntity {

    @Column(nullable = false)
    private LocalDateTime fromDate;

    @Column(nullable = false)
    private LocalDateTime toDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @Column(length = 50, nullable = false, unique = true)
    private String aadharNumber;

    @Column(length = 50, nullable = false)
    private String roomNumbers;

    @Column(length = 500, nullable = false)
    private int roomPrice;

    @Column(nullable = false)
    private int duration;

    @Column(length = 50, nullable = false)
    private int numOfRooms;

    @Column(length = 100, nullable = false)
    private int transactionId;

    @Column(length = 100, nullable = false)
    private LocalDateTime bookedOn;

    public String getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(String roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getBookedOn() {
        return bookedOn;
    }

    public void setBookedOn(LocalDateTime bookedOn) {
        this.bookedOn = bookedOn;
    }

    @Override
    public String toString() {
        return "BookingInfoEntity{" +
                "bookingId=" + bookingId +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", duration=" + duration +
                ", numOfRooms=" + numOfRooms +
                ", transactionId=" + transactionId +
                ", bookedOn=" + bookedOn +
                ", roomNumbers=" + roomNumbers +
                '}';
    }
}
