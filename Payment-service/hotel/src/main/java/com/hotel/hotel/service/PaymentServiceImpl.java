package com.hotel.hotel.service;

import com.hotel.hotel.dao.PaymentDao;
import com.hotel.hotel.dto.PaymentDTO;
import com.hotel.hotel.entities.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentServiceImpl implements PaymentService{


  PaymentDao paymentDao;

  @Autowired
  public PaymentServiceImpl(PaymentDao paymentDao) {
    super();
    this.paymentDao = paymentDao;
  }

  //method to save payment detials in the database and retried the transaction id
  public int makePayment(int bookingId, PaymentDTO paymentInfo) {
    TransactionDetailsEntity paymentDetailsEntity = new TransactionDetailsEntity(bookingId, paymentInfo);

    return paymentDao.save(paymentDetailsEntity).getTransactionId();
  }

  //fetch the payment details corresponding to the transaction id sent in the http request
  public TransactionDetailsEntity getPaymentById(int transactionId) throws Exception {
    Optional<TransactionDetailsEntity> transactionIdPresent =  this.paymentDao.findById(transactionId);

    if(transactionIdPresent.isPresent()) {
      return transactionIdPresent.get();
    }else {
      throw new Exception("Transaction Id Not Found");
    }
  }

}
