package com.binh.core.rest.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binh.core.dto.request.MotelRoomDTO;
import com.binh.core.entity.MotelRoom;
import com.binh.core.service.MotelRoomService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/v1/rooms")
public class MotelRoomController {

	@Autowired
	private MotelRoomService motelroomservice;

	@PostMapping
	public MotelRoom createRoom(@Valid @RequestBody MotelRoomDTO room, KeycloakAuthenticationToken authentication)
			throws NotFoundException, FileNotFoundException, IOException {
		return motelroomservice.save(room, authentication);

	}

	@GetMapping
	public Page<MotelRoom> getAll(@RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		return motelroomservice.getAll(pageNum, pageSize);
	}
	
	@GetMapping("/{id}")
	public MotelRoom getById(@PathVariable("id") Integer roomId) throws NotFoundException {
		return motelroomservice.getMotelRoomById(roomId);
	}

	@GetMapping("/by-user")
	// @PathVariable: Tìm resource theo ID, VD: api/v1/rooms/12
	// @RequestParam: Truy vấn danh sách resource theo thuộc tính: vd:
	// /api/v1/rooms?username=binh
	public List<MotelRoom> getMotelRooms(@RequestParam(name = "userName", required = true) String userName) {
		return motelroomservice.getMotelRooms(userName);
	}
}
