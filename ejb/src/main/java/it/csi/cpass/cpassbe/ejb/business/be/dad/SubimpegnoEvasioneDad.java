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
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.evasione.CpassTOrdSubimpegnoEvasioneDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdSubimpegnoEvasione;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;

@ApplicationScoped
public class SubimpegnoEvasioneDad extends BaseDad {

	@Inject
	private CpassTOrdSubimpegnoEvasioneDao cpassTOrdSubimpegnoEvasioneDao;
	/**
	 * 
	 * @param subimpegnoEvasione
	 * @return
	 */
	public SubimpegnoEvasione insert(SubimpegnoEvasione subimpegnoEvasione) {
		CpassTOrdSubimpegnoEvasione cpassTOrdSubimpegnoEvasione = CpassMappers.SUBIMPEGNO_EVASIONE.toEntity(subimpegnoEvasione);
		cpassTOrdSubimpegnoEvasione = cpassTOrdSubimpegnoEvasioneDao.insert(cpassTOrdSubimpegnoEvasione);
		cpassTOrdSubimpegnoEvasioneDao.flush();
		final SubimpegnoEvasione result = CpassMappers.SUBIMPEGNO_EVASIONE.toModel(cpassTOrdSubimpegnoEvasione);
		return result;
	}
	/**
	 * 
	 * @param subimpegnoEvasione
	 * @return
	 */
	public SubimpegnoEvasione update(SubimpegnoEvasione subimpegnoEvasione) {
		final CpassTOrdSubimpegnoEvasione cpassTOrdSubimpegnoEvasione = CpassMappers.SUBIMPEGNO_EVASIONE.toEntity(subimpegnoEvasione);
		final SubimpegnoEvasione result = CpassMappers.SUBIMPEGNO_EVASIONE.toModel(cpassTOrdSubimpegnoEvasioneDao.update(cpassTOrdSubimpegnoEvasione));
		return result;
	}
	/**
	 * 
	 * @param impegnoOrdineId
	 * @return
	 */
	public BigDecimal calcolaTotaleEvaso(UUID impegnoOrdineId) {
		return cpassTOrdSubimpegnoEvasioneDao.calcolaTotaleEvaso(impegnoOrdineId);
	}
	/**
	 * 
	 * @param impegnoAnno
	 * @param impegnoNumero
	 * @param subimpegnoAnno
	 * @param subimpegnoNumero
	 * @param testataEvasioneId
	 * @return
	 */
	public BigDecimal calcolaTotale(Integer impegnoAnno, Integer impegnoNumero, Integer subimpegnoAnno, Integer subimpegnoNumero, UUID testataEvasioneId) {
		return cpassTOrdSubimpegnoEvasioneDao.calcolaTotale(impegnoAnno, impegnoNumero, subimpegnoAnno, subimpegnoNumero, testataEvasioneId);
	}
	/**
	 * 
	 * @param subimpegnoOrdineId
	 * @return
	 */
	public List<SubimpegnoEvasione> findByIdSubimpegnoOrdine(UUID subimpegnoOrdineId) {
		final List<CpassTOrdSubimpegnoEvasione> cpassTOrdSubimpegnoEvasiones = cpassTOrdSubimpegnoEvasioneDao.findByIdSubimpegnoOrdine(subimpegnoOrdineId);
		final List<SubimpegnoEvasione> subimpegnoEvasiones = CpassMappers.SUBIMPEGNO_EVASIONE.toModels(cpassTOrdSubimpegnoEvasiones);
		return subimpegnoEvasiones;
	}

}
