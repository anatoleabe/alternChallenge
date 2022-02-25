/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.service;

import com.cancun.web.model.AvailableRoom;
import com.cancun.web.model.Reservation;
import com.cancun.web.repository.ReservationProxy;
import com.cancun.web.repository.RoomProxy;
import java.util.Date;
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
    @Autowired
    private RoomProxy roomProxy;

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

        if (reservation.getId() == null) {
            // Create
            savedReservation = reservationProxy.createReservation(reservation);
        } else {
            //Update
            savedReservation = reservationProxy.updateReservation(reservation);
        }

        return savedReservation;
    }

    public Reservation cancelReservation(Reservation reservation) {
        Reservation savedReservation;

        reservation.setCancelled(true);
        savedReservation = reservationProxy.createReservation(reservation);

        return savedReservation;
    }

    public Iterable<AvailableRoom> checkAvailableRooms(Date start, Date end) {

        final Iterable<AvailableRoom> availableRooms = reservationProxy.checkAvailableRooms(start, end);

        return availableRooms;
    }

}
