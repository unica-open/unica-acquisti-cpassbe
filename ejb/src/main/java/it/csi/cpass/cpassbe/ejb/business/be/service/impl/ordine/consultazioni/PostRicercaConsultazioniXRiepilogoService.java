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

import it.csi.cpass.cpassbe.ejb.business.be.dad.ConsultazioniRiepilogoDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.consultazioni.PostRicercaConsultazioniXRiepilogoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.consultazioni.PostRicercaConsultazioniXRiepilogoResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;

public class PostRicercaConsultazioniXRiepilogoService extends BaseService<PostRicercaConsultazioniXRiepilogoRequest, PostRicercaConsultazioniXRiepilogoResponse> {

	ConsultazioniRiepilogoDad consultazioniRiepilogoDad;
	RicercaXConsultazioni filtro;
	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 * @param rigaOrdineDad    the testataOrdine DAD
	 */
	public PostRicercaConsultazioniXRiepilogoService(ConfigurationHelper configurationHelper,ConsultazioniRiepilogoDad consultazioniRiepilogoDad) {
		super(configurationHelper);
		this.consultazioniRiepilogoDad = consultazioniRiepilogoDad;
	}

	@Override
	protected void checkServiceParams() {
		//		if(filtro.getTipoConsultazione().equals("PROVVEDIMENTO_SPESA")) {
		//			checkModel(filtro.getProvvedimento(), "provvedimento obbligatorio");
		//			checkNotNull(filtro.getProvvedimento().getAnno()  , "Provvedimento Anno");
		//			checkNotNull(filtro.getProvvedimento().getNumero(), "Provvedimento Numero");
		//			checkModel(filtro.getProvvedimento().getProvvedimentoTipo(), "Provvedimento Tipo");
		//		}else {
		//			checkNotNull(filtro.getTipoProcedura(), "tipo procedura");
		//			checkNotNull(filtro.getNumeroProcedura(), "numero procedura");
		//		}
	}

	@Override
	protected void execute() {
		filtro = request.getRicercaXConsultazioni();
		final List<Impegno> listaImpegniEsercizio         = request.getListImpegnoEsercizio();
		final List<Impegno> listaImpegniEsercizioProssimo = request.getListImpegnoEsercizioProssimo();
		response.setConsultazioniRiepilogo(consultazioniRiepilogoDad.valorizzaConsultazioniXRiepilogo(filtro,listaImpegniEsercizio,listaImpegniEsercizioProssimo));
	}

}
