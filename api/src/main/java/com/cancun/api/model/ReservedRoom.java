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
public class ReservedRoom implements Serializable {

    private Long id;

    private Long reservedQuantity;

    public ReservedRoom(Room room, Long reservedQuantity) {
        this.id = room.getId();
        this.reservedQuantity = reservedQuantity;
    }
}
