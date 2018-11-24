package org.springframework.samples.petclinic.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.samples.petclinic.rest.JacksonCustomProductoDeserializer;
import org.springframework.samples.petclinic.rest.JacksonCustomProductoSerializer;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "productos")
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
    private String unidadMedida;
    
    @Column(name= "precio")
    private Integer precio;
    

    @Column(name= "contenido")
    private Integer contenido;
    
    @Column(name= "num_minimo")
    private Integer numMinimo;
    
 
   //viendo si funciona
    @Column(name= "estante_id")
    private Integer estanteId;
    

	@Column(name= "especie_id")
    private Integer especieId;
    /*
    @ManyToOne
    @JoinColumn(name = "especie_id")
    private Especie especie;
    
    @ManyToOne
    @JoinColumn(name = "estante_id")
    private Estante Estante;
     */
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

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidad_medida) {
		this.unidadMedida = unidad_medida;
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

	public Integer getNumMinimo() {
		return numMinimo;
	}

	public void setNumMinimo(Integer numMinimo) {
		this.numMinimo = numMinimo;
	}
	

	public Integer getEspecieId() {
		return especieId;
	}

	
	public void setEspecieId(Integer especieId) {
		this.especieId = especieId;
	}
	

    public Integer getEstanteId() {
		return estanteId;
	}

	public void setEstanteId(Integer estanteId) {
		this.estanteId = estanteId;
	}

	
	

/*
	public Integer getNum_minimo() {
		return num_minimo;
	}

	public void setNum_minimo(Integer num_minimo) {
		this.num_minimo = num_minimo;
	}
	
	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Estante getEstante() {
		return Estante;
	}

	public void setEstante(Estante Estante) {
		this.Estante = Estante;
	}*/
	
    
    
}
