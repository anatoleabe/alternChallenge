/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cancun.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cancun.api.model.Room;
import com.cancun.api.repository.RoomRepository;

import lombok.Data;

/**
 *
 * @author anatoleabe
 */
@Data
@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Optional<Room> getRoom(final Long id) {
        return roomRepository.findById(id);
    }

    public Iterable<Room> getRooms() {
        return roomRepository.findAll();
    }

    public void deleteRoom(final Long id) {
        roomRepository.deleteById(id);
    }

    public Room saveRoom(Room room) {
        Room savedRoom = roomRepository.save(room);
        return savedRoom;
    }

}