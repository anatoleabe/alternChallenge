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

import com.cancun.api.model.Room;
import com.cancun.api.service.RoomService;
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
public class RoomController {

    @Autowired
    private RoomService roomService;
    

    /**
     * Read - Get all rooms
     *
     * @return - An Iterable object of Room full filled
     */
    @GetMapping("/rooms")
    public Iterable<Room> getRooms() {
        return roomService.getRooms();
    }

    /**
     * Read - Get one room
     *
     * @param id The id of the room
     * @return An Room object full filled
     */
    @GetMapping("/room/{id}")
    public Room getRoom(@PathVariable("id") final Long id) {
        Optional<Room> room = roomService.getRoom(id);
        if (room.isPresent()) {
            return room.get();
        } else {
            return null;
        }
    }

    @PostMapping("/room")
    Room newRoom(@RequestBody Room newRoom) {
        return roomService.saveRoom(newRoom);
    }

    /**
     * Update - Update an existing room
     *
     * @param id - The id of the room to update
     * @param room - The room object updated
     * @return
     */
    @PutMapping("/room/{id}")
    public Room updateRoom(@PathVariable("id") final Long id, @RequestBody Room room) {
        Optional<Room> e = roomService.getRoom(id);
        if (e.isPresent()) {
            Room currentRoom = e.get();

            String type = room.getType();
            if (type != null) {
                currentRoom.setType(type);
            }
            String description = room.getDescription();
            if (description != null) {
                currentRoom.setDescription(description);;
            }
            String image = room.getImage();
            if (image != null) {
                currentRoom.setImage(image);
            }
            Integer quantity = room.getQuantity();
            if (quantity != null) {
                currentRoom.setQuantity(quantity);
            }
            BigDecimal price = room.getPrice();
            if (price != null) {
                currentRoom.setPrice(price);
            }
            roomService.saveRoom(currentRoom);
            return currentRoom;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an room
     *
     * @param id - The id of the room to delete
     */
    @DeleteMapping("/room/{id}")
    public void deleteRoom(@PathVariable("id") final Long id) {
        roomService.deleteRoom(id);
    }

}
