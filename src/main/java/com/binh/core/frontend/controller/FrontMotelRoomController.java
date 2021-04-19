package com.binh.core.frontend.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.core.entity.Category;
import com.binh.core.entity.Province;
import com.binh.core.service.CategoryService;
import com.binh.core.service.MotelRoomService;
import com.binh.core.service.ProvinceService;

@Controller
@RequestMapping("/")
public class FrontMotelRoomController {
	@Autowired
	private MotelRoomService service;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private ProvinceService provinceService;
	
	@GetMapping("/dangtin")
	@RolesAllowed("user")
	public String showForm(Model model) {
		Set<Category> categories = catService.findChildrenByCode("batdongsan");
		List<Province> provinces = provinceService.getAll();
		model.addAttribute("categories", categories);
		model.addAttribute("provinces", provinces);
		return "frontend/room/home";
	}
}
