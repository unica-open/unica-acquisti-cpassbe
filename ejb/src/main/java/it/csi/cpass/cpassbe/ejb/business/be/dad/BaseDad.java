/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import java.util.function.Function;

import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTProgressivoDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTProgressivo;
import it.csi.cpass.cpassbe.ejb.entity.CpassTProgressivoPk;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.util.log.LogUtil;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Base Data Access Delegate (DAD) class: maps between Entities and Models
 */
public abstract class BaseDad {

	@Inject private CpassTProgressivoDao cpassTProgressivoDao;
	/** Logger */
	protected final LogUtil log = new LogUtil(getClass());

	/**
	 * Flush of the entity manager
	 */
	public void flush() {
		cpassTProgressivoDao.flush();
	}
	
	/**
	 * Flush And Clear of the entity manager
	 */
	public void flushAndClear() {
		cpassTProgressivoDao.flushAndClear();
	}
	
	
	/**
	 * Gets a new progressive value starting from "1"
	 * @param tipo the type
	 * @param codice the code
	 * @return the progressive value
	 */
	protected Integer getProgressivo1(String tipo, String codice) {
		final String methodName = "getProgressivo1";
		CpassTProgressivoPk id = new CpassTProgressivoPk();
		id.setProgressivoTipo(tipo);
		id.setProgressivoCodice(codice);
		CpassTProgressivo cpassTProgressivo = cpassTProgressivoDao.findOne(id).orElseGet(() -> {
			CpassTProgressivo ctp = new CpassTProgressivo();
			ctp.setId(id);
			ctp.setProgressivoNumero(Integer.valueOf(1));
			ctp = cpassTProgressivoDao.insert(ctp);
			return ctp;
		});
		cpassTProgressivo.setProgressivoNumero(Integer.valueOf(cpassTProgressivo.getProgressivoNumero().intValue() + 1));
		cpassTProgressivoDao.saveAndFlush(cpassTProgressivo);
		Integer numero = cpassTProgressivo.getProgressivoNumero();
		log.trace(methodName, () -> "Returning CpassTProgressivo for tipo = \"" + tipo + "\", codice = \"" + codice + "\": " + numero);
		return numero;
	}

	
	/**
	 * Gets a new progressive value
	 * @param tipo the type
	 * @param codice the code
	 * @return the progressive value
	 */
	protected Integer getProgressivo(String tipo, String codice) {
		final String methodName = "getProgressivo";
		CpassTProgressivoPk id = new CpassTProgressivoPk();
		id.setProgressivoTipo(tipo);
		id.setProgressivoCodice(codice);
		CpassTProgressivo cpassTProgressivo = cpassTProgressivoDao.findOne(id).orElseGet(() -> {
			CpassTProgressivo ctp = new CpassTProgressivo();
			ctp.setId(id);
			ctp.setProgressivoNumero(Integer.valueOf(0));
			ctp = cpassTProgressivoDao.insert(ctp);
			return ctp;
		});
		cpassTProgressivo.setProgressivoNumero(Integer.valueOf(cpassTProgressivo.getProgressivoNumero().intValue() + 1));
		cpassTProgressivoDao.saveAndFlush(cpassTProgressivo);
		Integer numero = cpassTProgressivo.getProgressivoNumero();
		log.trace(methodName, () -> "Returning CpassTProgressivo for tipo = \"" + tipo + "\", codice = \"" + codice + "\": " + numero);
		return numero;
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
		PagedList<D> pagedList = new PagedListImpl<>();
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
}
