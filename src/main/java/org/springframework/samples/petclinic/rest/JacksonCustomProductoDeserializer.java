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

import org.springframework.samples.petclinic.model.Producto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Vitaliy Fedoriv
 *
 */

public class JacksonCustomProductoDeserializer extends StdDeserializer<Producto> {
	
	public JacksonCustomProductoDeserializer(){
		this(null);
	}

	public JacksonCustomProductoDeserializer(Class<Producto> t) {
		super(t);
	}

	@Override
	public Producto deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		Producto Producto = new Producto();
		int id = node.get("id").asInt();
		String nombre = node.get("nombre").asText(null);
		String unidad_medida = node.get("unidad_medida").asText(null);
		Integer precio = node.get("precio").asInt();
		Integer contenido = node.get("contenido").asInt();
		Integer num_minimo = node.get("num_minimo").asInt();
		Integer especie_id = node.get("especie_id").asInt();
		Integer cuadrante_id = node.get("especie_id").asInt();

		if (!(id == 0)) {
			Producto.setId(id);
		}
        Producto.setNombre(nombre);
        Producto.setUnidad_medida(unidad_medida);
        Producto.setPrecio(precio);
        Producto.setContenido(contenido);
        Producto.setNum_minimo(num_minimo);
        Producto.setEspecie_id(especie_id);
        Producto.setCuadrante_id(cuadrante_id);

		return Producto;
	}

}