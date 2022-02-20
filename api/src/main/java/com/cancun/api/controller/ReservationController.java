/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.api.controller;

/**
 *
 * @author anatoleabe
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cancun.api.model.Reservation;
import com.cancun.api.service.ReservationService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * Read - Get all reservations
     *
     * @return - An Iterable object of Reservation full filled
     */
    @GetMapping("/reservations")
    public Iterable<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    /**
     * Read - Get one reservation
     *
     * @param id The id of the reservation
     * @return An Reservation object full filled
     */
    @GetMapping("/reservation/{id}")
    public Reservation getReservation(@PathVariable("id") final Long id) {
        Optional<Reservation> reservation = reservationService.getReservation(id);
        if (reservation.isPresent()) {
            return reservation.get();
        } else {
            return null;
        }
    }

    @PostMapping("/reservations")
    Reservation newReservation(@RequestBody Reservation newReservation) {
        return reservationService.saveReservation(newReservation);
    }

    /**
     * Update - Update an existing reservation
     *
     * @param id - The id of the reservation to update
     * @param reservation - The reservation object updated
     * @return
     */
    @PutMapping("/reservation/{id}")
    public Reservation updateReservation(@PathVariable("id") final Long id, @RequestBody Reservation reservation) {
        Optional<Reservation> e = reservationService.getReservation(id);
        if (e.isPresent()) {
            Reservation currentReservation = e.get();

            Integer quantity = reservation.getQuantity();
            if (quantity != null) {
                currentReservation.setQuantity(quantity);
            }
            Long startDate = reservation.getStartDate();
            if (startDate != null) {
                currentReservation.setStartDate(startDate);
            }
            Long endDate = reservation.getEndDate();
            if (endDate != null) {
                currentReservation.setEndDate(endDate);
            }
            Boolean cancelled = reservation.isCancelled();
            if (cancelled != null) {
                currentReservation.setCancelled(cancelled);
            }
            
            currentReservation.setLastModified(new Date().toInstant().getEpochSecond());
            reservationService.saveReservation(currentReservation);
            return currentReservation;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an reservation
     *
     * @param id - The id of the reservation to delete
     */
    @DeleteMapping("/reservation/{id}")
    public void deleteReservation(@PathVariable("id") final Long id) {
        reservationService.deleteReservation(id);
    }

}
