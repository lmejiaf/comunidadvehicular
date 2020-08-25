package com.wisedevs.comv.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import com.wisedevs.comv.entities.Empresa;
import com.wisedevs.comv.entities.Imagen;
import com.wisedevs.comv.services.IEmpresaService;
import com.wisedevs.comv.services.ImagenServiceImpl;

@RestController
@RequestMapping("/admin")
public class EmpresaController {

	@Autowired
	private IEmpresaService EmpresaService;
	
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	
	@PostMapping("/empresa/upload/{id}")
	public Imagen uploadFile(@RequestParam MultipartFile file, HttpServletResponse response, @PathVariable Long id) 
			throws IOException {
		
		if(EmpresaService.findById(id)==null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			return null;
		}
		return imagenService.save(file, response, id, "Empresa");
		
	}
	
	@DeleteMapping("/empresa/eliminarImagen/{id}")
	public void deleteImage(@PathVariable Long id, HttpServletResponse response) {
		if(EmpresaService.findById(id)==null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
		}else {
		imagenService.delete(id, "Empresa");
		}
	}
	
	@PutMapping("/empresa/actualizarImagen/{id}")
	public Imagen UpdateImage(@RequestParam MultipartFile file, HttpServletResponse response, @PathVariable Long id) 
			throws IOException {
		
		if(EmpresaService.findById(id)==null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			return null;
		}else {
			imagenService.delete(id, "Empresa");
			return imagenService.save(file, response, id, "Empresa");			
		}

	}
	
	@GetMapping("/empresa/listar")
	public List<Empresa> listar(){
		return EmpresaService.listar();
	}
	
	@GetMapping("/empresa/buscarbyid/{id}")
	public Empresa buscarId(@PathVariable("id") Long id ) {
		return EmpresaService.findById(id);
	}
	
	@GetMapping("/empresa/buscarbynombre/{nombre}")
	public Empresa buscarId(@PathVariable("nombre") String nombre ) {
		return EmpresaService.findByNombre(nombre);
	}
	
	@PostMapping("/empresa/crear/pertenece/{categoria}")
	public ResponseEntity<String> save(@RequestBody Empresa empresa, @PathVariable("categoria") String categoria) {
	
			return EmpresaService.save(empresa, categoria);

	}
	
	@PutMapping("/empresa/actualizar/pertenece/{categoria}")
	public ResponseEntity<String> actualizar(@RequestBody Empresa empresa, @PathVariable("categoria") String categoria,
			BindingResult bindingResult) {
		
		return EmpresaService.actualizar(empresa, categoria, bindingResult);
	}
	
	
	@DeleteMapping("/empresa/delete/{id}")
	public void delete(@PathVariable("id") Long id ) {
		EmpresaService.deleteById(id);
	}
	
	@DeleteMapping("/empresa/deletebynombre/{nombre}")
	public void deleteNombre(@PathVariable("nombre") String nombre ) {
		Empresa empresa = EmpresaService.findByNombre(nombre);
		if(empresa!=null)
			EmpresaService.delete(empresa);
	}
	
	
	
	
	
	
}
