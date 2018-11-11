package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.rest.JacksonCustomCuadranteDeserializer;
import org.springframework.samples.petclinic.rest.JacksonCustomCuadranteSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "Cuadrante")
@JsonSerialize(using = JacksonCustomCuadranteSerializer.class)
@JsonDeserialize(using = JacksonCustomCuadranteDeserializer.class)

public class Cuadrante {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name= "max_estante")
	private Integer max_estante;

	@Column(name= "estante_id")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Cuadrante", fetch = FetchType.EAGER)
    private Set<Estante> estantes;

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

	public Set<Estante> getEstantes() {
		return estantes;
	}

	public void setEstantes(Set<Estante> estantes) {
		this.estantes = estantes;
	}


}
