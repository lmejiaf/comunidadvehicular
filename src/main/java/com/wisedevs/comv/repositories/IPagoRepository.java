package com.wisedevs.comv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wisedevs.comv.entities.Pago;

@Repository
public interface IPagoRepository extends JpaRepository<Pago, Long>{

}
