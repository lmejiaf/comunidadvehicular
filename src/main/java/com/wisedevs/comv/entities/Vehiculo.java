package com.wisedevs.comv.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Vehiculo implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	private String marca;
	
	@NotNull
	@NotBlank
	private String patente;
	
	@NotNull
	@NotBlank
	private String modelo;
	
	@NotNull
	@NotBlank
	private Integer anho;
	
	@OneToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
