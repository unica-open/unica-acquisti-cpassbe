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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFornitoreDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

@ApplicationScoped
public class FornitoreDad extends BaseDad {

	@Inject
	private CpassTFornitoreDao cpassTFornitoreDao;
	/**
	 * 
	 * @param codice
	 * @return
	 */
	public Fornitore getFornitoreByCodice(String codice) {

		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		return getFornitoreByCodice (codice, getId(ente));
	}
	/**
	 * 
	 * @param codice
	 * @param enteId
	 * @return
	 */
	public Fornitore getFornitoreByCodice(String codice, UUID enteId) {
		final CpassTFornitore cpassTFornitore = cpassTFornitoreDao.getFornitoreByCodice(codice, enteId);
		final Fornitore fornitore = CpassMappers.FORNITORE.toModel(cpassTFornitore);
		return fornitore;
	}
	/**
	 * 
	 * @param fornitore
	 * @return
	 */
	public Fornitore insert(Fornitore fornitore) {
		log.info("insert fornitore ", "cpassTFornitore " + fornitore.getCodice());
		CpassTFornitore cpassTFornitore = CpassMappers.FORNITORE.toEntity(fornitore);
		cpassTFornitore.setIndirizzo(fornitore.getIndirizzoConSedime());
		cpassTFornitore = cpassTFornitoreDao.insert(cpassTFornitore);
		cpassTFornitoreDao.flush();
		return CpassMappers.FORNITORE.toModel(cpassTFornitore);
	}
	
	/**
	 * 
	 * @param fornitore
	 * @param ente
	 * @return
	 */
	public List<Fornitore> postRicercaFornitoreInterno(Fornitore fornitore,Ente ente) {
		final List<CpassTFornitore> cpassTFornitores = cpassTFornitoreDao.getFornitore(
				fornitore.getCodice(),
				fornitore.getCodiceFiscale(),
				fornitore.getPartitaIva(),
				fornitore.getRagioneSociale(),
				getId(ente));

		return CpassMappers.FORNITORE.toModels(cpassTFornitores);
	}
	/**
	 * 
	 * @param fornitore
	 * @param ente
	 * @return
	 */
	public List<Fornitore> postRicercaFornitoreMinimalInterno(Fornitore fornitore,Ente ente) {
		final List<CpassTFornitore> cpassTFornitores = cpassTFornitoreDao.getFornitore(
				fornitore.getCodice(),
				fornitore.getCodiceFiscale(),
				fornitore.getPartitaIva(),
				fornitore.getRagioneSociale(),
				getId(ente));

		return CpassMappers.FORNITORE_MINIMAL.toModels(cpassTFornitores);
	}
	/**
	 * 
	 * @param fornitoreId
	 * @return
	 */
	public Fornitore getRicercaFornitoreInterno(UUID fornitoreId) {
		final CpassTFornitore cpassTFornitore = cpassTFornitoreDao.findOne(fornitoreId).orElse(new CpassTFornitore());
		return CpassMappers.FORNITORE.toModel(cpassTFornitore);
	}
}
