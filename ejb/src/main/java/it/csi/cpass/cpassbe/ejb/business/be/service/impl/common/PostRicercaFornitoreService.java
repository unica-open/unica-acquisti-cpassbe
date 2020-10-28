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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaFornitoreRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaFornitoreResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedListImpl;

/**
 * Retrieves an Fornitores
 */
public class PostRicercaFornitoreService extends BaseService<PostRicercaFornitoreRequest, PostRicercaFornitoreResponse> {

	private final ExternalHelperLookup externalHelperLookup;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper  the configuration helper
	 * @param externalHelperLookup the external helper lookup
	 */
	public PostRicercaFornitoreService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
	}

	@Override
	protected void execute() {
		FiltroFornitore filtroFornitore = new FiltroFornitore();
		filtroFornitore.setFornitore(request.getFornitore());
		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);

		ExternalServiceResolveWrapper<FornitoreHelper> handler = externalHelperLookup.lookup(FornitoreHelper.class);
		List<Fornitore> fornitori = invokeExternalService(handler, () -> handler.getInstance().getFornitori(handler.getParams(), filtroFornitore));

		for (Fornitore fornitore : fornitori) {
			String indirizzoCompleto = "";
			if (fornitore.getSedime() != null) {
				indirizzoCompleto += fornitore.getSedime() + " ";
			}
			if (fornitore.getIndirizzo() != null) {
				indirizzoCompleto += fornitore.getIndirizzo() + " ";
			}
			if (fornitore.getNumeroCivico() != null) {
				indirizzoCompleto += fornitore.getNumeroCivico();
			}
			fornitore.setIndirizzo(indirizzoCompleto.trim());
		}

		response.setFornitori(new PagedListImpl<>(fornitori));
	}

	@Override
	protected <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier) {
		ExternalServiceResponseWrapper<E> externalResponse = supplier.get();

		checkBusinessCondition(externalResponse.isSuccess(),
				() -> MsgCpassOrd.ORDORDE0002.getError("errori", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));

		List<E> fornitori = (List<E>) externalResponse.getResponse();
		if (fornitori.size() == 0) {
//			final String errori;
//			if (externalResponse.getMessages() != null && externalResponse.getMessages().size() > 0) {
//				errori = externalResponse.getMessages().stream().collect(Collectors.joining(", "));
//			}
			checkBusinessCondition(false, () -> MsgCpassOrd.ORDORDE0074.getError());
		}

		return externalResponse.getResponse();
	}

}
