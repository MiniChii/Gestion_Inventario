/*
 * Copyright 2016-2017 the original author or authors.
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.clinicService.ApplicationTestConfig;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.InventarioService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Test class for {@link OwnerRestController}
 *
 * @author Vitaliy Fedoriv
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
public class ProductoRestControllerTests {

    @Autowired
    private ProductoRestController productoRestController;

    @MockBean
    private InventarioService inventarioService;

    private MockMvc mockMvc;

    private Collection<Producto> productos;

    @Before
    public void initProductos(){
    	this.mockMvc = MockMvcBuilders.standaloneSetup(productoRestController)
    			.setControllerAdvice(new ExceptionControllerAdvice())
    			.build();
    	productos = new ArrayList<Producto>();

    	Producto prod = new Producto();
    	prod.setId(1);
    	prod.setNombre("Alimento para gato");
    	prod.setNumMinimo(8);
    	prod.setPrecio(4000);
    	prod.setContenido(1);
    	prod.setUnidadMedida("kg");
    	prod.setEspecieId(1);
    	prod.setEstanteId(1);
    	productos.add(prod);
    	
    	
    	prod = new Producto();
    	prod.setId(2);
    	prod.setNombre("Alimento para perro");
    	prod.setNumMinimo(8);
    	prod.setPrecio(45000);
    	prod.setContenido(15);
    	prod.setUnidadMedida("kg");
    	prod.setEspecieId(2);
    	prod.setEstanteId(1);
    	productos.add(prod);

    	prod = new Producto();
    	prod.setId(3);
    	prod.setNombre("Alimento para hamster");
    	prod.setNumMinimo(8);
    	prod.setPrecio(1100);
    	prod.setContenido(1);
    	prod.setUnidadMedida("kg");
    	prod.setEspecieId(3);
    	prod.setEstanteId(2);
    	productos.add(prod);
    	
    	prod = new Producto();
    	prod.setId(4);
    	prod.setNombre("Perrina");
    	prod.setNumMinimo(10);
    	prod.setPrecio(5200);
    	prod.setContenido(3);
    	prod.setUnidadMedida("kg");
    	prod.setEspecieId(2);
    	prod.setEstanteId(1);
    	productos.add(prod);
    	
    }
    
    @Test
    @WithMockUser(roles="PROD_ADMIN")
    public void obtieneProductosEnUnRangoDePrecios() throws Exception {
    	given(this.inventarioService.findProductoByPrecio(1100, 50000)).willReturn(productos);
    	
        this.mockMvc.perform(get("/api/productos/buscarPrecio/1100/50000")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.[0].id").value(1))
            .andExpect(jsonPath("$.[0].nombre").value("Alimento para gato"));

    }
    
    //Diego
    @Test
    @WithMockUser(roles="PROD_ADMIN")
    public void obtieneProductoPorElNombre() throws Exception {
    	given(this.inventarioService.findProductoByName("Perrina")).willReturn(productos);
    	
        this.mockMvc.perform(get("/api/productos/buscarNombre/Perrina")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.[3].id").value(4))
            .andExpect(jsonPath("$.[3].nombre").value("Perrina"));

    }
    
    //Diego 
    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void NoObtieneProductoPorElNombre() throws Exception {
    	given(this.inventarioService.findProductoByName("MasterDog")).willReturn(productos);
        this.mockMvc.perform(get("/api/productos/buscarNombre/MasterDog")
        	.accept(MediaType.APPLICATION_JSON))
             
            .andExpect(jsonPath("$.[0].id").doesNotExist());
    }
/*
   
    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetOwnerNotFound() throws Exception {
    	given(this.inventarioService.findOwnerById(-1)).willReturn(null);
        this.mockMvc.perform(get("/api/owners/-1")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

	@Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetOwnerSuccess() throws Exception {
    	given(this.inventarioService.findOwnerById(1)).willReturn(productos.get(0));
        this.mockMvc.perform(get("/api/owners/1")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.firstName").value("George"));
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetOwnersListSuccess() throws Exception {
    	productos.remove(0);
    	productos.remove(1);
    	given(this.inventarioService.findOwnerByLastName("Davis")).willReturn(productos);
        this.mockMvc.perform(get("/api/owners/*//*lastname/Davis")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.[0].id").value(2))
            .andExpect(jsonPath("$.[0].firstName").value("Betty"))
            .andExpect(jsonPath("$.[1].id").value(4))
            .andExpect(jsonPath("$.[1].firstName").value("Harold"));
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetOwnersListNotFound() throws Exception {
    	productos.clear();
    	given(this.inventarioService.findOwnerByLastName("0")).willReturn(productos);
        this.mockMvc.perform(get("/api/owners/?lastName=0")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetAllOwnersSuccess() throws Exception {
    	productos.remove(0);
    	productos.remove(1);
    	given(this.inventarioService.findAllOwners()).willReturn(productos);
        this.mockMvc.perform(get("/api/owners/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.[0].id").value(2))
            .andExpect(jsonPath("$.[0].firstName").value("Betty"))
            .andExpect(jsonPath("$.[1].id").value(4))
            .andExpect(jsonPath("$.[1].firstName").value("Harold"));
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testGetAllOwnersNotFound() throws Exception {
    	productos.clear();
    	given(this.inventarioService.findAllOwners()).willReturn(productos);
        this.mockMvc.perform(get("/api/owners/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testCreateOwnerSuccess() throws Exception {
    	Owner newOwner = productos.get(0);
    	newOwner.setId(999);
    	ObjectMapper mapper = new ObjectMapper();
    	String newOwnerAsJSON = mapper.writeValueAsString(newOwner);
    	this.mockMvc.perform(post("/api/owners/")
    		.content(newOwnerAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
    		.andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testCreateOwnerError() throws Exception {
    	Owner newOwner = productos.get(0);
    	newOwner.setId(null);
    	newOwner.setFirstName(null);
    	ObjectMapper mapper = new ObjectMapper();
    	String newOwnerAsJSON = mapper.writeValueAsString(newOwner);
    	this.mockMvc.perform(post("/api/owners/")
        		.content(newOwnerAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andExpect(status().isBadRequest());
     }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testUpdateOwnerSuccess() throws Exception {
    	given(this.inventarioService.findOwnerById(1)).willReturn(productos.get(0));
    	Owner newOwner = productos.get(0);
    	newOwner.setFirstName("George I");
    	ObjectMapper mapper = new ObjectMapper();
    	String newOwnerAsJSON = mapper.writeValueAsString(newOwner);
    	this.mockMvc.perform(put("/api/owners/1")
    		.content(newOwnerAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(content().contentType("application/json;charset=UTF-8"))
        	.andExpect(status().isNoContent());

    	this.mockMvc.perform(get("/api/owners/1")
           	.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.firstName").value("George I"));

    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testUpdateOwnerError() throws Exception {
    	Owner newOwner = productos.get(0);
    	newOwner.setFirstName("");
    	ObjectMapper mapper = new ObjectMapper();
    	String newOwnerAsJSON = mapper.writeValueAsString(newOwner);
    	this.mockMvc.perform(put("/api/owners/1")
    		.content(newOwnerAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isBadRequest());
     }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testDeleteOwnerSuccess() throws Exception {
    	Owner newOwner = productos.get(0);
    	ObjectMapper mapper = new ObjectMapper();
    	String newOwnerAsJSON = mapper.writeValueAsString(newOwner);
    	given(this.inventarioService.findOwnerById(1)).willReturn(productos.get(0));
    	this.mockMvc.perform(delete("/api/owners/1")
    		.content(newOwnerAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles="OWNER_ADMIN")
    public void testDeleteOwnerError() throws Exception {
    	Owner newOwner = productos.get(0);
    	ObjectMapper mapper = new ObjectMapper();
    	String newOwnerAsJSON = mapper.writeValueAsString(newOwner);
    	given(this.inventarioService.findOwnerById(-1)).willReturn(null);
    	this.mockMvc.perform(delete("/api/owners/-1")
    		.content(newOwnerAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isNotFound());
    }
*/
}
