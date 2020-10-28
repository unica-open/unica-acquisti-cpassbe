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
import it.csi.cpass.cpassbe.ejb.business.be.service.request.common.PostRicercaDocumentoSpesaRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.common.PostRicercaDocumentoSpesaResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.DocumentoSpesa;
import it.csi.cpass.cpassbe.lib.dto.error.MsgCpassOrd;
import it.csi.cpass.cpassbe.lib.external.DocumentoSpesaHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;
import it.csi.cpass.cpassbe.lib.external.res.ExternalServiceResponseWrapper;

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
	public PostRicercaDocumentoSpesaService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup) {
		super(configurationHelper);
		this.externalHelperLookup = externalHelperLookup;
	}

	@Override
	protected void execute() {
		//DocumentoSpesa filtroDocumentoSpesa = request.getDocumentoSpesa();
		DocumentoSpesa filtro = request.getDocumentoSpesa();
		if(filtro.getAnnoProtocollo()==null) {
			checkBusinessCondition( filtro.getAnnoDocumento() !=null && filtro.getNumeroDocumento()!=null && filtro.getCodiceFornitore()!=null && filtro.getTipoDocumento() != null ,MsgCpassOrd.ORDORDE0003.getError());
		}else {
			checkBusinessCondition(filtro.getNumeroProtocollo()!=null,MsgCpassOrd.ORDORDE0003.getError());
		}
		ExternalServiceResolveWrapper<DocumentoSpesaHelper> handler = externalHelperLookup.lookup(DocumentoSpesaHelper.class);
		List<DocumentoSpesa> lista = invokeExternalService(handler, () -> handler.getInstance().getDocumentoSpesa(handler.getParams(), filtro));
		response.setDocumentoSpesa(lista);
	}

	@Override
	protected <H, E> E invokeExternalService(ExternalServiceResolveWrapper<H> handler, Supplier<ExternalServiceResponseWrapper<E>> supplier) {
		ExternalServiceResponseWrapper<E> externalResponse = supplier.get();

		checkBusinessCondition(externalResponse.isSuccess(),() -> MsgCpassOrd.ORDORDE0002.getError("errori", externalResponse.getErrors().stream().collect(Collectors.joining(", "))));

		List<E> documentiSpesa = (List<E>) externalResponse.getResponse();
		checkBusinessCondition(documentiSpesa != null && documentiSpesa.size() != 0, () -> MsgCpassOrd.ORDORDE0090.getError());	

		E resp = externalResponse.getResponse();
		
		return resp;
	}

}



