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

import com.wisedevs.comv.entities.Empresa;
import com.wisedevs.comv.entities.Marca;
import com.wisedevs.comv.repositories.IEmpresaRepository;
import com.wisedevs.comv.repositories.IMarcaRepository;

@Service
public class MarcaServiceImpl implements IMarcaService{

	@Autowired
	private IMarcaRepository MarcaRepository;
	
	@Autowired
	private IEmpresaRepository EmpresaRepository;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Marca> listar() {
		return MarcaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Marca findById(Long id) {
		return MarcaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Marca findByNombre(String nombre) {
		return MarcaRepository.findByNombre(nombre);
	}

	@Override
	@Transactional
	public ResponseEntity<String> save(Marca marca, MultipartFile file) {
		if (MarcaRepository.existsByNombre(marca.getNombre())) {
			return new ResponseEntity<>("Ya existe una marca con este nombre", HttpStatus.BAD_REQUEST);
		}
		
		MarcaRepository.save(marca);
		if(file!=null && !file.isEmpty()) {
			try {
				imagenService.save(file, marca.getNombre(), "Marca");
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return new ResponseEntity<>("Marca creada correctamente", HttpStatus.CREATED);
		
	}

	@Override
	@Transactional
	public ResponseEntity<String> actualizar(Marca marca, MultipartFile file) {
		
		if(marca.getId()==null)
            return new ResponseEntity<>("Para actualizar info de una marca se debe enviar su id", HttpStatus.BAD_REQUEST);

		if (marca.getNombre()!=null && MarcaRepository.existsByNombre(marca.getNombre())) 
			return new ResponseEntity<>("Ya existe una marca con este nombre", HttpStatus.BAD_REQUEST);
		
		Optional<Marca> oldMarcaInfoOp = MarcaRepository.findById(marca.getId());
		if(!oldMarcaInfoOp.isPresent()) {
			return new ResponseEntity<>("No existe una marca con este id", HttpStatus.BAD_REQUEST);
		}
		Marca oldMarcaInfo = oldMarcaInfoOp.get();
		if(marca.getNombre()==null) //si en el json no viene esta informacion, la info se toma de la oldempresa
			marca.setNombre(oldMarcaInfo.getNombre());
	
		
		marca.setBigImage(oldMarcaInfo.getBigImage());
		marca.setMediumImage(oldMarcaInfo.getMediumImage());
		marca.setSmallImage(oldMarcaInfo.getSmallImage());
		marca.setEmpresas(oldMarcaInfo.getEmpresas());

		MarcaRepository.save(marca);
		if(file!=null && !file.isEmpty()) {
			try {
				imagenService.delete(marca.getId(), "Marca");
				imagenService.save(file, marca.getNombre(), "Marca");
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return new ResponseEntity<>("Marca actualizada correctamente", HttpStatus.CREATED);
		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Optional<Marca> marcaOp = MarcaRepository.findById(id);
		if(marcaOp.isPresent()) {
			imagenService.delete(id, "Marca");
			MarcaRepository.deleteById(id);
		}
		
		
	}

	@Override
	@Transactional
	public void deleteByNombre(String name) {
		
		Marca marca = MarcaRepository.findByNombre(name);
		if(marca!=null) {
			imagenService.delete(marca.getId(), "Marca");
			MarcaRepository.delete(marca);
		}
		
	}

	@Override
	@Transactional
	public ResponseEntity<String> agregarEmpresaAMarca(String marcaNombre, String empresaNombre) {
		
		Marca marca = MarcaRepository.findByNombre(marcaNombre);
		if(marca==null) 
			return new ResponseEntity<>("No existe una marca con el nombre "+ marcaNombre, HttpStatus.BAD_REQUEST);
		
		Optional<Empresa> empresaOp = EmpresaRepository.findByNombre(empresaNombre);
		if(!empresaOp.isPresent()) 
			return new ResponseEntity<>("No existe una empresa con el nombre "+ empresaNombre, HttpStatus.BAD_REQUEST);
		
		Empresa empresa = empresaOp.get();
		if(marca.getEmpresas().add(empresa)) {
			marca.setEmpresas(marca.getEmpresas());
			if(empresa.getMarcas().add(marca)) {
				empresa.setMarcas(empresa.getMarcas());
				return new ResponseEntity<>("Se agrego la empresa "+empresaNombre+" a la marca "+marcaNombre, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("Error al agregar la empresa a esta marca", HttpStatus.BAD_REQUEST);

	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteEmpresaDeMarca(String marcaNombre, String empresaNombre) {
		Marca marca = MarcaRepository.findByNombre(marcaNombre);
		if(marca==null) 
			return new ResponseEntity<>("No existe una marca con el nombre "+ marcaNombre, HttpStatus.BAD_REQUEST);
		
		Empresa empresa = EmpresaRepository.findByNombre(empresaNombre).get();
		if(empresa==null) 
			return new ResponseEntity<>("No existe una empresa con el nombre "+ empresaNombre, HttpStatus.BAD_REQUEST);
		
		if(marca.getEmpresas().remove(empresa)) {
			marca.setEmpresas(marca.getEmpresas());
			
			return new ResponseEntity<>("Se elimino la empresa "+empresaNombre+" de la marca "+marcaNombre, HttpStatus.OK);
		}
		return new ResponseEntity<>("Error al eliminar la empresa de esta marca", HttpStatus.BAD_REQUEST);
		
	}
	
	

}
