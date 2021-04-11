package com.binh.core.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.binh.core.dto.request.CategoryRequest;
import com.binh.core.service.CategoryService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/administrator/category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping(value = "/create")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("admin/category/create");
		CategoryRequest category = new CategoryRequest();
		mav.addObject("category", category);
		return mav;
	}

	@PostMapping(value = "/create")
	public ModelAndView createCategory(@Valid CategoryRequest categoryReq, BindingResult bindingResult)
			throws NotFoundException {
		ModelAndView mav = new ModelAndView("admin/category/create");
		service.saveCategory(categoryReq);
		mav.addObject("category", categoryReq);
		return mav;
	}
}
