package com.prog39599.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import com.prog39599.beans.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestHomeController {

	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testLoadingIndex() throws Exception {
		
		this.mockMvc.perform(get("/"))
		  .andExpect(status().isOk())
		  .andExpect(view().name("index"));
		
	}
	
	@Test
	public void testLoadingInsert() throws Exception {
		
		this.mockMvc.perform(get("/register").flashAttr("customer", new Customer()));
	}
	
}
