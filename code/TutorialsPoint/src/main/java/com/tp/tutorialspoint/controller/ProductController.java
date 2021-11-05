package com.tp.tutorialspoint.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp.tutorialspoint.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
	private static Map<Integer, Product> productRepo = new HashMap<>();
	static {
		Product honey = new Product(1, "Honey");
		productRepo.put(honey.getId(), honey);

		Product almond = new Product(2, "Almond");
		productRepo.put(almond.getId(), almond);
	}

	@RequestMapping
	public ResponseEntity<Object> getProducts() {
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

	public ResponseEntity<Object> getProduct(
			@RequestParam(value = "name", required = false, defaultValue = "honey") String name) {
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productRepo.put(product.getId(), product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		productRepo.remove(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

}
