/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.repository;

import com.cancun.web.model.Reservation;
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
public class ReservationProxy {

    @Autowired
    private ApplicatonProperties props;

    /**
	 * Get all reservations
	 * @return An iterable of all reservations
	 */
	public Iterable<Reservation> getReservations() {

		String baseApiUrl = props.getApiUrl();
		String getReservationsUrl = baseApiUrl + "/reservations";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Reservation>> response = restTemplate.exchange(
				getReservationsUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Reservation>>() {}
			);
		
		log.debug("Get Reservations call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Get a reservation by the id
	 * @param id The id of the reservation
	 * @return The reservation which matches the id
	 */
	public Reservation getReservation(int id) {
		String baseApiUrl = props.getApiUrl();
		String getReservationUrl = baseApiUrl + "/reservation/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Reservation> response = restTemplate.exchange(
				getReservationUrl, 
				HttpMethod.GET, 
				null,
				Reservation.class
			);
		
		log.debug("Get Reservation call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Add a new reservation 
	 * @param e A new reservation (without an id)
	 * @return The reservation full filled (with an id)
	 */
	public Reservation createReservation(Reservation e) {
		String baseApiUrl = props.getApiUrl();
		String createReservationUrl = baseApiUrl + "/reservation";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Reservation> request = new HttpEntity<Reservation>(e);
		ResponseEntity<Reservation> response = restTemplate.exchange(
				createReservationUrl, 
				HttpMethod.POST, 
				request, 
				Reservation.class);
		
		log.debug("Create Reservation call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Update a reservation - using the PUT HTTP Method.
	 * @param e Existing reservation to update
	 */
	public Reservation updateReservation(Reservation e) {
		String baseApiUrl = props.getApiUrl();
		String updateReservationUrl = baseApiUrl + "/reservation/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Reservation> request = new HttpEntity<Reservation>(e);
		ResponseEntity<Reservation> response = restTemplate.exchange(
				updateReservationUrl, 
				HttpMethod.PUT, 
				request, 
				Reservation.class);
		
		log.debug("Update Reservation call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Delete a reservation using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The reservation to delete
	 */
	public void deleteReservation(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteReservationUrl = baseApiUrl + "/reservation/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteReservationUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Reservation call " + response.getStatusCode().toString());
	}

}
