package com.wisedevs.comv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wisedevs.comv.entities.Membresia;

@Repository
public interface IMembresiaRepository extends JpaRepository<Membresia, Long>{

}
