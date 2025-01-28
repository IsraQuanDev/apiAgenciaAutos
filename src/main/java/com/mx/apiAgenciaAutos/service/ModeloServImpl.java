package com.mx.apiAgenciaAutos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.apiAgenciaAutos.dao.MarcasDao;
import com.mx.apiAgenciaAutos.dao.ModelosDao;
import com.mx.apiAgenciaAutos.model.Marcas;
import com.mx.apiAgenciaAutos.model.Modelos;

@Service
public class ModeloServImpl {

	@Autowired
	ModelosDao modeloDao;
	
	@Autowired
	MarcasDao marcaDao;
	
	@Transactional(readOnly=true)
	public List<Modelos> listar(){
		
		return modeloDao.findAll();
	}
	
	//el id no se repita y el nombre, idmarca exista en la bd
	@Transactional
	public String guardar(Modelos modelo) {
		
		// Ciclo
		// Condicion
		// Bandera
		
		boolean banderaMar = false;
		boolean banderaMod = false;
		String respuesta = "";
		
		// validar que el id marca exista
		for (Marcas mar: marcaDao.findAll())
		{	
			if(mar.getId().equals(modelo.getMarca().getId())) {
			  //IdMarca existe 
			   banderaMar = true; 
					   
			// VAlidar los campos del modelo
					   for(Modelos mod: modeloDao.findAll()) {
						   if (mod.getId().equals(modelo.getId())){
							   // id modelo ya existe
							   banderaMod = true;
							   respuesta="idModeloExiste";
							   break;
						   }else if(mod.getNombre().equals(modelo.getNombre())) {
							// nombre modelo ya existe
							   banderaMod=true;
							   respuesta = "nombreModExiste";
							   break;
						   }
						   
						   
					   }
			   break;
				
		}		
	}
		
			if(banderaMar==false) {
				// no se guarda 
				respuesta="idMarcaNo eiste";
				banderaMod = true;
			}else if(banderaMod==false) {
				modeloDao.save(modelo);
			}
			return respuesta;
		
		
	}
	
	
	@Transactional 
	public List<Modelos> buscarPorNombre(String nombre) {
	    if (nombre == null || nombre.trim().isEmpty()) {
	        return new ArrayList<>(); // Si no se pasa nombre, devolver lista vacía
	    }
	    
	    // Realiza la búsqueda con una coincidencia que no distinga mayúsculas/minúsculas
	    return modeloDao.findByNombreContainingIgnoreCase(nombre);
	}

	
	@Transactional
	public String editar(Modelos modelo) {
	    // Bandera para validar si el modelo existe
	    boolean banderaMod = false;
	    boolean banderaMar = false;
	    String respuesta = "";

	    // Validar si el modelo con id existe
	    Modelos modeloExistente = modeloDao.findById(modelo.getId()).orElse(null);
	    if (modeloExistente == null) {
	        return "idModeloNoExiste"; // El modelo no existe
	    }

	    // Validar si la marca con el id proporcionado existe
	    for (Marcas mar : marcaDao.findAll()) {
	        if (mar.getId().equals(modelo.getMarca().getId())) {
	            banderaMar = true; // Marca encontrada
	            break;
	        }
	    }

	    // Si la marca no existe, respondemos con el mensaje correspondiente
	    if (!banderaMar) {
	        return "idMarcaNoExiste";
	    }

	    // Si todo está bien, se realiza la actualización
	    if (banderaMar && modeloExistente != null) {
	        modeloDao.save(modelo); // Guardamos el modelo editado
	        return "ModeloEditado"; // Respuesta exitosa
	    }

	    return respuesta; // En caso de que algo falle
	}


	
	@Transactional
	public String eliminar(Integer id) {
	    // Validar si el modelo con el id proporcionado existe
	    Modelos modeloExistente = modeloDao.findById(id).orElse(null);
	    if (modeloExistente == null) {
	        return "idModeloNoExiste"; // El modelo no existe
	    }

	    // Si el modelo existe, lo eliminamos
	    modeloDao.delete(modeloExistente);
	    return "ModeloEliminado"; // Respuesta exitosa
	}


	
}

