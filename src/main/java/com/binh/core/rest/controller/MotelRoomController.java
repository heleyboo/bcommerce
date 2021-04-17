package com.binh.core.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.core.dto.request.MotelRoomDTO;
import com.binh.core.entity.MotelRoom;
import com.binh.core.service.MotelRoomService;

@RestController
@RequestMapping("/api/v1/rooms")
public class MotelRoomController {

	@Autowired
	private MotelRoomService motelroomservice;

	@PostMapping
	public MotelRoom createRoom(@Valid @RequestBody MotelRoomDTO room) {
		
		return motelroomservice.save(room);
	}

	@GetMapping
	public List<MotelRoom> getAll() {
		return null;
	}
}
