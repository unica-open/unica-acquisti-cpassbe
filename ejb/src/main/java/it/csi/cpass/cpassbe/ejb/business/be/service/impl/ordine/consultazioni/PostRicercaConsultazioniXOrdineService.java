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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.consultazioni;

import java.util.List;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ConsultazioniDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.consultazioni.PostRicercaConsultazioniXOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.consultazioni.PostRicercaConsultazioniXOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.ConsultazioniOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;

public class PostRicercaConsultazioniXOrdineService extends BaseService<PostRicercaConsultazioniXOrdineRequest, PostRicercaConsultazioniXOrdineResponse> {

	ConsultazioniDad consultazioniDad;
	RicercaXConsultazioni filtro;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param rigaOrdineDad    the testataOrdine DAD
	 */
	public PostRicercaConsultazioniXOrdineService(ConfigurationHelper configurationHelper,ConsultazioniDad consultazioniDad) {
		super(configurationHelper);
		this.consultazioniDad = consultazioniDad;
	}

	@Override
	protected void checkServiceParams() {
		/*if(filtro.getTipoConsultazione().equals("PROVVEDIMENTO_SPESA")) {
			checkModel(filtro.getProvvedimento(), "provvedimento obbligatorio");
			checkNotNull(filtro.getProvvedimento().getAnno()  , "Provvedimento Anno");
			checkNotNull(filtro.getProvvedimento().getNumero(), "Provvedimento Numero");
			checkModel(filtro.getProvvedimento().getProvvedimentoTipo(), "Provvedimento Tipo");
		}else {
			checkNotNull(filtro.getTipoProcedura(), "tipo procedura");
			checkNotNull(filtro.getNumeroProcedura(), "numero procedura");
		}*/

	}

	@Override
	protected void execute() {
		filtro = request.getRicercaXConsultazioni();
		final List<ConsultazioniOrdine> listaconsultazioniOrdine =	consultazioniDad.valorizzaConsultazioniXOrdine(filtro);
		//List<ConsultazioniOrdine> listPaginata = UtilityCommon.estraiLaPaginaScelta(list,request.getPage(),request.getSize());
		//int totalPages = UtilityCommon.getTotalPage(list.size(), request.getSize());
		//PagedListImpl<ConsultazioniOrdine> pagedListOrdini = new PagedListImpl<>(listPaginata ,request.getPage(),totalPages,list.size());
		//response.setConsultazioniOrdine(pagedListOrdini);
		response.setListaconsultazioniOrdine(listaconsultazioniOrdine);
	}

}
