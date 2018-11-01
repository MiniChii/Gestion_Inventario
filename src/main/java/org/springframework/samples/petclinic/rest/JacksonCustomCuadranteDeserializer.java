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

import org.springframework.samples.petclinic.model.Cuadrante;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Vitaliy Fedoriv
 *
 */

public class JacksonCustomCuadranteDeserializer extends StdDeserializer<Cuadrante> {
	
	public JacksonCustomCuadranteDeserializer(){
		this(null);
	}

	public JacksonCustomCuadranteDeserializer(Class<Cuadrante> t) {
		super(t);
	}

	@Override
	public Cuadrante deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		Cuadrante Cuadrante = new Cuadrante();
		Integer id = node.get("id").asInt();
		Integer max_estante = node.get("estante").asInt();
		Integer estante_id = node.get("estante_id").asInt();

		if (!(id == 0)) {
			Cuadrante.setId(id);
		}
        Cuadrante.setId(id);
        Cuadrante.setMax_estante(max_estante);
        Cuadrante.setEstante_id(estante_id);
		return Cuadrante;
	}

}