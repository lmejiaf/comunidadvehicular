package com.wisedevs.comv.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.wisedevs.comv.entities.Imagen;



public interface IImagenService {

	public Imagen findByName(String name);  
	
	public Imagen findByPath(String path);
	
	public Imagen save(MultipartFile file, HttpServletResponse response, Long id, String entidad) throws IOException;
	
	public void delete(Long id, String entidad);
		
}
