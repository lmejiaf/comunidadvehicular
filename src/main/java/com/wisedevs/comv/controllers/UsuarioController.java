package com.wisedevs.comv.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@GetMapping("/hola")
	public String hola() {
		return "Hola mundo";
	}
	

}
