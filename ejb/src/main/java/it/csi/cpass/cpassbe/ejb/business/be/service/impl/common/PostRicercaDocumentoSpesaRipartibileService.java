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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.common;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaDocumentoSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaDocumentoSpesaResponse;
import it.csi.cpass.cpassbe.ejb.exception.NotFoundException;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.error.CoreError;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.external.DocumentoSpesaHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an documentispesas
 */
public class PostRicercaDocumentoSpesaRipartibileService extends BaseService<PostRicercaDocumentoSpesaRequest, PostRicercaDocumentoSpesaResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	private final SettoreDad settoreDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper  the configuration helper
	 * @param externalHelperLookup the external helper lookup
	 */
	public PostRicercaDocumentoSpesaRipartibileService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
		this.settoreDad = settoreDad;
	}

	@Override
	protected void execute() {
		final DocumentoSpesa filtro = request.getDocumentoSpesa();
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		final Settore settoreEmittente = CpassThreadLocalContainer.SETTORE_UTENTE.get();

		checkBusinessCondition(
				(filtro.getAnnoProtocollo()!=null && filtro.getNumeroProtocollo()!=null) ||
				(filtro.getAnnoDocumento() !=null && filtro.getNumeroDocumento()!=null && filtro.getCodiceFornitore()!=null && filtro.getTipoDocumento() != null) ,
				MsgCpassOrd.ORDORDE0003.getError()
				);

		checkBusinessCondition(settoreDad.getAooByIdSettore(settoreEmittente.getId() , enteId).isPresent(),CoreError.GENERIC_ERROR.getError("error","Aoo di riferimento non presente in tabella settore --> " + settoreEmittente.getCodice()));
		final String registroRepertorio = settoreDad.getAooByIdSettore(settoreEmittente.getId() , enteId).orElseThrow(() -> new NotFoundException("registro repertorio")).getAooCodice();
		filtro.setRegistroRepertorio(registroRepertorio);

		final ExternalServiceResolveWrapper<DocumentoSpesaHelper> handler = externalHelperLookup.lookup(DocumentoSpesaHelper.class,enteId);
		final List<DocumentoSpesa> lista = invokeExternalService(handler, () -> handler.getInstance().getDocumentoSpesaRipartibile(handler.getParams(), filtro));
		response.setDocumentoSpesa(lista);
	}

	@Override
	protected <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier) {
		final ExternalServiceResponseWrapper<E> externalResponse = supplier.get();
		checkBusinessCondition(externalResponse.isSuccess(),() -> MsgCpassOrd.ORDORDE0002.getError("errori", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));
		externalResponse.getResponse();
		// checkBusinessCondition(documentiSpesa != null && documentiSpesa.size() != 0, () -> MsgCpassOrd.ORDORDE0090.getError());
		final E resp = externalResponse.getResponse();
		return resp;
	}
}
