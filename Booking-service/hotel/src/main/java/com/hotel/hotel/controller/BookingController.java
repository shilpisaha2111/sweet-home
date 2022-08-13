package com.hotel.hotel.controller;


import com.hotel.hotel.dao.BookingDao;
import com.hotel.hotel.dto.BookingDTO;
import com.hotel.hotel.dto.BookingDTOResponse;
import com.hotel.hotel.dto.PaymentDTO;
import com.hotel.hotel.entities.BookingInfoEntity;
import com.hotel.hotel.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping(value = "")
public class BookingController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingDao bookingDao;

    /**
     * Method for creating Hotels
     * 127.0.0.1:8080/Hotel_app/v1/Hotels
     */


    @PostMapping(value = "/hotel/booking", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBookingEnquiry(@RequestBody BookingDTO bookingDTO) {

        //mapper for HotelDTO to HotelEntity to save the data to the database
        BookingInfoEntity newBookingEnquiry = modelMapper.map(bookingDTO, BookingInfoEntity.class);

        //explicitly setting the dat format in the entity class to retain its date format
        newBookingEnquiry.setFromDate(bookingDTO.getFromDate().atStartOfDay());
        newBookingEnquiry.setToDate(bookingDTO.getToDate().atStartOfDay());

        //calling the service method to implement the required fields setting values
        BookingInfoEntity savedHotelDetails = bookingService.saveBookingDetails(newBookingEnquiry);

        //mapper to get the value for the database to map to the dto BookingDTOResponse class
        BookingDTOResponse savedBookingDto = modelMapper.map(savedHotelDetails, BookingDTOResponse.class);

        //setting the value for date format to retain the formatting to display on the response
        savedBookingDto.setToDate(savedHotelDetails.getToDate().format(DateTimeFormatter.ISO_DATE_TIME));
        savedBookingDto.setFromDate(savedHotelDetails.getFromDate().format(DateTimeFormatter.ISO_DATE_TIME));
        savedBookingDto.setBookedOn(savedHotelDetails.getBookedOn().format(DateTimeFormatter.ISO_DATE_TIME));

        //returns the response dto and status as created
        return new ResponseEntity(savedBookingDto, HttpStatus.CREATED);
    }


    @PostMapping(value = "booking/{bookingId}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity confirmBookingDetails(@PathVariable(name = "bookingId") int bookingId, @RequestBody PaymentDTO paymentDTO) throws Exception {

        //throw error if the payment type is other than UPI or CARD
        if (!paymentDTO.getPaymentMode().equals("UPI") && !paymentDTO.getPaymentMode().equals("CARD"))
            return new ResponseEntity("Invalid mode of payment", HttpStatus.valueOf(400));

        //if booking id is not present in the dataabase
        Optional<BookingInfoEntity> bookingIdInfo = bookingDao.findById(bookingId);
        if (!bookingIdInfo.isPresent())
            return new ResponseEntity("Invalid Booking Id", HttpStatus.valueOf(400));

        //method is used to fetch the payment details from the payment service
        return new ResponseEntity(bookingService.bookHotel(bookingId, paymentDTO), HttpStatus.OK);
    }
}
