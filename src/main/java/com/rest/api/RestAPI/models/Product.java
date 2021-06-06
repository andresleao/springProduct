package com.rest.api.RestAPI.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Não pode ser vazio")
	@NotBlank(message = "Precisa ter um valor")
	@Size(min = 4, max = 255)
	private String name;
	
	@NotNull(message = "Não pode ser vazio")
	@Min(value = 0)
	@Max(value = 1000)
	private Integer qtd;
	
	private Date dateCreated;
	
	public Product() {
		
	}
	
	public Product(String name, Integer qtd) {
		this.name = name;
		this.qtd = qtd;
	}
	
	@PrePersist
	public void onPrePersist() {
		if (this.dateCreated == null) {
			this.dateCreated = new Date();
		}
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQtd() {
		return qtd;
	}
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Override
	public String toString() {
		return "{ id: " + this.id + ", name: " + this.name + ", qtd: " + this.qtd + ", dateCreated: "  + this.dateCreated + " }";
	}	
	
}
