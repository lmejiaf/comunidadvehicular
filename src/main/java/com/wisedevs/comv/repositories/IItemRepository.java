package com.wisedevs.comv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wisedevs.comv.entities.Item;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long>{

	
}
