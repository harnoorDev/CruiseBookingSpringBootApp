package com.prog39599.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prog39599.beans.Customer;

import com.prog39599.repositories.CustomerRepository;


import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
	
	
	private CustomerRepository customerRepository; 
	
	@GetMapping("/{id}")
	public Customer getIndividualCustomer(@PathVariable Long id)
	{
		
		
		Customer c = customerRepository.findById(id).get();
		c.setRoles(null);
		return c;
	}
	
	
	
	@GetMapping
	public List<Customer> getCustomerCollection(){
		
		return customerRepository.findAll();
	}
	
	
	
	

}
