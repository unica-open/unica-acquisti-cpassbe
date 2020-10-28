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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdRigaEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdRigaEvasione;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;

@ApplicationScoped
public class RigaEvasioneDad extends BaseDad {

	@Inject
	private CpassTOrdRigaEvasioneDao cpassTOrdRigaEvasioneDao;

	public RigaEvasione findOne(UUID key) {
		CpassTOrdRigaEvasione cpassTOrdRigaEvasione = cpassTOrdRigaEvasioneDao.findOne(key).get();
		return CpassMappers.RIGA_EVASIONE.toModel(cpassTOrdRigaEvasione);
	}

	public List<RigaEvasione> getRigheByDestinatarioEvasione(UUID idDestinatarioEvasione) {
		List<CpassTOrdRigaEvasione> righeEvasione = cpassTOrdRigaEvasioneDao.findByIdDestinatarioEvasione(idDestinatarioEvasione);
		return CpassMappers.RIGA_EVASIONE.toModels(righeEvasione);
	}

	public List<RigaEvasione> findByIdRigaOrdine(UUID idRigaOrdine) {
		List<CpassTOrdRigaEvasione> righeEvasione = cpassTOrdRigaEvasioneDao.findByIdRigaOrdine(idRigaOrdine);
		return CpassMappers.RIGA_EVASIONE.toModels(righeEvasione);
	}

	/**
	 * Inserts the rigaEvasione
	 * 
	 * @param rigaEvasione to insert
	 * @return the model instance
	 */
	public RigaEvasione saveRigaEvasione(RigaEvasione rigaEvasione) {
		CpassTOrdRigaEvasione rigaEvasioneEntity = CpassMappers.RIGA_EVASIONE.toEntity(rigaEvasione);

		// incremento il progressivo
		if (rigaEvasione.getProgressivo() == null || rigaEvasione.getProgressivo().equals(0)) {
			Integer maxProgressivo = 0;
			List<CpassTOrdRigaEvasione> righeEvasione = cpassTOrdRigaEvasioneDao.findByIdDestinatarioEvasione(rigaEvasione.getDestinatarioEvasione().getId());
			for (CpassTOrdRigaEvasione riga : righeEvasione) {
				if (riga.getProgressivo() > maxProgressivo) {
					maxProgressivo = riga.getProgressivo();
				}
			}
			rigaEvasioneEntity.setProgressivo(++maxProgressivo);
		}

		RigaEvasione result = CpassMappers.RIGA_EVASIONE.toModel(cpassTOrdRigaEvasioneDao.insert(rigaEvasioneEntity));
		return result;
	}

	/**
	 * Updates the rigaEvasione
	 * 
	 * @param rigaEvasione to update
	 * @return the model instance
	 */
	public RigaEvasione updateRigaEvasione(RigaEvasione rigaEvasione) {
		CpassTOrdRigaEvasione rigaEvasioneEntity = CpassMappers.RIGA_EVASIONE.toEntity(rigaEvasione);
		RigaEvasione result = CpassMappers.RIGA_EVASIONE.toModel(cpassTOrdRigaEvasioneDao.update(rigaEvasioneEntity));
		return result;
	}

	public void deleteRigaEvasione(UUID idRigaEvasione) {
		cpassTOrdRigaEvasioneDao.delete(idRigaEvasione);
	}
	
	public void deleteLogicallyRigaEvasione(UUID idRigaEvasione) {
		cpassTOrdRigaEvasioneDao.deleteLogically(idRigaEvasione);
	}

	public BigDecimal calcolaTotale(UUID rigaOrdineId) {
		return cpassTOrdRigaEvasioneDao.calcolaTotale(rigaOrdineId);
	}

}
