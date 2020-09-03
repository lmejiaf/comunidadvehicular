package com.wisedevs.comv.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wisedevs.comv.entities.Empresa;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Long>{

	Optional<Empresa> findByNombre(String nombre);
	boolean existsByNombre(String nombre);
}
