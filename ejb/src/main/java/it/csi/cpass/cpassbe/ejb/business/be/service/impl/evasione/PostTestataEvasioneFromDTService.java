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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import it.csi.cpass.cpassbe.ejb.business.be.dad.CommonDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DocumentoTrasportoRigaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaOrdineDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SettoreDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.impl.base.BaseService;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PostTestataEvasioneFromDTRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PostTestataEvasioneFromDTResponse;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityEvasione;
import it.csi.cpass.cpassbe.ejb.business.be.utility.UtilityImpegni;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato.StatoDocumentoTrasportoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Subimpegno;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.Destinatario;
import it.csi.cpass.cpassbe.lib.dto.ord.ImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.SubimpegnoOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasporto;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DocumentoTrasportoRiga;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.util.threadlocal.CpassThreadLocalContainer;

public class PostTestataEvasioneFromDTService
extends BaseService<PostTestataEvasioneFromDTRequest, PostTestataEvasioneFromDTResponse> {

	private DocumentoTrasporto documentoTrasporto;
	private final DecodificaDad decodificaDad;
	private final SettoreDad settoreDad;
	private final TestataEvasioneDad testataEvasioneDad;
	private final DocumentoTrasportoDad documentoTrasportoDad;
	private final DocumentoTrasportoRigaDad documentoTrasportoRigaDad;
	private final DestinatarioEvasioneDad destinatarioEvasioneDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoDad impegnoDad;
	private final ImpegnoOrdineDad impegnoOrdineDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final SubimpegnoEvasioneDad subimpegnoEvasioneDad;
	//private TestataOrdineDad testataOrdineDad;
	private final RigaOrdineDad rigaOrdineDad;
	private final DestinatarioOrdineDad destinatarioOrdineDad;
	private final CommonDad commonDad;

	/**
	 * Constructor
	 *
	 * @param configurationHelper the configuration helper
	 */
	public PostTestataEvasioneFromDTService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad, SettoreDad settoreDad,
			TestataEvasioneDad testataEvasioneDad, DocumentoTrasportoDad documentoTrasportoDad,
			DocumentoTrasportoRigaDad documentoTrasportoRigaDad, DestinatarioEvasioneDad destinatarioEvasioneDad,
			RigaEvasioneDad rigaEvasioneDad, ImpegnoDad impegnoDad, ImpegnoOrdineDad impegnoOrdineDad,
			ImpegnoEvasioneDad impegnoEvasioneDad, SubimpegnoEvasioneDad subimpegnoEvasioneDad,
			/*TestataOrdineDad testataOrdineDad,*/ RigaOrdineDad rigaOrdineDad, DestinatarioOrdineDad destinatarioOrdineDad,CommonDad commonDad) {
		super(configurationHelper);
		this.decodificaDad = decodificaDad;
		this.settoreDad = settoreDad;
		this.testataEvasioneDad = testataEvasioneDad;
		this.documentoTrasportoDad = documentoTrasportoDad;
		this.documentoTrasportoRigaDad = documentoTrasportoRigaDad;
		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoDad = impegnoDad;
		this.impegnoOrdineDad = impegnoOrdineDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.subimpegnoEvasioneDad = subimpegnoEvasioneDad;
		//this.testataOrdineDad = testataOrdineDad;
		this.rigaOrdineDad = rigaOrdineDad;
		this.destinatarioOrdineDad = destinatarioOrdineDad;
		this.commonDad = commonDad;
	}

	@Override
	protected void checkServiceParams() {
		documentoTrasporto = request.getDocumentoTrasporto();
		checkNotNull(documentoTrasporto, "documentoTrasporto", Boolean.TRUE);
		checkNotNull(request.getIdSettore(), "idSettore", Boolean.TRUE);
	}

	@Override
	protected void execute() {
		documentoTrasporto = request.getDocumentoTrasporto();
		final Utente utenteConnesso = CpassThreadLocalContainer.UTENTE_CONNESSO.get();
		final Settore settore = settoreDad.findById(request.getIdSettore());

		final List<DocumentoTrasportoRiga> documentoTrasportoRigaList = documentoTrasporto.getDocumentoTrasportoRigaList();

		BigDecimal totaleEvasione = BigDecimal.ZERO;
		TestataEvasione testataEvasione = testataEvasioneDad.saveTestataEvasione(
				UtilityEvasione.creaTestataEvasioneDaDocumentoTrasporto(
						decodificaDad, documentoTrasporto, settore, utenteConnesso
						));
		// valorizzo testataEvasioneId del documento di trasporto con il valore appena generato
		documentoTrasporto.setTestataEvasione(testataEvasione);
		documentoTrasportoDad.updateDocumentoTrasporto(documentoTrasporto);

		// creo una mappa di destinatari per evitare duplicati
		final Hashtable<UUID, DestinatarioEvasione> hstDestinatarioEvasione = new Hashtable<>();
		hstDestinatarioEvasione.clear();

		for (final DocumentoTrasportoRiga documentoTrasportoRiga : documentoTrasportoRigaList) {
			RigaOrdine rigaOrdine = documentoTrasportoRiga.getRigaOrdine();

			// inserisco il destinatario evasione
			// controllando che non ci sia già
			DestinatarioEvasione destinatarioEvasione = hstDestinatarioEvasione.get(rigaOrdine.getDestinatario().getId());
			if (destinatarioEvasione == null) {
				destinatarioEvasione = destinatarioEvasioneDad.saveDestinatarioEvasione(
						UtilityEvasione.creaDestinatarioEvasione(testataEvasione, rigaOrdine, decodificaDad));
				hstDestinatarioEvasione.put(rigaOrdine.getDestinatario().getId(), destinatarioEvasione);
			}

			// creo la riga evasione e ne inserisco l'id in DocumentoTrasportoRiga
			final RigaEvasione rigaEvasione = rigaEvasioneDad.saveRigaEvasione(
					UtilityEvasione.creaRigaEvasioneDaDocumentoTrasporto(documentoTrasportoRiga, rigaOrdine, destinatarioEvasione, decodificaDad));
			documentoTrasportoRiga.setRigaEvasione(rigaEvasione);
			documentoTrasportoRigaDad.updateDocumentoTrasportoRiga(documentoTrasportoRiga);

			// aggiorno il totale evasione con l'importo della riga appena creata
			totaleEvasione = totaleEvasione.add(rigaEvasione.getImportoTotale());

			// controllo gli impegni
			final List<ImpegnoOrdine> impegnoOrdineList = impegnoOrdineDad.getImpegniByRigaOrdineId(rigaOrdine.getId());
			if (impegnoOrdineList != null && impegnoOrdineList.size() == 1) {
				final ImpegnoOrdine impegnoOrdine = impegnoOrdineList.get(0);

				// 2.9 Verifica impegni/subimpegni ribaltati
				final Impegno impegno = UtilityImpegni.verificaImpegniRibaltati(impegnoDad, settore.getEnte().getId(), impegnoOrdine);
				if (impegno != null) {
					ImpegnoEvasione impegnoEvasione = UtilityEvasione.creaImpegnoEvasione(rigaEvasione, impegno, impegnoOrdine);

					final List<SubimpegnoOrdine> subimpegnoOrdineList = impegnoDad.getSubimpegnoOrdineByImpegnoOrdineId(impegnoOrdine.getId());
					if (subimpegnoOrdineList == null) {
						impegnoEvasione = impegnoEvasioneDad.saveImpegnoEvasione(impegnoEvasione);
					} else if (subimpegnoOrdineList.size() == 1) {
						final SubimpegnoOrdine subimpegnoOrdine = subimpegnoOrdineList.get(0);
						// 2.9 Verifica impegni/subimpegni ribaltati
						final Subimpegno subimpegno = UtilityImpegni.verificaSubimpegniRibaltati(impegnoDad, settore.getEnte().getId(), subimpegnoOrdine);
						if (subimpegno != null) {
							impegnoEvasione = impegnoEvasioneDad.saveImpegnoEvasione(impegnoEvasione);
							SubimpegnoEvasione subimpegnoEvasione = UtilityEvasione.creaSubimpegnoEvasione(impegnoEvasione, subimpegnoOrdine, subimpegno, rigaEvasione);
							subimpegnoEvasione = subimpegnoEvasioneDad.insert(subimpegnoEvasione);
						}
					}
				}
			}

			// Determinazione dello stato da assegnare alla riga d’ordine
			final Stato statoRigaOrdine = UtilityEvasione.getStatoRigaOrdine(rigaOrdine, rigaEvasioneDad, decodificaDad);
			rigaOrdine.setStato(statoRigaOrdine);
			rigaOrdine = rigaOrdineDad.updateRigaOrdine(rigaOrdine);

			// Determinazione dello stato da assegnare al destinatario d’ordine
			Destinatario destinatarioOrdine = rigaOrdine.getDestinatario();
			final List<RigaOrdine> rigaOrdineList = rigaOrdineDad.getRigheByDestinatario(destinatarioOrdine.getId());
			final Stato statoDestinatarioOrdine = UtilityEvasione.getStatoDestinatarioOrdine(rigaOrdineList, decodificaDad);

			destinatarioOrdine.setStato(statoDestinatarioOrdine);
			destinatarioOrdine = destinatarioOrdineDad.updateDestinatario(destinatarioOrdine);
		}

		// inserisco il totale evasione nella testata evasione
		testataEvasione.setTotaleConIva(totaleEvasione);
		//Valorizzazione ufficio Id se presente e mappato  	CPASS-584
		if(documentoTrasporto.getEndpointId()!= null) {
			final Optional<Ufficio> ufficio = commonDad.getUfficioById(documentoTrasporto.getEndpointId(), settore.getId());
			if(ufficio.isPresent()) {
				testataEvasione.setUfficio(ufficio.get());
			}
		}
		testataEvasione = testataEvasioneDad.updateTestataEvasione(testataEvasione);

		// aggiorno lo stato del documento di trasporto
		final Optional<Stato> statoOpt = decodificaDad.getStatoOpt(StatoDocumentoTrasportoEnum.ABBINATO.getCostante(), ConstantsCPassStato.TipoStatoEnum.DOCUMENTO_DI_TRASPORTO.getCostante());
		if (statoOpt.isPresent()) {
			documentoTrasporto.setStato(statoOpt.get());
			documentoTrasportoDad.updateDocumentoTrasporto(documentoTrasporto);
		}

		response.setTestataEvasione(testataEvasione);
	}
}
