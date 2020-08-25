package com.wisedevs.comv.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wisedevs.comv.entities.Categoria;
import com.wisedevs.comv.entities.Empresa;
import com.wisedevs.comv.entities.Imagen;
import com.wisedevs.comv.services.CategoriaServiceImpl;
import com.wisedevs.comv.services.ImagenServiceImpl;

@RestController
@RequestMapping("/admin")
public class CategoriaController {

	@Autowired
	private CategoriaServiceImpl categoriaService;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	
	@PostMapping("/categoria/upload/{id}")//subir imagen
	public Imagen uploadFile(@RequestParam MultipartFile file, HttpServletResponse response, @PathVariable Long id) 
			throws IOException {
		
		Optional<Categoria> catOp = categoriaService.findbyId(id);
		if(!catOp.isPresent()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			return null;
		}
		return imagenService.save(file, response, id, "Categoria");
	}
	
	@DeleteMapping("/categoria/eliminarImagen/{id}")//eliminar imagen
	public void deleteImage(@PathVariable Long id, HttpServletResponse response) {
		
		Optional<Categoria> catOp = categoriaService.findbyId(id);
		if(!catOp.isPresent()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			
		}else {
		imagenService.delete(id, "Categoria");
		}
	}
	
	@PutMapping("/categoria/actualizarImagen/{id}")
	public Imagen UpdateImage(@RequestParam MultipartFile file, HttpServletResponse response, @PathVariable Long id) 
			throws IOException {
		
		Optional<Categoria> catOp = categoriaService.findbyId(id);
		if(!catOp.isPresent()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			return null;
		}
		imagenService.delete(id, "Categoria");
		return imagenService.save(file, response, id, "Categoria");
	}
	
	@GetMapping("/categoria/obtenerbynombre/{nombre}")
	public Categoria obtenerCategoriaNombre(@PathVariable("nombre") String nombre) {
		return categoriaService.findbyNombre(nombre).get();
	}
	
	@GetMapping("/categoria/obtenerbyid/{id}")
	public Categoria obtenerCategoriaId(@PathVariable("id") Long id) {
		return categoriaService.findbyId(id).get();
	}
	
	@PostMapping("/categoria/crear")
	public ResponseEntity<String> save(@Valid @RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
	}
	
	@PutMapping("/categoria/actualizar")
	public ResponseEntity<String> actualizar(@Valid @RequestBody Categoria categoria) {
        return categoriaService.actualizar(categoria);
	}
	
	@DeleteMapping("/categoria/eliminar/{id}")
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
		 Categoria categoria = categoriaService.findbyId(id).get();
		 if(categoria!=null) {
			 return categoria.getEmpresas();
		 }
		 return null;
	}
	
	
}
