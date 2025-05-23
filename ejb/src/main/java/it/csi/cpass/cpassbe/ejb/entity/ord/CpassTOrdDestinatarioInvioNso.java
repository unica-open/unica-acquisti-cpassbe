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
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_ord_destinatario_invio_nso database table.
 *
 */
@Entity
@Table(name="cpass_t_ord_destinatario_invio_nso")
@NamedQuery(name="CpassTOrdDestinatarioInvioNso.findAll", query="SELECT c FROM CpassTOrdDestinatarioInvioNso c")
public class CpassTOrdDestinatarioInvioNso implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_ORD_DESTINATARIO_INVIO_NSOID_GENERATOR", sequenceName="cpass_t_ord_destinatario_invio_ns_destinatario_invio_nso_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_ORD_DESTINATARIO_INVIO_NSOID_GENERATOR")
	@Column(name="destinatario_invio_nso_id", unique=true, nullable=false)
	private Integer destinatarioInvioNsoId;

	@Column(name="cbc_id")
	private String cbcId;

	@Column(name="data_creazione")
	private Date dataCreazione;

	@Column(name="data_modifica")
	private Date dataModifica;

	@Column(name="esito_invio_errore_codice")
	private String esitoInvioErroreCodice;

	@Column(name="esito_invio_errore_descrizione")
	private String esitoInvioErroreDescrizione;

	@Column(name="esito_invio")
	private String esitoInvio;

	@Column(name="order_document_reference_id")
	private String orderDocumentReferenceId;

	@Column(name="progressivo_invio")
	private Integer progressivoInvio;

	@Column(name="utente_creazione")
	private String utenteCreazione;

	@Column(name="utente_modifica")
	private String utenteModifica;

	//bi-directional many-to-one association to CpassTOrdDestinatarioOrdine
	@ManyToOne
	@JoinColumn(name="destinatario_id")
	private CpassTOrdDestinatarioOrdine cpassTOrdDestinatarioOrdine;

	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@ManyToOne
	@JoinColumn(name="testata_ordine_id")
	private CpassTOrdTestataOrdine cpassTOrdTestataOrdine;

	//bi-directional many-to-one association to CpassTOrdDestinatarioInvioNsoXml
	@OneToMany(mappedBy="cpassTOrdDestinatarioInvioNso")
	private List<CpassTOrdDestinatarioInvioNsoXml> cpassTOrdDestinatarioInvioNsoXmls;

	@Column(name="esito_consegna_mdn_errore_codice")
	private String esitoConsegnaMdnErroreCodice;

	@Column(name="esito_consegna_mdn_errore_descrizione")
	private String esitoConsegnaMdnErroreDescrizione;

	@Column(name="esito_consegna_mdn")
	private String esitoConsegnaMdn;

	@Column(name="esito_consegna_nso_errore_codice")
	private String esitoConsegnaNsoErroreCodice;

	@Column(name="esito_consegna_nso_errore_descrizione")
	private String esitoConsegnaNsoErroreDescrizione;

	@Column(name="esito_consegna_nso")
	private String esitoConsegnaNso;

	@Column(name="urn")
	private String urn;

	@ManyToOne
	@JoinColumn(name="utente_invio")
	private CpassTUtente cpassTUtente;

	public CpassTOrdDestinatarioInvioNso() {
	}

	public Integer getDestinatarioInvioNsoId() {
		return this.destinatarioInvioNsoId;
	}

	public void setDestinatarioInvioNsoId(Integer destinatarioInvioNsoId) {
		this.destinatarioInvioNsoId = destinatarioInvioNsoId;
	}

	public String getCbcId() {
		return this.cbcId;
	}

	public void setCbcId(String cbcId) {
		this.cbcId = cbcId;
	}

	public Date getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getEsitoInvioErroreCodice() {
		return this.esitoInvioErroreCodice;
	}

	public void setEsitoInvioErroreCodice(String esitoInvioErroreCodice) {
		this.esitoInvioErroreCodice = esitoInvioErroreCodice;
	}

	public String getEsitoInvioErroreDescrizione() {
		return this.esitoInvioErroreDescrizione;
	}

	public void setEsitoInvioErroreDescrizione(String esitoInvioErroreDescrizione) {
		this.esitoInvioErroreDescrizione = esitoInvioErroreDescrizione;
	}

	public String getEsitoInvio() {
		return this.esitoInvio;
	}

	public void setEsitoInvio(String esitoInvio) {
		this.esitoInvio = esitoInvio;
	}

	public String getOrderDocumentReferenceId() {
		return this.orderDocumentReferenceId;
	}

	public void setOrderDocumentReferenceId(String orderDocumentReferenceId) {
		this.orderDocumentReferenceId = orderDocumentReferenceId;
	}

	public Integer getProgressivoInvio() {
		return this.progressivoInvio;
	}

	public void setProgressivoInvio(Integer progressivoInvio) {
		this.progressivoInvio = progressivoInvio;
	}

	public String getUtenteCreazione() {
		return this.utenteCreazione;
	}

	public void setUtenteCreazione(String utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}

	public String getUtenteModifica() {
		return this.utenteModifica;
	}

	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}

	public CpassTOrdDestinatarioOrdine getCpassTOrdDestinatarioOrdine() {
		return this.cpassTOrdDestinatarioOrdine;
	}

	public void setCpassTOrdDestinatarioOrdine(CpassTOrdDestinatarioOrdine cpassTOrdDestinatarioOrdine) {
		this.cpassTOrdDestinatarioOrdine = cpassTOrdDestinatarioOrdine;
	}

	public CpassTOrdTestataOrdine getCpassTOrdTestataOrdine() {
		return this.cpassTOrdTestataOrdine;
	}

	public void setCpassTOrdTestataOrdine(CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		this.cpassTOrdTestataOrdine = cpassTOrdTestataOrdine;
	}

	public List<CpassTOrdDestinatarioInvioNsoXml> getCpassTOrdDestinatarioInvioNsoXmls() {
		return this.cpassTOrdDestinatarioInvioNsoXmls;
	}

	public void setCpassTOrdDestinatarioInvioNsoXmls(List<CpassTOrdDestinatarioInvioNsoXml> cpassTOrdDestinatarioInvioNsoXmls) {
		this.cpassTOrdDestinatarioInvioNsoXmls = cpassTOrdDestinatarioInvioNsoXmls;
	}

	public CpassTOrdDestinatarioInvioNsoXml addCpassTOrdDestinatarioInvioNsoXml(CpassTOrdDestinatarioInvioNsoXml cpassTOrdDestinatarioInvioNsoXml) {
		getCpassTOrdDestinatarioInvioNsoXmls().add(cpassTOrdDestinatarioInvioNsoXml);
		cpassTOrdDestinatarioInvioNsoXml.setCpassTOrdDestinatarioInvioNso(this);

		return cpassTOrdDestinatarioInvioNsoXml;
	}

	public CpassTOrdDestinatarioInvioNsoXml removeCpassTOrdDestinatarioInvioNsoXml(CpassTOrdDestinatarioInvioNsoXml cpassTOrdDestinatarioInvioNsoXml) {
		getCpassTOrdDestinatarioInvioNsoXmls().remove(cpassTOrdDestinatarioInvioNsoXml);
		cpassTOrdDestinatarioInvioNsoXml.setCpassTOrdDestinatarioInvioNso(null);

		return cpassTOrdDestinatarioInvioNsoXml;
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
	public CpassTUtente getCpassTUtente() {
		return cpassTUtente;
	}

	/**
	 * @param utenteInvio the utenteInvio to set
	 */
	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}

	@Override
	public Integer getId() {
		return destinatarioInvioNsoId;
	}

	@Override
	public void setId(Integer id) {
		destinatarioInvioNsoId = id;
	}

}
