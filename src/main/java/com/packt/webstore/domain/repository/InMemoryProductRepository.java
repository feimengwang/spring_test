package com.packt.webstore.domain.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.packt.webstore.domain.Product;

@Repository
public class InMemoryProductRepository implements ProductRepository {
	List<Product> productList = new ArrayList<Product>();
	
	public Product getProductById(String productId){
		Product returnProduct = null;
		if(StringUtils.isEmpty(productId)){
			throw new IllegalArgumentException("No products found with the product id: "+ productId);
		}
		for (Product product : productList) {
			if(product.getProductId().equals(productId)){
				returnProduct = product;
				break;
			}
		}
		if(returnProduct ==null){
			throw new IllegalArgumentException("No products found with the product id: "+ productId);
		}
		return returnProduct;
	}
	
	public List<Product> getAllProducts() {
		Product iphone = new Product();
		iphone.setCategory("mobile");
		iphone.setProductId("P12345");
		iphone.setCondition("iphone condition");
		iphone.setDescription("iphone 7");
		iphone.setDiscontinued(true);
		iphone.setManufacturer("FUSHIKANG");
		iphone.setName("iphone");
		iphone.setUnitPrice(new BigDecimal(600));
		iphone.setUnitsInStock(123);

		Product nokia = new Product();
		nokia.setCategory("mobile");
		nokia.setProductId("P12347");
		nokia.setCondition("nokia condition");
		nokia.setDescription("nokia 7");
		nokia.setDiscontinued(true);
		nokia.setManufacturer("FUSHIKANG");
		nokia.setName("nokia");
		nokia.setUnitPrice(new BigDecimal(600));
		nokia.setUnitsInStock(123);

		Product mi = new Product();
		mi.setCategory("mobile");
		mi.setProductId("P12348");
		mi.setCondition("mi condition");
		mi.setDescription("mi 7");
		mi.setDiscontinued(true);
		mi.setManufacturer("FUSHIKANG");
		mi.setName("mi");
		mi.setUnitPrice(new BigDecimal(600));
		mi.setUnitsInStock(123);

		productList.add(iphone);
		productList.add(nokia);
		productList.add(mi);
		return productList;
	}

}
