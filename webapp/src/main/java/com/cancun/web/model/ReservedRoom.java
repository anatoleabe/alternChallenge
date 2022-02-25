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
public class ReservedRoom implements Serializable {

    private Integer id;

    private Long reservedQuantity;
    
}
