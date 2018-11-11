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
import org.springframework.samples.petclinic.model.Estante;
import org.springframework.samples.petclinic.model.Owner;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

		
		Cuadrante Cuadrante = new Cuadrante();
		Estante estante=new Estante();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = parser.getCodec().readTree(parser);
		JsonNode estante_node = node.get("estante_id");
		JsonNode type_node = node.get("type");
		estante = mapper.treeToValue(estante_node, Estante.class);
		Integer id = node.get("id").asInt();
		Integer max_estante = node.get("estante").asInt();
		
		if (!(id == 0)) {
			Cuadrante.setId(id);
		}
        Cuadrante.setId(id);
        Cuadrante.setMax_estante(max_estante);
        Cuadrante.setEstante_id(estante);
		return Cuadrante;
	}

}
