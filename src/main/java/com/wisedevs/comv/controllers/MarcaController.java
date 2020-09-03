package com.wisedevs.comv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wisedevs.comv.entities.Marca;
import com.wisedevs.comv.services.IMarcaService;

@RestController
@RequestMapping("/admin")
public class MarcaController {
	
	@Autowired
	private IMarcaService MarcaService;
	
	@GetMapping("/marca/listar")
	public List<Marca> listar(){
		return MarcaService.listar();
	}
	
	@GetMapping("/marca/ObtenerPorNombre/{nombre}")
	public Marca ObtenerPorNombre(@PathVariable(name="nombre") String nombre) {
		return MarcaService.findByNombre(nombre);
	}
	
	
	@GetMapping("/marca/ObtenerPorId/{id}")
	public Marca ObtenerPorId(@PathVariable(name="id") Long id) {
		return MarcaService.findById(id);
	}
	
	@PostMapping("/marca/crear")
	public ResponseEntity<String> crear(@RequestParam(required = false) MultipartFile file,@RequestParam String nombre) {
		Marca marca = new Marca();
		marca.setNombre(nombre);
		return MarcaService.save(marca, file);
	}
	
	@PutMapping("/marca/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam(required = false) MultipartFile file,
			@RequestParam(required = false) String nombre, @RequestParam(required = false) Long id) {
		Marca marca = new Marca();
		marca.setNombre(nombre);
		marca.setId(id);
		return MarcaService.actualizar(marca, file);
	}
	
	@DeleteMapping("marca/eliminarPorId/{id}")
	public void deleteById(@PathVariable(name="id") long id ) {
		MarcaService.deleteById(id);
	}
	
	@DeleteMapping("marca/eliminarPorNombre/{nombre}")
	public void deleteByNombre(@PathVariable(name="nombre") String nombre ) {
		MarcaService.deleteByNombre(nombre);
	}
	
	@PutMapping("/marca/agregarEmpresaAMarca/{empresa}/{marca}")
	public ResponseEntity<String> agregarEmpresaAMarca(@PathVariable("empresa") String empresa, @PathVariable("marca") String marca){
		return MarcaService.agregarEmpresaAMarca(marca, empresa);
	}
	
	@PutMapping("/marca/eliminarEmpresaDeMarca/{empresa}/{marca}")
	public ResponseEntity<String> deleteEmpresaAMarca(@PathVariable("empresa") String empresa, @PathVariable("marca") String marca){
		return MarcaService.deleteEmpresaDeMarca(marca, empresa);
	}
	
}
