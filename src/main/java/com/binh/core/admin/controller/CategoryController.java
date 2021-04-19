package com.binh.core.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.binh.core.dto.request.CategoryRequest;
import com.binh.core.entity.Category;
import com.binh.core.service.CategoryService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/administrator/category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping(value = "/create")
	public String showForm(Model model) {
		CategoryRequest category = new CategoryRequest();
		List<Category> categories = service.getAll();
		model.addAttribute("categories", categories);
		model.addAttribute("categoryRequest", category);
		return "admin/category/create";
	}

	@PostMapping(value = "/create")
	public String createCategory(@Valid CategoryRequest categoryRequest, BindingResult bindingResult, Model model)
			throws NotFoundException {
		if (bindingResult.hasErrors()) {
			List<Category> categories = service.getAll();
			model.addAttribute("categories", categories);
			model.addAttribute("categoryRequest", categoryRequest);
			return "admin/category/create";
		}
		service.saveCategory(categoryRequest);
		return "redirect:/administrator/category";
	}
	
	@GetMapping
	public ModelAndView listCategories(Model model) {
		ModelAndView mav = new ModelAndView("admin/category/list");
		List<Category> categories = service.getAll();
		model.addAttribute("categories", categories);
		return mav;
	}
	
	@GetMapping("/{code}")
	public ModelAndView editCategory(@PathVariable String code, Model model) throws NotFoundException {
		ModelAndView mav = new ModelAndView("admin/category/list");
		Category category = service.getCategoryByCode(code);
		model.addAttribute("category", category);
		return mav;
	}
}
