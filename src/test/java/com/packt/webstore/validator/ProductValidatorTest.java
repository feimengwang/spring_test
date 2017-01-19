package com.packt.webstore.validator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.context.WebApplicationContext;

import com.packt.webstore.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/packt/webstore/validator/test-DispatcherServlet-context.xml")
@WebAppConfiguration
public class ProductValidatorTest {
	@Autowired
	private ProductValidator productValidator;
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Test
	public void test_get_home() throws Exception{
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		mockMvc.perform(get("/")).andExpect(model().attributeExists("tagline"));
	}
	@Test
	public void product_without_UnitPrice_should_be_invalid() {
		// Arrange
		Product product = new Product();
		
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(2, bindException.getErrorCount());
		Assert.assertTrue(bindException.getLocalizedMessage().contains("Unit price is Invalid. It cannot be empty."));
	}

	@Test
	public void product_with_existing_productId_invalid() {
		// Arrange
		Product product = new Product();
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		Assert.assertTrue(
				bindException.getLocalizedMessage().contains("	product already exists with this product id."));
	}

	@Test
	public void a_valid_product_should_not_get_any_error_during_validation() {
		// Arrange
		Product product = new Product();
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(0, bindException.getErrorCount());
	}
}
