/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.service;

import com.cancun.web.model.Room;
import com.cancun.web.repository.RoomProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anatoleabe
 */

@Data
@Service
public class RoomService {

    @Autowired
    private RoomProxy roomProxy;

    public Room getRoom(final int id) {
        return roomProxy.getRoom(id);
    }

    public Iterable<Room> getRooms() {
        return roomProxy.getRooms();
    }

    public void deleteRoom(final int id) {
        roomProxy.deleteRoom(id);
    }

     public Room saveRoom(Room room) {
        Room savedRoom;

        if(room.getId() == null) {
            // Create
            savedRoom = roomProxy.createRoom(room);
        } else {
            //Update
            savedRoom = roomProxy.updateRoom(room);
        }
    
        return savedRoom;
    }

}