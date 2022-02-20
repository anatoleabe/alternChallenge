/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.repository;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author anatoleabe
 */
@Configuration
@ConfigurationProperties(prefix = "com.cancun.web")
@Data
public class ApplicatonProperties {

    private String apiUrl;

}