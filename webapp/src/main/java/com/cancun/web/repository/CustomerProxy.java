/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.repository;

import com.cancun.web.model.Customer;
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
public class CustomerProxy {

    @Autowired
    private ApplicatonProperties props;

    /**
	 * Get all customers
	 * @return An iterable of all customers
	 */
	public Iterable<Customer> getCustomers() {

		String baseApiUrl = props.getApiUrl();
		String getCustomersUrl = baseApiUrl + "/customers";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Customer>> response = restTemplate.exchange(
				getCustomersUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Customer>>() {}
			);
		
		log.debug("Get Customers call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Get an customer by the id
	 * @param id The id of the customer
	 * @return The customer which matches the id
	 */
	public Customer getCustomer(int id) {
		String baseApiUrl = props.getApiUrl();
		String getCustomerUrl = baseApiUrl + "/customer/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Customer> response = restTemplate.exchange(
				getCustomerUrl, 
				HttpMethod.GET, 
				null,
				Customer.class
			);
		
		log.debug("Get Customer call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Add a new customer 
	 * @param e A new customer (without an id)
	 * @return The customer full filled (with an id)
	 */
	public Customer createCustomer(Customer e) {
		String baseApiUrl = props.getApiUrl();
		String createCustomerUrl = baseApiUrl + "/customer";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Customer> request = new HttpEntity<Customer>(e);
		ResponseEntity<Customer> response = restTemplate.exchange(
				createCustomerUrl, 
				HttpMethod.POST, 
				request, 
				Customer.class);
		
		log.debug("Create Customer call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Update an customer - using the PUT HTTP Method.
	 * @param e Existing customer to update
	 */
	public Customer updateCustomer(Customer e) {
		String baseApiUrl = props.getApiUrl();
		String updateCustomerUrl = baseApiUrl + "/customer/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Customer> request = new HttpEntity<Customer>(e);
		ResponseEntity<Customer> response = restTemplate.exchange(
				updateCustomerUrl, 
				HttpMethod.PUT, 
				request, 
				Customer.class);
		
		log.debug("Update Customer call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Delete an customer using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The customer to delete
	 */
	public void deleteCustomer(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteCustomerUrl = baseApiUrl + "/customer/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteCustomerUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Customer call " + response.getStatusCode().toString());
	}

}
