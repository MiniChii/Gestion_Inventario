
package org.springframework.samples.petclinic.rest;

import java.io.IOException;

import org.springframework.samples.petclinic.model.Especie;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * @author Chii
 *
 */

public class JacksonCustomEspecieDeserializer extends StdDeserializer<Especie> {
	
	public JacksonCustomEspecieDeserializer(){
		this(null);
	}

	public JacksonCustomEspecieDeserializer(Class<Especie> t) {
		super(t);
	}

	@Override
	public Especie deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		Especie Especie = new Especie();
		int id = node.get("id").asInt();
		String nombre = node.get("nombre").asText(null);
		String etapa = node.get("etapa").asText(null);


		if (!(id == 0)) {
			Especie.setId(id);
		}
        Especie.setNombre(nombre);
        Especie.setEtapa(etapa);

		return Especie;
	}

}
