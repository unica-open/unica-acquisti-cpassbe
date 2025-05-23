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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdRigaEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdRigaEvasione;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

@ApplicationScoped
public class RigaEvasioneDad extends BaseDad {

	@Inject
	private CpassTOrdRigaEvasioneDao cpassTOrdRigaEvasioneDao;
	/**
	 * 
	 * @param key
	 * @return
	 */
	public RigaEvasione findOne(UUID key) {
		final Optional<CpassTOrdRigaEvasione> cpassTOrdRigaEvasioneOptional = cpassTOrdRigaEvasioneDao.findOne(key);
		if (cpassTOrdRigaEvasioneOptional.isPresent()) {
			final CpassTOrdRigaEvasione cpassTOrdRigaEvasione = cpassTOrdRigaEvasioneOptional.get();
			return CpassMappers.RIGA_EVASIONE.toModel(cpassTOrdRigaEvasione);
		} else {
			throw new NotFoundException("riga evasione");
		}
	}
	/**
	 * 
	 * @param idDestinatarioEvasione
	 * @return
	 */
	public List<RigaEvasione> getRigheByDestinatarioEvasione(UUID idDestinatarioEvasione) {
		final List<CpassTOrdRigaEvasione> righeEvasione = cpassTOrdRigaEvasioneDao.findByIdDestinatarioEvasione(idDestinatarioEvasione);
		return CpassMappers.RIGA_EVASIONE.toModels(righeEvasione);
	}
	/**
	 * 
	 * @param idRigaOrdine
	 * @return
	 */
	public List<RigaEvasione> findByIdRigaOrdine(UUID idRigaOrdine) {
		final List<CpassTOrdRigaEvasione> righeEvasione = cpassTOrdRigaEvasioneDao.findByIdRigaOrdine(idRigaOrdine);
		return CpassMappers.RIGA_EVASIONE.toModels(righeEvasione);
	}

	/**
	 * Inserts the rigaEvasione
	 *
	 * @param rigaEvasione to insert
	 * @return the model instance
	 */
	public RigaEvasione saveRigaEvasione(RigaEvasione rigaEvasione) {
		final CpassTOrdRigaEvasione rigaEvasioneEntity = CpassMappers.RIGA_EVASIONE.toEntity(rigaEvasione);
		// incremento il progressivo
		if (rigaEvasione.getProgressivo() == null || rigaEvasione.getProgressivo().equals(0)) {
			Integer maxProgressivo = 0;
			final List<CpassTOrdRigaEvasione> righeEvasione = cpassTOrdRigaEvasioneDao.findByIdDestinatarioEvasione(rigaEvasione.getDestinatarioEvasione().getId());
			for (final CpassTOrdRigaEvasione riga : righeEvasione) {
				if (riga.getProgressivo() > maxProgressivo) {
					maxProgressivo = riga.getProgressivo();
				}
			}
			maxProgressivo++;
			rigaEvasioneEntity.setProgressivo(maxProgressivo);
		}
		final CpassTOrdRigaEvasione ris = cpassTOrdRigaEvasioneDao.insert(rigaEvasioneEntity);
		cpassTOrdRigaEvasioneDao.flush();
		final RigaEvasione result = CpassMappers.RIGA_EVASIONE.toModel(ris);
		return result;
	}

	/**
	 * Updates the rigaEvasione
	 *
	 * @param rigaEvasione to update
	 * @return the model instance
	 */
	public RigaEvasione updateRigaEvasione(RigaEvasione rigaEvasione) {
		final CpassTOrdRigaEvasione rigaEvasioneEntity = CpassMappers.RIGA_EVASIONE.toEntity(rigaEvasione);
		final RigaEvasione result = CpassMappers.RIGA_EVASIONE.toModel(cpassTOrdRigaEvasioneDao.update(rigaEvasioneEntity));
		return result;
	}
	/**
	 * 
	 * @param idRigaEvasione
	 */
	public void deleteRigaEvasione(UUID idRigaEvasione) {
		cpassTOrdRigaEvasioneDao.delete(idRigaEvasione);
	}
	/**
	 * 
	 * @param idRigaEvasione
	 */
	public void deleteLogicallyRigaEvasione(UUID idRigaEvasione) {
		cpassTOrdRigaEvasioneDao.deleteLogically(idRigaEvasione);
	}
	/**
	 * 
	 * @param rigaOrdineId
	 * @return
	 */
	public BigDecimal calcolaTotale(UUID rigaOrdineId) {
		return cpassTOrdRigaEvasioneDao.calcolaTotale(rigaOrdineId);
	}
	/**
	 * 
	 * @param rigaOrdineId
	 * @param rigaEvasioneId
	 * @return
	 */
	public BigDecimal calcolaTotaleEsclusaEvasioneCorrente(UUID rigaOrdineId, UUID rigaEvasioneId) {
		return cpassTOrdRigaEvasioneDao.calcolaTotaleEsclusaEvasione(rigaOrdineId, rigaEvasioneId);
	}
	/**
	 * 
	 * @param rigaOrdineId
	 * @return
	 */
	public BigDecimal calcolaQuantitaEvasa(UUID rigaOrdineId) {
		return cpassTOrdRigaEvasioneDao.calcolaQuantitaEvasa(rigaOrdineId);
	}
	/**
	 * 
	 * @param idOrdines
	 * @param idOrdine
	 * @param fatturaAnno
	 * @param fatturaNumero
	 * @param fatturaTipo
	 * @param fatturaCodice
	 * @return
	 */
	public List<RigaEvasione> getRigheByOrdineEDocumento(List<UUID> idOrdines, UUID idOrdine, Integer fatturaAnno, String fatturaNumero, String fatturaTipo, String fatturaCodice){
		final List<CpassTOrdRigaEvasione> righeEvasione = cpassTOrdRigaEvasioneDao.findByIdOrdineEDocumento(idOrdines, idOrdine, fatturaAnno, fatturaNumero, fatturaTipo, fatturaCodice);
		return CpassMappers.RIGA_EVASIONE.toModels(righeEvasione);
	}
	/**
	 * 
	 * @param rigaOrdineId
	 * @return
	 */
	public BigDecimal getTotQtaEvasa(UUID rigaOrdineId) {
		return cpassTOrdRigaEvasioneDao.getTotQtaEvasa(rigaOrdineId);
	}
}
