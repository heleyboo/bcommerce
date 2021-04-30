package com.binh.core.api.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.core.entity.Category;
import com.binh.core.service.CategoryService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/v1/categories")
public class ApiCategoryController {
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public List<Category> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/test/{code}")
	public Category getOne(@PathVariable("code") String code) throws NotFoundException {
		return service.getCategoryByCode(code);
	}
}
