package com.hotel.hotel.dto;

//save paytem details dto to the searched booking id
public class PaymentRequest {

    private int bookingId;
    private PaymentDTO paymentDTO;

    public PaymentRequest() {
    }

    public PaymentRequest(int bookingId, PaymentDTO paymentDTO) {
        this.bookingId = bookingId;
        this.paymentDTO = paymentDTO;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }


}
