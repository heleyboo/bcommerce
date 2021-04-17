package com.binh.core.service;

import java.util.List;

import com.binh.core.dto.request.MotelRoomDTO;
import com.binh.core.entity.MotelRoom;

public interface MotelRoomService {
	public List<MotelRoom> getAll();
	public MotelRoom save(MotelRoomDTO room);
}
