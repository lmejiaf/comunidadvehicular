package com.wisedevs.comv.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisedevs.comv.entities.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long>{

	Optional<Categoria> findByNombre(String nombre);
	boolean existsByNombre(String nombre);
}
