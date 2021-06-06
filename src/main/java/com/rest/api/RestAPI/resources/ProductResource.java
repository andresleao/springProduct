package com.rest.api.RestAPI.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.RestAPI.models.Product;
import com.rest.api.RestAPI.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	private ProductService productService;
	
	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

// FIND ALL
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findAll() {
		List<Product> list = this.productService.findAll();
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}
	
// FIND
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> find(@PathVariable(value = "id") Long id) {
		Product product = this.productService.find(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}	
	
// CREATE	
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {
		if (!errors.hasErrors()) {
			Product productCreated = productService.create(product);
			//return ResponseEntity.ok(productCreated);
			return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
		}
		
		return ResponseEntity
				.badRequest()
				.body(errors
					.getAllErrors()
					.stream()
					.map(msg -> msg.getDefaultMessage())
					.collect(Collectors.joining(",")));	
	}
	
// UPDATE
	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Product product, Errors errors) {
		
		if (!errors.hasErrors()) {
			Product productUpdated = this.productService.update(id, product);
			return new ResponseEntity<Product>(productUpdated, HttpStatus.OK);
		}
		
		return ResponseEntity
				.badRequest()
				.body(errors
					.getAllErrors()
					.stream()
					.map(msg -> msg.getDefaultMessage())
					.collect(Collectors.joining(",")));		
	}
	
// DELETE
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") Long id, HttpServletResponse response) {
		this.productService.delete(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
}
