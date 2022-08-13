package com.hotel.hotel.dto;

public class PaymentRequest {

	//mapper to fetch details based on the booking id and send the details to the booking service
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
