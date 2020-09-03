package com.wisedevs.comv.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	
	

	
	@DeleteMapping("/empresa/eliminarImagen/{id}")
	public void deleteImage(@PathVariable Long id, HttpServletResponse response) {
		if(EmpresaService.findById(id)==null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
		}else {
		imagenService.delete(id, "Empresa");
		}
	}
	
	@PutMapping("/empresa/actualizarImagen/{id}")
	public Imagen UpdateImage(@RequestParam MultipartFile file, @PathVariable Long id) 
			throws IOException {
		Empresa emp = EmpresaService.findById(id);
		if(emp==null) {
			return null;
		}else {
			imagenService.delete(id, "Empresa");
			return imagenService.save(file, emp.getNombre(), "Empresa");			
		}

	}
	
	@GetMapping("/empresa/listar")
	public List<Empresa> listar(){
		return EmpresaService.listar();
	}
	
	@GetMapping("/empresa/buscarPorId/{id}")
	public Empresa buscarId(@PathVariable("id") Long id ) {
		return EmpresaService.findById(id);
	}
	
	@GetMapping("/empresa/buscarPorNombre/{nombre}")
	public Empresa buscarId(@PathVariable("nombre") String nombre ) {
		return EmpresaService.findByNombre(nombre);
	}
	
	@PostMapping("/empresa/crear")
	public ResponseEntity<String> save(@RequestParam MultipartFile file,
			@RequestParam String nombre, @RequestParam boolean activa,
			@RequestParam boolean sucursal, @RequestParam boolean marca,
			@RequestParam boolean turno, HttpServletResponse response, 
		    @RequestParam(required=false) String categoria) throws IOException {
		
			Empresa empresa = new Empresa(sucursal, marca, turno, activa, nombre);
	
			return EmpresaService.save(empresa, categoria, file);
			
	}
	
	@PutMapping("/empresa/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam(required=false) MultipartFile file,
			@RequestParam(required=false) String nombre, @RequestParam(required=false) boolean activa,
			@RequestParam(required=false) boolean sucursal, @RequestParam(required=false) boolean marca,
			@RequestParam(required=false) boolean turno, @RequestParam(required=false) String categoria,
			@RequestParam(required=false) Long id) throws IOException {
		
		Empresa empresa = new Empresa(sucursal, marca, turno, activa, nombre);
		empresa.setId(id);
		
		return EmpresaService.actualizar(empresa, categoria, file);
	}
	
	
	@DeleteMapping("/empresa/eliminarPorId/{id}")
	public void delete(@PathVariable("id") Long id ) {
		EmpresaService.deleteById(id);
	}
	
	@DeleteMapping("/empresa/eliminarPorNombre/{nombre}")
	public void deleteNombre(@PathVariable("nombre") String nombre ) {
		Empresa empresa = EmpresaService.findByNombre(nombre);
		if(empresa!=null)
			EmpresaService.delete(empresa);
	}
	
	
	@GetMapping("/empresa/obtenerCategoria/{nombre}")
	public String obtenerCategoria(@PathVariable("nombre") String nombre) {
		Empresa emp = EmpresaService.findByNombre(nombre);
		if(emp==null) 
			return "No existe una categoría con el nombre "+nombre;
		if(emp.getCategoria()==null) 
			return "Esta empresa actualmente no pertenece a una categoría";
		
		return emp.getCategoria().toString();
	}
	
	
	
}
