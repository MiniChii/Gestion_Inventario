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
import org.springframework.samples.petclinic.model.Estante;
import org.springframework.samples.petclinic.model.Visit;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author Vitaliy Fedoriv
 *
 */

public class JacksonCustomEstanteSerializer extends StdSerializer<Estante> {

	public JacksonCustomEstanteSerializer() {
		this(null);
	}

	public JacksonCustomEstanteSerializer(Class<Estante> t) {
		super(t);
	}

	@Override
	public void serialize(Estante esta, JsonGenerator jgen, SerializerProvider provider) throws IOException {

		jgen.writeStartObject();
		if (esta.getId() == null) {
			jgen.writeNullField("id");
		} else {
			jgen.writeNumberField("id", esta.getId());
		}

		jgen.writeNumberField("nombre", esta.getNum_repisas());
		jgen.writeNumberField("unidad_medida", esta.getMax_volumen());
	

	}

}
