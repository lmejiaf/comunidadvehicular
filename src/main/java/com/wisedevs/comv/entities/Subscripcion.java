package com.wisedevs.comv.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wisedevs.comv.enums.SubEstado;

@Entity
public class Subscripcion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	@ManyToOne
	@JoinColumn(name="membresia_id", nullable=false)
	private Membresia membresia;
	
	
	@OneToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;
	
	
	@Enumerated(EnumType.STRING)
	private SubEstado estado;
	
	
	@OneToOne
	@JoinColumn(name="pago_id", nullable=false)
	private Pago pago;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
