package com.rest.api.RestAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rest.api.RestAPI.models.Product;
import com.rest.api.RestAPI.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}
	
	@Override
	public Product find(Long id) {
		return this.productRepository.findById(id).orElse(null);
	}

	@Override
	public Product create(Product product) {
		this.productRepository.save(product);
		return product;
	}
	
	@Override
	public Product update(Long id, Product product) {
		
		return this.productRepository.findById(id)
				.map(record ->{
					record.setName(product.getName());
					record.setQtd(product.getQtd());
					Product productUpdated = this.productRepository.save(record);
					return productUpdated;
				}).orElse(null);		
	}
	
	@Override
	public void delete(Long id) {
		Product product = productRepository.findById(id).orElse(null);
		
		if (product != null) {
			this.productRepository.delete(product);
		}
	}

}
