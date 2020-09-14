package com.prog39599.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import com.prog39599.beans.Customer;
import com.prog39599.beans.Roles;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestCustomerRepository {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Test 
	public void whenFindById_thenReturnCustomer() {
		
		//given 
		Customer customer = Customer.builder()
				           .username("test")
				           .encryptedPassword("78#!7766aqy45")
				           .enabled(Byte.valueOf("0"))
				           .roles(new ArrayList<Roles>())
				           .build(); 
		entityManager.persist(customer); 
		entityManager.flush();
		
		//when 
		Customer found =customerRepository.findById(customer.getId()).get();
		
		//then
		assertThat(found.getId()).isEqualTo(customer.getId());


	}
	
	@Test 
	public void whenFindByUserName_thenReturnCustomer() {
		
		//given 
		Customer customer = Customer.builder() 
				     
				           .username("messi")
				           .encryptedPassword("098i#!fgtyh67")
				           .enabled(Byte.valueOf("0"))
				           .roles(new ArrayList<Roles>())
				           .build(); 
		entityManager.persist(customer); 
		entityManager.flush();
		
		//when 
		Customer found = customerRepository.findByUsername(customer.getUsername());
		
		//then
		assertThat(found.getUsername()).isEqualTo(customer.getUsername());


	}
	

}
