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
package org.springframework.samples.petclinic.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation of the {@link OwnerRepository} interface.
 *
 * @author Mike Keith
 * @author Rod Johnson
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Vitaliy Fedoriv
 */
@Repository
@Profile("jpa")
public class JpaProductoRepositoryImpl implements ProductoRepository{

    @PersistenceContext
    private EntityManager em;


    /**
     * Important: in the current version of this method, we load Owners with all their Pets and Visits while
     * we do not need Visits at all and we only need one property from the Pet objects (the 'name' property).
     * There are some ways to improve it such as:
     * - creating a Ligtweight class (example here: https://community.jboss.org/wiki/LightweightClass)
     * - Turning on lazy-loading and using {@link OpenSessionInViewFilter}
     */

    @Override
    public Producto findById(int id) {
        // using 'join fetch' because a single query should load both owners and pets
        // using 'left join fetch' because it might happen that an owner does not have pets yet
        Query query = this.em.createQuery("SELECT producto FROM Producto WHERE producto.id =:id");
        query.setParameter("id", id);
        return (Producto) query.getSingleResult();
    }


    @Override
    public void save(Producto prod) {
        if (prod.getId() == null) {
            this.em.persist(prod);
        } else {
            this.em.merge(prod);
        }

    }
    
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Producto> findAll() throws DataAccessException {
		Query query = this.em.createQuery("SELECT prod FROM Producto prod");
        return query.getResultList();
	}

	@Override
	public void delete(Producto prod) throws DataAccessException {
		this.em.remove(this.em.contains(prod) ? prod : this.em.merge(prod));
	}



	@SuppressWarnings("unchecked")
	@Override
	public Collection<Producto> findByNombre(String Nombre) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = this.em.createQuery("SELECT producto FROM Producto WHERE producto.nombre =:nombre");
		query.setParameter("nombre", Nombre);
		return query.getResultList();

	}


	@SuppressWarnings("unchecked")
	@Override
	public Collection<Producto> findByEspecie(int id_especie) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = this.em.createQuery("SELECT producto FROM Producto WHERE producto.id =:id");
		query.setParameter("id", id_especie);
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public Collection<Producto> findByPrecio(int precio) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = this.em.createQuery("SELECT producto FROM Producto WHERE producto.precio =:precio");
		query.setParameter("precio", precio);
		return query.getResultList();
	}


}
