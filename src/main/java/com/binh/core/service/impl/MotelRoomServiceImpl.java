package com.binh.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.core.dto.request.MotelRoomDTO;
import com.binh.core.entity.MotelRoom;
import com.binh.core.repository.MotelRoomRepository;
import com.binh.core.service.MotelRoomService;

@Service
public class MotelRoomServiceImpl implements MotelRoomService {
	@Autowired
	private MotelRoomRepository repo;
	
	public List<MotelRoom> getAll() {
		return repo.findAll();
	}

	@Override
	public MotelRoom save(MotelRoomDTO room) {
		// mapping
		MotelRoom motelRoom = new MotelRoom();
		motelRoom.setTitle(room.getTitle());
		motelRoom.setDescription(room.getDescription());
		motelRoom.setPrice(room.getPrice());
		motelRoom.setArea(room.getArea());
		motelRoom.setAddress(room.getAddress());
		
		return repo.save(motelRoom);
	}
}
