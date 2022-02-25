/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.api.repository;

import com.cancun.api.model.Reservation;
import com.cancun.api.model.ReservedRoom;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anatoleabe
 */
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query(value = "SELECT new com.cancun.api.model.ReservedRoom(r.roomId, SUM(r.quantity)) "
            + "FROM Reservation r WHERE "
            + "r.cancelled = FALSE AND "
            + "((?1 BETWEEN r.startDate AND r.endDate) OR "
            + "(?2 BETWEEN r.startDate AND r.endDate) OR "
            + "(?1 <= r.startDate AND ?2 >= r.endDate)) "
            + "GROUP BY r.roomId")
    List<ReservedRoom> findReservedRooms(Date start, Date end);
}
