package com.mx.apiAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.apiAgenciaAutos.model.Modelos;
import com.mx.apiAgenciaAutos.service.ModeloServImpl;

@RestController
@RequestMapping(path="ModelosWs")
@CrossOrigin

public class ModelosWs {

	@Autowired
	ModeloServImpl modeloImp;

	// http://localhost:9000/ModelosWs/listar
	@GetMapping(path="listar")
	public List<Modelos> listar(){
		return modeloImp.listar();
	}

	// http://localhost:9000/ModelosWs/guardar
	@PostMapping(path="guardar")
	public ResponseEntity<?> guardar(@RequestBody Modelos modelo){
		String response = modeloImp.guardar(modelo);
		
		if(response.equals("idModeloExiste"))
			return new ResponseEntity<>("No se guardo idModelo ya existe",HttpStatus.OK);
		else if(response.equals("nombreModExiste"))
			return new ResponseEntity<>("No se guardo nombreModelo ya existe",HttpStatus.OK);
		else if(response.equals("idMarcaNoexiste"))
			return new ResponseEntity<>("No se guardo idMarca no existe",HttpStatus.OK);
		else 
			return new ResponseEntity<>(modelo,HttpStatus.OK);
		
	}
	
	
	@PostMapping(path = "buscarPorNombre")
	public ResponseEntity<?> buscarPorNombre(@RequestBody String nombre) {
	    System.out.println("Buscando modelo con nombre: " + nombre);
	    
	    // Realiza la búsqueda de modelos por nombre
	    List<Modelos> modelos = modeloImp.buscarPorNombre(nombre);
	    
	    // Verifica si se encontraron resultados
	    if (modelos != null && !modelos.isEmpty()) {
	        // Si se encuentran modelos, agregamos la información de la marca
	        for (Modelos modelo : modelos) {
	            System.out.println("Modelo encontrado: " + modelo.getNombre() + " - Marca: " + modelo.getMarca().getNombre());
	        }
	        return new ResponseEntity<>(modelos, HttpStatus.OK);
	    } else {
	        System.out.println("No se encontraron modelos");
	        return new ResponseEntity<>("No se encontraron modelos con ese nombre", HttpStatus.NOT_FOUND);
	    }
	}

	
	
	// http://localhost:9000/ModelosWs/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Modelos modelo) {
	    String response = modeloImp.editar(modelo);

	    if (response.equals("idModeloNoExiste")) {
	        return new ResponseEntity<>("No se pudo editar: El modelo con el ID proporcionado no existe.", HttpStatus.NOT_FOUND);
	    } else if (response.equals("idMarcaNoExiste")) {
	        return new ResponseEntity<>("No se pudo editar: El ID de marca proporcionado no existe.", HttpStatus.BAD_REQUEST);
	    } else if (response.equals("ModeloEditado")) {
	        return new ResponseEntity<>(modelo, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error desconocido al editar el modelo.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	// http://localhost:9000/ModelosWs/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Integer id) {
	    String response = modeloImp.eliminar(id);

	    if (response.equals("idModeloNoExiste")) {
	        return new ResponseEntity<>("No se pudo eliminar: El modelo con el ID proporcionado no existe.", HttpStatus.NOT_FOUND);
	    } else if (response.equals("ModeloEliminado")) {
	        return new ResponseEntity<>("Modelo eliminado exitosamente.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error desconocido al eliminar el modelo.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}



	
}
