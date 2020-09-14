package com.prog39599.controllers;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prog39599.beans.CruiseShip;
import com.prog39599.beans.Customer;
import com.prog39599.beans.Roles;
import com.prog39599.beans.Room;
import com.prog39599.beans.RoomType;

import com.prog39599.repositories.CruiseShipRepository;
import com.prog39599.repositories.CustomerRepository;
import com.prog39599.repositories.RolesRepository;
import com.prog39599.repositories.RoomRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
private CustomerRepository customerRepository;
private RolesRepository rolesRepository;
private CruiseShipRepository cruiseRepository;	
private RoomRepository roomRepository;
	@GetMapping("/")
	public String index() {
		
		
		return "index";
	}
	
	
	
	@GetMapping("/staff")
	public String staffIndex(Model model)
	{
		CruiseShip cs = cruiseRepository.findById(Long.valueOf(1)).get();
	    model.addAttribute("StaffLuxRoomsLeft", cs.getStaffLuxRoomsLeft());
	    model.addAttribute("StaffDelRoomsLeft", cs.getStaffDelRoomsLeft());
	    model.addAttribute("StaffEcoRoomsLeft", cs.getStaffEcoRoomsLeft());
		model.addAttribute("room", new Room());
		return "/staff/index";
		
		
	}
	
	@GetMapping("/user")
	public String userIndex(Model model)
	{
		
		
	    CruiseShip cs = cruiseRepository.findById(Long.valueOf(1)).get();
	    model.addAttribute("DeluxeRoomsLeft", cs.getDeluxeRoomsLeft());
	    model.addAttribute("EconomyRoomsLeft", cs.getEconomyRoomsLeft());
	    model.addAttribute("LuxuryRoomsLeft", cs.getLuxuryRoomsLeft());
	     
		model.addAttribute("room", new Room());
		return "/user/index";
	}

	
	@PostMapping("/staff/bookRoom")
	public String bookRoomStaff(Model model, @ModelAttribute Room room, Authentication auth)
	{
		
Boolean isFull = false;
		
		Customer c = customerRepository.findByUsername(auth.getName());
		 CruiseShip cs = cruiseRepository.findById(Long.valueOf(1)).get();
		 
		
			
		 if(cs.getStaffDelRoomsLeft() == 0 && room.getRoomType() == RoomType.DELUXE_800$)
		 {
			isFull = true;
		 }
		 else if(cs.getStaffLuxRoomsLeft() == 0 && room.getRoomType() == RoomType.LUXURY_1000$)
		 {
			 isFull = true;
		 }
		 
		 else if(cs.getStaffEcoRoomsLeft() == 0 && room.getRoomType() == RoomType.ECONOMY_500$)
		 {
			 isFull = true;
		 }
		 if(isFull)
		 {
            model.addAttribute("roomtype", room.getRoomType());
            return "/staff/roomNotAvailable";
		 }
		 if(room.getRoomType() == RoomType.DELUXE_800$)
		 {
			cs.setStaffDelRoomsLeft(cs.getStaffDelRoomsLeft()-1);
			
		 }
		 else if(room.getRoomType() == RoomType.ECONOMY_500$)
		 {
			cs.setStaffEcoRoomsLeft(cs.getStaffEcoRoomsLeft()-1);
			
		 }
		 else if(room.getRoomType() == RoomType.LUXURY_1000$)
		 {
			cs.setStaffLuxRoomsLeft(cs.getStaffLuxRoomsLeft()-1);
		     
		 }
		 
		    room.setPrice(0);
			room.setCustomer(c);
			room.setCruise(cs);
			customerRepository.save(c);
			cruiseRepository.save(cs);
			roomRepository.save(room);
			model.addAttribute("room", room);
			model.addAttribute("name", auth.getName());
		
			return "/staff/success";
			
	}
	
	
	@GetMapping("/admin")
	public String adminIndex (Model model)
	{
		model.addAttribute("customerList", customerRepository.findAll());
	   model.addAttribute("roomList", roomRepository.findAll());
	  
		return "/admin/index";
		
	}
	
	
	@GetMapping("editUser/{id}")
	public String adminEditUser(@PathVariable Long id, Model model)
	{
		
	  Customer c = customerRepository.findById(id).get();
	  model.addAttribute("customer", c);
	  return "admin/editUser";
		
		
	}
	

	@GetMapping("editRoom/{id}")
	public String adminEditRoom(@PathVariable Long id, Model model)
	{
		
	  Room r = roomRepository.findById(id).get();
	  model.addAttribute("room", r);
	  return "admin/editRoom";
		
		
	}
	
	@GetMapping("deleteUser/{id}")
	public String adminDeleteUser(@PathVariable Long id, Model model)
	{
		 Customer c = customerRepository.findById(id).get();	  
		   customerRepository.delete(c);
		   model.addAttribute("customerList", customerRepository.findAll());
		   model.addAttribute("roomList", roomRepository.findAll());
			return "/admin/index";
		
		
	}
	
	@PostMapping("/modifyCustomer")
	public String adminModifyCustomer(@ModelAttribute Customer c, Model model)
	{
		
		

	   model.addAttribute("customerList", customerRepository.findAll());
	   model.addAttribute("roomList", roomRepository.findAll());
		customerRepository.save(c);
		return "/admin/index";
		
		
	}
	@PostMapping("/modifyRoom")
	public String adminModifyRoom(@ModelAttribute Room r, Model model)
	{
		
		
      roomRepository.save(r);
	   model.addAttribute("customerList", customerRepository.findAll());
	   model.addAttribute("roomList", roomRepository.findAll());
	   
		return "/admin/index";
		
		
	}
	
	
	
	@PostMapping("/user/bookRoom")
	public String bookRoom(Model model, @ModelAttribute Room room, Authentication auth, @RequestParam(required = false) Boolean isSenior, @RequestParam(required = false) Boolean isWithChildren)
	{
		
		Boolean isFull = false;
		
		Customer c = customerRepository.findByUsername(auth.getName());
	
	
		
		 CruiseShip cs = cruiseRepository.findById(Long.valueOf(1)).get();
		 
	
		 if(cs.getDeluxeRoomsLeft() == 0 && room.getRoomType() == RoomType.DELUXE_800$)
		 {
			isFull = true;
		 }
		 else if(cs.getLuxuryRoomsLeft() == 0 && room.getRoomType() == RoomType.LUXURY_1000$)
		 {
			 isFull = true;
		 }
		 
		 else if(cs.getEconomyRoomsLeft() == 0 && room.getRoomType() == RoomType.ECONOMY_500$)
		 {
			 isFull = true;
		 }
		 if(isFull)
		 {
            model.addAttribute("roomtype", room.getRoomType());
            return "/user/roomNotAvailable";
		 }
		 c.setIsSenior(isSenior);
		 c.setIsWithChildren(isWithChildren);
		 
		 if(room.getRoomType() == RoomType.DELUXE_800$)
		 {
			 cs.setDeluxeRoomsLeft(cs.getDeluxeRoomsLeft() -1);
			 room.setPrice(Double.valueOf(800.00));
		 }
		 else if(room.getRoomType() == RoomType.ECONOMY_500$)
		 {
			 cs.setEconomyRoomsLeft(cs.getEconomyRoomsLeft() -1);
			 room.setPrice(Double.valueOf(500.00));
		 }
		 else if(room.getRoomType() == RoomType.LUXURY_1000$)
		 {
			 cs.setLuxuryRoomsLeft(cs.getLuxuryRoomsLeft()-1);
		     room.setPrice(Double.valueOf(1000.00));
		 }
		 
		 
			
				if(c.getIsSenior() != null || c.getIsWithChildren()!=null)
				
				{	
				 if(c.getIsSenior() || c.getIsWithChildren())
				 {
					 room.setPrice(Double.valueOf(String.format("%.2f", (room.getPrice() - (room.getPrice()* 0.50)))));
				 }
				}
				
				room.setCustomer(c);
				room.setCruise(cs);
				customerRepository.save(c);
				cruiseRepository.save(cs);
				roomRepository.save(room);
				model.addAttribute("room", room);
				model.addAttribute("name", auth.getName());
				
			
	
		
		return "/user/success";
		
		
	}
	
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String goRegistration()
	{
		
		return "register";
	}
	
	@PostMapping("/register")
	public String doRegistration(@RequestParam String username, @RequestParam String password)
	{
		Customer customer = Customer.builder().username(username).encryptedPassword(encodePassword(password)).enabled(Byte.valueOf("1")).roles(new ArrayList<Roles>()).build();
		
		customer.getRoles().add(rolesRepository.findByRoleName("ROLE_USER"));
		customerRepository.save(customer);
		return "redirect:/";
	}
	
	private String encodePassword(String password)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	

}
