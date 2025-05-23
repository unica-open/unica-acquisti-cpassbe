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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.ods;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.common.BaseCommonService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.ods.PostOggettiSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.ods.PostOggettiSpesaResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassBo;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PostOggettiSpesaService extends BaseCommonService<PostOggettiSpesaRequest, PostOggettiSpesaResponse> {

	private Ods ods;
	private final DecodificaDad decodificaDad;
	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 */
	public PostOggettiSpesaService(ConfigurationHelper configurationHelper,  DecodificaDad decodificaDad, CommonDad commonDad ) {
		super(configurationHelper, commonDad);
		this.decodificaDad =  decodificaDad;
	}

	@Override
	protected void checkServiceParams() {
		ods = request.getOds();
		checkModel(ods.getCpv(), "cpv");
		checkModel(ods.getAliquoteIva(), "aliquote iva");
		checkModel(ods.getUnitaMisura(), "unita' di misura");
		checkNotNull( ods.getCodice(),"codice");
		checkNotNull( ods.getDescrizione(),"descrizione");
		checkNotNull( ods.getPrezzoUnitario(),"prezzo unitario");
	}

	@Override
	protected void execute() {
		final Ente ente = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte();
		//Occorre verificare che il codice ods indicato nel campo di mappa 3 Codice non sia già presente in archivio,
		//altrimenti occorre esporre al front-end il messaggio BAC-ODS-E0005
		final Ods esistente = decodificaDad.findODSByCodice(ods.getCodice().trim(),ente.getId());
		checkBusinessCondition(esistente == null, MsgCpassBo.BACODSE0005.getError());
		this.ods.setEnte(ente);
		final Ods odsNew = commonDad.postOggettoSpesa(this.ods,ente);
		response.setOds(odsNew);
	}
}
