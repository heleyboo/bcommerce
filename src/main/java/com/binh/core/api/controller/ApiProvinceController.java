package com.binh.core.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.core.entity.District;
import com.binh.core.entity.Province;
import com.binh.core.service.ProvinceService;

@RestController
@RequestMapping("/api/v1/provinces")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiProvinceController {
	
	@Autowired
	private ProvinceService provinceService;
	
	@GetMapping("/{id}/districts")
	public List<District> getDistricts(@PathVariable("id") String provinceId) {
		return provinceService.getDistricts(provinceId);
	}
	@GetMapping
	public List<Province> getProvinces() {
		return provinceService.getProvinces();
	}
}
