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

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.InventarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Vitaliy Fedoriv
 *
 */

@RestController //para que spring le diga al controlador correspondiente
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/productos")
public class ProductoRestController {

	@Autowired //para el singleton
	private InventarioService invService;

	//primer servicio
	@PreAuthorize( "hasRole(@roles.PROD_ADMIN)" )
	@RequestMapping(value = "/*/nombre/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Producto>> getProductoList(@PathVariable("nombre") String prodNombre) {
		if (prodNombre == null) {
			prodNombre = "";
		}
		Collection<Producto> productos = this.invService.findProductoByName(prodNombre);
		if (productos.isEmpty()) {
			return new ResponseEntity<Collection<Producto>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Producto>>(productos, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.PROD_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Producto>> getProductos() {
		Collection<Producto> prods = this.invService.findAllProductos();
		if (prods.isEmpty()) {
			return new ResponseEntity<Collection<Producto>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Producto>>(prods, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.PROD_ADMIN)" )
	@RequestMapping(value = "/{prodId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Producto> getProducto(@PathVariable("prodId") int prodId) {
		Producto prod = null;
		prod = this.invService.findProductoById(prodId);
		if (prod == null) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Producto>(prod, HttpStatus.OK);
	}

    @PreAuthorize( "hasRole(@roles.PROD_ADMIN)" )
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Producto> addProducto(@RequestBody @Valid Producto prod, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (prod == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Producto>(headers, HttpStatus.BAD_REQUEST);
		}
		this.invService.saveProducto(prod);
		headers.setLocation(ucBuilder.path("/api/owners/{id}").buildAndExpand(prod.getId()).toUri());
		return new ResponseEntity<Producto>(prod, headers, HttpStatus.CREATED);
	}

    @PreAuthorize( "hasRole(@roles.PROD_ADMIN)" )
	@RequestMapping(value = "/{prodId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Producto> updateProducto(@PathVariable("prodId") int prodId, @RequestBody @Valid Producto prod,
			BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (prod == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Producto>(headers, HttpStatus.BAD_REQUEST);
		}
		Producto currentProd = this.invService.findProductoById(prodId);
		if (currentProd == null) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
		currentProd.setNombre(prod.getNombre());
		currentProd.setUnidad_medida(prod.getUnidad_medida());
		currentProd.setPrecio(prod.getPrecio());
		currentProd.setContenido(prod.getContenido());
		currentProd.setNum_minimo(prod.getNum_minimo());
		this.invService.saveProducto(currentProd);
		return new ResponseEntity<Producto>(currentProd, HttpStatus.NO_CONTENT);
	}

    @PreAuthorize( "hasRole(@roles.PROD_ADMIN)" )
	@RequestMapping(value = "/{prodId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> deleteProducto(@PathVariable("prodId") int prodId) {
		Producto prod = this.invService.findProductoById(prodId);
		if (prod == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.invService.deleteProducto(prod);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
