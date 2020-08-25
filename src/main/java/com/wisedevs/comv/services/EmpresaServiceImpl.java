package com.wisedevs.comv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.wisedevs.comv.entities.Empresa;
import com.wisedevs.comv.repositories.IEmpresaRepository;

@Service
public class EmpresaServiceImpl implements IEmpresaService{

	@Autowired
	private IEmpresaRepository EmpresaRepository;
	
	@Autowired
	private ICategoriaService CategoriaService;
	
	@Override
	@Transactional
	public ResponseEntity<String> save(Empresa empresa, String categoria) {
	
		
		if (EmpresaRepository.existsByNombre(empresa.getNombre())) {
			return new ResponseEntity<>("Ya existe una empresa con este nombre", HttpStatus.BAD_REQUEST);
		}
		
		if (CategoriaService.existsByNombre(categoria)) {
			empresa.setCategoria(CategoriaService.findbyNombre(categoria).get());
			EmpresaRepository.save(empresa);
			return new ResponseEntity<>("Empresa creada correctamente", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("No existe una categoria con el nombre "+categoria, HttpStatus.BAD_REQUEST);

		}
		
	}
	
	@Override
	@Transactional
	public ResponseEntity<String> actualizar(Empresa empresa, String categoria, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Campos con formato inválido", HttpStatus.BAD_REQUEST);
        }
		
		if(empresa.getId()==null)
            return new ResponseEntity<>("Para actualizar info de una empresa se debe enviar su id", HttpStatus.BAD_REQUEST);

		
		if (empresa.getNombre()!=null && EmpresaRepository.existsByNombre(empresa.getNombre())) {
			return new ResponseEntity<>("Ya existe una empresa con este nombre", HttpStatus.BAD_REQUEST);
		}
		Empresa oldEmpresaInfo = EmpresaRepository.findById(empresa.getId()).get();
		
		if(empresa.getNombre()==null) //si en el json no viene esta informacion, la info se toma de la oldempresa
			empresa.setNombre(oldEmpresaInfo.getNombre());
		if(!empresa.isMarca())
			empresa.setMarca(oldEmpresaInfo.isMarca());
		if(!empresa.isActiva())
			empresa.setActiva(oldEmpresaInfo.isActiva());
		if(!empresa.isSucursal())
			empresa.setSucursal(oldEmpresaInfo.isSucursal());
		if(!empresa.isTurno())
			empresa.setTurno(oldEmpresaInfo.isTurno());
		
		empresa.setBigImage(oldEmpresaInfo.getBigImage());
		empresa.setMediumImage(oldEmpresaInfo.getMediumImage());//desde este controlador no se puede actualizar imagenes
		empresa.setSmallImage(oldEmpresaInfo.getSmallImage());
		
		if (CategoriaService.existsByNombre(categoria)) {
			empresa.setCategoria(CategoriaService.findbyNombre(categoria).get());
			EmpresaRepository.save(empresa);
			return new ResponseEntity<>("Empresa actualizada correctamente", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("No existe una categoria con el nombre "+categoria, HttpStatus.BAD_REQUEST);

		}
	}

	@Override
	@Transactional
	public void delete(Empresa empresa) {
		EmpresaRepository.delete(empresa);
		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		EmpresaRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empresa> listar() {
		
		return EmpresaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Empresa findByNombre(String nombre) {
		return EmpresaRepository.findByNombre(nombre).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Empresa findById(Long id) {
		return EmpresaRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean existsByNombre(String nombre) {
		return EmpresaRepository.existsByNombre(nombre);
	}


	

}
