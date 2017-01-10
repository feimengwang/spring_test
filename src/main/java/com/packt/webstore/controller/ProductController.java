package com.packt.webstore.controller;

import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService service;

	@RequestMapping
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", service.getAllProducts());
		modelAndView.setViewName("products");
		return modelAndView;
	}

	@RequestMapping("/all")
	public String listProducts(Model model) {

		model.addAttribute("products", service.getAllProducts());
		return "products";
	}

	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String category) {
		model.addAttribute("products", service.getProdutsByCategory(category));

		return "products";
	}

	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(Model model,
			@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> paramters) {
		System.out.println("paramters"+paramters);
		model.addAttribute("products", service.getProductsByFilter(paramters));
		return "products";
	}
	@RequestMapping("/filter/{Bycategory}/{ByCriteria}")
	public String getProductsByFilter2(Model model,
			@MatrixVariable(pathVar = "Bycategory") Map<String, List<String>> category,
			@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> criteria) {
		System.out.println("paramters="+category+"=;="+criteria);
		//model.addAttribute("products", service.getProductsByFilter(paramters));
		return "products";
	}
	
	@RequestMapping("/product")
	public String getProduct(Model model,@RequestParam String id){
		
		return "product";
	}
}
