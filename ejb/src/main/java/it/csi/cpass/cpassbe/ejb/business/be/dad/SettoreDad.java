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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import it.csi.cpass.cpassbe.ejb.business.be.dad.sort.SettoreSort;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTSettoreIndirizzoDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTUtenteDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTSettoreIndirizzo;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;

/**
 * Data Access Delegate for Settore
 */
@ApplicationScoped
public class SettoreDad extends BaseDad {

	@Inject private CpassTSettoreDao cpassTSettoreDao;
	@Inject private CpassTUtenteDao cpassTUtenteDao;
	@Inject private CpassTSettoreIndirizzoDao cpassTSettoreIndirizzoDao;

	/**
	 * Find paginated
	 * @param page the page
	 * @param size the size
	 * @param sort
	 * @param settore the settore
	 * @return the model instances
	 */
	public PagedList<Settore> postRicercaSettori(int page, int size, Sort sort, Settore settore) {

		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			sortField = SettoreSort.byModelName(sort.getField()).getQueryName();
			sortDirection = sort.getOrder().getSortDirection();
		}

		Page<CpassTSettore> cpassTSettores = cpassTSettoreDao.findPaginated(
			settore.getCap(),
			settore.getCodice(),
			settore.getDescrizione(),
			settore.getIndirizzo(),
			settore.getLocalita(),
			settore.getProvincia(),
			settore.getTelefono(),
			settore.getUtenteSettoreDefault(),
			settore.getRup(),
			settore.getTipoSettore() != null ? settore.getTipoSettore().getId() : null,
			settore.getEnte() != null ? settore.getEnte().getId() : null,
			page,
			size,
			sortField,
			sortDirection);

		return toPagedList(cpassTSettores, page, size, CpassMappers.SETTORE::toModel);
	}

	/**
	 * Find indirizzi of a Settore
	 * @param settore the settore
	 * @return the model instances
	 */
	public List<SettoreIndirizzo> postRicercaSettoreIndirizzi(Settore settore) {

		List<CpassTSettoreIndirizzo> list = cpassTSettoreIndirizzoDao.findBySettore(settore);

		return CpassMappers.SETTORE_INDIRIZZO.toModels(list);
	}
	
	/**
	 * Finds the settore by its codice
	 * @param codice the codice to filter by
	 * @return the settore
	 */
	public Settore findByCodice(String codice) {
		CpassTSettore settore = cpassTSettoreDao.findByCodice(codice);
		return CpassMappers.SETTORE.toModel(settore);
	}
	
	/**
	 * Finds the settore by its id
	 * <p><strong>NOTE</strong>: is executed in a transaction even if the calling method is non-transactional
	 * @param id the id to filter by
	 * @return the settore
	 */
	@Transactional(value = TxType.REQUIRED)
	public Settore findOne(UUID id) {
		Optional<CpassTSettore> optional = cpassTSettoreDao.findOne(id);
		if (optional.isEmpty()) {
			return null;
		}
		CpassTSettore settore = optional.get();
		return CpassMappers.SETTORE.toModel(settore);
	}
	
	/**
	 * Finds the settore by its id
	 * <p><strong>NOTE</strong>: is executed in a transaction even if the calling method is non-transactional
	 * @param id the id to filter by
	 * @return the settore
	 */
	@Transactional(value = TxType.REQUIRED)
	public Settore findById(UUID id) {
		Optional<CpassTSettore> optional = cpassTSettoreDao.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		CpassTSettore settore = optional.get();
		return CpassMappers.SETTORE.toModel(settore);
	}

	
	
	public List<Settore> getMySectorFamily(UUID settoreId) {
		List<CpassTSettore> listaPadre = cpassTSettoreDao.getSettoriBySettorePadre(settoreId);		
		List<CpassTSettore> listaAllFigli = new ArrayList<CpassTSettore>(listaPadre);

		listaAllFigli = getMySectorFamily(listaPadre, listaAllFigli);
		return CpassMappers.SETTORE.toModels(listaAllFigli);
	}	

	
	
	private List<CpassTSettore> getMySectorFamily(List<CpassTSettore> listaSettori,List<CpassTSettore> listaAllFigli) {
		String methodName = "getMySectorFamily";
		List<CpassTSettore> listaSettoriPadri = listaSettori;
		List<CpassTSettore> listaNewSon = new ArrayList<CpassTSettore>();
		for( CpassTSettore set : listaSettoriPadri) {
			List<CpassTSettore> sons = cpassTSettoreDao.getSettoriBySettorePadre(set.getSettoreId());
			listaNewSon = addSons(listaNewSon,sons);
		}

		listaAllFigli.addAll(listaNewSon);
		if (listaNewSon.size() >0 ) {
			getMySectorFamily(listaNewSon, listaAllFigli);
		}
		return listaAllFigli;
	}

	private List<CpassTSettore> addSons(List<CpassTSettore> listaAllFigli, List<CpassTSettore> listaNewSon) {
		List<CpassTSettore> listaAllFigliNew = listaAllFigli;
		for( CpassTSettore son : listaNewSon) {
			listaAllFigliNew.add(son);
		}
		return listaAllFigliNew;
	}	
	
	public List<Settore> getSettori() {
		List<CpassTSettore> settore = cpassTSettoreDao.findAll();
		return CpassMappers.SETTORE.toModels(settore);
	}

	
}
