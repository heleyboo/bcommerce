package com.binh.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binh.core.entity.MotelRoom;

public interface MotelRoomRepository extends JpaRepository<MotelRoom, Integer> {
	// findByUserName, findByArea
	public List<MotelRoom> findByUserName(String userName);
}
