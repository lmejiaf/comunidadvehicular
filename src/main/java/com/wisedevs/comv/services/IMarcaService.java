package com.wisedevs.comv.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.wisedevs.comv.entities.Marca;

public interface IMarcaService {

	public List<Marca> listar();
	public Marca findById(Long id);
	public Marca findByNombre(String nombre);
	public ResponseEntity<String> save(Marca marca, MultipartFile file);
	public ResponseEntity<String> actualizar(Marca marca, MultipartFile file);
	public void deleteById(Long id);
	public void deleteByNombre(String name);
	public ResponseEntity<String> agregarEmpresaAMarca(String marca, String empresa);
	public ResponseEntity<String> deleteEmpresaDeMarca(String marca, String empresa);
}
