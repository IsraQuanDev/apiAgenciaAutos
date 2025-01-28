package com.mx.apiAgenciaAutos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.apiAgenciaAutos.model.Marcas;
import com.mx.apiAgenciaAutos.service.MarcaServImpl;
 
@RestController
@RequestMapping(path="MarcasWs")
@CrossOrigin
public class MarcasWs {

	@Autowired
	MarcaServImpl marcaImp; 	
	
	@GetMapping(path="listar")
	public List<Marcas> listar(){
		return marcaImp.listar();
	}
	
	@PostMapping(path="guardar")
	public ResponseEntity<?> guardar(@RequestBody Marcas marca){
		
		String response = marcaImp.guardar(marca);
		
		if(response.equals("idExiste"))
			return new ResponseEntity<>("Ese id ya existe", HttpStatus.OK);
		else if(response.equals("nombreExiste"))
			return new ResponseEntity<>("Ese nombre ya existe", HttpStatus.OK);
		else 
			return new ResponseEntity<>(marca,HttpStatus.CREATED);
	}
	
	
	@PostMapping(path = "buscarPorNombre")
    public ResponseEntity<?> buscarPorNombre(@RequestBody Marcas marca) {
        Optional<Marcas> resultado = marcaImp.buscarPorNombre(marca.getNombre());
        if (resultado.isPresent()) {
            return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Marca no encontrada", HttpStatus.NOT_FOUND);
        }
    }
	
	 
	 @PostMapping(path = "editar")
	    public ResponseEntity<?> editarMarca(@RequestBody Marcas marca) {
	        String response = marcaImp.editarMarca(marca);

	        if (response.equals("marcaNoExiste"))
	            return new ResponseEntity<>("La marca con el id proporcionado no existe", HttpStatus.NOT_FOUND);
	        else if (response.equals("nombreExiste"))
	            return new ResponseEntity<>("Ese nombre de marca ya existe", HttpStatus.OK);
	        else
	            return new ResponseEntity<>(marca, HttpStatus.OK);
	    }
	 
	 
	 @PostMapping(path = "eliminar")
	    public ResponseEntity<?> eliminar(@RequestBody Integer id) {
	        String response = marcaImp.eliminar(id);

	        if (response.equals("marcaNoExiste")) {
	            return new ResponseEntity<>("La marca con el id proporcionado no existe", HttpStatus.NOT_FOUND);
	        } else {
	            return new ResponseEntity<>("Marca eliminada con éxito", HttpStatus.OK);
	        }
	    }
	
}
