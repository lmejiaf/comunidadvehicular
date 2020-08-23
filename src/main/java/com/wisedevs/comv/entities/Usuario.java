package com.wisedevs.comv.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario implements Serializable{

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	@NotBlank
	private String nombres;
	
	@NotNull
	@NotBlank
	private String apellidos;
	
	@NotNull
	@NotBlank
	private String password;
	
	@OneToOne(mappedBy="usuario",cascade= CascadeType.ALL)
	private Vehiculo vehiculo;
	
	@OneToOne(mappedBy="usuario",cascade= CascadeType.ALL)
	private Subscripcion subscripcion;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
