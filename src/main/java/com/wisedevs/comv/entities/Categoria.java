package com.wisedevs.comv.entities;

import java.io.Serializable;
import java.util.List;

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
	 


	@Override
	public String toString() {
		return "Información de la categoría:   Id: "+id+", Nombre: "+nombre+", Numero de empresas: "+empresas.size();
	}



	private static final long serialVersionUID = -5435227294074820342L;
    
}
