/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author anatoleabe
 */
@Data
public class Room implements Serializable {

    private Long id;

    private String type;
    
    private String description;
    
    private String image;
    
    private Integer quantity;
    
    private BigDecimal price;

    private Date creation;

    private Date lastModified;
    
    
}
