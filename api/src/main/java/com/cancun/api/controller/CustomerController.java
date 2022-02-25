/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.api.controller;

/**
 *
 * @author anatoleabe
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cancun.api.model.Customer;
import com.cancun.api.service.CustomerService;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Read - Get all customers
     *
     * @return - An Iterable object of Customer full filled
     */
    @GetMapping("/customers")
    public Iterable<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    /**
     * Read - Get one customer
     *
     * @param id The id of the customer
     * @return An Customer object full filled
     */
    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") final Long id) {
        Optional<Customer> customer = customerService.getCustomer(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            return null;
        }
    }

    @PostMapping("/customer")
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerService.saveCustomer(newCustomer);
    }

    /**
     * Update - Update an existing customer
     *
     * @param id - The id of the customer to update
     * @param customer - The customer object updated
     * @return
     */
    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@PathVariable("id") final Long id, @RequestBody Customer customer) {
        Optional<Customer> e = customerService.getCustomer(id);
        if (e.isPresent()) {
            Customer currentCustomer = e.get();
            String firstName = customer.getFirstName();
            if (firstName != null) {
                currentCustomer.setFirstName(firstName);
            }
            String lastname = customer.getFirstName();
            if (lastname != null) {
                currentCustomer.setLastName(lastname);
            }
            String email = customer.getEmail();
            if (email != null) {
                currentCustomer.setEmail(email);
            }
            String tel = customer.getPhone();
            if (tel != null) {
                currentCustomer.setPhone(tel);
            }
            customerService.saveCustomer(currentCustomer);
            return currentCustomer;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an customer
     *
     * @param id - The id of the customer to delete
     */
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable("id") final Long id) {
        customerService.deleteCustomer(id);
    }

}
