package com.prog39599.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog39599.beans.Room;

public interface RoomRepository extends JpaRepository <Room, Long> {
	
	

}
