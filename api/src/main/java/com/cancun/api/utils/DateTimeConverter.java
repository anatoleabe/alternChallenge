/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.api.utils;

/**
 *
 * @author anatoleabe
 */

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.util.Date;

@Converter
public class DateTimeConverter implements AttributeConverter<Date, Long> {

    @Override
    public Long convertToDatabaseColumn(Date attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toInstant().toEpochMilli();
    }

    @Override
    public Date convertToEntityAttribute(Long dbData) {
        if (dbData == null) {
            return null;
        }
        Instant now = Instant.ofEpochMilli(dbData);
        return new Date(now.toEpochMilli());
    }
}
