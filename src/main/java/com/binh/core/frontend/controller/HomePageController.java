package com.binh.core.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomePageController {
	@RequestMapping("/")
	public String homePage(Model model) {
		return "frontend/room/home";
	}
}
