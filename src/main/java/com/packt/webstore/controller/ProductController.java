package com.packt.webstore.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService service;

	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView HandError(HttpServletRequest req, ProductNotFoundException exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("invalidProductId", exception.getProductId());
		modelAndView.addObject("exception", exception);
		modelAndView.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		modelAndView.setViewName("productNotFound");
		return modelAndView;
	}

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.setDisallowedFields("unitsInStock", "unitsInOrder");
	}

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
		System.out.println("category="+category);
		model.addAttribute("products", service.getProdutsByCategory(category));

		return "products";
	}

	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(Model model,
			@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> paramters) {
		System.out.println("paramters" + paramters);
		model.addAttribute("products", service.getProductsByFilter(paramters));
		return "products";
	}

	@RequestMapping("/filter/{Bycategory}/{ByCriteria}")
	public String getProductsByFilter2(Model model,
			@MatrixVariable(pathVar = "Bycategory") Map<String, List<String>> category,
			@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> criteria) {
		System.out.println("paramters=" + category + "=;=" + criteria);
		// model.addAttribute("products",
		// service.getProductsByFilter(paramters));
		return "products";
	}

	@RequestMapping("/product")
	public String getProduct(Model model, @RequestParam("id") String id) {
		System.out.println(id);
		model.addAttribute("product", service.getProductById(id));
		return "product";
	}

	@RequestMapping("/{category}/{price}")
	public String getProductByManufacturer(@MatrixVariable(pathVar = "price") Map<String, String> price,
			@PathVariable("category") String category, @RequestParam("manufacturer") String manufacturer, Model model) {
		model.addAttribute("products", service.getProductsByManufacturerAndCategory(category, manufacturer, price));
		return "products";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProduct(Model model) {
		System.out.println("getAddNewProduct");
		model.addAttribute("newProduct", new Product());
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProduct(@ModelAttribute("newProduct") Product product, BindingResult result,
			HttpServletRequest request) {
		if (result.getSuppressedFields().length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
		}
		MultipartFile multipartFile = product.getProductImg();
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println(root);
		System.out.println(multipartFile.getOriginalFilename());
		if (multipartFile != null && !multipartFile.isEmpty()) {
			try {
				multipartFile.transferTo(new File(root + "resources\\images\\" + multipartFile.getOriginalFilename()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				throw new RuntimeException("Product Image saving failed", e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		service.addNewProduct(product);

		return "redirect:/products";
	}
}
