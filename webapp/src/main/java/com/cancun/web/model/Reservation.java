/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.model;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author anatoleabe
 */
@Data
public class Reservation implements Serializable {

    private Long id;
    
    private int quantity;
    
    private long startDate;
    
    private long endDate;
    
    private boolean cancelled;
    
    private long creation;
    
    private long lastModified;
    
    private Room roomId;
    
    private Customer customerId;

}
