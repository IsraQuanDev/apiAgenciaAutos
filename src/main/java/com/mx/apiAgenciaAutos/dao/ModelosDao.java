package com.mx.apiAgenciaAutos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.apiAgenciaAutos.model.Modelos;

public interface ModelosDao extends JpaRepository<Modelos,Integer> {
 

	List<Modelos> findByNombre(String nombre);

	List<Modelos> findByNombreContainingIgnoreCase(String nombre);
	
}
