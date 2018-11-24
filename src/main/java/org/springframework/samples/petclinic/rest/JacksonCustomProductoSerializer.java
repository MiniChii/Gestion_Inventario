/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.rest;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.springframework.samples.petclinic.model.Estante;
import org.springframework.samples.petclinic.model.Especie;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Visit;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author Vitaliy Fedoriv
 *
 */

public class JacksonCustomProductoSerializer extends StdSerializer<Producto> {

	public JacksonCustomProductoSerializer() {
		this(null);
	}

	public JacksonCustomProductoSerializer(Class<Producto> t) {
		super(t);
	}

	@Override
	public void serialize(Producto prod, JsonGenerator jgen, 
			SerializerProvider provider) throws IOException {

		jgen.writeStartObject();
		//id
		if (prod.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", prod.getId());
		}
		//nombre que no debe ser null
		jgen.writeStringField("nombre", prod.getNombre());
		//uni_medid
		if(prod.getUnidadMedida()==null) {
			jgen.writeNullField("unidad_medida");
		}else {
			jgen.writeStringField("unidad_medida", prod.getUnidadMedida());
		}
		//precio
		if(prod.getPrecio()==null) {
			jgen.writeNullField("precio");
		}else {
			jgen.writeNumberField("precio", prod.getPrecio());
		}
		//contenido
		if(prod.getContenido()==null) {
			jgen.writeNullField("contenido");
		}else {
			jgen.writeNumberField("contenido", prod.getContenido());
		}
		
		if(prod.getNumMinimo()==null) {
			jgen.writeNullField("num_minimo");
		}else {
			jgen.writeNumberField("num_minimo", prod.getNumMinimo());
		}
		
		
		//jgen.writeNumberField("estante_id", prod.getEstanteId());
		//jgen.writeNumberField("especie_id", prod.getEspecieId());
		
		
		if(prod.getEspecieId()==null) {
			jgen.writeNullField("especie_id");
		}
		if(prod.getEstanteId()==null) {
			jgen.writeNullField("especie_id");
		}
		/*		
		jgen.writeNumberField("especie_id", prod.getEspecieId());
		*/
		/*
		Especie esp = prod.getEspecie();
		jgen.writeObjectFieldStart("especie");
		jgen.writeNumberField("id", esp.getId());
		jgen.writeStringField("nombre", esp.getNombre());
		jgen.writeStringField("etapa", esp.getEtapa());
		jgen.writeEndObject(); // especie
		
		Estante esta = prod.getEstante();
		jgen.writeObjectFieldStart("estante");
		jgen.writeNumberField("id", esta.getId());
		jgen.writeNumberField("num_repisas", esta.getNum_repisas());
		jgen.writeNumberField("max_volumen", esta.getMax_volumen());
		jgen.writeEndObject(); // estante
		
		*/
		jgen.writeEndObject();

	}

}
