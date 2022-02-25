/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.api.model;

import com.cancun.api.utils.DateTimeConverter;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    @Convert(converter = DateTimeConverter.class)
    private Date startDate;
    
    @Column(name = "end_date", nullable = false)
    @Convert(converter = DateTimeConverter.class)
    private Date endDate;
    
    @Column(name = "cancelled", nullable = false)
    private boolean cancelled;
    
    @Basic(optional = false)
    @Column(name = "creation", nullable = false)
    @Convert(converter = DateTimeConverter.class)
    @CreatedDate
    private Date creation;
    
    @Basic(optional = false)
    @Convert(converter = DateTimeConverter.class)
    @LastModifiedDate
    @Column(name = "lastModified", nullable = false)
    private Date lastModified;
    
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Room roomId;
    
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Customer customerId;

}
