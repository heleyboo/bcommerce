package com.binh.core.rest.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.core.dto.response.CodeValueDTO;
import com.binh.core.enums.RoomDirection;

@RestController
@RequestMapping("/api/v1/directions")
public class DirectionController {
	@GetMapping
	public List<CodeValueDTO> getDirections() {

		List<RoomDirection> directions = Arrays.asList(RoomDirection.values());
		return directions.stream().map(it -> new CodeValueDTO(it.getCode(), it.getName())).collect(Collectors.toList());
	}
}
