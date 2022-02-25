/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author anatoleabe
 */
@Data
public class Reservation implements Serializable {

    private Long id;
    
    private int quantity;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    
    private boolean cancelled;
    
    private Date creation;
    
    private Date lastModified;
    
    private Room roomId;
    
    private Customer customerId;

}
