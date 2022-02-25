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
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "firstName", nullable = false, length = 64)
    private String firstName;
    
    @Column(name = "lastName", nullable = false, length = 64)
    private String lastName;
    
    @Column(name = "email", nullable = false, length = 64)
    private String email;
    
    @Column(name = "phone", nullable = false, length = 64)
    private String phone;
    
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
}