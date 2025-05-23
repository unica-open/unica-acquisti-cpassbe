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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.cpass.cpassbe.ejb.business.be.dao.BaseDao;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.ejb.util.jpa.PageImpl;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;
import it.csi.cpass.cpassbe.lib.util.reflection.GenericTypeResolver;

/**
 * Base Data Access Object (DAO) implementor
 * @param <T> the type
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	/** The persistence context for JPA */
	@PersistenceContext protected EntityManager entityManager;
	/** The underlying class */
	protected final Class<T> clazz;
	/** The logger */
	protected final LogUtil log = new LogUtil(getClass());

	/**
	 * Default constructor
	 */
	@SuppressWarnings("unchecked")
	protected BaseDaoImpl() {
		this.clazz = (Class<T>) GenericTypeResolver.resolveActualTypeArgs(getClass(), BaseDaoImpl.class)[0];
	}

	@Override
	public void flush() {
		entityManager.flush();
	}

	@Override
	public void clear() {
		entityManager.clear();
	}

	@Override
	public void flushAndClear() {
		entityManager.flush();
		entityManager.clear();
	}

	/**
	 * Composes a typed query
	 * @param <E> the entity type
	 * @param jpql the JPQL to compose the query for
	 * @param params the parameters for the query
	 * @param entityClass the class for the entity
	 * @return the typed query
	 */
	protected <E> TypedQuery<E> composeTypedQuery(CharSequence jpql, Map<String, Object> params, Class<E> entityClass) {
		final String methodName = "composeTypedQuery";
		if(jpql == null) {
			throw new IllegalArgumentException("The JPQL string must be set");
		}
		log.trace(methodName, () -> traceSql(jpql, params));
		final TypedQuery<E> query = entityManager.createQuery(jpql.toString(), entityClass);
		return replaceParams(query, params);
	}

	/**
	 * Composes a typed query
	 * @param jpql the JPQL to compose the query for
	 * @param params the parameters for the query
	 * @return the typed query
	 */
	protected TypedQuery<T> composeTypedQuery(CharSequence jpql, Map<String, Object> params) {
		return composeTypedQuery(jpql, params, clazz);
	}

	/**
	 * Composes a query
	 * @param jpql the JPQL to compose the query for
	 * @param params the parameters for the query
	 * @return the query
	 */
	protected Query composeQuery(CharSequence jpql, Map<String, Object> params) {
		final String methodName = "composeQuery";
		if(jpql == null) {
			throw new IllegalArgumentException("The JPQL string must be set");
		}
		log.trace(methodName, () -> traceSql(jpql, params));
		final Query query = entityManager.createQuery(jpql.toString());
		return replaceParams(query, params);
	}

	/**
	 * Composes a native query
	 * @param sql the JPQL to compose the query for
	 * @param params the parameters for the query
	 * @return the typed query
	 */
	protected Query composeNativeQuery(CharSequence sql, Map<String, Object> params) {
		final String methodName = "composeNativeQuery";
		if(sql == null) {
			throw new IllegalArgumentException("The SQL string must be set");
		}
		log.trace(methodName, () -> traceSql(sql, params));
		final Query query = entityManager.createNativeQuery(sql.toString());
		return replaceParams(query, params);
	}

	/**
	 * Composes a "typed" native query
	 * @param <E> the entity type
	 * @param sql the JPQL to compose the query for
	 * @param params the parameters for the query
	 * @param entityClass the entity class
	 * @return the query
	 */
	protected <E> Query composeTypedNativeQuery(CharSequence sql, Map<String, Object> params, Class<E> entityClass) {
		final String methodName = "composeTypedNativeQuery";
		if(sql == null) {
			throw new IllegalArgumentException("The SQL string must be set");
		}
		log.trace(methodName, () -> traceSql(sql, params));
		final Query query = entityManager.createNativeQuery(sql.toString(), entityClass);
		return replaceParams(query, params);
	}

	/**
	 * Composes a "typed" native query
	 * @param sql the JPQL to compose the query for
	 * @param params the parameters for the query
	 * @return the query
	 */
	protected Query composeTypedNativeQuery(CharSequence sql, Map<String, Object> params) {
		return composeTypedNativeQuery(sql, params, clazz);
	}

	/**
	 * Replaces the parameters in a query
	 * @param query the query whose parameters are to be replaced
	 * @param params the parameters
	 * @return the query with the parameters replaced
	 */
	private Query replaceParams(Query query, Map<String, Object> params) {
		if(params == null) {
			return query;
		}
		return params.entrySet()
				.stream()
				.reduce(
						query,
						(acc, entry) -> acc.setParameter(entry.getKey(), entry.getValue()),
						// Useless parameter, leaked from ParallelStream
						(acc1, acc2) -> acc1);
	}
	/**
	 * Replaces the parameters in a typed query
	 * @param query the query whose parameters are to be replaced
	 * @param params the parameters
	 * @return the query with the parameters replaced
	 */
	private <E> TypedQuery<E> replaceParams(TypedQuery<E> query, Map<String, Object> params) {
		if(params == null) {
			return query;
		}
		return params.entrySet()
				.stream()
				.reduce(
						query,
						(acc, entry) -> acc.setParameter(entry.getKey(), entry.getValue()),
						// Useless parameter, leaked from ParallelStream
						(acc1, acc2) -> acc1);
	}

	/**
	 * Retrieves a paged result
	 * @param <E> the entity type
	 * @param jpql the jpql to execute
	 * @param params the parameters
	 * @param page the page number
	 * @param size the page size if size < 0 return count if size > 0 return paginated data if size = 0 return All
	 * @param entityClass the entity class
	 * @return the page
	 */
	protected <E> Page<E> getPagedResult(CharSequence jpql, Map<String, Object> params, int page, int size, Class<E> entityClass) {
		final Query qn = composeQuery(getCountQuery(jpql.toString()), params);
		final long count = ((Number) qn.getSingleResult()).longValue();
		if(count == 0) {
			return new PageImpl<>(0);
		}

		if(size < 0) {
			return new PageImpl<>(count, new ArrayList<>());
		}

		final TypedQuery<E> query = composeTypedQuery(jpql, params, entityClass);
		if(size > 0) {
			query.setFirstResult(page * size);
			query.setMaxResults(size);
		}
		return new PageImpl<>(count, query.getResultList());
	}

	/**
	 * Retrieves a paged result
	 * @param jpql the jpql to execute
	 * @param params the parameters
	 * @param page the page number
	 * @param size the page size
	 * @return the page
	 */
	protected Page<T> getPagedResult(CharSequence jpql, Map<String, Object> params, int page, int size) {
		return getPagedResult(jpql, params, page, size, clazz);
	}

	/**
	 * Composes the COUNT query
	 * @param jpql the JPQL
	 * @return the query
	 */
	/*
	public String getCountQuery(final CharSequence jpql) {
		final String jpqlString = jpql.toString();
		String upperJpqlString = jpqlString.toUpperCase();
		final int fromIndex = upperJpqlString.indexOf("FROM");
		final int toIndex = upperJpqlString.lastIndexOf("ORDER BY");

		String innerQuery;
		if (toIndex != -1) {
			innerQuery = jpqlString.substring(fromIndex, toIndex);
		} else {
			innerQuery = jpqlString.substring(fromIndex);
		}
		return String.format("SELECT COUNT(*) %s", innerQuery);
	}
	 */
	//private String getDistinctCountQuery(final String jpql) {
	public String getCountQuery(final String jpql) {
		String jpqlCountContent = "*";
		final String upperCaseJpql = jpql.toUpperCase(Locale.ITALIAN);

		final int distinctIndex = upperCaseJpql.indexOf("DISTINCT");
		final int fromIndex = upperCaseJpql.indexOf("FROM");
		final int commaIndex = upperCaseJpql.indexOf(",");

		// TODO: verificare la gestione con la versione di Hibernate presente nell'applicativo. Magari ora funziona...
		// Se ho la distinct, la distinct e' PRIMA del primo from e ho virgole nella distinct
		if(distinctIndex != -1 && distinctIndex < fromIndex && commaIndex != -1 && commaIndex < fromIndex) {
			// La versione migliore sarebbe quella di racchiudere la query originaria in una sotto-query. Ma Hibernate non lo supporta
			// Cfr. https://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html/ch16.html#queryhql-subqueries
			throw new UnsupportedOperationException("COUNT Query per una distinct a parametri multipli non supportata da Hibernate.");
		}

		String jpqlCount = jpql.substring(fromIndex);

		// Se ho la distinct, la distinct e' PRIMA del primo from e non ho virgole nella distinct
		if(distinctIndex > -1 && distinctIndex < fromIndex) {
			// Injetto il dato in distinct
			jpqlCountContent = jpql.substring(distinctIndex, fromIndex);
		}

		final int toIndex = jpqlCount.toUpperCase(Locale.ITALIAN).lastIndexOf("ORDER BY");
		if (toIndex != -1) {
			jpqlCount = jpqlCount.substring(0, toIndex);
		}
		return String.format("SELECT COUNT(%s) %s", jpqlCountContent, jpqlCount);
	}


	/**
	 * Retrieves a native paged result
	 * @param <E> the entity type
	 * @param sql the sql to execute
	 * @param params the parameters
	 * @param page the page number
	 * @param size the page size
	 * @param entityClass the entity class
	 * @return the page
	 */
	protected <E> Page<E> getNativePagedResult(CharSequence sql, Map<String, Object> params, int page, int size, Class<E> entityClass) {
		final Query qn = composeQuery(getCountQuery(sql.toString()), params);
		final long count = ((Number) qn.getSingleResult()).longValue();
		if(count == 0) {
			return new PageImpl<>(0);
		}

		final Query query = composeTypedNativeQuery(sql, params, entityClass);
		query.setFirstResult(page * size);
		query.setMaxResults(size);

		@SuppressWarnings("unchecked")
		final
		List<E> resultList = query.getResultList();
		return new PageImpl<>(count, resultList);
	}

	/**
	 * Retrieves a native paged result
	 * @param sql the sql to execute
	 * @param params the parameters
	 * @param page the page number
	 * @param size the page size
	 * @return the page
	 */
	protected Page<T> getNativePagedResult(CharSequence sql, Map<String, Object> params, int page, int size) {
		return getNativePagedResult(sql, params, page, size, clazz);
	}

	/**
	 * Applies the sort value
	 * @param jpql the jpql
	 * @param sorts the sorts
	 * @param defaultSort the default sort
	 */
	protected void applySort(StringBuilder jpql, List<Sort> sorts, String defaultSort) {
		jpql.append(" ORDER BY ");
		if(sorts == null || sorts.isEmpty()) {
			jpql.append(defaultSort);
			return;
		}
		jpql.append(sorts
				.stream()
				.map(s -> s.getField() + " " + s.getOrder().name())
				.collect(Collectors.joining(", ")));
	}

	/**
	 * Traces the SQL/JPQL to a log
	 * @param sql the SQL/JPQL to execute
	 * @param params the parameters
	 * @return the trace
	 */
	private Object traceSql(CharSequence sql, Map<String, Object> params) {
		return sql.toString() + (params == null ? "" : " - PARAMS: " + params.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining(", ")));
	}

}
