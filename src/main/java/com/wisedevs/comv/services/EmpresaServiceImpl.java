package com.wisedevs.comv.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wisedevs.comv.entities.Categoria;
import com.wisedevs.comv.entities.Empresa;
import com.wisedevs.comv.entities.Marca;
import com.wisedevs.comv.repositories.IEmpresaRepository;

@Service
public class EmpresaServiceImpl implements IEmpresaService{

	@Autowired
	private IEmpresaRepository EmpresaRepository;
	
	@Autowired
	private ICategoriaService CategoriaService;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	@Override
	@Transactional
	public ResponseEntity<String> save(Empresa empresa, String categoria,MultipartFile file)
			throws IOException {
	
		
		if (EmpresaRepository.existsByNombre(empresa.getNombre())) {
			return new ResponseEntity<>("Ya existe una empresa con este nombre", HttpStatus.BAD_REQUEST);
		}
		
		if(categoria!=null) {
			Optional<Categoria> cat =  CategoriaService.findbyNombre(categoria);
			if (cat.isPresent()) {
				Categoria cate = cat.get();
				empresa.setCategoria(cate);
			}else {
				return new ResponseEntity<>("No existe una categoria con el nombre "+categoria, HttpStatus.BAD_REQUEST);
			}
		}
		EmpresaRepository.save(empresa);
		imagenService.save(file, empresa.getNombre(), "Empresa");	
		return new ResponseEntity<>("Empresa creada correctamente", HttpStatus.CREATED);
		
	}
	
	@Override
	@Transactional
	public ResponseEntity<String> actualizar(Empresa empresa, String categoria, 
			MultipartFile file) throws IOException {

		
		if(empresa.getId()==null)
            return new ResponseEntity<>("Para actualizar info de una empresa se debe enviar su id", HttpStatus.BAD_REQUEST);

		
		if (empresa.getNombre()!=null && EmpresaRepository.existsByNombre(empresa.getNombre())) {
			return new ResponseEntity<>("Ya existe una empresa con este nombre", HttpStatus.BAD_REQUEST);
		}
		Optional<Empresa> OoldEmpresaInfo = EmpresaRepository.findById(empresa.getId());
		
		if(!OoldEmpresaInfo.isPresent()) {
			return new ResponseEntity<>("No existe una empresa con este id", HttpStatus.BAD_REQUEST);
		}
		Empresa oldEmpresaInfo = OoldEmpresaInfo.get();
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
		empresa.setMediumImage(oldEmpresaInfo.getMediumImage());
		empresa.setSmallImage(oldEmpresaInfo.getSmallImage());	

		if(categoria!=null) {
			Optional<Categoria> cat = CategoriaService.findbyNombre(categoria);
		
			if (cat.isPresent()) {
				empresa.setCategoria(cat.get());
			}else {
				return new ResponseEntity<>("No existe una categoria con el nombre "+categoria, HttpStatus.BAD_REQUEST);
			}
		}
		EmpresaRepository.save(empresa);
		if(file!=null && !file.isEmpty()) {
			imagenService.delete(empresa.getId(), "Empresa");
			imagenService.save(file, empresa.getNombre(), "Empresa");	
		}
		
		
		return new ResponseEntity<>("Empresa actualizada correctamente", HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public void delete(Empresa empresa) {
		imagenService.delete(empresa.getId(), "Empresa");
		for(Marca m: empresa.getMarcas()) {
			m.setEmpresas(null);
		}
		empresa.setMarcas(null);
		EmpresaRepository.delete(empresa);
		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		Optional<Empresa> empresaOp = EmpresaRepository.findById(id);
		if(empresaOp.isPresent()) {
			imagenService.delete(id, "Empresa");
			Empresa empresa = empresaOp.get();
			for(Marca m: empresa.getMarcas()) {
				m.setEmpresas(null);
			}
			empresa.setMarcas(null);
			EmpresaRepository.deleteById(id);
		}
		
		
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
