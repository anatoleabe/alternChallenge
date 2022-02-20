/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.api.repository;

import com.cancun.api.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author anatoleabe
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

}
