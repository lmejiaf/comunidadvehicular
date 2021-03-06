package com.wisedevs.comv.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

@Entity
@Table(name="empresas")
public class Empresa implements Serializable{
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinColumn(name="categoria_id", nullable=false)
    @JsonBackReference
    private Categoria categoria;
    
    //@OneToOne
    //private Configuracion configuracion;
    @NotNull
    private boolean sucursal;
    
    @NotNull
    private boolean marca;
    
    @NotNull
    private boolean turno;
    
    @NotNull
    private boolean activa;
    
    @NotBlank
    @NotNull
    private String nombre;
    
    @Column
    private String BigImage;
    
    @Column
    private String MediumImage;
    
    @Column
    private String SmallImage;
    

	public String getBigImage() {
		return BigImage;
	}


	public void setBigImage(String bigImage) {
		BigImage = bigImage;
	}


	public String getMediumImage() {
		return MediumImage;
	}


	public void setMediumImage(String mediumImage) {
		MediumImage = mediumImage;
	}


	public String getSmallImage() {
		return SmallImage;
	}


	public void setSmallImage(String samllImage) {
		SmallImage = samllImage;
	}
    
  
	public boolean isSucursal() {
		return sucursal;
	}



	public void setSucursal(boolean sucursal) {
		this.sucursal = sucursal;
	}



	public boolean isMarca() {
		return marca;
	}



	public void setMarca(boolean marca) {
		this.marca = marca;
	}



	public boolean isTurno() {
		return turno;
	}



	public void setTurno(boolean turno) {
		this.turno = turno;
	}



	public boolean isActiva() {
		return activa;
	}



	public void setActiva(boolean activa) {
		this.activa = activa;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	private static final long serialVersionUID = -3121912795942968070L;
}