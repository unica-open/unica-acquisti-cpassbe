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
package it.csi.cpass.cpassbe.ejb.business.be.dao.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseEntityDao;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.util.reflection.ReflectionHelper;

/**
 * Base Data Access Object (DAO) implementor
 * @param <K> the key type
 * @param <T> the entity type
 */
public abstract class BaseEntityDaoImpl<K, T extends BaseEntity<K>> extends BaseDaoImpl<T> implements BaseEntityDao<K, T> {

	/** The underlying id field */
	protected final Field idField;

	/**
	 * Default constructor
	 */
	protected BaseEntityDaoImpl() {
		super();
		this.idField = getIdField();
	}

	private Field getIdField() {
		Field id = ReflectionHelper.getFieldByAnnotation(this.clazz, Id.class);
		if(id == null) {
			id = ReflectionHelper.getFieldByAnnotation(this.clazz, EmbeddedId.class);
		}
		return id;
	}

	@Override
	public Optional<T> findById(K key) {
		ReflectionHelper.getFieldByAnnotation(this.clazz, EmbeddedId.class);
		final StringBuilder sb = new StringBuilder()
				.append("FROM ")
				.append(clazz.getName())
				.append(" WHERE ")
				.append(idField.getName())
				.append(" = :id ");
		final Map<String, Object> params = new HashMap<>();
		params.put("id", key);
		final TypedQuery<T> query = composeTypedQuery(sb, params);
		return query.getResultList()
				.stream()
				.findFirst();
	}

	@Override
	public Optional<T> findOne(K key) {
		ReflectionHelper.getFieldByAnnotation(this.clazz, EmbeddedId.class);
		final StringBuilder sb = new StringBuilder()
				.append("FROM ")
				.append(clazz.getName())
				.append(" WHERE ")
				.append(idField.getName())
				.append(" = :id ");
		final Map<String, Object> params = new HashMap<>();
		params.put("id", key);
		final TypedQuery<T> query = composeTypedQuery(sb, params);
		return query.getResultList()
				.stream()
				.findFirst();
	}

	@Override
	public List<T> findAll() {
		final StringBuilder sb = new StringBuilder()
				.append("FROM ")
				.append(clazz.getName())
				.append(" ORDER BY ")
				.append(idField.getName());
		final TypedQuery<T> query = composeTypedQuery(sb, null);
		return query.getResultList();
	}

	@Override
	public Page<T> findAll(int page, int size) {
		final StringBuilder jpql = new StringBuilder()
				.append("FROM ")
				.append(clazz.getName())
				.append(" ORDER BY ")
				.append(idField.getName());
		return getPagedResult(jpql, null, page, size);
	}

	@Override
	public T insert(T entity) {
		entity.initId();
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		return entityManager.merge(entity);
	}

	@Override
	public T save(T entity) {
		if(entity.getId() == null) {
			return insert(entity);
		}
		return update(entity);
	}

	@Override
	public T saveAndFlush(T entity) {
		final T result = this.save(entity);
		this.flush();
		return result;
	}

	@Override
	public void delete(K key) {
		final StringBuilder sb = new StringBuilder()
				.append("DELETE FROM ")
				.append(clazz.getName())
				.append(" WHERE ")
				.append(idField.getName())
				.append(" = :id");
		final Map<String, Object> params = new HashMap<>();
		params.put("id", key);
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	@Override
	public void deleteAll() {
		final StringBuilder sb = new StringBuilder()
				.append("DELETE FROM ")
				.append(clazz.getName());
		final Map<String, Object> params = new HashMap<>();
		final Query query = composeQuery(sb, params);
		query.executeUpdate();
	}

	/**
	 * Gets the id
	 * @param entity the model
	 * @return the id
	 */
	protected K getId(BaseEntity<K> entity) {
		return entity != null ? entity.getId() : null;
	}

}
