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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoAltriDatiDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.pba.CpassTPbaInterventoImportiDao;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaInterventoImporti;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.ejb.util.jpa.Page;
import it.csi.cpass.cpassbe.lib.dto.pba.InterventoImporti;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;

/**
 * Data Access Delegate for interventos
 */
@ApplicationScoped
public class InterventoImportiDad extends BaseDad {

	@Inject private CpassTPbaInterventoImportiDao cpassTPbaInterventoImportiDao;
	@Inject private CpassTPbaInterventoAltriDatiDao cpassTPbaInterventoAltriDatiDao;


	/**
	 * Find by id
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Optional<InterventoImporti> getInterventoImporti(UUID uuid) {
		return cpassTPbaInterventoImportiDao.findOne(uuid)
				.map(CpassMappers.INTERVENTO_IMPORTI::toModel);
	}
	/**
	 * Find paginated
	 * @param interventoImporti the intervento importi
	 * @param page the page
	 * @param size the size
	 * @return the model instances
	 */
	public PagedList<InterventoImporti> getInterventiImporti(InterventoImporti interventoImporti, int page, int size) {
		final Page<CpassTPbaInterventoImporti> cpassTPbaInterventoImportis = cpassTPbaInterventoImportiDao.findPaginated(
				interventoImporti.getImportoAnniSuccessivi(),
				interventoImporti.getImportoAnnoSecondo(),
				interventoImporti.getImportoAnnoTerzo(),
				interventoImporti.getImportoAnniSuccessivi(),
				getId(interventoImporti.getIntervento()),
				getId(interventoImporti.getRisorsa()),
				page,
				size);
		return toPagedList(cpassTPbaInterventoImportis, page, size, CpassMappers.INTERVENTO_IMPORTI::toModel);
	}

	public List<InterventoImporti> getInterventiImportiByInterventoId(UUID interventoId) {
		final List<CpassTPbaInterventoImporti> cpassTPbaInterventoImportis = cpassTPbaInterventoImportiDao.getInterventiImportiByInterventoId(interventoId);
		return CpassMappers.INTERVENTO_IMPORTI_MINIMAL.toModels(cpassTPbaInterventoImportis);
	}

	/**
	 * Deletes by uuid
	 * @param uuid the uuid
	 */
	public void deleteInterventoImporti(UUID uuid) {
		cpassTPbaInterventoImportiDao.deleteLogically(uuid);
	}

	/**
	 * Deletes by uuid
	 * @param uuid the uuid
	 */
	public void deleteNotLogically(UUID uuid) {
		cpassTPbaInterventoImportiDao.delete(uuid);
	}


	/**
	 * Inserts the interventoImporti
	 * @param interventoImporti the interventoImporti
	 * @return the model instance
	 */
	public InterventoImporti saveInterventoImporti(InterventoImporti interventoImporti) {
		if (interventoImporti.getRichiestaMotivazione() == null) {
			interventoImporti.setRichiestaMotivazione(Boolean.FALSE);
		}
		final CpassTPbaInterventoImporti cpassTPbaInterventoImporti = CpassMappers.INTERVENTO_IMPORTI.toEntity(interventoImporti);
		cpassTPbaInterventoImportiDao.insert(cpassTPbaInterventoImporti);
		cpassTPbaInterventoImportiDao.flush();
		return interventoImporti;
	}

	/**
	 * Inserts the interventoImporti
	 * @param interventoImporti the interventoImporti
	 * @return the model instance
	 */
	public InterventoImporti saveInterventoImportiXCsv(InterventoImporti interventoImporti) {
		if (interventoImporti.getRichiestaMotivazione() == null) {
			interventoImporti.setRichiestaMotivazione(Boolean.FALSE);
		}
		final CpassTPbaInterventoImporti cpassTPbaInterventoImporti = CpassMappers.INTERVENTO_IMPORTI.toEntity(interventoImporti);
		cpassTPbaInterventoImportiDao.insert(cpassTPbaInterventoImporti);
		return interventoImporti;
	}

	/**
	 * Updates the interventoImporti
	 * @param interventoImporti the interventoImporti
	 * @return the model instance
	 */
	public InterventoImporti updateInterventoImporti(InterventoImporti interventoImporti) {
		if (interventoImporti.getRichiestaMotivazione() == null) {
			interventoImporti.setRichiestaMotivazione(Boolean.FALSE);
		}
		final CpassTPbaInterventoImporti cpassTPbaInterventoImporti = CpassMappers.INTERVENTO_IMPORTI.toEntity(interventoImporti);
		cpassTPbaInterventoImportiDao.update(cpassTPbaInterventoImporti);
		cpassTPbaInterventoImportiDao.flush();
		return interventoImporti;
	}
	/**
	 * Save the intervento importi
	 * @param interventiImporti
	 * @return the intervento importi
	 */
	public List<InterventoImporti> saveInterventiImportiXCsv(List<InterventoImporti> interventiImporti) {
		final List<InterventoImporti> ris = new ArrayList<>();
		for(final InterventoImporti ii : interventiImporti) {
			ris.add(saveInterventoImportiXCsv(ii));
		}
		return ris;
	}

	/**
	 *
	 * @param interventoId
	 * @return BigDecimal
	 */
	public BigDecimal getImportoTotByInterventoId(UUID interventoId) {
		return cpassTPbaInterventoImportiDao.getImportoTotByInterventoId(interventoId);
	}

	/**
	 *
	 * @param interventoId
	 * @return BigDecimal
	 */
	public BigDecimal getImportoIvaTotByInterventoId(UUID interventoId) {
		return cpassTPbaInterventoAltriDatiDao.getImportoIvaTotByInterventoId(interventoId);
	}
}
