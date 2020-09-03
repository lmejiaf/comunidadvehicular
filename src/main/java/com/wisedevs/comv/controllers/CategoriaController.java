package com.wisedevs.comv.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisedevs.comv.entities.Categoria;
import com.wisedevs.comv.entities.Empresa;
import com.wisedevs.comv.services.CategoriaServiceImpl;

@RestController
@RequestMapping("/admin")
public class CategoriaController {

	@Autowired
	private CategoriaServiceImpl categoriaService;
	

	@PostMapping("/categoria/crear")
	public ResponseEntity<String> save(@Valid @RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
	}
	
	@GetMapping("/categoria/obtenerPorNombre/{nombre}")
	public Categoria obtenerCategoriaNombre(@PathVariable("nombre") String nombre) {
		return categoriaService.findbyNombre(nombre).get();
	}
	
	@GetMapping("/categoria/obtenerPorId/{id}")
	public Categoria obtenerCategoriaId(@PathVariable("id") Long id) {
		Optional<Categoria> cat = categoriaService.findbyId(id);
		if(cat.isPresent())
			return cat.get();
		return null;
	}

	
	@PutMapping("/categoria/actualizar")
	public ResponseEntity<String> actualizar(@Valid @RequestBody Categoria categoria) {
        return categoriaService.actualizar(categoria);
	}
	
	@DeleteMapping("/categoria/eliminarPorId/{id}")
	public ResponseEntity<String> eliminar(@PathVariable("id") Long id){
		categoriaService.delete(id);
		return new ResponseEntity<>("Categoria eliminada correctamente", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/categoria/listar")
	public List<Categoria> listar(){
		return categoriaService.findAll();
	}
	
	@GetMapping("/categoria/{id}/listarEmpresas")
	public List<Empresa> listarEmpresas(@PathVariable("id") Long id){
		 Optional<Categoria> categoria = categoriaService.findbyId(id);
		 if(categoria.isPresent()) {
			 return categoria.get().getEmpresas();
		 }
		 return null;
	}
	
	
}
