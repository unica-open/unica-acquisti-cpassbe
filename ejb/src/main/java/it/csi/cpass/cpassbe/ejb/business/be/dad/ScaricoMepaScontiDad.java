/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.business.be.dad;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.mepa.CpassTScaricoMepaScontiDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaSconti;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaSconti;

@ApplicationScoped
public class ScaricoMepaScontiDad extends BaseDad {

	@Inject
	private CpassTScaricoMepaScontiDao cpassTScaricoMepaScontiDao;
	/**
	 * 
	 * @param scaricoMepaSconti
	 * @return
	 */
	public ScaricoMepaSconti saveScaricoMepaSconti(ScaricoMepaSconti scaricoMepaSconti) {
		CpassTScaricoMepaSconti cpassTScaricoMepaSconti =  CpassMappers.SCARICO_MEPA_SCONTI.toEntity(scaricoMepaSconti);
		cpassTScaricoMepaSconti = cpassTScaricoMepaScontiDao.saveAndFlush(cpassTScaricoMepaSconti);
		scaricoMepaSconti = CpassMappers.SCARICO_MEPA_SCONTI.toModel(cpassTScaricoMepaSconti);
		return scaricoMepaSconti;
	}

}
