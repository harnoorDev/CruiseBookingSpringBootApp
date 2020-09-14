package com.prog39599.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prog39599.beans.Customer;
import com.prog39599.beans.Room;

import com.prog39599.repositories.CustomerRepository;
import com.prog39599.repositories.RoomRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/room")
public class RoomController {
	
	private RoomRepository roomRepository;
	
	
	@GetMapping("/{id}")
	public Room getIndividualRoom(@PathVariable Long id)
	{
		
		Room r = roomRepository.findById(id).get();
	    r.setCustomer(null);
		return r;
	}
	
	@PostMapping(consumes="application/json")
	public Long postRoom(@RequestBody Room room)
	{
		return roomRepository.save(room).getId();
	}
	
	
	@GetMapping 
	public List<Room> getRoomCollection(){
		
		return roomRepository.findAll();
	}
	

}
