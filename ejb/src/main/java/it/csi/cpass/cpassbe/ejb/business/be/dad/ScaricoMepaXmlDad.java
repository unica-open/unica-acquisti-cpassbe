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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.ord.mepa.CpassTScaricoMepaXmlDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.mepa.CpassTScaricoMepaXml;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ord.mepa.ScaricoMepaXml;

@ApplicationScoped
public class ScaricoMepaXmlDad extends BaseDad {

	@Inject
	private CpassTScaricoMepaXmlDao cpassTScaricoMepaXmlDao;
	/**
	 * 
	 * @param scaricoMepaXml
	 * @return
	 */
	public ScaricoMepaXml svaeScaricoMepaXml(ScaricoMepaXml scaricoMepaXml) {
		CpassTScaricoMepaXml cpassTScaricoMepaXml = CpassMappers.SCARICO_MEPA_XML.toEntity(scaricoMepaXml);
		cpassTScaricoMepaXml = cpassTScaricoMepaXmlDao.saveAndFlush(cpassTScaricoMepaXml);
		scaricoMepaXml = CpassMappers.SCARICO_MEPA_XML.toModel(cpassTScaricoMepaXml);
		return scaricoMepaXml;
	}

}
