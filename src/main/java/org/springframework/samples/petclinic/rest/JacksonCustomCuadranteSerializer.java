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

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Cuadrante;
import org.springframework.samples.petclinic.model.Estante;
import org.springframework.samples.petclinic.model.Visit;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author Vitaliy Fedoriv
 *
 */

public class JacksonCustomCuadranteSerializer extends StdSerializer<Cuadrante> {

	public JacksonCustomCuadranteSerializer() {
		this(null);
	}

	public JacksonCustomCuadranteSerializer(Class<Cuadrante> t) {
		super(t);
	}

	@Override
	public void serialize(Cuadrante cuad, JsonGenerator jgen, SerializerProvider provider) throws IOException {

		jgen.writeStartObject();
		if (cuad.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", cuad.getId());
		}
		jgen.writeNumberField("max_estante", cuad.getMax_estante());
		
		/*Estante estante = cuad.getEstante_id();
		jgen.writeObjectFieldStart("estante_id");
		jgen.writeNumberField("id", estante.getId());
		jgen.writeNumberField("num_repisas",estante.getNum_repisas());
		jgen.writeNumberField("max_volumen", estante.getMax_volumen());
		*/
				
		
	}

}