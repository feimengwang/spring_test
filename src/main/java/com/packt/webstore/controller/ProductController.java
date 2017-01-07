package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.domain.Product;

@Controller
public class ProductController {

	@RequestMapping("/products")
	public String listProducts(Model model){
		Product product = new Product();
		product.setCategory("");
		return "products";
	}
}
