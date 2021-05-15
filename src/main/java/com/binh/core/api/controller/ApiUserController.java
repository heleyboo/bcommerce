package com.binh.core.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.core.entity.Category;
import com.binh.core.entity.User;
import com.binh.core.service.CategoryService;
import com.binh.core.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class ApiUserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public List<User> getAll() {
		return service.getAll();
	}
}
