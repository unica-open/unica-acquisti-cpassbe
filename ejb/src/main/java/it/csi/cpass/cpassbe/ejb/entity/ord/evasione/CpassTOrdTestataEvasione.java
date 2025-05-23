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
package it.csi.cpass.cpassbe.ejb.entity.ord.evasione;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUfficio;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;

/**
 * The persistent class for the cpass_t_ord_testata_evasione database table.
 *
 */
@Entity
@Table(name = "cpass_t_ord_testata_evasione")
@NamedQuery(name = "CpassTOrdTestataEvasione.findAll", query = "SELECT c FROM CpassTOrdTestataEvasione c")
public class CpassTOrdTestataEvasione extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;

	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_testata_evasione");

	@Id
	@Column(name = "testata_evasione_id")
	private UUID testataEvasioneId;

	@Column(name = "data_autorizzazione")
	private Date dataAutorizzazione;

	@Column(name = "data_invio_contabilita")
	private Date dataInvioContabilita;

	@Column(name = "data_conferma")
	private Date dataConferma;

	@Column(name = "data_inserimento")
	private Date dataInserimento;

	@Column(name = "data_consegna")
	private Date dataConsegna;

	@Column(name = "data_modifica")
	private Date dataModifica;

	@Column(name = "data_ripartizione")
	private Date dataRipartizione;

	private String descrizione;

	@Column(name = "documento_consegna")
	private String documentoConsegna;

	@Column(name = "documento_data_consegna")
	private Date documentoDataConsegna;

	@Column(name = "evasione_anno")
	private Integer evasioneAnno;

	@Column(name = "evasione_numero")
	private Integer evasioneNumero;

	@Column(name = "fattura_anno")
	private Integer fatturaAnno;

	@Column(name = "fattura_codice_fornitore")
	private String fatturaCodiceFornitore;

	@Column(name = "fattura_numero")
	private String fatturaNumero;

	@Column(name = "fattura_protocollo_anno")
	private Integer fatturaProtocolloAnno;

	@Column(name = "fattura_protocollo_numero")
	private Integer fatturaProtocolloNumero;

	@Column(name = "fattura_tipo")
	private String fatturaTipo;

	@Column(name = "fattura_totale")
	private BigDecimal fatturaTotale;

	@Column(name = "fattura_totale_liquidabile")
	private BigDecimal fatturaTotaleLiquidabile;

	private String note;

	@Column(name = "totale_con_iva")
	private BigDecimal totaleConIva;

	@Column(name = "documento_trasporto_id")
	private Integer documentoTrasportoId;

	// bi-directional many-to-one association to CpassTOrdDestinatarioEvasione
	@OneToMany(mappedBy = "cpassTOrdTestataEvasione")
	private List<CpassTOrdDestinatarioEvasione> cpassTOrdDestinatarioEvasiones;

	// bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name = "stato_id")
	private CpassDStato cpassDStato;

	// bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name = "ufficio_id")
	private CpassTUfficio cpassTUfficio;

	// bi-directional many-to-one association to CpassTFornitore
	@ManyToOne
	@JoinColumn(name = "fornitore_id")
	private CpassTFornitore cpassTFornitore;

	// bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name = "settore_competente_id")
	private CpassTSettore cpassTSettore;

	// bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name = "utente_compilatore_id")
	private CpassTUtente cpassTUtente;


	// bi-directional many-to-one association to CpassDOrdTipoEvasione
	@ManyToOne
	@JoinColumn(name = "tipo_evasione_id")
	private CpassDOrdTipoEvasione cpassDOrdTipoEvasione;

	@Column(name = "fattura_codice_ext")
	private String fatturaCodiceExt;

	public CpassTOrdTestataEvasione() {
	}

	public UUID getTestataEvasioneId() {
		return this.testataEvasioneId;
	}

	public void setTestataEvasioneId(UUID testataEvasioneId) {
		this.testataEvasioneId = testataEvasioneId;
	}

	public Date getDataAutorizzazione() {
		return this.dataAutorizzazione;
	}

	public void setDataAutorizzazione(Date dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}

	public Date getDataConferma() {
		return this.dataConferma;
	}

	public void setDataConferma(Date dataConferma) {
		this.dataConferma = dataConferma;
	}

	public Date getDataConsegna() {
		return this.dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public Date getDataInserimento() {
		return this.dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	@Override
	public Date getDataModifica() {
		return this.dataModifica;
	}

	@Override
	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public Date getDataRipartizione() {
		return this.dataRipartizione;
	}

	public void setDataRipartizione(Date dataRipartizione) {
		this.dataRipartizione = dataRipartizione;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDocumentoConsegna() {
		return this.documentoConsegna;
	}

	public void setDocumentoConsegna(String documentoConsegna) {
		this.documentoConsegna = documentoConsegna;
	}

	public Date getDocumentoDataConsegna() {
		return this.documentoDataConsegna;
	}

	public void setDocumentoDataConsegna(Date documentoDataConsegna) {
		this.documentoDataConsegna = documentoDataConsegna;
	}

	public Integer getEvasioneAnno() {
		return this.evasioneAnno;
	}

	public void setEvasioneAnno(Integer evasioneAnno) {
		this.evasioneAnno = evasioneAnno;
	}

	public Integer getEvasioneNumero() {
		return this.evasioneNumero;
	}

	public void setEvasioneNumero(Integer evasioneNumero) {
		this.evasioneNumero = evasioneNumero;
	}

	public Integer getFatturaAnno() {
		return this.fatturaAnno;
	}

	public void setFatturaAnno(Integer fatturaAnno) {
		this.fatturaAnno = fatturaAnno;
	}

	public String getFatturaCodiceFornitore() {
		return this.fatturaCodiceFornitore;
	}

	public void setFatturaCodiceFornitore(String fatturaCodiceFornitore) {
		this.fatturaCodiceFornitore = fatturaCodiceFornitore;
	}

	public String getFatturaNumero() {
		return this.fatturaNumero;
	}

	public void setFatturaNumero(String fatturaNumero) {
		this.fatturaNumero = fatturaNumero;
	}

	public Integer getFatturaProtocolloAnno() {
		return this.fatturaProtocolloAnno;
	}

	public void setFatturaProtocolloAnno(Integer fatturaProtocolloAnno) {
		this.fatturaProtocolloAnno = fatturaProtocolloAnno;
	}

	public Integer getFatturaProtocolloNumero() {
		return this.fatturaProtocolloNumero;
	}

	public void setFatturaProtocolloNumero(Integer fatturaProtocolloNumero) {
		this.fatturaProtocolloNumero = fatturaProtocolloNumero;
	}

	public String getFatturaTipo() {
		return this.fatturaTipo;
	}

	public void setFatturaTipo(String fatturaTipo) {
		this.fatturaTipo = fatturaTipo;
	}

	public BigDecimal getFatturaTotale() {
		return this.fatturaTotale;
	}

	public void setFatturaTotale(BigDecimal fatturaTotale) {
		this.fatturaTotale = fatturaTotale;
	}

	public BigDecimal getFatturaTotaleLiquidabile() {
		return this.fatturaTotaleLiquidabile;
	}

	public void setFatturaTotaleLiquidabile(BigDecimal fatturaTotaleLiquidabile) {
		this.fatturaTotaleLiquidabile = fatturaTotaleLiquidabile;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getTotaleConIva() {
		return this.totaleConIva;
	}

	public void setTotaleConIva(BigDecimal totaleConIva) {
		this.totaleConIva = totaleConIva;
	}

	public Integer getDocumentoTrasportoId() {
		return documentoTrasportoId;
	}

	public void setDocumentoTrasportoId(Integer documentoTrasportoId) {
		this.documentoTrasportoId = documentoTrasportoId;
	}

	public List<CpassTOrdDestinatarioEvasione> getCpassTOrdDestinatarioEvasiones() {
		return this.cpassTOrdDestinatarioEvasiones;
	}

	public void setCpassTOrdDestinatarioEvasiones(List<CpassTOrdDestinatarioEvasione> cpassTOrdDestinatarioEvasiones) {
		this.cpassTOrdDestinatarioEvasiones = cpassTOrdDestinatarioEvasiones;
	}

	public CpassTOrdDestinatarioEvasione addCpassTOrdDestinatarioEvasione(CpassTOrdDestinatarioEvasione cpassTOrdDestinatarioEvasione) {
		getCpassTOrdDestinatarioEvasiones().add(cpassTOrdDestinatarioEvasione);
		cpassTOrdDestinatarioEvasione.setCpassTOrdTestataEvasione(this);

		return cpassTOrdDestinatarioEvasione;
	}

	public CpassTOrdDestinatarioEvasione removeCpassTOrdDestinatarioEvasione(CpassTOrdDestinatarioEvasione cpassTOrdDestinatarioEvasione) {
		getCpassTOrdDestinatarioEvasiones().remove(cpassTOrdDestinatarioEvasione);
		cpassTOrdDestinatarioEvasione.setCpassTOrdTestataEvasione(null);

		return cpassTOrdDestinatarioEvasione;
	}

	public CpassDStato getCpassDStato() {
		return this.cpassDStato;
	}

	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	public CpassTFornitore getCpassTFornitore() {
		return this.cpassTFornitore;
	}

	public void setCpassTFornitore(CpassTFornitore cpassTFornitore) {
		this.cpassTFornitore = cpassTFornitore;
	}

	public CpassTSettore getCpassTSettore() {
		return this.cpassTSettore;
	}

	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	public CpassTUtente getCpassTUtente() {
		return this.cpassTUtente;
	}

	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}

	public CpassDOrdTipoEvasione getCpassDOrdTipoEvasione() {
		return cpassDOrdTipoEvasione;
	}

	public void setCpassDOrdTipoEvasione(CpassDOrdTipoEvasione cpassDOrdTipoEvasione) {
		this.cpassDOrdTipoEvasione = cpassDOrdTipoEvasione;
	}

	public CpassTUfficio getCpassTUfficio() {
		return cpassTUfficio;
	}

	public void setCpassTUfficio(CpassTUfficio cpassTUfficio) {
		this.cpassTUfficio = cpassTUfficio;
	}

	/**
	 * @return the dataInvioContabilita
	 */
	public Date getDataInvioContabilita() {
		return dataInvioContabilita;
	}

	/**
	 * @param dataInvioContabilita the dataInvioContabilita to set
	 */
	public void setDataInvioContabilita(Date dataInvioContabilita) {
		this.dataInvioContabilita = dataInvioContabilita;
	}

	/**
	 * @return the fatturaCodiceExt
	 */
	public String getFatturaCodiceExt() {
		return fatturaCodiceExt;
	}

	/**
	 * @param fatturaCodiceExt the fatturaCodiceExt to set
	 */
	public void setFatturaCodiceExt(String fatturaCodiceExt) {
		this.fatturaCodiceExt = fatturaCodiceExt;
	}

	@Override
	public UUID getId() {
		return testataEvasioneId;
	}

	@Override
	public void setId(UUID id) {
		testataEvasioneId = id;
	}

	@Override
	public void initId() {
		this.testataEvasioneId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, evasioneAnno + "|" + evasioneNumero + "|" + cpassTSettore.getId());
	}

}
