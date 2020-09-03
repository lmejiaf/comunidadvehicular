package com.wisedevs.comv.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="marcas")
public class Marca implements Serializable{


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	private String nombre;
	
    @JoinTable(
            name = "empresas_marcas",
            joinColumns = @JoinColumn(name = "empresa_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="marca_id", nullable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames =  {"empresa_id", "marca_id"})}
        )
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference
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


	public List<Empresa> getEmpresas() {
		return empresas;
	}


	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}


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
	
	
	private static final long serialVersionUID = 1561408263111291106L;
}
