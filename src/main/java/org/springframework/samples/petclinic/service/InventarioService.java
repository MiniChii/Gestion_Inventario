/*
 * Copyright 2002-2017 the original author or authors.
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
package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import org.springframework.samples.petclinic.model.Producto;


/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 * @author Vitaliy Fedoriv
 */
public interface InventarioService {
	
	Collection<Producto> findProductoByName(String prodNombre) throws DataAccessException;
	Collection<Producto> findAllProductos() throws DataAccessException;
	public Collection<Producto> findProductoByPrecio(int minPrecioProd,int maxPrecioProd) throws DataAccessException;	
	Producto findProductoById(int prodId) throws DataAccessException;
	void saveProducto(Producto prod) throws DataAccessException;
	void deleteProducto(Producto prod) throws DataAccessException;

	Collection<Producto> OrderByPrecio();
	

	Collection<Producto> OrderByNombre() throws DataAccessException;


}
