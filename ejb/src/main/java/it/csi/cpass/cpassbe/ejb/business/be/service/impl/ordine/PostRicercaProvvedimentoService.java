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

import java.util.ArrayList;
import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaProvvedimentoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaProvvedimentoResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.dto.ord.Provvedimento;
import it.csi.cpass.cpassbe.lib.external.ProvvedimentoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Retrieves an Provvedimentos
 */
public class PostRicercaProvvedimentoService extends BaseService<PostRicercaProvvedimentoRequest, PostRicercaProvvedimentoResponse> {

	private final ExternalHelperLookup externalHelperLookup;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper  the configuration helper
	 * @param externalHelperLookup the external helper lookup
	 */
	public PostRicercaProvvedimentoService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
	}

	@Override
	protected void execute() {
		//TODO da scommentare per chiamare stilo
		//ExternalServiceResolveWrapper<ProvvedimentoHelper> handler = externalHelperLookup.lookup(ProvvedimentoHelper.class);
		//List<Provvedimento> provvedimenti = invokeExternalService(handler, () -> handler.getInstance().getProvvedimenti(handler.getParams(), request.getProvvedimento()));
	    
		List<Provvedimento> provvedimenti = new ArrayList<Provvedimento>();
		Provvedimento provvedimento = new Provvedimento();
		provvedimento.setAnno(request.getProvvedimento().getAnno());
		provvedimento.setNumero(request.getProvvedimento().getNumero());
		provvedimento.setDescrizione("desc " + request.getProvvedimento().getAnno());
		provvedimento.setSettore("sett " + request.getProvvedimento().getNumero());
		provvedimenti.add(provvedimento);
		response.setProvvedimenti(new PagedListImpl<>(provvedimenti));
	}
}
