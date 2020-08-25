package com.wisedevs.comv.services;


import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wisedevs.comv.entities.Categoria;
import com.wisedevs.comv.entities.Empresa;
import com.wisedevs.comv.entities.Imagen;
import com.wisedevs.comv.repositories.IImagenRepository;

import net.coobird.thumbnailator.Thumbnails;


@Service
public class ImagenServiceImpl implements IImagenService{

	@Autowired
	private IImagenRepository imagenRepository;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private IEmpresaService EmpresaService;
	
	
	@Override
	@Transactional(readOnly = true)
	public Imagen findByName(String name) {
		return imagenRepository.findByName(name);
	}

	@Override
	@Transactional
	public Imagen save(MultipartFile file, HttpServletResponse response, Long id, 
			String entidad) throws IOException {

		if(file == null || file.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			return null;//"Error: El archivo subido no exite"
		}
		
		
		//creeamos imagen tamaño grande
		StringBuilder builder = new StringBuilder();
		builder.append(System.getProperty("user.dir"));//ruta actual
		builder.append(File.separator);
		builder.append("src"+ File.separator+"main"+File.separator+"resources"+File.separator+"imagenes");
		builder.append(File.separator);
		
		String random = stringAleatorio(45);
		String BigName =  "BIG"+random+file.getOriginalFilename();
		String pathBig = builder.append(BigName).toString();
		
		byte[] fileBytes = file.getBytes();
		Path path = Paths.get(pathBig);
		Files.write(path, fileBytes);		
								
		MultipartFile file2 = file;
		
		File convFile = new File(pathBig);
		file2.transferTo(convFile);
		
		File outFile = new File(pathBig);
		Thumbnails.of(convFile).size(2440, 1480).toFile(outFile);
		
		Imagen imagen = new Imagen(BigName, file.getContentType(), pathBig);
		
		//creeamos imagen tamaño mediano
		StringBuilder builder3 = new StringBuilder();
		builder3.append(System.getProperty("user.dir"));
		builder3.append(File.separator);
		builder3.append("src"+ File.separator+"main"+File.separator+"resources"+File.separator+"imagenes");
		builder3.append(File.separator);
		
		String MediumName = "MEDIUM"+random+file.getOriginalFilename();
		String pathMedium = builder3.append(MediumName).toString();
		
		Path path3 = Paths.get(pathMedium);						
		Files.write(path3, fileBytes);
		
		File convFileee = new File(pathMedium);
		
		File outFileee = new File(pathMedium);
		Thumbnails.of(convFileee).size(1600, 1024).toFile(outFileee);
		
		Imagen imagen3 = new Imagen(MediumName, file.getContentType(), pathMedium);
		
		//creeamos imagen tamaño pequeño
		StringBuilder builder2 = new StringBuilder();
		builder2.append(System.getProperty("user.dir"));
		builder2.append(File.separator);
		builder2.append("src"+ File.separator+"main"+File.separator+"resources"+File.separator+"imagenes");
		builder2.append(File.separator);
		
		String SmallName = "SMALL"+random+file.getOriginalFilename();
		String pathSmall = builder2.append(SmallName).toString();
		
		Path path2 = Paths.get(pathSmall);						
		Files.write(path2, fileBytes);
		
		File convFilee = new File(pathSmall);
		
		File outFilee = new File(pathSmall);
		Thumbnails.of(convFilee).size(800, 600).toFile(outFilee);
		
		Imagen imagen2 = new Imagen(SmallName, file.getContentType(), pathSmall);
		
		//Guadamos segun la entidad
		switch (entidad) {
		case "Categoria":

			Categoria categoria = categoriaService.findbyId(id).get();
			categoria.setBigImage(BigName);
			categoria.setMediumImage(MediumName);
			categoria.setSmallImage(SmallName);
			categoriaService.save(categoria);
			
			break;
		case "Empresa":
			Empresa empresa = EmpresaService.findById(id);
			empresa.setBigImage(BigName);
			empresa.setMediumImage(MediumName);
			empresa.setSmallImage(SmallName);
			EmpresaService.save(empresa, empresa.getCategoria().getNombre());
			
			break;
			
		default://la entidad no existe
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			return null;
			
	}    
		imagenRepository.save(imagen);
		imagenRepository.save(imagen2);
		imagenRepository.save(imagen3);
		response.setStatus(HttpServletResponse.SC_OK); 
		return imagen;
		
	}
	
	private String stringAleatorio(int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray(); 
		StringBuilder sb = new StringBuilder(length); 
		Random random = new Random(); 
		for (int i = 0; i < length; i++) { 
		    char c = chars[random.nextInt(chars.length)]; 
		    sb.append(c); 
		} 
		return new String(sb.toString());
	}
	
	
	
	private void Eliminar(String ruta) {
		
		StringBuilder builder = new StringBuilder();
		builder.append(System.getProperty("user.dir"));//ruta actual
		builder.append(File.separator);
		builder.append("src"+ File.separator+"main"+File.separator+"resources"+File.separator+"imagenes");
		builder.append(File.separator);
		builder.append(ruta);
		
		Path path = Paths.get(builder.toString());
		try {
		    Files.delete(path);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	}

	
	public void eliminarImagen(String name) {
		Eliminar(name);
		imagenRepository.deleteByName(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Imagen findByPath(String path) {
		return imagenRepository.findByPath(path);
	}
	
	
	@Override
	@Transactional
	public void delete(Long id, String entidad) {
		switch (entidad) {
		case "Categoria":

			Categoria categoria = categoriaService.findbyId(id).get();
			if(categoria.getBigImage()!=null) {//verificamos que esta categoria si tenga imagenes
				eliminarImagen(categoria.getBigImage());
				categoria.setBigImage(null);
				
				eliminarImagen(categoria.getMediumImage());
				categoria.setMediumImage(null);
				
				eliminarImagen(categoria.getSmallImage());
				categoria.setSmallImage(null);
				
				categoriaService.save(categoria);
			}
			
			break;
		case "Empresa":
			Empresa empresa = EmpresaService.findById(id);
			if(empresa.getBigImage()!=null) {//verificamos que esta empresa si tenga imagenes (Evitamos null pointer)
				eliminarImagen(empresa.getBigImage());
				empresa.setBigImage(null);
				
				eliminarImagen(empresa.getMediumImage());
				empresa.setMediumImage(null);
				
				eliminarImagen(empresa.getSmallImage());
				empresa.setSmallImage(null);
				
				EmpresaService.save(empresa, empresa.getCategoria().getNombre());
			}
			break;
			
		default://la entidad no existe
				
		}
	}
	

}
