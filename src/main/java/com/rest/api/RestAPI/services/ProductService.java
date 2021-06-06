package com.rest.api.RestAPI.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rest.api.RestAPI.models.Product;

public interface ProductService {

	public List<Product> findAll();
	public Product find(Long id);
	public Product create(Product product);
	public Product update(Long id, Product product);
	public void delete(Long id);
}
