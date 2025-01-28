package com.mx.apiAgenciaAutos.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/*
 
 CREATE TABLE MARCAS_AGEN(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(100) NOT NULL,
ORIGEN VARCHAR2(90) NOT NULL,
FECHA_LANZ DATE
); 
 
 */

@Entity
@Table(name="MARCAS_AGEN")
@Data
public class Marcas {

	
	private static final long serialVersionUID =1L;
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name = "ORIGEN")
	private String origen;
	
	@Column(name="FECHA_LANZ")
	private Date fechaLanz;
	
	// Cardinalidad --- una marca tiene  muchos modelos
	@OneToMany(mappedBy="marca",cascade=CascadeType.ALL)
	@JsonIgnore
	List<Modelos> lista = new ArrayList<Modelos>();
	
}
