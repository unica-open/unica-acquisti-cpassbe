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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine;

import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaDdtEvasioneRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PostRicercaDdtEvasioneResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.custom.RicercaDdt;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;
import it.csi.cpass.cpassbe.lib.util.pagination.PagedList;


/**
 * Retrieves an Provvedimentos
 */
public class PostRicercaDdtEvasioneService extends BaseService<PostRicercaDdtEvasioneRequest, PostRicercaDdtEvasioneResponse> {
	private final DocumentoTrasportoDad documentoTrasportoDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private RicercaDdt ricercaDdt;

	@Override
	protected void checkServiceParams() {
		ricercaDdt = request.getRicercaDdt();
		checkNotNull(ricercaDdt, "ricercaDdt", Boolean.TRUE);
		checkNotNull(ricercaDdt.getIdSettore(), "idSettore", Boolean.TRUE);
	}

	/**
	 * Constructor
	 *
	 * @param configurationHelper  the configuration helper
	 * @param externalHelperLookup the external helper lookup
	 */
	public PostRicercaDdtEvasioneService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup,
			DocumentoTrasportoDad documentoTrasportoDad, RigaEvasioneDad rigaEvasioneDad) {
		super(configurationHelper);
		this.documentoTrasportoDad = documentoTrasportoDad;
		this.rigaEvasioneDad = rigaEvasioneDad;
	}

	@Override
	protected void execute() {
		final UUID settoreId = ricercaDdt.getIdSettore();
		final PagedList<DocumentoTrasporto> res = documentoTrasportoDad.ricercaDocumentoTrasporto(settoreId, ricercaDdt, request.getPage(), request.getSize(), request.getSort());

		for(final DocumentoTrasporto dt : res.getList()) {
			for(final DocumentoTrasportoRiga dtr : dt.getDocumentoTrasportoRigaList()) {
				if(dtr.getTestataOrdine() != null) {
					final SettoreInterventi tipoAcquisto = dtr.getTestataOrdine().getTipoAcquisto();
					if(tipoAcquisto != null && tipoAcquisto.getCodice() != null && tipoAcquisto.getCodice().equals("F")) {
						if(dtr.getRigaOrdine() != null) {
							final BigDecimal quantitaRigaOrdine = dtr.getRigaOrdine().getQuantita();
							if(quantitaRigaOrdine != null) {
								dtr.setQtaEvadibile(quantitaRigaOrdine.subtract(rigaEvasioneDad.calcolaQuantitaEvasa(dtr.getRigaOrdine().getId())));
							}
						}
					}
				}
			}
		}
		response.setDocumentoTrasportoPagedList(res);
	}
}
