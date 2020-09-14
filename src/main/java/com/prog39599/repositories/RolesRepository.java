package com.prog39599.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog39599.beans.Roles;

public interface RolesRepository extends JpaRepository <Roles, Long>{
	
	public Roles findByRoleName(String name);

}
