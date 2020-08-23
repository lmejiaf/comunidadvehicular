package com.wisedevs.comv.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pago implements Serializable{

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	private String idPago;
	
	@OneToOne(mappedBy="pago",cascade= CascadeType.ALL)
	private Subscripcion subscripcion;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
