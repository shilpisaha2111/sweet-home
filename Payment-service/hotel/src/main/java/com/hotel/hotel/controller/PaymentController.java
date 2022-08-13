package com.hotel.hotel.controller;


import com.hotel.hotel.dto.PaymentDTO;
import com.hotel.hotel.entities.TransactionDetailsEntity;
import com.hotel.hotel.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

  PaymentService paymentService;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  public PaymentController(PaymentService paymentService) {
    super();
    this.paymentService = paymentService;
  }

  /**
   * Method for creating Hotels
   * 127.0.0.1:8080/Hotel_app/v1/Hotels
   */

  @PostMapping(value="/payment/transaction", consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  //method to save payment detials in the database and retried the transaction id
  public ResponseEntity returnTransactionID(@RequestBody PaymentDTO paymentDTO){

    int responseTransaction = paymentService.makePayment(paymentDTO.getBookingId(),paymentDTO);
    return new ResponseEntity(responseTransaction, HttpStatus.CREATED);
  }

  @GetMapping(value="/payment/transaction/{transactionId}")
  //fetch the payment details corresponding to the transaction id sent in the http request
  public ResponseEntity getPaymentDetailsBasedOnId(@PathVariable(name="transactionId") int transactionId) throws Exception{
    TransactionDetailsEntity responseTransaction = paymentService.getPaymentById(transactionId);
    return new ResponseEntity(responseTransaction, HttpStatus.OK);
  }
}
