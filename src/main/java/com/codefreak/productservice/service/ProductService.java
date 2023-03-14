package com.codefreak.productservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.codefreak.productservice.dto.ProductResponse;
import com.codefreak.productservice.dto.ProductResquest;
import com.codefreak.productservice.model.Product;

import com.codefreak.productservice.respository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	
private final  ProductRepository ProductRepository;





/*public ProductService(ProductResponse productResponse, Product product,ProductRepository productRepository) {
	this.ProductRepository = productRepository;
	this.ProductResponse = productResponse;
	this.product = product;
}*/

	
/*public ProductService(ProductRepository productRepository) {
	
	this.ProductRepository = productRepository;
}*/



public void createProduct(@RequestBody ProductResquest productRequest){

	Product product= new Product();
	product.setName(productRequest.getName());
	product.setDescription(productRequest.getDescription());
	product.setPrice(productRequest.getPrice());
	
	ProductRepository.save(product);
	log.info("product " +product.getId()+" is saved");
	
	}


public List <ProductResponse> getallproducts(){
	
	//System.out.println("________________________came inside");
	//ProductResponse mapper = new ProductResponse();

	//List<DepartmentDTO> deptDTO = Arrays.asList(mapper.map(departmentList, DepartmentDTO[].class));

	List <Product> products = null;
	try{
		products=ProductRepository.findAll();
		//System.out.println("_____________________________________________________"+products);
		
		
	}
	
	catch(Exception e){e.printStackTrace();}
	

	return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
	
	//ObjectMapperUtils.mapAll(products, ProductResponse.class);
	
	
	 
	}





private ProductResponse mapToProductResponse(Product product2) {
	
	
	//Product product= new Product();
	
	 ProductResponse ProductResponse = new ProductResponse();
	
	
	ProductResponse.setId(((Product) product2).getId());
	ProductResponse.setName(((Product) product2).getName());
	ProductResponse.setDescription(((Product) product2).getDescription());
	ProductResponse.setPrice(((Product) product2).getPrice());
	return ProductResponse;

}








}
