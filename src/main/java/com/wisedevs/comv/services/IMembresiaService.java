package com.wisedevs.comv.services;

import java.util.List;

import com.wisedevs.comv.entities.Membresia;

public interface IMembresiaService {

	public void crear(String nombre, String... items);
	public List<Membresia> listar();
}
