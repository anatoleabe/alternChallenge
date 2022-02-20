/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cancun.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cancun.api.model.Customer;
import com.cancun.api.repository.CustomerRepository;

import lombok.Data;

/**
 *
 * @author anatoleabe
 */
@Data
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> getCustomer(final Long id) {
        return customerRepository.findById(id);
    }

    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(final Long id) {
        customerRepository.deleteById(id);
    }

    public Customer saveCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

}