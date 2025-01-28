package com.mx.apiAgenciaAutos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.apiAgenciaAutos.dao.MarcasDao;
import com.mx.apiAgenciaAutos.model.Marcas;

@Service 
public class MarcaServImpl {

	@Autowired
	MarcasDao marcaDao;
	
	@Transactional(readOnly=true)
	public List<Marcas> listar(){
		
		return marcaDao.findAll();
	}
	
	
	// Validar el id y el nombre no se repiten, guardar
	
	@Transactional
	public String guardar(Marcas marca) {
		
	// Algoritmo 
    // ciclo 
	// if
	// bandera
	String resultado =" ";
	boolean bandera = false;
	
	for(Marcas m: marcaDao.findAll()){
		if(m.getId().equals(marca.getId())) {
			resultado ="idExiste";
			bandera = true;
		}else if(m.getNombre().equals(marca.getNombre())) {
			resultado="nombreExiste";
			bandera = true;
		}
		
	}
	if(!bandera) {
		marcaDao.save(marca);
		resultado = "guardado";
	}
	
	return resultado;
		
	}
	
	
	@Transactional 
    public Optional<Marcas> buscarPorNombre(String nombre) {
        return marcaDao.findByNombre(nombre);
    }
	
	 
	// Método para editar la marca
    @Transactional
    public String editarMarca(Marcas marca) {
        // Verificar si la marca con el id proporcionado existe
        if (!marcaDao.existsById(marca.getId())) {
            return "marcaNoExiste"; // Marca no encontrada
        }

        // Validar si el nombre ya existe en otra marca
        for (Marcas m : marcaDao.findAll()) {
            if (m.getNombre().equals(marca.getNombre()) && !m.getId().equals(marca.getId())) {
                return "nombreExiste"; // El nombre ya existe
            }
        }

        // Si la marca existe y no hay conflicto con el nombre, actualizar
        marcaDao.save(marca);
        return "actualizado"; // Marca actualizada correctamente
    }
    
    
 // Método para eliminar una marca
    @Transactional
    public String eliminar(Integer id) {
        // Verificar si la marca con el id proporcionado existe
        if (!marcaDao.existsById(id)) {
            return "marcaNoExiste"; // La marca no existe
        }

        // Eliminar la marca
        marcaDao.deleteById(id);
        return "eliminado"; // Marca eliminada correctamente
    }
	
	
}
