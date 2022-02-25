/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.repository;

import com.cancun.web.model.Reservation;
import com.cancun.web.model.Room;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;

@Slf4j
@Component
/**
 *
 * @author anatoleabe
 */
public class RoomProxy {

    @Autowired
    private ApplicatonProperties props;

    /**
     * Get all rooms
     *
     * @return An iterable of all rooms
     */
    public Iterable<Room> getRooms() {

        String baseApiUrl = props.getApiUrl();
        String getRoomsUrl = baseApiUrl + "/rooms";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Room>> response = restTemplate.exchange(
                getRoomsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Room>>() {
        }
        );

        log.debug("Get Rooms call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an room by the id
     *
     * @param id The id of the room
     * @return The room which matches the id
     */
    public Room getRoom(int id) {
        String baseApiUrl = props.getApiUrl();
        String getRoomUrl = baseApiUrl + "/room/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Room> response = restTemplate.exchange(
                getRoomUrl,
                HttpMethod.GET,
                null,
                Room.class
        );

        log.debug("Get Room call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new room
     *
     * @param e A new room (without an id)
     * @return The room full filled (with an id)
     */
    public Room createRoom(Room e) {
        String baseApiUrl = props.getApiUrl();
        String createRoomUrl = baseApiUrl + "/room";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Room> request = new HttpEntity<Room>(e);
        ResponseEntity<Room> response = restTemplate.exchange(
                createRoomUrl,
                HttpMethod.POST,
                request,
                Room.class);

        log.debug("Create Room call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an room - using the PUT HTTP Method.
     *
     * @param e Existing room to update
     */
    public Room updateRoom(Room e) {
        String baseApiUrl = props.getApiUrl();
        String updateRoomUrl = baseApiUrl + "/room/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Room> request = new HttpEntity<Room>(e);
        ResponseEntity<Room> response = restTemplate.exchange(
                updateRoomUrl,
                HttpMethod.PUT,
                request,
                Room.class);

        log.debug("Update Room call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Delete an room using exchange method of RestTemplate instead of delete
     * method in order to log the response status code.
     *
     * @param e The room to delete
     */
    public void deleteRoom(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteRoomUrl = baseApiUrl + "/room/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteRoomUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Room call " + response.getStatusCode().toString());
    }

    /**
     * Find all reservations using exchange method of RestTemplate
     *
     * @param start
     * @param end
     */
    public void findReservedRooms(Date start, Date end) {
        String baseApiUrl = props.getApiUrl();
        String getRoomUrl = baseApiUrl + "/room/check";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Reservation> response = restTemplate.exchange(
                getRoomUrl,
                HttpMethod.GET,
                null,
                Reservation.class
        );

        log.debug("findReservedRooms call " + response.getStatusCode().toString());
    }

}
