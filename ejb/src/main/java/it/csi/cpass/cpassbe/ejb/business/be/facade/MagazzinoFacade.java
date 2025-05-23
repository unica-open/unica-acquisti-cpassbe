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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import it.csi.cpass.cpassbe.ejb.business.be.dad.MagazzinoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.magazzino.GetMagazziniService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.magazzino.GetMagazzinoByIdService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.magazzino.GetRicercaMagazzinoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.magazzino.GetMagazziniRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.magazzino.GetMagazzinoByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.magazzino.GetRicercaMagazzinoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.magazzino.GetMagazziniResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.magazzino.GetMagazzinoByIdResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.magazzino.GetRicercaMagazzinoResponse;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;

/**
 * Facade for the /magazzino path
 */
@Stateless
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MagazzinoFacade extends BaseFacade{

	@Inject private MagazzinoDad magazzinoDad;

	public GetMagazzinoByIdResponse getMagazzinoById(Integer id) {
		return executeService(new GetMagazzinoByIdRequest(id),new GetMagazzinoByIdService(configurationHelper, magazzinoDad));
	}


	public GetRicercaMagazzinoResponse getRicercaMagazzino(@Min(0) Integer offset, @Min(1) @Max(100) Integer limit, String sort,String direction, Magazzino magazzino) {
		return executeService(new GetRicercaMagazzinoRequest(offset, limit, sort, direction,magazzino),new GetRicercaMagazzinoService(configurationHelper, magazzinoDad));
	}

	public GetMagazziniResponse getMagazzini() {
		return executeService(new GetMagazziniRequest(),new GetMagazziniService(configurationHelper, magazzinoDad));
	}

}
