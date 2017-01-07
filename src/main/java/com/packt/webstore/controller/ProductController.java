package com.packt.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.domain.repository.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository reposity;

	@RequestMapping("/products")
	public String listProducts(Model model) {

		model.addAttribute("products", reposity.getAllProducts());
		return "products";
	}
}
