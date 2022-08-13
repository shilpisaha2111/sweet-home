package com.hotel.hotel.service;

import com.hotel.hotel.dto.PaymentDTO;
import com.hotel.hotel.entities.BookingInfoEntity;


public interface BookingService {
    public BookingInfoEntity saveBookingDetails(BookingInfoEntity bookingInfo);

    public BookingInfoEntity getBookingDetails(int id);

    public BookingInfoEntity bookHotel(int bookingId, PaymentDTO paymentDTO) throws Exception;

}
