package com.prog39599.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.prog39599.beans.Customer.CustomerBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class Room {
	

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	@Getter @Setter private Long id;
	

	@Column
	@Enumerated(EnumType.STRING)
	@Getter @Setter	private RoomType roomType;
	
	  
		 @ManyToOne(fetch = FetchType.LAZY)
		    @JoinColumn(name = "customer_id")
			@Getter @Setter   private Customer customer;
	
	 
	   
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "cruise_id")
	    @Getter @Setter   private CruiseShip cruise;
	 
	 @Column
		@Getter @Setter private double price;
	 
	
	
	

}
