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

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dao.cpass.CpassDElaborazioneTipoDao;
import it.csi.cpass.cpassbe.ejb.mapper.CpassMappers;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;

/**
 * Data Access for ElaborazioneTipoDao
 */
@ApplicationScoped
public class ElaborazioneTipoDad extends BaseDad {

	@Inject private CpassDElaborazioneTipoDao cpassDElaborazioneTipoDao;
	
	/**
	 * 
	 * @param elaborazioneTipoCodice
	 * @return
	 */
	public Optional<ElaborazioneTipo> findByElaborazioneTipoCodice(String elaborazioneTipoCodice) {
		return cpassDElaborazioneTipoDao.findByElaborazioneTipoCodice(elaborazioneTipoCodice).map(CpassMappers.ELABORAZIONE_TIPO::toModel);
	}

}
