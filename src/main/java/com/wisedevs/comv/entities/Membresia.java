package com.wisedevs.comv.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class Membresia implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	private String nombre;
	
	@NotNull
	@NotBlank
	private Integer precio;
	
	@OneToMany(mappedBy="membresia",cascade= CascadeType.ALL)
	private List<Subscripcion> subscripcion;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "membresia")
	private List<Item> items;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public List<Subscripcion> getSubscripcion() {
		return subscripcion;
	}


	public void setSubscripcion(List<Subscripcion> subscripcion) {
		this.subscripcion = subscripcion;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	


	public Integer getPrecio() {
		return precio;
	}


	public void setPrecio(Integer precio) {
		this.precio = precio;
	}


	public Membresia(Long id, @NotNull @NotBlank String nombre, @NotNull @NotBlank Integer precio,
			List<Subscripcion> subscripcion, List<Item> items) {
		
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.subscripcion = subscripcion;
		this.items = items;
	}


	public Membresia() {
		
	}
	
	
	
}
