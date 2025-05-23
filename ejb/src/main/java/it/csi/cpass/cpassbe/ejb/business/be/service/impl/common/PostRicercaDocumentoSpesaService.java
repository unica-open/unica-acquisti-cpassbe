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
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.external.DocumentoSpesaHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

/**
 * Retrieves an documentispesas
 */
public class PostRicercaDocumentoSpesaService extends BaseService<PostRicercaDocumentoSpesaRequest, PostRicercaDocumentoSpesaResponse> {

	private final ExternalHelperLookup externalHelperLookup;
	/**
	 * Constructor
	 *
	 * @param configurationHelper  the configuration helper
	 * @param externalHelperLookup the external helper lookup
	 */
	public PostRicercaDocumentoSpesaService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup, SettoreDad settoreDad) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
	}

	@Override
	protected void execute() {
		final UUID enteId = CpassThreadLocalContainer.SETTORE_UTENTE.get().getEnte().getId();
		//Settore settoreEmittente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final DocumentoSpesa filtro = request.getDocumentoSpesa();
		//if( !settoreDad.getAooByIdSettore(settoreEmittente.getId() , enteId).isPresent()) {
		//TODO errore
		//checkBusinessCondition(!settoreDad.getAooByIdSettore(settoreEmittente.getId() , enteId).isPresent(), () -> CoreError.GENERIC_ERROR.getError("error", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));
		//checkBusinessCondition(!settoreDad.getAooByIdSettore(settoreEmittente.getId() , enteId).isPresent(),CoreError.GENERIC_ERROR.getError("error","Aoo di riferimento non presente in tabella settore --> " + settoreEmittente.getCodice()));

		//}

		//	String registroRepertorio = settoreDad.getAooByIdSettore(settoreEmittente.getId() , enteId).get().getAooCodice();
		//filtro.setRegistroRepertorio(registroRepertorio);
		if(filtro.getAnnoProtocollo()==null) {
			checkBusinessCondition( filtro.getAnnoDocumento() !=null && filtro.getNumeroDocumento()!=null && filtro.getCodiceFornitore()!=null && filtro.getTipoDocumento() != null ,MsgCpassOrd.ORDORDE0003.getError());
		}else {
			checkBusinessCondition(filtro.getNumeroProtocollo()!=null,MsgCpassOrd.ORDORDE0003.getError());
		}
		final ExternalServiceResolveWrapper<DocumentoSpesaHelper> handler = externalHelperLookup.lookup(DocumentoSpesaHelper.class,enteId);
		final List<DocumentoSpesa> lista = invokeExternalService(handler, () -> handler.getInstance().getDocumentoSpesa(handler.getParams(), filtro));
		response.setDocumentoSpesa(lista);
	}

	@Override
	protected <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier) {
		final ExternalServiceResponseWrapper<E> externalResponse = supplier.get();
		checkBusinessCondition(externalResponse.isSuccess(),() -> MsgCpassOrd.ORDORDE0002.getError("errori", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));
		final List<E> documentiSpesa = (List<E>) externalResponse.getResponse();
		checkBusinessCondition(documentiSpesa != null && documentiSpesa.size() != 0, () -> MsgCpassOrd.ORDORDE0090.getError());
		final E resp = externalResponse.getResponse();
		return resp;
	}

}



