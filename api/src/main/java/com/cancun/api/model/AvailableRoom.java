/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.api.model;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author anatoleabe
 */
@Data
public class AvailableRoom extends Room implements Serializable{
    
    private Long availableQuantity;

    public AvailableRoom(Room room, Long availableQuantity) {
        this.availableQuantity = availableQuantity;
        this.setId(room.getId());
        this.setType(room.getType());
        this.setDescription(room.getDescription());
        this.setCreation(room.getCreation());
        this.setLastModified(room.getLastModified());
        this.setQuantity(room.getQuantity());
    }

    public AvailableRoom(Room room) {
        this.availableQuantity = room.getQuantity().longValue();
        this.setId(room.getId());
        this.setType(room.getType());
        this.setDescription(room.getDescription());
        this.setCreation(room.getCreation());
        this.setLastModified(room.getLastModified());
        this.setQuantity(room.getQuantity());
    }
    
    
}
