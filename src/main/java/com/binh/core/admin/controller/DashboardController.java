package com.binh.core.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.binh.core.entity.User;
import com.binh.core.service.UserService;

@Controller
@RequestMapping("/administrator")
public class DashboardController {
	@Autowired
	private UserService userService;

	@GetMapping("/dashboard")
	public ModelAndView dashboard(Model model) {
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;
	}
}
