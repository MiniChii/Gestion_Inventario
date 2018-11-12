package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.samples.petclinic.rest.JacksonCustomCuadranteDeserializer;
import org.springframework.samples.petclinic.rest.JacksonCustomCuadranteSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "cuadrante")
@JsonSerialize(using = JacksonCustomCuadranteSerializer.class)
@JsonDeserialize(using = JacksonCustomCuadranteDeserializer.class)

public class Cuadrante {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name= "max_estante")
	private Integer max_estante;
	
/*
    @ManyToOne
    @JoinColumn(name = "estante_id")
    private int estante_id;
*/

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getMax_estante() {
		return max_estante;
	}


	public void setMax_estante(Integer max_estante) {
		this.max_estante = max_estante;
	}

/*
	public Integer getEstante_id() {
		return estante_id;
	}


	public void setEstante_id(Integer estante_id) {
		this.estante_id = estante_id;
	}

	*/




}
