/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dao;

import java.util.List;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;

/**
 * Base Data Access Object (DAO) interface
 * @param <K> the id type
 * @param <T> the entity type
 */
public interface BaseEntityDao<K, T extends BaseEntity<K>> extends BaseDao<T> {

	/**
	 * Finds an entity by its key
	 * @param key the key
	 * @return the entity
	 */
	Optional<T> findById(K key);
	/**
	 * Finds an entity by its key
	 * @param key the key
	 * @return the entity
	 */
	Optional<T> findOne(K key);
	/**
	 * Finds all the entities
	 * @return the entities
	 */
	List<T> findAll();

	/**
	 * Finds all the entities.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the page of entities
	 */
	Page<T> findAll(int page, int size);
	/**
	 * Inserts the entity
	 * @param entity the entity to be inserted
	 * @return the inserted entity
	 */
	T insert(T entity);
	/**
	 * Updates the entity
	 * @param entity the entity to be updated
	 * @return the updated entity
	 */
	T update(T entity);
	/**
	 * Deletes the entity
	 * @param key the key of the entity
	 */
	void delete(K key);
	/**
	 * Deletes the table
	 * @param key the key of the entity
	 */
	void deleteAll();
	/**
	 * Saves the entity
	 * @param entity the entity to be saved
	 * @return the saved entity
	 */
	T save(T entity);
	/**
	 * Saves the entity and flushes the persistence context
	 * @param entity the entity to be saved
	 * @return the saved entity
	 */
	T saveAndFlush(T entity);
}
