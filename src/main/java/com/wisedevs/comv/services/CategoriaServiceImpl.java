package com.wisedevs.comv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisedevs.comv.entities.Categoria;
import com.wisedevs.comv.entities.Empresa;
import com.wisedevs.comv.repositories.ICategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

	@Autowired
	private ICategoriaRepository categoriaRepositorio;
	

	@Override
	@Transactional
	public ResponseEntity<String> save(Categoria categoria) {
		
		if (categoriaRepositorio.existsByNombre(categoria.getNombre())) {
			return new ResponseEntity<>("Ya existe una categoria con este nombre", HttpStatus.BAD_REQUEST);
		}
		
		categoriaRepositorio.save(categoria);
		return new ResponseEntity<>("Categoria creada correctamente", HttpStatus.CREATED);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Categoria> findbyId(Long id) {
		return categoriaRepositorio.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Categoria> findbyNombre(String nombre) {
		return categoriaRepositorio.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return categoriaRepositorio.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Optional<Categoria> cat = categoriaRepositorio.findById(id);
		if(cat.isPresent()) {
			Categoria categoria = cat.get();
			
			for(Empresa e:categoria.getEmpresas()) {
				e.setCategoria(null);
			}
			categoria.setEmpresas(null);
			categoriaRepositorio.delete(categoria);	
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByNombre(String nombre) {
		return categoriaRepositorio.existsByNombre(nombre);
	}

	@Override
	@Transactional
	public ResponseEntity<String> actualizar(Categoria categoria) {

		if(categoria.getId()==null)
            return new ResponseEntity<>("Para actualizar info de una categoria se debe enviar su id", HttpStatus.BAD_REQUEST);

		
		if (categoria.getNombre()!=null && categoriaRepositorio.existsByNombre(categoria.getNombre())) {
			return new ResponseEntity<>("Ya existe una categoria con este nombre", HttpStatus.BAD_REQUEST);
		}
		Categoria oldCategoriaInfo = categoriaRepositorio.findById(categoria.getId()).get();
		
		if(categoria.getNombre()==null) //si en el json no viene esta informacion, la info se toma de la oldempresa
			categoria.setNombre(oldCategoriaInfo.getNombre());
	

		categoriaRepositorio.save(categoria);
		return new ResponseEntity<>("Categoria actualizada correctamente", HttpStatus.CREATED);
		
	}
	
}
