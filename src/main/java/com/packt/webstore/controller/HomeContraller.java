package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeContraller {

	@RequestMapping("/")
	public String welcome(Model model){
		model.addAttribute("greeting", "Welcome to web store! ");
		model.addAttribute("tagline","The one and only the amazing webstore.");
		return "welcome";
	}
}
