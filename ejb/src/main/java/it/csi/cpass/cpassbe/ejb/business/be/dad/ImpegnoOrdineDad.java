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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdImpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.CpassTOrdSubimpegnoOrdineDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSubimpegnoOrdine;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubimpegnoOrdine;

@ApplicationScoped
public class ImpegnoOrdineDad extends BaseDad {

	@Inject
	private CpassTOrdImpegnoOrdineDao cpassTOrdImpegnoOrdineDao;
	@Inject
	private CpassTOrdSubimpegnoOrdineDao cpassTOrdSubimpegnoOrdineDao;
	/**
	 * 
	 * @param rigaOrdineId
	 * @return
	 */
	public List<ImpegnoOrdine> getImpegniByRigaOrdineId(UUID rigaOrdineId) {
		final List<CpassTOrdImpegnoOrdine> cpassTOrdImpegnoOrdineList = cpassTOrdImpegnoOrdineDao.getImpegni(rigaOrdineId);
		return CpassMappers.IMPEGNO_ORDINE.toModels(cpassTOrdImpegnoOrdineList);
	}
	/**
	 * 
	 * @param rigaOrdineId
	 * @return
	 */
	public List<SubimpegnoOrdine> getSubImpegniByRigaOrdineId(UUID rigaOrdineId) {
		final List<CpassTOrdSubimpegnoOrdine> cpassTOrdSubImpegnoOrdineList = cpassTOrdSubimpegnoOrdineDao.getSubimpegni(rigaOrdineId);
		return CpassMappers.SUBIMPEGNO_ORDINE.toModels(cpassTOrdSubImpegnoOrdineList);
	}
	/**
	 * 
	 * @param impegnoOrdine
	 * @return
	 */
	public ImpegnoOrdine update(ImpegnoOrdine impegnoOrdine) {
		final CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine = CpassMappers.IMPEGNO_ORDINE.toEntity(impegnoOrdine);
		final ImpegnoOrdine result = CpassMappers.IMPEGNO_ORDINE.toModel(cpassTOrdImpegnoOrdineDao.update(cpassTOrdImpegnoOrdine));
		return result;
	}
	/**
	 * 
	 * @param subimpegnoOrdine
	 * @return
	 */
	public SubimpegnoOrdine update(SubimpegnoOrdine subimpegnoOrdine) {
		final CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine = CpassMappers.SUBIMPEGNO_ORDINE.toEntity(subimpegnoOrdine);
		final SubimpegnoOrdine result = CpassMappers.SUBIMPEGNO_ORDINE.toModel(cpassTOrdSubimpegnoOrdineDao.update(cpassTOrdSubimpegnoOrdine));
		return result;
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void updateSubImpFromTestataordine(UUID testataOrdineId) {
		cpassTOrdSubimpegnoOrdineDao.updateFromTestataordine(testataOrdineId);
	}
	/**
	 * 
	 * @param testataOrdineId
	 */
	public void updateImpFromTestataordine(UUID testataOrdineId) {
		cpassTOrdImpegnoOrdineDao.updateFromTestataordine(testataOrdineId);
	}

}
