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
import com.binh.core.entity.User;
import com.binh.core.service.UserService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/administrator/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/create")
	public String showForm(Model model) {
		List<User> users = userService.getAll();
		User user = new User();
		model.addAttribute("user", user);
		return "admin/user/create";
	}
	
	@PostMapping(value = "/create")
	public String createUser(@Valid User user, BindingResult bindingResult, Model model)
			throws NotFoundException {
		if (bindingResult.hasErrors()) {
			List<User> users = userService.getAll();
			model.addAttribute("user", user);
			return "admin/user/create";
		}
		userService.saveUser(user);
		return "redirect:/administrator/user";
	}
	
	@GetMapping
	public ModelAndView listUser(Model model) {
		ModelAndView mav = new ModelAndView("admin/user/list");
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		return mav;
	}

}
