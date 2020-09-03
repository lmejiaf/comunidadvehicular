package com.wisedevs.comv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wisedevs.comv.entities.Marca;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Long>{

	public Marca findByNombre(String nombre);
	public void deleteByNombre(String nombre);
	public boolean existsByNombre(String nombre);
}
