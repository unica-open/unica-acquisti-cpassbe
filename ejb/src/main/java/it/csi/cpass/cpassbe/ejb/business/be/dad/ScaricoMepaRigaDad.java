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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.mepa.CpassTScaricoMepaRigaDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaRiga;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaRiga;


@ApplicationScoped
public class ScaricoMepaRigaDad extends BaseDad {
	@Inject
	private CpassTScaricoMepaRigaDao cpassTScaricoMepaRigaDao;
	/**
	 * 
	 * @param scaricoMepaRiga
	 * @return
	 */
	public ScaricoMepaRiga saveScaricoMepaRiga(ScaricoMepaRiga scaricoMepaRiga) {
		CpassTScaricoMepaRiga cpassTScaricoMepaRiga = CpassMappers.SCARICO_MEPA_RIGA.toEntity(scaricoMepaRiga);
		cpassTScaricoMepaRiga = cpassTScaricoMepaRigaDao.saveAndFlush(cpassTScaricoMepaRiga);
		scaricoMepaRiga = CpassMappers.SCARICO_MEPA_RIGA.toModel(cpassTScaricoMepaRiga);
		return scaricoMepaRiga;
	}
}
