package com.wisedevs.comv.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name="categorias")
public class Categoria implements Serializable{
    

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @NotNull
    private String nombre;
    
    @JsonIgnoreProperties("categoria")
    @OneToMany(mappedBy = "categoria")
    private List<Empresa> empresas;

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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	  public List<Empresa> getEmpresas() { return empresas; }
	  
	  
	  public void setEmpresas(List<Empresa> empresas) { this.empresas = empresas; }
	 


	private static final long serialVersionUID = -5435227294074820342L;
    
}
