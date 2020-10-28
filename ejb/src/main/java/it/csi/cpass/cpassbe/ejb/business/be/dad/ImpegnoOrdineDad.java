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

	public ImpegnoOrdine update(ImpegnoOrdine impegnoOrdine) {
		CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine = CpassMappers.IMPEGNO_ORDINE.toEntity(impegnoOrdine);
		ImpegnoOrdine result = CpassMappers.IMPEGNO_ORDINE.toModel(cpassTOrdImpegnoOrdineDao.update(cpassTOrdImpegnoOrdine));
		return result;
	}
	
	public SubimpegnoOrdine update(SubimpegnoOrdine subimpegnoOrdine) {
		CpassTOrdSubimpegnoOrdine cpassTOrdSubimpegnoOrdine = CpassMappers.SUBIMPEGNO_ORDINE.toEntity(subimpegnoOrdine);
		SubimpegnoOrdine result = CpassMappers.SUBIMPEGNO_ORDINE.toModel(cpassTOrdSubimpegnoOrdineDao.update(cpassTOrdSubimpegnoOrdine));
		return result;
	}

}
