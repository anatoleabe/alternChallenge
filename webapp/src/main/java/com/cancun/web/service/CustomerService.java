/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cancun.web.service;

import com.cancun.web.model.Customer;
import com.cancun.web.repository.CustomerProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anatoleabe
 */

@Data
@Service
public class CustomerService {

    @Autowired
    private CustomerProxy customerProxy;

    public Customer getCustomer(final int id) {
        return customerProxy.getCustomer(id);
    }

    public Iterable<Customer> getCustomers() {
        return customerProxy.getCustomers();
    }

    public void deleteCustomer(final int id) {
        customerProxy.deleteCustomer(id);
    }

     public Customer saveCustomer(Customer customer) {
        Customer savedCustomer;

        if(customer.getId() == null) {
            // Create
            savedCustomer = customerProxy.createCustomer(customer);
        } else {
            //Update
            savedCustomer = customerProxy.updateCustomer(customer);
        }
    
        return savedCustomer;
    }

}