package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.samples.petclinic.rest.JacksonCustomEstanteDeserializer;
import org.springframework.samples.petclinic.rest.JacksonCustomEstanteSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "Estante")
@JsonSerialize(using = JacksonCustomEstanteSerializer.class)
@JsonDeserialize(using = JacksonCustomEstanteDeserializer.class)
public class Estante {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name= "num_repisas")
    private Integer num_repisas;
    
    @Column(name= "max_volumen")
    private Integer max_volumen;

    @ManyToOne
    @JoinColumn(name = "id")
    private Cuadrante cuadrante;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum_repisas() {
		return num_repisas;
	}

	public void setNum_repisas(Integer num_repisas) {
		this.num_repisas = num_repisas;
	}

	public Integer getMax_volumen() {
		return max_volumen;
	}

	public void setMax_volumen(Integer max_volumen) {
		this.max_volumen = max_volumen;
	}

	public Cuadrante getCuadrante() {
		return cuadrante;
	}

	public void setCuadrante(Cuadrante cuadrante) {
		this.cuadrante = cuadrante;
	}
    
}
