package com.packt.webstore.validator;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Product;

public class UnitsInStockValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("support");
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		if (product.getUnitPrice() != null && new BigDecimal(1000).compareTo(product.getUnitPrice()) <= 0) {
			errors.rejectValue("unitsInStock", "com.packt.webstore.validator.UnitsInStockValidator.message");
		}

	}

}
