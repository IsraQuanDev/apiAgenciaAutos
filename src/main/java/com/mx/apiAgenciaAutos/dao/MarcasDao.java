package com.mx.apiAgenciaAutos.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.apiAgenciaAutos.model.Marcas;

public interface MarcasDao extends JpaRepository<Marcas,Integer> {

    Optional<Marcas> findByNombre(String nombre);

    // Método para verificar si una marca existe por id
    boolean existsById(Integer id);
}
