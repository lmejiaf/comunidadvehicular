package com.wisedevs.comv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wisedevs.comv.services.IMembresiaService;

@Controller
@RequestMapping("/admin")
public class MembresiaController {
	
	@Autowired
	private IMembresiaService memServ;
	
	@GetMapping("/membresias/index")
	public String index(Model model) {
		
		model.addAttribute("titulo","membresias");
		
		return "membresias/index";
		
		
	}
	@GetMapping("/membresias/crear")
	public String crear(Model model) {
		
		model.addAttribute("titulo","crea una membresia");
		return "membresias/crear";
		
		
	}
	
	@GetMapping("/membresias/acciones/crear")
	public void crear(@RequestParam String nombre, @RequestParam String... items) {
		
		
		
		
		
	}
	
	
}
