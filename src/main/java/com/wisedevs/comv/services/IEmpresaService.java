package com.wisedevs.comv.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.wisedevs.comv.entities.Empresa;

public interface IEmpresaService {

	public ResponseEntity<String> save(Empresa empresa, String categoria);
	public ResponseEntity<String> actualizar(Empresa empresa, String categoria, BindingResult bindingResult);
	public void delete(Empresa empresa);
	public void deleteById(Long id);
	public List<Empresa> listar();
	public Empresa findByNombre(String nombre);
	public Empresa findById(Long id);
	boolean existsByNombre(String nombre);
}
