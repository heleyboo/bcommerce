package com.binh.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.binh.core.entity.MotelRoom;
import com.binh.core.specifications.RoomSpecification;

public interface MotelRoomRepository extends JpaRepository<MotelRoom, Integer>, JpaSpecificationExecutor<MotelRoom> {
	// findByUserName, findByArea
	public List<MotelRoom> findByUserName(String userName);
}
