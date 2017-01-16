package com.packt.webstore.domain.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.attribute.standard.RequestingUserName;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository {
	List<Product> productList = new ArrayList<Product>();

	public InMemoryProductRepository() {
		super();
		Product iphone = new Product();
		iphone.setCategory("apple");
		iphone.setProductId("P1234");
		iphone.setCondition("iphone condition");
		iphone.setDescription("iphone 7");
		iphone.setDiscontinued(true);
		iphone.setManufacturer("FUSHIKANG");
		iphone.setName("iphone");
		iphone.setUnitPrice(new BigDecimal(600));
		iphone.setUnitsInStock(123);

		Product nokia = new Product();
		nokia.setCategory("nokia");
		nokia.setProductId("P12347");
		nokia.setCondition("nokia condition");
		nokia.setDescription("nokia 7");
		nokia.setDiscontinued(true);
		nokia.setManufacturer("FUSHIKANG");
		nokia.setName("nokia");
		nokia.setUnitPrice(new BigDecimal(600));
		nokia.setUnitsInStock(123);

		Product mi = new Product();
		mi.setCategory("mi");
		mi.setProductId("P12348");
		mi.setCondition("mi condition");
		mi.setDescription("mi 7");
		mi.setDiscontinued(true);
		mi.setManufacturer("FUSHIKANG");
		mi.setName("mi");
		mi.setUnitPrice(new BigDecimal(600));
		mi.setUnitsInStock(123);
		Product mi5 = new Product();
		mi5.setCategory("mi");
		mi5.setProductId("P12349");
		mi5.setCondition("mi condition");
		mi5.setDescription("mi 7");
		mi5.setDiscontinued(true);
		mi5.setManufacturer("FUSHIKANG");
		mi5.setName("mi");
		mi5.setUnitPrice(new BigDecimal(600));
		mi5.setUnitsInStock(123);

		productList.add(iphone);
		productList.add(nokia);
		productList.add(mi);
		productList.add(mi5);
	}

	public Product getProductById(String productId) {
		Product returnProduct = null;
		if (StringUtils.isEmpty(productId)) {
			throw new ProductNotFoundException( productId);
		}
		for (Product product : productList) {
			if (product.getProductId().equals(productId)) {
				returnProduct = product;
				break;
			}
		}
		if (returnProduct == null) {
			 throw new ProductNotFoundException( productId);
		}
		return returnProduct;
	}

	public List<Product> getAllProducts() {

		return productList;
	}

	public List<Product> getProdutsByCategory(String category) {
		List<Product> returnProduct = null;
		if (StringUtils.isEmpty(category)) {
			throw new IllegalArgumentException("No products found with the category : " + category);
		}
		for (Product product : productList) {
			if (category.equalsIgnoreCase(product.getCategory())) {
				if (returnProduct == null)
					returnProduct = new ArrayList<Product>();
				System.out.println("hhh="+product);
				returnProduct.add(product);
			}
		}
		if (returnProduct == null) {
			throw new IllegalArgumentException("No products found with the category : " + category);
		}
		System.out.println("returnProduct="+returnProduct);
		return returnProduct;
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		System.out.println(filterParams);
		Set<Product> brandProducts = new HashSet<Product>();
		Set<Product> categoryProducts = new HashSet<Product>();
		if (filterParams != null) {
			if (filterParams.get("brand") != null) {
				for (String brand : filterParams.get("brand")) {
					for (Product product : productList) {
						System.out.println(product);
						if (brand.equalsIgnoreCase(product.getManufacturer())) {
							brandProducts.add(product);
							System.out.println("jj="+product);
						}
					}
				}
			}
			if (filterParams.get("category") != null) {
				for (String category : filterParams.get("category")) {
					System.out.println(category);
					categoryProducts.addAll(getProdutsByCategory(category));
				}
			}

		}
		System.out.println(categoryProducts);
		categoryProducts.retainAll(brandProducts);
		System.out.println(categoryProducts);

		return categoryProducts;
	}

	public List<Product> getProductsByManufacturer(String manufacturer) {
		List<Product> returnProduct = null;
		if (StringUtils.isEmpty(manufacturer)) {
			return productList;
		}
		for (Product product : productList) {
			if (manufacturer.equalsIgnoreCase(product.getCategory())) {
				if (returnProduct == null)
					returnProduct = new ArrayList<Product>();
				System.out.println("hhh="+product);
				returnProduct.add(product);
			}
		}
		if (returnProduct == null) {
			throw new IllegalArgumentException("No products found with the manufacturer : " + manufacturer);
		}
		System.out.println("returnProduct="+returnProduct);
		return returnProduct;
	}

	public void addNewProduct(Product product) {
		productList.add(product);
		
	}

}
