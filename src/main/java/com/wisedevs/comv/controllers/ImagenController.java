package com.wisedevs.comv.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.wisedevs.comv.entities.Imagen;
import com.wisedevs.comv.services.ImagenServiceImpl;


@RestController
public class ImagenController {
	
	@Autowired
	private ImagenServiceImpl imagenService;
	

	@GetMapping("/admin/imagen/buscarPorNombre/{nombre}")
	public Imagen obtenerImagenNombre(@PathVariable String nombre) {
		return imagenService.findByName(nombre);
	}
	
	@GetMapping("/admin/imagen/buscarPorPath/{path}")
	public Imagen obtenerImagenPath(@PathVariable String path) {
		return imagenService.findByPath(path);
	}
	
	
	



}
