package com.wisedevs.comv.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisedevs.comv.entities.Item;
import com.wisedevs.comv.entities.Membresia;
import com.wisedevs.comv.repositories.IItemRepository;
import com.wisedevs.comv.repositories.IMembresiaRepository;

@Service
@Transactional
public class MembresiaServiceImpl implements IMembresiaService {

	
	@Autowired
	private IMembresiaRepository memRepo;
	
	@Autowired
	private IItemRepository itemRepo;
	
	@Override
	public void crear(String nombre, String... items) {
		// TODO Auto-generated method stub
		Membresia m= new Membresia();
		
		List<Item> listItems= new ArrayList<>();
		
		for (String item : items) {	
			Item i= new Item();
			i.setDescripcion(item);
			i.setMembresia(m);
			itemRepo.save(i);
			listItems.add(i);
		}
		
		m.setItems(listItems);
		m.setNombre(nombre);
		
		memRepo.save(m);
		
		
		
		
	}

	@Override
	public List<Membresia> listar() {
		// TODO Auto-generated method stub
		return memRepo.findAll();
	}

	
}
