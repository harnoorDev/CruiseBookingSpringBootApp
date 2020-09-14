package com.prog39599.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Range;

import com.prog39599.beans.Customer.CustomerBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder



public class CruiseShip {
	
	

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cruise_id")
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="cruise")
	@Getter @Setter	private List<Room> room;
	
	
	@Builder.Default @Getter @Setter 
	
	private int luxuryRoomsLeft = 50;
	
	@Builder.Default @Getter @Setter 
	
	private int deluxeRoomsLeft = 50;
	
	@Builder.Default @Getter @Setter
	
	private int economyRoomsLeft = 50;
	
	@Builder.Default @Getter @Setter 
	
	private int staffLuxRoomsLeft = 10;
	
	@Builder.Default @Getter @Setter 
	
	private int staffDelRoomsLeft = 20;
	
@Builder.Default @Getter @Setter 
	
	private int staffEcoRoomsLeft = 20;
	

}
