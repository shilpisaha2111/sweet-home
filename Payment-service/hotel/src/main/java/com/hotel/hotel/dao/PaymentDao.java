package com.hotel.hotel.dao;

import com.hotel.hotel.entities.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * This layer will be used to talk to the databases
 */
//mapper from entity class to dto
public interface PaymentDao extends JpaRepository<TransactionDetailsEntity, Integer> {

}
