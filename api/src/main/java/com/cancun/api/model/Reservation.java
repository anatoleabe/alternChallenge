/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.api.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author anatoleabe
 */
@Data
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "quantity", nullable = false)
    private int quantity;
    
    @Column(name = "start_date", nullable = false)
    private long startDate;
    
    @Column(name = "end_date", nullable = false)
    private long endDate;
    
    @Column(name = "cancelled", nullable = false)
    private boolean cancelled;
    
    @Column(name = "creation", nullable = false)
    private long creation;
    
    @Column(name = "lastModified", nullable = false)
    private long lastModified;
    
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Room roomId;
    
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Customer customerId;

}
