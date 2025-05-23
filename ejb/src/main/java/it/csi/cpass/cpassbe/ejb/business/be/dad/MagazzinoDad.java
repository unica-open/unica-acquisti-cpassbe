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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.mag.CpassTMagMagazzinoDao;
import it.csi.cpass.cpassbe.ejb.entity.mag.CpassTMagMagazzino;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.Sort;

/**
 * Data Access Delegate for Magazzino
 */
@ApplicationScoped
public class MagazzinoDad extends BaseDad {

	@Inject
	private CpassTMagMagazzinoDao cpassTMagMagazzinoDao;

	/**
	 * Find by id
	 *
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<Magazzino> getMagazzinoById(Integer id) {
		return cpassTMagMagazzinoDao.findOne(id).map(CpassMappers.MAGAZZINO::toModel);
	}

	/**
	 * Find by id
	 *
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public List<Magazzino> getMagazziniByEnteId(UUID enteId) {
		final List<CpassTMagMagazzino> entity = cpassTMagMagazzinoDao.getMagazziniByEnteId(enteId);
		final List<Magazzino> lista = CpassMappers.MAGAZZINO.toModels(entity);
		return lista;
	}
/**
 * 
 * @param page
 * @param size
 * @param sort
 * @param mag
 * @return
 */
	public PagedList<Magazzino> getMagazzini(int page, int size, Sort sort,Magazzino mag) {
		final Page<CpassTMagMagazzino> cpassTMagMagazzino = cpassTMagMagazzinoDao.findPaginated(
				mag.getCodice(),
				mag.getDescrizione(),
				getId(mag.getEnte()),
				page,
				size,
				null,
				null
				);
		final PagedList<Magazzino> pagedList = toPagedList(cpassTMagMagazzino, page, size, CpassMappers.MAGAZZINO::toModel);
		return pagedList;
	}

	/**
	 *
	 * @param magazzino
	 * @return Magazzino
	 */
	public Magazzino saveTestataRms(Magazzino magazzino) {
		final CpassTMagMagazzino entity = CpassMappers.MAGAZZINO.toEntity(magazzino);
		final CpassTMagMagazzino ret = cpassTMagMagazzinoDao.save(entity);
		return CpassMappers.MAGAZZINO.toModel(ret);
	}

}
