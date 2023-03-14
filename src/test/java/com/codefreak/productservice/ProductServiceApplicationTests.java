package com.codefreak.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.codefreak.productservice.dto.ProductResquest;
import com.codefreak.productservice.model.Product;
import com.codefreak.productservice.respository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;



@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc

class ProductServiceApplicationTests {
	

	@Container
   static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	
	@Autowired
	private MockMvc MockMvc;
	 @Autowired
	private ObjectMapper objectMapper;
	  @Autowired
	    private ProductRepository productRepository;
	
	@DynamicPropertySource
	 static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
	    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	




	
	@Test
	void shouldCreateproducts() throws Exception {
		ProductResquest productRequest= new ProductResquest();
		String ProductResqueststring=objectMapper.writeValueAsString(productRequest);
		
		MockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ProductResqueststring))
		.andExpect(status().isCreated());
		 Assertions.assertEquals(1, productRepository.findAll().size());
	}
	
	private ProductResquest getProductRequest(){
		ProductResquest productrequest= new ProductResquest();
		productrequest.setName("iphone 11");
		productrequest.setDescription("iphone 11");
		productrequest.setPrice(BigDecimal.valueOf(1200));
		return productrequest;
		
	}

}
