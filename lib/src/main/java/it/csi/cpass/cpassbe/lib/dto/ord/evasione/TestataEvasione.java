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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.Utente;

public class TestataEvasione extends BaseAuditedDto<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date dataAutorizzazione;
	private Date dataConferma;
	private Date dataInserimento;
	private Date dataConsegna;
	private Date dataModifica;
	private Date dataRipartizione;
	private String descrizione;
	private String documentoConsegna;
	private Date documentoDataConsegna;
	private Integer evasioneAnno;
	private Integer evasioneNumero;
	private Integer fatturaAnno;
	private String fatturaCodiceFornitore;
	private String fatturaNumero;
	private Integer fatturaProtocolloAnno;
	private Integer fatturaProtocolloNumero;
	private String fatturaTipo;
	private BigDecimal fatturaTotale;
	private BigDecimal fatturaTotaleLiquidabile;
	private String note;
	private BigDecimal totaleConIva;
	private List<DestinatarioEvasione> destinatarioEvasiones;
	private Stato stato;
	private Ufficio ufficio;
	private Integer documentoTrasportoId;
	private Fornitore fornitore;
	private Settore settore;
	private Utente utenteCompilatore;
	//private CausaleSospensioneEvasione causaleSospensioneEvasione;
	private TipoEvasione tipoEvasione;
	private Date dataInvioContabilita;
	private String fatturaCodiceExt;

	public TestataEvasione() {
	}

	/**
	 * @return the dataAutorizzazione
	 */
	public Date getDataAutorizzazione() {
		return dataAutorizzazione;
	}

	/**
	 * @param dataAutorizzazione the dataAutorizzazione to set
	 */
	public void setDataAutorizzazione(Date dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}

	/**
	 * @return the dataConferma
	 */
	public Date getDataConferma() {
		return dataConferma;
	}

	/**
	 * @param dataConferma the dataConferma to set
	 */
	public void setDataConferma(Date dataConferma) {
		this.dataConferma = dataConferma;
	}

	/**
	 * @return the dataInserimento
	 */
	public Date getDataInserimento() {
		return dataInserimento;
	}

	/**
	 * @param dataInserimento the dataInserimento to set
	 */
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	/**
	 * @return the dataConsegna
	 */
	public Date getDataConsegna() {
		return dataConsegna;
	}

	/**
	 * @param dataConsegna the dataConsegna to set
	 */
	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	/**
	 * @return the dataModifica
	 */
	@Override
	public Date getDataModifica() {
		return dataModifica;
	}

	/**
	 * @param dataModifica the dataModifica to set
	 */
	@Override
	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	/**
	 * @return the dataRipartizione
	 */
	public Date getDataRipartizione() {
		return dataRipartizione;
	}

	/**
	 * @param dataRipartizione the dataRipartizione to set
	 */
	public void setDataRipartizione(Date dataRipartizione) {
		this.dataRipartizione = dataRipartizione;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the documentoConsegna
	 */
	public String getDocumentoConsegna() {
		return documentoConsegna;
	}

	/**
	 * @param documentoConsegna the documentoConsegna to set
	 */
	public void setDocumentoConsegna(String documentoConsegna) {
		this.documentoConsegna = documentoConsegna;
	}

	/**
	 * @return the documentoDataConsegna
	 */
	public Date getDocumentoDataConsegna() {
		return documentoDataConsegna;
	}

	/**
	 * @param documentoDataConsegna the documentoDataConsegna to set
	 */
	public void setDocumentoDataConsegna(Date documentoDataConsegna) {
		this.documentoDataConsegna = documentoDataConsegna;
	}

	/**
	 * @return the evasioneAnno
	 */
	public Integer getEvasioneAnno() {
		return evasioneAnno;
	}

	/**
	 * @param evasioneAnno the evasioneAnno to set
	 */
	public void setEvasioneAnno(Integer evasioneAnno) {
		this.evasioneAnno = evasioneAnno;
	}

	/**
	 * @return the evasioneNumero
	 */
	public Integer getEvasioneNumero() {
		return evasioneNumero;
	}

	/**
	 * @param evasioneNumero the evasioneNumero to set
	 */
	public void setEvasioneNumero(Integer evasioneNumero) {
		this.evasioneNumero = evasioneNumero;
	}

	/**
	 * @return the fatturaAnno
	 */
	public Integer getFatturaAnno() {
		return fatturaAnno;
	}

	/**
	 * @param fatturaAnno the fatturaAnno to set
	 */
	public void setFatturaAnno(Integer fatturaAnno) {
		this.fatturaAnno = fatturaAnno;
	}

	/**
	 * @return the fatturaCodiceFornitore
	 */
	public String getFatturaCodiceFornitore() {
		return fatturaCodiceFornitore;
	}

	/**
	 * @param fatturaCodiceFornitore the fatturaCodice to set
	 */
	public void setFatturaCodiceFornitore(String fatturaCodiceFornitore) {
		this.fatturaCodiceFornitore = fatturaCodiceFornitore;
	}

	/**
	 * @return the fatturaNumero
	 */
	public String getFatturaNumero() {
		return fatturaNumero;
	}

	/**
	 * @param fatturaNumero the fatturaNumero to set
	 */
	public void setFatturaNumero(String fatturaNumero) {
		this.fatturaNumero = fatturaNumero;
	}

	/**
	 * @return the fatturaProtocolloAnno
	 */
	public Integer getFatturaProtocolloAnno() {
		return fatturaProtocolloAnno;
	}

	/**
	 * @param fatturaProtocolloAnno the fatturaProtocolloAnno to set
	 */
	public void setFatturaProtocolloAnno(Integer fatturaProtocolloAnno) {
		this.fatturaProtocolloAnno = fatturaProtocolloAnno;
	}

	/**
	 * @return the fatturaProtocolloNumero
	 */
	public Integer getFatturaProtocolloNumero() {
		return fatturaProtocolloNumero;
	}

	/**
	 * @param fatturaProtocolloNumero the fatturaProtocolloNumero to set
	 */
	public void setFatturaProtocolloNumero(Integer fatturaProtocolloNumero) {
		this.fatturaProtocolloNumero = fatturaProtocolloNumero;
	}

	/**
	 * @return the fatturaTipo
	 */
	public String getFatturaTipo() {
		return fatturaTipo;
	}

	/**
	 * @param fatturaTipo the fatturaTipo to set
	 */
	public void setFatturaTipo(String fatturaTipo) {
		this.fatturaTipo = fatturaTipo;
	}

	/**
	 * @return the fatturaTotale
	 */
	public BigDecimal getFatturaTotale() {
		return fatturaTotale;
	}

	/**
	 * @param fatturaTotale the fatturaTotale to set
	 */
	public void setFatturaTotale(BigDecimal fatturaTotale) {
		this.fatturaTotale = fatturaTotale;
	}

	/**
	 * @return the fatturaTotaleLiquidabile
	 */
	public BigDecimal getFatturaTotaleLiquidabile() {
		return fatturaTotaleLiquidabile;
	}

	/**
	 * @param fatturaTotaleLiquidabile the fatturaTotaleLiquidabile to set
	 */
	public void setFatturaTotaleLiquidabile(BigDecimal fatturaTotaleLiquidabile) {
		this.fatturaTotaleLiquidabile = fatturaTotaleLiquidabile;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the totaleConIva
	 */
	public BigDecimal getTotaleConIva() {
		return totaleConIva;
	}

	/**
	 * @param totaleConIva the totaleConIva to set
	 */
	public void setTotaleConIva(BigDecimal totaleConIva) {
		this.totaleConIva = totaleConIva;
	}

	/**
	 * @return the destinatarioEvasiones
	 */
	public List<DestinatarioEvasione> getDestinatarioEvasiones() {
		return destinatarioEvasiones;
	}

	/**
	 * @param destinatarioEvasiones the destinatarioEvasiones to set
	 */
	public void setDestinatarioEvasiones(List<DestinatarioEvasione> destinatarioEvasiones) {
		this.destinatarioEvasiones = destinatarioEvasiones;
	}

	/**
	 * @return the stato
	 */
	public Stato getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}

	/**
	 * @return the fornitore
	 */
	public Fornitore getFornitore() {
		return fornitore;
	}

	/**
	 * @param fornitore the fornitore to set
	 */
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @param settore the settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}

	/**
	 * @return the utente
	 */
	public Utente getUtenteCompilatore() {
		return utenteCompilatore;
	}

	/**
	 * @param utenteCompilatore the utente to set
	 */
	public void setUtenteCompilatore(Utente utenteCompilatore) {
		this.utenteCompilatore = utenteCompilatore;
	}

	/**
	 * @return the tipoEvasione
	 */
	public TipoEvasione getTipoEvasione() {
		return tipoEvasione;
	}

	/**
	 * @param tipoEvasione the tipoEvasione to set
	 */
	public void setTipoEvasione(TipoEvasione tipoEvasione) {
		this.tipoEvasione = tipoEvasione;
	}

	/**
	 * @return the ufficio
	 */
	public Ufficio getUfficio() {
		return ufficio;
	}

	/**
	 * @param ufficio the ufficio to set
	 */
	public void setUfficio(Ufficio ufficio) {
		this.ufficio = ufficio;
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

	public Integer getDocumentoTrasportoId() {
		return documentoTrasportoId;
	}

	public void setDocumentoTrasportoId(Integer documentoTrasportoId) {
		this.documentoTrasportoId = documentoTrasportoId;
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


}
