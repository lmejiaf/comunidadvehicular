package com.wisedevs.comv.services;

import java.io.IOException;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.wisedevs.comv.entities.Empresa;

public interface IEmpresaService {

	public ResponseEntity<String> save(Empresa empresa, String categoria,MultipartFile file) throws IOException;
	public ResponseEntity<String> actualizar(Empresa empresa, String categoria, MultipartFile file) throws IOException;
	public void delete(Empresa empresa);
	public void deleteById(Long id);
	public List<Empresa> listar();
	public Empresa findByNombre(String nombre);
	public Empresa findById(Long id);
	boolean existsByNombre(String nombre);
}
