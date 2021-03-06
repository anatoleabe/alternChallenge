/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author anatoleabe
 */
@Data
public class Customer implements Serializable {

    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String phone;
    
    private Date creation;
    
    private Date lastModified;
}
