package com.rest.api.RestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.api.RestAPI.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
