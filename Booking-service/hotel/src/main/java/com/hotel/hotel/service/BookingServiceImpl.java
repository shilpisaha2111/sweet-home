package com.hotel.hotel.service;

import com.hotel.hotel.dao.BookingDao;
import com.hotel.hotel.dto.PaymentDTO;
import com.hotel.hotel.entities.BookingInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;


@Service
public class BookingServiceImpl implements BookingService {


    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private RestTemplate restTemplate;

    //method to save booking details to the database
    @Override
    public BookingInfoEntity saveBookingDetails(BookingInfoEntity bookingInfo) {

        int numOfRooms = bookingInfo.getNumOfRooms();

        Period period = Period.between(bookingInfo.getFromDate().toLocalDate(), bookingInfo.getToDate().toLocalDate());
        int dateCount = period.getDays();
        int roomPrice = 1000 * numOfRooms * (dateCount);
        bookingInfo.setRoomPrice(roomPrice);

        //set  current date
        LocalDateTime localDate = LocalDateTime.now();
        bookingInfo.setBookedOn(localDate);


        //list of room number generated
        ArrayList<String> roomNumbers = getRandomNumbers(numOfRooms);
        bookingInfo.setRoomNumbers(roomNumbers.toString());
        return bookingDao.save(bookingInfo);
    }

    //method to fetch booking details from the database
    @Override
    public BookingInfoEntity getBookingDetails(int id) {
        return bookingDao.findById(id).get();
    }

    public static ArrayList<String> getRandomNumbers(int count) {
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }

    //method to fetch transaction id from the paytem service and saving the booking details to the database
    @Override
    public BookingInfoEntity bookHotel(int bookingId, PaymentDTO paymentDTO) throws Exception {

        String paymentUrl = "http://payment-service/payment/transaction";

        Optional<BookingInfoEntity> bookingIdInfo = bookingDao.findById(bookingId);
       // if (bookingIdInfo.isPresent()) {
            Map<String, String> bookingUriMap = new HashMap<>();
            bookingUriMap.put("bookingId", String.valueOf(bookingId));
            BookingInfoEntity bookingInfo = bookingIdInfo.get();
            int transactionId = restTemplate.postForObject(paymentUrl, paymentDTO, int.class);
            bookingInfo.setTransactionId(transactionId);
            bookingDao.save(bookingInfo);
            return bookingInfo;
        /*} else {
            throw new Exception("Invalid Booking Id ");
        }*/
    }

}
