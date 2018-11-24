/*
 * Copyright 2002-2013 the original author or authors.
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
package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.ProductoRepository;

/**
 * Spring Data JPA specialization of the {@link OwnerRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */

@Profile("spring-data-jpa")
public interface SpringDataProductoRepository extends ProductoRepository,Repository<Producto, Integer> {

    @Override
    //@Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
    @Query("SELECT DISTINCT prod FROM Producto prod WHERE prod.nombre LIKE :nombre%")
    public Collection<Producto> findByNombre(@Param("nombre") String nameString);

    // @Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
    @Override
    @Query("SELECT producto FROM Producto producto WHERE producto.id =:id")
    public Producto findById(@Param("id") int id);
}
