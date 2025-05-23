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

import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RdaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RmsDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ScaricoMepaTestataDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.ordine.DeleteTestataOrdineRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.ordine.DeleteTestataOrdineResponse;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class DeleteTestataOrdineService extends  BaseOrdineService<DeleteTestataOrdineRequest, DeleteTestataOrdineResponse> {
	//BaseService<DeleteTestataOrdineRequest, DeleteTestataOrdineResponse> {

	private final DestinatarioOrdineDad destinatarioDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final ImpegnoDad impegnoDad;
	private final DecodificaDad decodificaDad;
	private final ScaricoMepaTestataDad scaricoMepaTestataDad;
	private final RmsDad rmsDad;
	private final RdaDad rdaDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper
	 * @param testataOrdineDad
	 * @param destinatarioDad
	 * @param rigaOrdineDad
	 * @param impegnoDad
	 */
	public DeleteTestataOrdineService(ConfigurationHelper configurationHelper, TestataOrdineDad testataOrdineDad,
			DestinatarioOrdineDad destinatarioDad, RigaOrdineDad rigaOrdineDad,
			ImpegnoDad impegnoDad, DecodificaDad decodificaDad, ScaricoMepaTestataDad scaricoMepaTestataDad,
			RmsDad rmsDad, RdaDad rdaDad,SettoreDad settoreDad) {
		super(configurationHelper, testataOrdineDad, settoreDad);
		this.destinatarioDad = destinatarioDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.impegnoDad = impegnoDad;
		this.decodificaDad = decodificaDad;
		this.scaricoMepaTestataDad = scaricoMepaTestataDad;
		this.rmsDad = rmsDad;
		this.rdaDad = rdaDad;
	}

	@Override
	protected void checkServiceParams() {
		final UUID idTestataOrdine = request.getIdTestataOrdine();
		checkNotNull(idTestataOrdine, "idTestataOrdine", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		final Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		final Ente ente = settoreCorrente.getEnte();

		final TestataOrdine testataOrdine = testataOrdineDad.getTestataOrdineModel(request.getIdTestataOrdine(),ente.getId());

		for(final Destinatario destinatario : testataOrdine.getListDestinatario()) {

			final List<RigaOrdine> righe = rigaOrdineDad.getRigheByDestinatario(destinatario.getId());

			// elimino tutte le righe collegate
			for(final RigaOrdine riga : righe) {

				//elimino gli impegni (subimpegni compresi)
				impegnoDad.deleteImpegniByRiga(riga.getId());

				//e la riga
				rigaOrdineDad.deleteRiga(riga.getId());
			}

			// elimino il destinatario
			destinatarioDad.deleteDestinatario(destinatario.getId());
		}

		testataOrdineDad.deleteAssociati(testataOrdine);

		scollegaRdaDaOrdine(testataOrdine, decodificaDad, rdaDad, rmsDad);
		//cancello il record sulla tabella del protocollo
		testataOrdineDad.deleteProtocolliOrdineByTestataOrdine(request.getIdTestataOrdine());

		testataOrdineDad.deleteTestataOrdine(request.getIdTestataOrdine());

		ripristinaScaricoMepaTestata(testataOrdine, scaricoMepaTestataDad, decodificaDad);
		// aggiorno la tabella ordini MEPA, se è il caso
		//		if (testataOrdine.getTipoOrdine().getTipologiaDocumentoCodice()
		//			.equals(ConstantsCpassOrdTipoOrdine.TipoOrdine.MEPA.getCostante()) &&
		//			 testataOrdine.getScaricoMepaTestata() != null) {
		//
		//			ScaricoMepaTestata scaricoMepaTestata = testataOrdine.getScaricoMepaTestata();
		//			scaricoMepaTestata.setStato(decodificaDad.getStato(
		//				ConstantsCPassStato.StatoEnum.DA_CARICARE.getCostante(), ConstantsCPassStato.TipoStatoEnum.ORDINE_MEPA.getCostante())
		//				.orElse(null));
		//
		//			scaricoMepaTestataDad.updateScaricoMepaTestata(scaricoMepaTestata);
		//		}

	}

}
