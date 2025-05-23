/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.util.Date;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * The Class DestinatarioInvioNso
 */
public class DestinatarioInvioNso extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String cbcId;
	private Date dataCreazione;
	private Date dataModifica;
	private String esitoInvioErroreCodice;
	private String esitoInvioErroreDescrizione;
	private String esitoInvio;
	private String orderDocumentReferenceId;
	private Integer progressivoInvio;
	private String utenteCreazione;
	private String utenteModifica;
	private Destinatario destinatario;
	private TestataOrdine testataOrdine;
//	private List<DestinatarioInvioNsoXml> destinatarioInvioNsoXmls;
	private String esitoConsegnaMdnErroreCodice;
	private String esitoConsegnaMdnErroreDescrizione;
	private String esitoConsegnaMdn;
	private String esitoConsegnaNsoErroreCodice;
	private String esitoConsegnaNsoErroreDescrizione;
	private String esitoConsegnaNso;
	private String urn;
	private Utente utenteInvio;

	public DestinatarioInvioNso() {}


	/**
	 * @return the cbcId
	 */
	public String getCbcId() {
		return cbcId;
	}


	/**
	 * @param cbcId the cbcId to set
	 */
	public void setCbcId(String cbcId) {
		this.cbcId = cbcId;
	}


	/**
	 * @return the dataCreazione
	 */
	public Date getDataCreazione() {
		return dataCreazione;
	}


	/**
	 * @param dataCreazione the dataCreazione to set
	 */
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}


	/**
	 * @return the dataModifica
	 */
	public Date getDataModifica() {
		return dataModifica;
	}


	/**
	 * @param dataModifica the dataModifica to set
	 */
	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}


	/**
	 * @return the esitoInvioErroreCodice
	 */
	public String getEsitoInvioErroreCodice() {
		return esitoInvioErroreCodice;
	}


	/**
	 * @param esitoInvioErroreCodice the esitoInvioErroreCodice to set
	 */
	public void setEsitoInvioErroreCodice(String esitoInvioErroreCodice) {
		this.esitoInvioErroreCodice = esitoInvioErroreCodice;
	}


	/**
	 * @return the esitoInvioErroreDescrizione
	 */
	public String getEsitoInvioErroreDescrizione() {
		return esitoInvioErroreDescrizione;
	}


	/**
	 * @param esitoInvioErroreDescrizione the esitoInvioErroreDescrizione to set
	 */
	public void setEsitoInvioErroreDescrizione(String esitoInvioErroreDescrizione) {
		this.esitoInvioErroreDescrizione = esitoInvioErroreDescrizione;
	}


	/**
	 * @return the esitoInvio
	 */
	public String getEsitoInvio() {
		return esitoInvio;
	}


	/**
	 * @param esitoInvio the esitoInvio to set
	 */
	public void setEsitoInvio(String esitoInvio) {
		this.esitoInvio = esitoInvio;
	}


	/**
	 * @return the orderDocumentReferenceId
	 */
	public String getOrderDocumentReferenceId() {
		return orderDocumentReferenceId;
	}


	/**
	 * @param orderDocumentReferenceId the orderDocumentReferenceId to set
	 */
	public void setOrderDocumentReferenceId(String orderDocumentReferenceId) {
		this.orderDocumentReferenceId = orderDocumentReferenceId;
	}


	/**
	 * @return the progressivoInvio
	 */
	public Integer getProgressivoInvio() {
		return progressivoInvio;
	}


	/**
	 * @param progressivoInvio the progressivoInvio to set
	 */
	public void setProgressivoInvio(Integer progressivoInvio) {
		this.progressivoInvio = progressivoInvio;
	}


	/**
	 * @return the utenteCreazione
	 */
	public String getUtenteCreazione() {
		return utenteCreazione;
	}


	/**
	 * @param utenteCreazione the utenteCreazione to set
	 */
	public void setUtenteCreazione(String utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}


	/**
	 * @return the utenteModifica
	 */
	public String getUtenteModifica() {
		return utenteModifica;
	}


	/**
	 * @param utenteModifica the utenteModifica to set
	 */
	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}


	/**
	 * @return the destinatario
	 */
	public Destinatario getDestinatario() {
		return destinatario;
	}


	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}


	/**
	 * @return the testataOrdine
	 */
	public TestataOrdine getTestataOrdine() {
		return testataOrdine;
	}


	/**
	 * @param testataOrdine the testataOrdine to set
	 */
	public void setTestataOrdine(TestataOrdine testataOrdine) {
		this.testataOrdine = testataOrdine;
	}
	/**
	 * @return the esitoConsegnaMdnErroreCodice
	 */
	public String getEsitoConsegnaMdnErroreCodice() {
		return esitoConsegnaMdnErroreCodice;
	}


	/**
	 * @param esitoConsegnaMdnErroreCodice the esitoConsegnaMdnErroreCodice to set
	 */
	public void setEsitoConsegnaMdnErroreCodice(String esitoConsegnaMdnErroreCodice) {
		this.esitoConsegnaMdnErroreCodice = esitoConsegnaMdnErroreCodice;
	}


	/**
	 * @return the esitoConsegnaMdnErroreDescrizione
	 */
	public String getEsitoConsegnaMdnErroreDescrizione() {
		return esitoConsegnaMdnErroreDescrizione;
	}


	/**
	 * @param esitoConsegnaMdnErroreDescrizione the esitoConsegnaMdnErroreDescrizione to set
	 */
	public void setEsitoConsegnaMdnErroreDescrizione(String esitoConsegnaMdnErroreDescrizione) {
		this.esitoConsegnaMdnErroreDescrizione = esitoConsegnaMdnErroreDescrizione;
	}


	/**
	 * @return the esitoConsegnaMdn
	 */
	public String getEsitoConsegnaMdn() {
		return esitoConsegnaMdn;
	}


	/**
	 * @param esitoConsegnaMdn the esitoConsegnaMdn to set
	 */
	public void setEsitoConsegnaMdn(String esitoConsegnaMdn) {
		this.esitoConsegnaMdn = esitoConsegnaMdn;
	}


	/**
	 * @return the esitoConsegnaNsoErroreCodice
	 */
	public String getEsitoConsegnaNsoErroreCodice() {
		return esitoConsegnaNsoErroreCodice;
	}


	/**
	 * @param esitoConsegnaNsoErroreCodice the esitoConsegnaNsoErroreCodice to set
	 */
	public void setEsitoConsegnaNsoErroreCodice(String esitoConsegnaNsoErroreCodice) {
		this.esitoConsegnaNsoErroreCodice = esitoConsegnaNsoErroreCodice;
	}


	/**
	 * @return the esitoConsegnaNsoErroreDescrizione
	 */
	public String getEsitoConsegnaNsoErroreDescrizione() {
		return esitoConsegnaNsoErroreDescrizione;
	}


	/**
	 * @param esitoConsegnaNsoErroreDescrizione the esitoConsegnaNsoErroreDescrizione to set
	 */
	public void setEsitoConsegnaNsoErroreDescrizione(String esitoConsegnaNsoErroreDescrizione) {
		this.esitoConsegnaNsoErroreDescrizione = esitoConsegnaNsoErroreDescrizione;
	}


	/**
	 * @return the esitoConsegnaNso
	 */
	public String getEsitoConsegnaNso() {
		return esitoConsegnaNso;
	}


	/**
	 * @param esitoConsegnaNso the esitoConsegnaNso to set
	 */
	public void setEsitoConsegnaNso(String esitoConsegnaNso) {
		this.esitoConsegnaNso = esitoConsegnaNso;
	}


	/**
	 * @return the urn
	 */
	public String getUrn() {
		return urn;
	}


	/**
	 * @param urn the urn to set
	 */
	public void setUrn(String urn) {
		this.urn = urn;
	}


	/**
	 * @return the utenteInvio
	 */
	public Utente getUtenteInvio() {
		return utenteInvio;
	}


	/**
	 * @param utenteInvio the utenteInvio to set
	 */
	public void setUtenteInvio(Utente utenteInvio) {
		this.utenteInvio = utenteInvio;
	}


	@Override
	public String toString() {
		return new StringBuilder()
				.append("DestinatarioInvioNso [")
				.append(", id=").append(id)
				.append("]").toString();
	}

}
