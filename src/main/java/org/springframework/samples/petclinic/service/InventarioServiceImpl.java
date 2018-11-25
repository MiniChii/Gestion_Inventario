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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.PetTypeRepository;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 * @author Vitaliy Fedoriv
 */

@Service
public class InventarioServiceImpl implements InventarioService {

	private ProductoRepository productoRepository;

	@Autowired
	public InventarioServiceImpl(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Producto> findProductoByName(String prodNombre) throws DataAccessException {
		return productoRepository.findByNombreStartingWithIgnoreCase(prodNombre);

	}
	
	@Transactional(readOnly = true)
	public Collection<Producto> findProductoByPrecio(int minPrecioProd,int maxPrecioProd) throws DataAccessException {
		return productoRepository.findByPrecioBetween( minPrecioProd, maxPrecioProd);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Producto> findAllProductos() throws DataAccessException {
		return productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findProductoById(int prodId) throws DataAccessException {
		return productoRepository.findById(prodId);	
	}
	
	

	@Override
	@Transactional
	public void saveProducto(Producto prod) throws DataAccessException {
		productoRepository.save(prod);
	}

	@Override
	@Transactional
	public void deleteProducto(Producto prod) throws DataAccessException {
		productoRepository.delete(prod);
	}



	@Override
	@Transactional(readOnly = true)
	public Collection<Producto> OrderByPrecio() {
		return productoRepository.OrderByPrecio();	

	}


	




	@Override
	@Transactional
	public Collection<Producto> OrderByNombre() throws DataAccessException {
		// TODO Auto-generated method stub
		return productoRepository.OrderByNombre();
	}

	@Override
	public Collection<Producto> findByEspecieId(int id_especie) throws DataAccessException {
		// TODO Auto-generated method stub
		return productoRepository.findByEspecieId(id_especie);
	}
}
