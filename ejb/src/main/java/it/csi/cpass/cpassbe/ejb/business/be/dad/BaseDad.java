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
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.ejb.util.jpa.PageImpl;
import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Base Data Access Delegate (DAD) class: maps between Entities and Models
 */
public abstract class BaseDad {

	//@Inject private CpassTProgressivoDao cpassTProgressivoDao;
	/** Logger */
	protected final LogUtil log = new LogUtil(getClass());
	/**
	 * 
	 * @param <K>
	 * @param <T>
	 * @param lista
	 * @return
	 */
	protected <K,T extends BaseDto<K>> Map<K,T> listToMap(List<T> lista) {
		final Map<K, T> resultsMap = new HashMap<>();
		if(lista==null || lista.size()==0) {
			return resultsMap;
		}
		for (final T elemento : lista) {
			resultsMap.put(elemento.getId(), elemento);
		}
		return resultsMap;
	}

	/**
	 * Converts a page of data from the persistence layer to a paged list for the business layer
	 * @param <D> the Model type
	 * @param <E> the Entity type
	 * @param elements the persistence layer elements
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @param converter the converter function
	 * @return the paged list corresponding to the given page
	 */
	protected <D, E> PagedList<D> toPagedList(Page<E> elements, int pageNumber, int pageSize, Function<E, D> converter) {
		final PagedList<D> pagedList = new PagedListImpl<>();
		pagedList.setTotalElements(elements.getTotalElements());
		if(pageSize > 0) {
			pagedList.setCurrentPage(pageNumber);
			pagedList.setTotalPages((long) Math.ceil((double)elements.getTotalElements() / pageSize));
		} else {
			pagedList.setCurrentPage(0);
			pagedList.setTotalPages(1);
		}

		elements.getContent()
		.stream()
		.map(converter::apply)
		.forEach(pagedList::add);
		return pagedList;
	}

	/**
	 * Extracts, null-safe, the id from the model
	 * @param model the model
	 * @return the id
	 */
	protected <K> K getId(BaseDto<K> model) {
		return model != null ? model.getId() : null;
	}

	/**
	 * 
	 * @param <E>
	 * @param lista
	 * @param page
	 * @param size
	 * @return
	 */
	protected <E> Page<E> getPagedResult(List<E> lista, int page, int size) {
		final int count = lista.size();
		if(count == 0) {return new PageImpl<>(0);}

		final List<E> listaDipagina = new ArrayList<>();
		final long firstResult = (long)page * size;
		final long lastResult  = firstResult + size;
		for(int i = 0; i<lista.size();i++ ) {
			if (i>=count) {
				break;
			}
			if (i>firstResult && i<=lastResult) {
				final E el =lista.get(i);
				listaDipagina.add(el);
			}
		}
		return new PageImpl<>(count, listaDipagina);
	}

}
