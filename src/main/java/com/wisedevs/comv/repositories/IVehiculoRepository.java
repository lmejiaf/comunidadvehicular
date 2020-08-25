package com.wisedevs.comv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wisedevs.comv.entities.Vehiculo;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Long>{

}
