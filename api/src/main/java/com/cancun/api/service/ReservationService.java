/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.api.service;

import com.cancun.api.model.AvailableRoom;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cancun.api.model.Reservation;
import com.cancun.api.model.ReservedRoom;
import com.cancun.api.repository.ReservationRepository;
import com.cancun.api.repository.RoomRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

/**
 *
 * @author anatoleabe
 */
@Data
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;

    public Optional<Reservation> getReservation(final Long id) {
        return reservationRepository.findById(id);
    }

    public Iterable<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public void deleteReservation(final Long id) {
        reservationRepository.deleteById(id);
    }

    public Reservation saveReservation(Reservation reservation) {
        Reservation savedReservation = reservationRepository.save(reservation);
        return savedReservation;
    }

    public List<AvailableRoom> availableRooms(Date start, Date end) {
        List<ReservedRoom> reservedRooms = reservationRepository.findReservedRooms(start, end);
        
        System.out.println("====== reservedRooms ======"+reservedRooms);

        final Map<Integer, Integer> reservedRoomMap = new HashMap<>();

        reservedRooms.forEach(reservedRoom -> reservedRoomMap.put(reservedRoom.getId().intValue(), reservedRoom.getReservedQuantity().intValue()));

        final List<AvailableRoom> availableRooms = roomRepository
                .findByQuantityGreaterThan(0)
                .stream()
                .map(room -> {
                    if (reservedRoomMap.containsKey(room.getId().intValue())) {
                        final Integer reservedQuantity = reservedRoomMap.get(room.getId().intValue());
                        final Integer availableQuantity = room.getQuantity() - reservedQuantity;
                        return new AvailableRoom(room, availableQuantity.longValue());
                    }

                    return new AvailableRoom(room);
                })
                .filter(availableRoomType -> availableRoomType.getAvailableQuantity() > 0)
                .collect(Collectors.toList());

        return availableRooms;
    }

}
