package com.wisedevs.comv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.wisedevs.comv.entities.Categoria;

public interface ICategoriaService {

	public ResponseEntity<String> save(Categoria categoria);
	public ResponseEntity<String> actualizar(Categoria categoria);
	public Optional<Categoria> findbyId(Long id);
	public Optional<Categoria> findbyNombre(String nombre);
	public boolean existsByNombre(String nombre);
	public List<Categoria> findAll();
	public void delete(Long id);
}
