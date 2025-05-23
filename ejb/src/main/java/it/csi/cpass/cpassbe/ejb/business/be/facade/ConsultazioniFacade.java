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
package it.csi.cpass.cpassbe.ejb.business.be.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.cpass.cpassbe.ejb.business.be.dad.ConsultazioniDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ConsultazioniRiepilogoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.FornitoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.PostRicercaImpegnoDaSiacService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.consultazioni.PostRicercaConsultazioniXImpegnoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.consultazioni.PostRicercaConsultazioniXOrdineService;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.ordine.consultazioni.PostRicercaConsultazioniXRiepilogoService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.PostRicercaImpegnoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.consultazioni.PostRicercaConsultazioniXImpegnoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.consultazioni.PostRicercaConsultazioniXOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.consultazioni.PostRicercaConsultazioniXRiepilogoRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.PostRicercaImpegnoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.consultazioni.PostRicercaConsultazioniXImpegnoResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.consultazioni.PostRicercaConsultazioniXOrdineResponse;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.consultazioni.PostRicercaConsultazioniXRiepilogoResponse;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.ord.FiltroImpegni;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.consultazioni.RicercaXConsultazioni;


/**
 * Fa&ccedil;ade for the /decodifica path
 */
@Stateless
public class ConsultazioniFacade extends BaseFacade {
	// Injection point
	@Inject private ConsultazioniRiepilogoDad consultazioniRiepilogoDad;
	@Inject private ConsultazioniDad consultazioniDad;
	@Inject private ImpegnoDad impegnoDad;
	@Inject private SettoreDad settoreDad;
	@Inject private FornitoreDad     fornitoreDad;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	//public PostRicercaConsultazioniXImpegnoResponse postRicercaConsultazioniXImpegno(Integer page, Integer limit,RicercaXConsultazioni ricercaXConsultazioni) {
	public PostRicercaConsultazioniXImpegnoResponse postRicercaConsultazioniXImpegno(RicercaXConsultazioni ricercaXConsultazioni) {
		if(ricercaXConsultazioni.getTipoConsultazione().equals("PROVVEDIMENTO_SPESA")) {
			final PostRicercaImpegnoResponse res1 = estraiImpegni(ricercaXConsultazioni, ricercaXConsultazioni.getAnnoEsercizio());
			final PostRicercaImpegnoResponse res2 = estraiImpegni(ricercaXConsultazioni, ricercaXConsultazioni.getAnnoEsercizio()+1);
			return executeService(
					//new PostRicercaConsultazioniXImpegnoRequest( ricercaXConsultazioni,res1.getImpegni().getList(),res2.getImpegni().getList(),page, limit),
					new PostRicercaConsultazioniXImpegnoRequest( ricercaXConsultazioni,res1.getImpegni().getList(),res2.getImpegni().getList()),
					new PostRicercaConsultazioniXImpegnoService(configurationHelper, externalHelperLookup, consultazioniDad, fornitoreDad)
					);
		}else {
			log.error("postRicercaConsultazioniXImpegno", "chiamata non prevista tipo consultazione --> "+ricercaXConsultazioni.getTipoConsultazione());
			return null;
		}
	}



	/**
	 * @param ricercaXConsultazioni
	 * @return
	 */
	private PostRicercaImpegnoResponse estraiImpegni(RicercaXConsultazioni ricercaXConsultazioni,Integer annoEsercizio) {
		final FiltroImpegni filtroImpegni = new FiltroImpegni();
		filtroImpegni.setAnnoEsercizio(annoEsercizio);


		final Subimpegno subimpegno = new Subimpegno();
		final Impegno impegno = new Impegno();
		impegno.setAnno(null);
		impegno.setNumero(null);
		impegno.setNumeroProvvedimento(ricercaXConsultazioni.getProvvedimento().getNumero());
		impegno.setAnnoProvvedimento(ricercaXConsultazioni.getProvvedimento().getAnno());
		final Fornitore fornitore = new Fornitore();
		impegno.setFornitore(fornitore);

		final TestataOrdine testataOrdine = new TestataOrdine();
		testataOrdine.setProvvedimento(ricercaXConsultazioni.getProvvedimento());
		filtroImpegni.setTestataOrdine(testataOrdine);
		subimpegno.setImpegno(impegno );
		filtroImpegni.setSubimpegno(subimpegno );

		//Fornitore fornitore = new Fornitore();
		//filtroImpegni.setFornitore(fornitore);
		//String codiceFornitoreFiltro = impegnoFiltro.getFornitore().getCodice();
		final PostRicercaImpegnoResponse res = executeService(new PostRicercaImpegnoRequest(1, 1000, null, null, filtroImpegni ),new PostRicercaImpegnoDaSiacService(configurationHelper, externalHelperLookup, impegnoDad, settoreDad));
		return res;
	}

	public PostRicercaConsultazioniXOrdineResponse postRicercaConsultazioniXOrdine(RicercaXConsultazioni ricercaXConsultazioni) {
		//public PostRicercaConsultazioniXOrdineResponse postRicercaConsultazioniXOrdine(Integer page, Integer limit,RicercaXConsultazioni ricercaXConsultazioni) {
		return executeService(
				new PostRicercaConsultazioniXOrdineRequest( ricercaXConsultazioni),
				//new PostRicercaConsultazioniXOrdineRequest( page,  limit,ricercaXConsultazioni),
				new PostRicercaConsultazioniXOrdineService(configurationHelper, consultazioniDad)
				);
	}

	public PostRicercaConsultazioniXRiepilogoResponse postRicercaConsultazioniXRiepilogo(RicercaXConsultazioni ricercaXConsultazioni) {
		final PostRicercaImpegnoResponse res1 = estraiImpegni(ricercaXConsultazioni, ricercaXConsultazioni.getAnnoEsercizio());
		final PostRicercaImpegnoResponse res2 = estraiImpegni(ricercaXConsultazioni, ricercaXConsultazioni.getAnnoEsercizio()+1);

		return executeService( new PostRicercaConsultazioniXRiepilogoRequest(ricercaXConsultazioni,res1.getImpegni().getList(),res2.getImpegni().getList()), new PostRicercaConsultazioniXRiepilogoService(configurationHelper, consultazioniRiepilogoDad));
	}

}
