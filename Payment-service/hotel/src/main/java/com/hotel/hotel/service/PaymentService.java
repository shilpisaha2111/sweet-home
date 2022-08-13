package com.hotel.hotel.service;

import com.hotel.hotel.dto.PaymentDTO;
import com.hotel.hotel.entities.TransactionDetailsEntity;


public interface PaymentService {
    public int makePayment(int bookingId, PaymentDTO paymentInfo);

    public TransactionDetailsEntity getPaymentById(int paymentId) throws Exception;
}
