/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.api.model;

import com.cancun.api.utils.DateTimeConverter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "type", nullable = false, length = 32)
    private String type;
    
    @Column(name = "description", length = 256)
    private String description;
    
    @Column(name = "image", length = 256)
    private String image;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;
    
    @Basic(optional = false)
    @Column(name = "creation", nullable = false)
    @Convert(converter = DateTimeConverter.class)
    @CreatedDate
    private Date creation;
    
    @Column(name = "lastModified", nullable = false)
    @Basic(optional = false)
    @Convert(converter = DateTimeConverter.class)
    @LastModifiedDate
    private Date lastModified;
}
