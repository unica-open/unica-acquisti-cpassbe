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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostDestinatarioOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostDestinatarioOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;

public class PostDestinatarioOrdineService extends BaseService<PostDestinatarioOrdineRequest, PostDestinatarioOrdineResponse> {
	
	private final DestinatarioOrdineDad destinatarioDad;
	private final SettoreDad settoreDad;
	private Destinatario destinatario;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 * @param decodificaDad       the decodifica DAD
	 */
	public PostDestinatarioOrdineService(ConfigurationHelper configurationHelper, DestinatarioOrdineDad destinatarioDad, SettoreDad settoreDad) {
		super(configurationHelper);
		this.destinatarioDad = destinatarioDad;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void checkServiceParams() {
		destinatario = request.getOrdineDestinatario();
		checkNotNull(destinatario, "destinatario", true);
		checkNotNull(destinatario.getSettore(), "settore", true);
		checkNotNull(destinatario.getSettore().getCodice(), "codiceSettore", true);
	}

	@Override
	protected void execute() {
		
		// controllo che il settore passato esista e sia valido
		Settore settore = settoreDad.findByCodice(destinatario.getSettore().getCodice());
		checkBusinessCondition(settore != null, MsgCpassOrd.ORDORDE0008.getError());
		
		//controllo che se uno dei campi indirizzo è popoloato, lo siano tutti
		if(destinatario.getIndirizzo() != null || destinatario.getNumCivico() != null || destinatario.getLocalita() != null || destinatario.getCap() != null || destinatario.getProvincia() != null) {
			checkBusinessCondition(!StringUtils.isEmpty(destinatario.getIndirizzo()) && !StringUtils.isEmpty(destinatario.getNumCivico()) && !StringUtils.isEmpty(destinatario.getLocalita()) &&
					!StringUtils.isEmpty(destinatario.getCap()) && !StringUtils.isEmpty(destinatario.getProvincia()), MsgCpassOrd.ORDORDE0009.getError());
	    }
		
		//controllo che se vi sono altri destinatari legati allo stesso ordine, non vi sia già lo stesso
		List<Destinatario> destinatariOrdine = destinatarioDad.findByOrdine(destinatario.getTestataOrdine().getId());
		boolean foundEquals = false;
		for(Destinatario dest : destinatariOrdine) {
			if(dest.getSettore() != null && dest.getSettore().getCodice() != null && dest.getSettore().getCodice().equalsIgnoreCase(destinatario.getSettore().getCodice())){
				foundEquals = true;
			}
		}
		checkBusinessCondition(!foundEquals, MsgCpassOrd.ORDORDE0030.getError());
		
		destinatario = destinatarioDad.saveDestinatario(destinatario);
		response.setDestinatario(destinatario);
	}

}
