package org.springframework.samples.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.rest.JacksonCustomProductoDeserializer;
import org.springframework.samples.petclinic.rest.JacksonCustomProductoSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "producto")
@JsonSerialize(using = JacksonCustomProductoSerializer.class)
@JsonDeserialize(using = JacksonCustomProductoDeserializer.class)

public class Producto{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	

    @Column(name = "nombre")
    @NotEmpty
    private String nombre;
    
    @Column(name= "unidad_medida")
    private String unidad_medida;
    
    @Column(name= "precio")
    private Integer precio;
    

    @Column(name= "contenido")
    private Integer contenido;
    
    @Column(name= "num_minimo")
    private Integer num_minimo;
    
    @ManyToOne
    @JoinColumn(name = "especie_id")
    private Integer especie_id;
    
    @ManyToOne
    @JoinColumn(name = "cuadrante_id")
    private Integer cuadrante_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidad_medida() {
		return unidad_medida;
	}

	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getContenido() {
		return contenido;
	}

	public void setContenido(Integer contenido) {
		this.contenido = contenido;
	}

	public Integer getNum_minimo() {
		return num_minimo;
	}

	public void setNum_minimo(Integer num_minimo) {
		this.num_minimo = num_minimo;
	}

	public Integer getEspecie_id() {
		return especie_id;
	}

	public void setEspecie_id(Integer especie_id) {
		this.especie_id = especie_id;
	}

	public Integer getCuadrante_id() {
		return cuadrante_id;
	}

	public void setCuadrante_id(Integer cuadrante_id) {
		this.cuadrante_id = cuadrante_id;
	}
	
    
}
