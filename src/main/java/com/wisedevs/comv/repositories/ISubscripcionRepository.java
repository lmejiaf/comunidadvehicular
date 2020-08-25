package com.wisedevs.comv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wisedevs.comv.entities.Subscripcion;

@Repository
public interface ISubscripcionRepository extends JpaRepository<Subscripcion, Long>{

}
