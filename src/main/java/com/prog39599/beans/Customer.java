package com.prog39599.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor

@Builder

public class Customer {
	
	
	



	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	@Getter @Setter private Long id;
	

	
	@Column
	private String fullName;
	
	@Column
	@NotNull
	@Getter @Setter private String username;
	
	@Column
	@NotNull
	@Getter @Setter private String encryptedPassword;
	
	@Column 
	@Getter @Setter private String email;
	
	@Column
	@NotNull
	@Getter @Setter private Byte enabled;
	
	@Column
	@Getter @Setter private Boolean isSenior;
	
	@Column
	@Getter @Setter private Boolean isWithChildren;
	
	
	
	
	@OneToMany( mappedBy = "customer")
	private List<Room> room;

	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@Getter @Setter	private List<Roles> roles = new ArrayList<Roles>();

}
