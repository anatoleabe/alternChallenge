/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.service;

import com.cancun.web.model.Reservation;
import com.cancun.web.repository.ReservationProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anatoleabe
 */

@Data
@Service
public class ReservationService {

    @Autowired
    private ReservationProxy reservationProxy;

    public Reservation getReservation(final int id) {
        return reservationProxy.getReservation(id);
    }

    public Iterable<Reservation> getReservations() {
        return reservationProxy.getReservations();
    }

    public void deleteReservation(final int id) {
        reservationProxy.deleteReservation(id);
    }

     public Reservation saveReservation(Reservation reservation) {
        Reservation savedReservation;

        if(reservation.getId() == null) {
            // Create
            savedReservation = reservationProxy.createReservation(reservation);
        } else {
            //Update
            savedReservation = reservationProxy.updateReservation(reservation);
        }
    
        return savedReservation;
    }

}