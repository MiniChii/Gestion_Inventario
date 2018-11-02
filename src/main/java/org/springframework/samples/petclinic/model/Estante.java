package org.springframework.samples.petclinic.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.rest.JacksonCustomEstanteDeserializer;
import org.springframework.samples.petclinic.rest.JacksonCustomEstanteSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "estante")
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuadrante", fetch = FetchType.EAGER)
    private Set<Estante> estantes;
    
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
    
}
