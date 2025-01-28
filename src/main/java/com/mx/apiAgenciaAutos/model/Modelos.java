package com.mx.apiAgenciaAutos.model;
 
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; 
import jakarta.persistence.Table;
import lombok.Data;

/*

CREATE TABLE MODELOS_AGEN(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(100) NOT NULL,
TRANSMISSION NVARCHAR2(90) NOT NULL,
PRECIO NUMBER,
EXISTENCIA NUMBER NOT NULL,
ID_MARCA NUMBER NOT NULL,
FOREIGN KEY(ID_MARCA) REFERENCES MARCAS_AGEN(ID)
);
*/


@Entity
@Table(name="MODELOS_AGEN")
@Data
public class Modelos implements Serializable {

	private static final long serialVersionUID =1L;
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name = "TRANSMISSION")
	private String transmission;
	
	@Column(name = "PRECIO")
	private Float precio;
	
	@Column(name="EXISTENCIA")
	private Integer existencia;
	
	// Cardinalidad --- Muchos modelos pertenecen a una marca
    // fetch --- indica que la relacion debe ser cargada al instante -- hilos
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name ="ID_MARCA") 
	Marcas marca;

 
	
}
