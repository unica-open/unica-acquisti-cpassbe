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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTElaborazioneDao;
import it.csi.cpass.cpassbe.ejb.entity.CpassTElaborazione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;

/**
 * Data Access Delegate for elaboraziones
 */
@ApplicationScoped
public class ElaborazioneDad extends BaseDad {

	@Inject private CpassTElaborazioneDao          cpassTElaborazioneDao;

	/**
	 * Loads the elaboration
	 * @param key the elaboration key
	 * @return the elaboration
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Elaborazione loadElaborazione(Integer key) {
		return cpassTElaborazioneDao.findOne(key)
				.map(CpassMappers.ELABORAZIONE::toModel)
				.orElse(null);
	}

	/**
	 * Inserts the elaborazione
	 * @param elaborazione the Elaborazione
	 * @return the model instance
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Elaborazione saveElaborazione(Elaborazione elaborazione) {
		CpassTElaborazione cpassTElaborazione = CpassMappers.ELABORAZIONE.toEntity(elaborazione);
		cpassTElaborazione = cpassTElaborazioneDao.save(cpassTElaborazione);
		cpassTElaborazioneDao.flushAndClear();
		elaborazione.setId(cpassTElaborazione.getId());
		return elaborazione;
	}
	/**
	 * 
	 * @param entityId
	 * @return
	 */
	public List<Elaborazione> getElaborazioniByEntityId(String entityId) {
		final List<CpassTElaborazione> cpassTElaboraziones = cpassTElaborazioneDao.findByEntityId(entityId);
		return CpassMappers.ELABORAZIONE.toModels(cpassTElaboraziones);
	}
	/**
	 * 
	 * @param elaborazione
	 * @return
	 */
	public List<Elaborazione> postRicercaElaborazione(Elaborazione elaborazione) {
		final List<CpassTElaborazione> cpassTElaboraziones = cpassTElaborazioneDao.getElaborazione(elaborazione.getEntitaId(), elaborazione.getStato(),elaborazione.getElaborazioneTipo().getCodice(), elaborazione.getEnte().getId());
		return CpassMappers.ELABORAZIONE.toModels(cpassTElaboraziones);
	}
	/**
	 * 
	 * @param elaborazioneId
	 * @return
	 */
	public Elaborazione getElaborazioneById(Integer elaborazioneId) {
		final Optional<CpassTElaborazione> cpassTElaborazioneOptional = cpassTElaborazioneDao.findOne(elaborazioneId);
		if (cpassTElaborazioneOptional.isPresent()) {
			final CpassTElaborazione cpassTElaborazione = cpassTElaborazioneOptional.get();
			return CpassMappers.ELABORAZIONE.toModel(cpassTElaborazione);
		} else {
			throw new NotFoundException("elaborazione");
		}
	}
	/**
	 * 
	 * @param despatchAdviceId
	 * @return
	 */
	public List<Elaborazione> getElaborazioneDocumentoTrasportoScartato(String despatchAdviceId) {
		final List<CpassTElaborazione> cpassTElaborazione =
				cpassTElaborazioneDao.getElaborazioneDocumentoTrasportoScartato(despatchAdviceId);
		return CpassMappers.ELABORAZIONE.toModels(cpassTElaborazione);
	}

	/**
	 * Updates the elaborazione
	 * @param elaborazione the Elaborazione
	 * @deprecated sostituire col metodo Save sostitutivo di insert e update
	 * @return the model instance
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Deprecated(forRemoval = true)
	public Elaborazione updateElaborazione(Elaborazione elaborazione) {
		final CpassTElaborazione cpassTElaborazione = CpassMappers.ELABORAZIONE.toEntity(elaborazione);
		cpassTElaborazioneDao.update(cpassTElaborazione);
		cpassTElaborazioneDao.flush();
		return elaborazione;
	}
	/**
	 * 
	 * @param idEnte
	 * @param listaElaborazioneTipoId
	 * @param data
	 * @param numElabGiornata
	 * @return
	 */
	public List<Elaborazione> getElaborazioneByEnteAndType(UUID idEnte, List<Integer> listaElaborazioneTipoId,Date data ,Integer numElabGiornata) {
		final List<CpassTElaborazione> cpassTElaborazione =cpassTElaborazioneDao.getElaborazioneByEnteAndType( idEnte,  listaElaborazioneTipoId, data, numElabGiornata);
		return CpassMappers.ELABORAZIONE.toModels(cpassTElaborazione);
	}
}
