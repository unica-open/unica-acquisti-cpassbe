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

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassTFornitoreDao;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;

@ApplicationScoped
public class FornitoreDad extends BaseDad {

	@Inject
	private CpassTFornitoreDao cpassTFornitoreDao;

	public Fornitore getFornitoreByCodice(String codice) {
		CpassTFornitore cpassTFornitore = cpassTFornitoreDao.getFornitoreByCodice(codice);
		Fornitore fornitore = CpassMappers.FORNITORE.toModel(cpassTFornitore);
		return fornitore;
	}

	public Fornitore insert(Fornitore fornitore) {
		CpassTFornitore cpassTFornitore = CpassMappers.FORNITORE.toEntity(fornitore);
		cpassTFornitore = cpassTFornitoreDao.insert(cpassTFornitore);
		return CpassMappers.FORNITORE.toModel(cpassTFornitore);
	}

}
