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
package it.csi.cpass.cpassbe.ejb.business.be.service.impl.evasione;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import it.csi.cpass.cpassbe.ejb.business.be.dad.DecodificaDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.DestinatarioEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ElaborazioneTipoDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.ImpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.RigaEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SubimpegnoEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.SystemDad;
import it.csi.cpass.cpassbe.ejb.business.be.dad.TestataEvasioneDad;
import it.csi.cpass.cpassbe.ejb.business.be.service.request.evasione.PutEvasioneInviaContabilitaByIdRequest;
import it.csi.cpass.cpassbe.ejb.business.be.service.response.evasione.PutEvasioneInviaContabilitaByIdResponse;
import it.csi.cpass.cpassbe.ejb.external.ExternalHelperLookup;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassElaborazione;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassParametro;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStato;
import it.csi.cpass.cpassbe.ejb.util.ConstantsCPassStatoElOrdine;
import it.csi.cpass.cpassbe.ejb.util.CpassThreadLocalContainer;
import it.csi.cpass.cpassbe.ejb.util.ElaborazioneTipoEnum;
import it.csi.cpass.cpassbe.ejb.util.conf.ConfigurationHelper;
import it.csi.cpass.cpassbe.lib.IntegrationConstants;
import it.csi.cpass.cpassbe.lib.dto.ApiError;
import it.csi.cpass.cpassbe.lib.dto.Elaborazione;
import it.csi.cpass.cpassbe.lib.dto.ElaborazioneTipo;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.FiltroFornitore;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.InvioQuoteDocumento;
import it.csi.cpass.cpassbe.lib.dto.ModalitaPagamento;
import it.csi.cpass.cpassbe.lib.dto.Parametro;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.ord.StatoElOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.DestinatarioEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.ImpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.SubimpegnoEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.TestataEvasione;
import it.csi.cpass.cpassbe.lib.external.FornitoreHelper;
import it.csi.cpass.cpassbe.lib.external.InvioQuoteDocumentoHelper;
import it.csi.cpass.cpassbe.lib.external.itf.ExternalServiceResolveWrapper;

public class PutEvasioneInviaContabilitaByIdService
		extends BaseTestataEvasioneService<PutEvasioneInviaContabilitaByIdRequest, PutEvasioneInviaContabilitaByIdResponse> {

	private final ExternalHelperLookup externalHelperLookup;

	private final DecodificaDad decodificaDad;
	private final ElaborazioneDad elaborazioneDad;
	private final SystemDad systemDad;
	private final ElaborazioneTipoDad elaborazioneTipoDad;

	private final DestinatarioEvasioneDad destinatarioEvasioneDad;
	private final RigaEvasioneDad rigaEvasioneDad;
	private final ImpegnoEvasioneDad impegnoEvasioneDad;
	private final SubimpegnoEvasioneDad subimpegnoEvasioneDad;

	private String modalitaPagamentoSoggetto = null;
	private boolean bDocumentoSpesaPrimaOccorrenza = true;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ITALY);

	/**
	 * Constructor
	 * 
	 * @param configurationHelper
	 * @param testataEvasioneDad
	 * @param impegnoDad
	 * @param decodificaDad
	 */
	public PutEvasioneInviaContabilitaByIdService(ConfigurationHelper configurationHelper, ExternalHelperLookup externalHelperLookup,
			TestataEvasioneDad testataEvasioneDad, DestinatarioEvasioneDad destinatarioEvasioneDad, RigaEvasioneDad rigaEvasioneDad,
			ImpegnoEvasioneDad impegnoEvasioneDad, SubimpegnoEvasioneDad subimpegnoEvasioneDad, DecodificaDad decodificaDad, ElaborazioneDad elaborazioneDad,
			SystemDad systemDad, ElaborazioneTipoDad elaborazioneTipoDad) {
		super(configurationHelper, testataEvasioneDad);
		this.externalHelperLookup = externalHelperLookup;

		this.destinatarioEvasioneDad = destinatarioEvasioneDad;
		this.rigaEvasioneDad = rigaEvasioneDad;
		this.impegnoEvasioneDad = impegnoEvasioneDad;
		this.subimpegnoEvasioneDad = subimpegnoEvasioneDad;

		this.decodificaDad = decodificaDad;
		this.elaborazioneDad = elaborazioneDad;
		this.systemDad = systemDad;
		this.elaborazioneTipoDad = elaborazioneTipoDad;
	}

	@Override
	protected void checkServiceParams() {
	}

	@Override
	protected void execute() {
		final String methodName = "execute";
		Settore settoreCorrente = CpassThreadLocalContainer.SETTORE_UTENTE.get();
		Ente ente = settoreCorrente.getEnte();
		List<ApiError> apiErrors = new ArrayList<ApiError>();

		TestataEvasione testataEvasione = testataEvasioneDad.getTestataEvasioneModel(request.getId());

		// Preparazione quote
		ArrayList<QuotaItem> listQuotaItem = preparazioneQuote();

		getModalitaPagamentoSoggetto(testataEvasione);

		// invioQuoteDocumento
		String contenutoDocumento = creazioneXmlPerInvioQuote(testataEvasione, listQuotaItem);

		InvioQuoteDocumento invioQuoteDocumento = new InvioQuoteDocumento();
		invioQuoteDocumento.setCodiceTipoDocumento("DOCUMENTO_SPESA");
		invioQuoteDocumento.setContenutoDocumento(contenutoDocumento);

		ExternalServiceResolveWrapper<InvioQuoteDocumentoHelper> handler = externalHelperLookup.lookup(InvioQuoteDocumentoHelper.class);
		Integer idOperazione = invokeExternalService(handler, () -> handler.getInstance().postInvioQuoteDocumento(handler.getParams(), invioQuoteDocumento));
		log.info(methodName, "idOperazione: " + idOperazione);

		// verifica MODALITA_INVIO_EVASIONE
		Parametro paramModalitaInvioEvasione = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.MODALITA_INVIO_EVASIONE.getCostante(),
				ConstantsCPassParametro.RiferimentoEnum.EVASIONE.getCostante(), ente.getId());
		String modalitaInvioEvasione = paramModalitaInvioEvasione != null ? paramModalitaInvioEvasione.getValore() : null;
		if (modalitaInvioEvasione != null && modalitaInvioEvasione.equals(ConstantsCPassParametro.ValoreEnum.MODALITA_INVIO_EVASIONE_ASINCRONA.getCostante())) {
			// inserimento in tabella elaborazione
			Elaborazione elaborazione = new Elaborazione();
			elaborazione.setEntitaId(testataEvasione.getId().toString());

			String utenteId = CpassThreadLocalContainer.UTENTE_CONNESSO.get().getId().toString();
			elaborazione.setUtente(utenteId);

			elaborazione.setStato(ConstantsCPassElaborazione.StatoEnum.DA_ELABORARE.getStatoDB());
			elaborazione.setData(new Date(System.currentTimeMillis()));
			elaborazione.setEsito(null);
			
			Optional<ElaborazioneTipo> elaborazioneTipo = elaborazioneTipoDad.findByElaborazioneTipoCodice(ElaborazioneTipoEnum.INVIO_EVASIONE.getCostante());
			elaborazione.setElaborazioneTipo(elaborazioneTipo.get());
			
			elaborazione.setIdEsterno("" + idOperazione);

			elaborazione = elaborazioneDad.saveElaborazione(elaborazione);
		}

		// verifica SERVIZIO_VERIFICA_EVASIONE
		Parametro paramServizioVerificaEvasione = systemDad.getParametro(ConstantsCPassParametro.ChiaveEnum.SERVIZIO_VERIFICA_EVASIONE.getCostante(),
				ConstantsCPassParametro.RiferimentoEnum.EVASIONE.getCostante(), ente.getId());
		String servizioVerificaEvasione = paramServizioVerificaEvasione != null ? paramServizioVerificaEvasione.getValore() : null;
		if (servizioVerificaEvasione != null
				&& servizioVerificaEvasione.equals(ConstantsCPassParametro.ValoreEnum.SERVIZIO_VERIFICA_EVASIONE_FALSE.getCostante())) {

			// per tutti i destinatari collegati
			List<DestinatarioEvasione> destinatarioEvasiones = destinatarioEvasioneDad.findByEvasione(request.getId());
			for (DestinatarioEvasione destinatarioEvasione : destinatarioEvasiones) {

				// per tutte le righe collegate
				List<RigaEvasione> righeEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
				for (RigaEvasione rigaEvasione : righeEvasiones) {

					List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
					if (impegnoEvasiones != null && impegnoEvasiones.size() > 0) {
						for (ImpegnoEvasione impegnoEvasione : impegnoEvasiones) {
							impegnoEvasione.setImportoLiquidato(impegnoEvasione.getImportoRipartito());
							impegnoEvasioneDad.update(impegnoEvasione);

							if (impegnoEvasione.getSubimpegnoEvasiones() != null) {
								for (SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
									subimpegnoEvasione.setImportoLiquidato(subimpegnoEvasione.getImportoRipartito());
									subimpegnoEvasioneDad.update(subimpegnoEvasione);
								}
							}
						}
					}

					// aggiornare stato riga evasione
					String codiceStatoRigaEvasione = ConstantsCPassStatoElOrdine.StatoEnum.RIGA_EVASIONE_TOTALMENTE_FATTURATA.getCostante();
					StatoElOrdine statoElOrdineRigaEvasione = decodificaDad.getStatoElOrdine(codiceStatoRigaEvasione,
							ConstantsCPassStatoElOrdine.TipoEnum.RIGA_EVASIONE.getCostante());
					rigaEvasione.setStatoElOrdine(statoElOrdineRigaEvasione);
					rigaEvasioneDad.updateRigaEvasione(rigaEvasione);
				}

				// aggiornare stato destinatario evasione
				String codiceStatoRigaEvasione = ConstantsCPassStatoElOrdine.StatoEnum.DESTINATARIO_EVASIONE_TOTALMENTE_FATTURATO.getCostante();
				StatoElOrdine statoElOrdineRigaEvasione = decodificaDad.getStatoElOrdine(codiceStatoRigaEvasione,
						ConstantsCPassStatoElOrdine.TipoEnum.DESTINATARIO_EVASIONE.getCostante());
				destinatarioEvasione.setStatoElOrdine(statoElOrdineRigaEvasione);
				destinatarioEvasioneDad.updateDestinatarioEvasione(destinatarioEvasione);
			}
		}
		
		// aggiornamento stato evasione
		String codiceStatoTestataEvasione = ConstantsCPassStato.StatoEnum.INVIATA.getCostante();
		Stato statoEvasione = decodificaDad.getStato(codiceStatoTestataEvasione, ConstantsCPassStato.TipoEnum.EVASIONE.getCostante()).get();
		testataEvasione.setStato(statoEvasione);
		
		// aggiornamento data invio
		testataEvasione.setDataInvioContabilita(new Date(System.currentTimeMillis()));
				
		testataEvasioneDad.updateTestataEvasione(testataEvasione);

		separaMessaggiErrorePerTipo(apiErrors);

		response.setTestataEvasione(testataEvasione);
	}

	private void getModalitaPagamentoSoggetto(TestataEvasione testataEvasione) {
		// leggere la prima modalità di pagamento valida restituita (elencoModalitaPagamento.codice con elencoModalitaPagamento.stato.codice =
		// 'valido')
		FiltroFornitore filtroFornitore = new FiltroFornitore();

		Fornitore fornitore = new Fornitore();
		fornitore.setCodice(testataEvasione.getFatturaCodice());
		filtroFornitore.setFornitore(fornitore);

		filtroFornitore.setStatoFornitore(IntegrationConstants.SOGGETTO_STATO_VALIDO);

		ExternalServiceResolveWrapper<FornitoreHelper> handler = externalHelperLookup.lookup(FornitoreHelper.class);
		List<Fornitore> fornitori = invokeExternalService(handler, () -> handler.getInstance().getFornitori(handler.getParams(), filtroFornitore));

		for (ModalitaPagamento modalitaPagamento : fornitori.get(0).getElencoModalitaPagamento()) {
			if (modalitaPagamentoSoggetto == null && modalitaPagamento.getStato().equalsIgnoreCase("valido")) {
				modalitaPagamentoSoggetto = modalitaPagamento.getCodice();
			}
		}
	}

	private ArrayList<QuotaItem> preparazioneQuote() {
		ArrayList<QuotaItem> listQuotaItem = new ArrayList<QuotaItem>();

		// per tutti i destinatari collegati
		List<DestinatarioEvasione> destinatarioEvasiones = destinatarioEvasioneDad.findByEvasione(request.getId());
		for (DestinatarioEvasione destinatarioEvasione : destinatarioEvasiones) {

			// per tutte le righe collegate
			List<RigaEvasione> righeEvasiones = rigaEvasioneDad.getRigheByDestinatarioEvasione(destinatarioEvasione.getId());
			for (RigaEvasione rigaEvasione : righeEvasiones) {

				List<ImpegnoEvasione> impegnoEvasiones = impegnoEvasioneDad.getImpegniByRigaEvasione(rigaEvasione.getId());
				if (impegnoEvasiones != null && impegnoEvasiones.size() > 0) {
					for (ImpegnoEvasione impegnoEvasione : impegnoEvasiones) {

						if (impegnoEvasione.getSubimpegnoEvasiones() == null || impegnoEvasione.getSubimpegnoEvasiones().size() == 0) {
							QuotaItem quotaItem = new QuotaItem();
							quotaItem.sospensioni.add(new SospensioneItem());
							quotaItem.impegnoAnno = impegnoEvasione.getImpegnoAnno();
							quotaItem.impegnoNumero = impegnoEvasione.getImpegnoNumero();
							
							SospensioneItem tempSospensione = quotaItem.sospensioni.get(0);

							tempSospensione.dataSospensioneQuota = impegnoEvasione.getDataSospensione();
							if (impegnoEvasione.getCausaleSospensioneEvasione() != null) {
								tempSospensione.causaleSospensioneQuota = impegnoEvasione.getCausaleSospensioneEvasione().getCausaleSospensioneCodice();
							}

							int iQuota = listQuotaItem.indexOf(quotaItem);
							if (iQuota == -1) {
								if (tempSospensione.causaleSospensioneQuota == null) {
									quotaItem.importoQuota = impegnoEvasione.getImportoRipartito();
								} else {
									quotaItem.importoQuota = impegnoEvasione.getImportoSospeso();
								}
								listQuotaItem.add(quotaItem);
							} else {
								quotaItem = listQuotaItem.get(iQuota);
								if (tempSospensione.causaleSospensioneQuota == null) {
									quotaItem.importoQuota = quotaItem.importoQuota.add(impegnoEvasione.getImportoRipartito());
								} else {
									quotaItem.importoQuota = quotaItem.importoQuota.add(impegnoEvasione.getImportoSospeso());
								}
							}

						} else {
							for (SubimpegnoEvasione subimpegnoEvasione : impegnoEvasione.getSubimpegnoEvasiones()) {
								subimpegnoEvasione.setImportoLiquidato(subimpegnoEvasione.getImportoRipartito());

								QuotaItem quotaItem = new QuotaItem();
								quotaItem.sospensioni.add(new SospensioneItem());
								quotaItem.impegnoAnno = subimpegnoEvasione.getImpegnoAnno();
								quotaItem.impegnoNumero = subimpegnoEvasione.getImpegnoNumero();

								quotaItem.subimpegnoAnno = subimpegnoEvasione.getSubimpegnoAnno();
								quotaItem.subimpegnoNumero = subimpegnoEvasione.getSubimpegnoNumero();
								
								SospensioneItem tempSospensione = quotaItem.sospensioni.get(0);
								
								tempSospensione.dataSospensioneQuota = impegnoEvasione.getDataSospensione();
								if (impegnoEvasione.getCausaleSospensioneEvasione() != null) {
									tempSospensione.causaleSospensioneQuota = impegnoEvasione.getCausaleSospensioneEvasione().getCausaleSospensioneCodice();
								}

								int iQuota = listQuotaItem.indexOf(quotaItem);
								if (iQuota == -1) {
									if (tempSospensione.causaleSospensioneQuota == null) {
										quotaItem.importoQuota = subimpegnoEvasione.getImportoRipartito();
									} else {
										quotaItem.importoQuota = subimpegnoEvasione.getImportoSospeso();
									}
									listQuotaItem.add(quotaItem);
								} else {
									quotaItem = listQuotaItem.get(iQuota);
									if (tempSospensione.causaleSospensioneQuota == null) {
										quotaItem.importoQuota = quotaItem.importoQuota.add(subimpegnoEvasione.getImportoRipartito());
									} else {
										quotaItem.importoQuota = quotaItem.importoQuota.add(subimpegnoEvasione.getImportoSospeso());
									}
								}
							}
						}

					}
				}
			}
		}

		return listQuotaItem;
	}

	private String creazioneXmlPerInvioQuote(TestataEvasione testataEvasione, ArrayList<QuotaItem> listQuotaItem) {
		bDocumentoSpesaPrimaOccorrenza = true;
		
		StringBuilder sbXml = new StringBuilder();
		sbXml.append("<elenchiDocumentiAllegato>");
		sbXml.append("<elenchi>");
		sbXml.append("<elenco saltaInserimento=\'true\'>");
		sbXml.append("<subdocumenti>");

		for (QuotaItem quotaItem : listQuotaItem) {
			sbXml.append("<subdocumentoSpesa>");
			// vedere paragrafo 10.4
			sbXml.append(getSubDocumentoSpesa(testataEvasione, quotaItem));
			sbXml.append("</subdocumentoSpesa>");
		}

		sbXml.append("</subdocumenti>");
		sbXml.append("</elenco>");
		sbXml.append("</elenchi>");
		sbXml.append("</elenchiDocumentiAllegato>");

		return sbXml.toString();
	}

	private String getSubDocumentoSpesa(TestataEvasione testataEvasione, QuotaItem quotaItem) {
		StringBuilder sbXml = new StringBuilder();
		sbXml.append(getDocumentoSpesa(testataEvasione));

		// Impegno Vedere 2.3 Impegno
		sbXml.append(getImpegno(quotaItem));

		// subImpegno Vedere 2.4 Subimpegno
		sbXml.append(getSubImpegno(quotaItem));

		sbXml.append(addTag("modalitaPagamentoSoggetto", addTag("codiceModalitaPagamento", modalitaPagamentoSoggetto)));
		sbXml.append(addTag("importo", quotaItem.importoQuota)); // (cfr cdu chiamante)
		sbXml.append(addTag("importoDaDedurre", 0));
		sbXml.append(addTag("loginOperazione", "APPJ"));

		
		if (quotaItem.sospensioni != null && quotaItem.sospensioni.size() > 0) {
			sbXml.append("<sospensioni>");
			
			for(SospensioneItem sosp : quotaItem.sospensioni) {
				if(sosp.dataSospensioneQuota != null || sosp.causaleSospensioneQuota != null || sosp.dataRiattivazione != null) {
					sbXml.append("<sospensione>");
					if(sosp.dataSospensioneQuota != null) {
						sbXml.append(addTag("dataSospensione", sdf.format(sosp.dataSospensioneQuota)));// La data va passata nel formato: 2015-09-27T14:30:05+02:00
					}
					if(sosp.causaleSospensioneQuota != null) {
						sbXml.append(addTag("causaleSospensione", sosp.causaleSospensioneQuota)); 
					}
					if(sosp.dataRiattivazione != null) {
						sbXml.append(addTag("dataRiattivazione", sosp.dataRiattivazione));
					}
					sbXml.append("</sospensione>");
				}
			}
			
			sbXml.append("</sospensioni>");
		}
		
		return sbXml.toString();
	}

	private String getImpegno(QuotaItem quotaItem) {
		StringBuilder sbXml = new StringBuilder();
		sbXml.append(addTag("annoMovimento", quotaItem.impegnoAnno)); // Anno impegno relativo alla quota (cfr cdu chiamante)");
		sbXml.append(addTag("numero", quotaItem.impegnoNumero)); // Numero impegno relativo alla quota (cfr cdu chiamante)");

		return addTag("impegno", sbXml.toString());
	}

	private String getSubImpegno(QuotaItem quotaItem) {
		StringBuilder sbXml = new StringBuilder();
		sbXml.append(addTag("annoMovimento", quotaItem.subimpegnoAnno)); // Anno subimpegno relativo alla quota (cfr cdu chiamante)
		sbXml.append(addTag("numero", quotaItem.subimpegnoAnno)); // Numero subimpegno relativo alla quota (cfr cdu chiamante)

		return addTag("subImpegno", sbXml.toString());
	}

	private String getDocumentoSpesa(TestataEvasione testataEvasione) {
		StringBuilder sbXml = new StringBuilder();

		// vedere paragrafo 10.3
		if (bDocumentoSpesaPrimaOccorrenza) {
			bDocumentoSpesaPrimaOccorrenza = false;
			
			String soggetto = addTag("codiceSoggetto", testataEvasione.getFatturaCodice());
			sbXml.append(addTag("soggetto", soggetto));

			String tipoDocumento = addTag("codice", testataEvasione.getFatturaTipo());
			tipoDocumento += addTag("tipoFamigliaDocumento", "SPESA");
			sbXml.append(addTag("tipoDocumento", tipoDocumento));

			sbXml.append(addTag("anno", testataEvasione.getFatturaAnno()));
			sbXml.append(addTag("numero", testataEvasione.getFatturaNumero()));
		}
		sbXml.append(addTag("uid", "-1"));

		return addTag("documentoSpesa", sbXml.toString());
	}

	private String addTag(String tag, Object value) {
		if (value == null) {
			return "";
		} else {
			return "<" + tag + ">" + value + "</" + tag + ">";
		}
	}

}

class QuotaItem {
	public Integer impegnoAnno = null;
	public Integer impegnoNumero = null;
	public Integer subimpegnoAnno = null;
	public Integer subimpegnoNumero = null;

	public BigDecimal importoQuota = null;
	
	public List<SospensioneItem> sospensioni = new ArrayList<SospensioneItem>();
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		QuotaItem quotaItem = (QuotaItem) obj;
		return Objects.equals(this.impegnoAnno, quotaItem.impegnoAnno) && Objects.equals(this.impegnoNumero, quotaItem.impegnoNumero)
				&& Objects.equals(this.subimpegnoAnno, quotaItem.subimpegnoAnno) && Objects.equals(this.subimpegnoNumero, quotaItem.subimpegnoNumero)
				&& Objects.equals(this.sospensioni, quotaItem.sospensioni);
	}

}

class SospensioneItem {
	public Date dataSospensioneQuota = null;
	public String causaleSospensioneQuota = null;
	public Date dataRiattivazione = null;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		SospensioneItem sospensioneItem = (SospensioneItem) obj;
		return Objects.equals(this.dataSospensioneQuota, sospensioneItem.dataSospensioneQuota)
				&& Objects.equals(this.causaleSospensioneQuota, sospensioneItem.causaleSospensioneQuota)
				&& Objects.equals(this.dataRiattivazione, sospensioneItem.dataRiattivazione);
	}
}
