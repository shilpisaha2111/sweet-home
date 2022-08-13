package com.hotel.hotel.dao;

import com.hotel.hotel.entities.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * This layer will be used to talk to the databases
 */
//mapper from entity class to dto
public interface BookingDao extends JpaRepository<BookingInfoEntity, Integer> {

}
