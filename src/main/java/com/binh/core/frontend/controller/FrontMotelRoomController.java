package com.binh.core.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.core.service.MotelRoomService;

@Controller
@RequestMapping("/dangtin")
public class FrontMotelRoomController {
	@Autowired
	private MotelRoomService service;
	
	@GetMapping
	public String showForm(Model model) {
		return "frontend/room/home";
	}
}
