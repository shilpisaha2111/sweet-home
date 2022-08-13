package com.hotel.hotel.entities;

import com.hotel.hotel.dto.PaymentDTO;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class TransactionDetailsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int transactionId;

  @Column(length=50, nullable = false)
  private String paymentMode;

  @Column(length = 50, nullable = false)
  private int bookingId;

  @Column(length = 50)
  private String upiId;

  @Column(length = 50)
  private String cardNumber;

  private LocalDate fromDate;


  public LocalDate getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  public TransactionDetailsEntity(int bookingId, PaymentDTO paymentDetail) {
    this.bookingId = bookingId;
    this.paymentMode = paymentDetail.getPaymentMode();
    this.upiId = paymentDetail.getUpiId();
    this.cardNumber = paymentDetail.getCardNumber();
    this.fromDate = paymentDetail.getFromDate();
    this.transactionId = paymentDetail.getTransactionId();
  }

  public TransactionDetailsEntity() {

  }

  public int getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(int transactionId) {
    this.transactionId = transactionId;
  }

  public String getPaymentMode() {
    return paymentMode;
  }

  public void setPaymentMode(String paymentMode) {
    this.paymentMode = paymentMode;
  }

  public int getBookingId() {
    return bookingId;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  public String getUpiId() {
    return upiId;
  }

  public void setUpiId(String upiId) {
    this.upiId = upiId;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Override
  public String toString() {
    return "TransactionDetailsEntity{" +
            "transactionId=" + transactionId +
            ", paymentMode='" + paymentMode + '\'' +
            ", bookingId=" + bookingId +
            ", upiId='" + upiId + '\'' +
            ", cardNumber='" + cardNumber + '\'' +
            ", fromDate=" + fromDate +
            '}';
  }

}
