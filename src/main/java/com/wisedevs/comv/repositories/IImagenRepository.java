package com.wisedevs.comv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wisedevs.comv.entities.Imagen;


@Repository
public interface IImagenRepository  extends JpaRepository<Imagen, Long>{

	public Imagen findByName(String name);
	
	public Imagen findByPath(String path);
	
	public void deleteByName(String name);
	
	public void deleteByPath(String path);
	
	
}
