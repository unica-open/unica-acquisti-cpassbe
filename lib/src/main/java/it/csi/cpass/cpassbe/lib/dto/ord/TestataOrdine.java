/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Fornitore;
import it.csi.cpass.cpassbe.lib.dto.Impegno;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * The type Testata Ordine
 */
public class TestataOrdine extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// testata_ordine_id

	private Settore settore; // vedi settore_emittente_id;

	private Ufficio ufficio; // vedi ufficio_id - cpass_t_ufficio

	private Integer anno; // ordine_anno
	private Integer numero; // ordine_numero
	private Utente utenteCompilatore;

	private Stato stato; // vedi stato_id

	private Date dataEmissione; // data_emissione
	private Date dataConferma; // data_conferma
	private Date dataAutorizzazione; // data_autorizzazione
	private Date dataScadenza; // data_scadenza
	private Date dataAnnullamento;

	private StatoNso statoNso;

	private String descrizione; // descrizione_acquisto ???
	private String note; // note

	private TipoOrdine tipoOrdine; // tipo_ordine_id - cpass_d_ord_tipo_ordine

	private Integer lottoAnno; // lotto_anno
	private Integer lottoNumero; // lotto_numero

	private Fornitore fornitore; // fornitore_id - vedi cpass_t_fornitore

	// oggetto, altra tabella?
	private Provvedimento provvedimento; // provvedimento_anno, provvedimento_numero

	private TipoProcedura tipoProcedura; // tipo_procedura_id - cpass_d_ord_tipo_procedura
	private String numeroProcedura;

	private String consegnaRiferimento; // consegna_riferimento
	private Date consegnaDataDa; // consegna_data_da
	private Date consegnaDataA; // consegna_data_a
	private String consegnaIndirizzo; // consegna_indirizzo
	private String consegnaCap; // consegna_cap
	private String consegnaLocalita; // consegna_localita
	private BigDecimal totaleConIva;
	private BigDecimal totaleNoIva;

	private List<Destinatario> listDestinatario = new ArrayList<>();

	private List<Impegno> listImpegno = new ArrayList<>();

	/** Default constructor */
	public TestataOrdine() {
	}

	/**
	 * Constructor
	 *
	 * @param id the id
	 */
	public TestataOrdine(UUID id) {
		super(id);
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
	 * @return the anno
	 */
	public Integer getAnno() {
		return anno;
	}

	/**
	 * @param anno the anno to set
	 */
	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
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
	 * @return the dataEmissione
	 */
	public Date getDataEmissione() {
		return dataEmissione;
	}

	/**
	 * @param dataEmissione the dataEmissione to set
	 */
	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
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
	 * @return the dataScadenza
	 */
	public Date getDataScadenza() {
		return dataScadenza;
	}

	/**
	 * @param dataScadenza the dataScadenza to set
	 */
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**
	 * @return the statoNso
	 */
	public StatoNso getStatoNso() {
		return statoNso;
	}

	/**
	 * @param statoNso the statoNso to set
	 */
	public void setStatoNso(StatoNso statoNso) {
		this.statoNso = statoNso;
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
	 * @return the tipoOrdine
	 */
	public TipoOrdine getTipoOrdine() {
		return tipoOrdine;
	}

	/**
	 * @param tipoOrdine the tipoOrdine to set
	 */
	public void setTipoOrdine(TipoOrdine tipoOrdine) {
		this.tipoOrdine = tipoOrdine;
	}

	/**
	 * @return the lottoAnno
	 */
	public Integer getLottoAnno() {
		return lottoAnno;
	}

	/**
	 * @param lottoAnno the lottoAnno to set
	 */
	public void setLottoAnno(Integer lottoAnno) {
		this.lottoAnno = lottoAnno;
	}

	/**
	 * @return the lottoNumero
	 */
	public Integer getLottoNumero() {
		return lottoNumero;
	}

	/**
	 * @param lottoNumero the lottoNumero to set
	 */
	public void setLottoNumero(Integer lottoNumero) {
		this.lottoNumero = lottoNumero;
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
	 * @return the provvedimento
	 */
	public Provvedimento getProvvedimento() {
		return provvedimento;
	}

	/**
	 * @param provvedimento the provvedimento to set
	 */
	public void setProvvedimento(Provvedimento provvedimento) {
		this.provvedimento = provvedimento;
	}

	/**
	 * @return the tipoProcedura
	 */
	public TipoProcedura getTipoProcedura() {
		return tipoProcedura;
	}

	/**
	 * @param tipoProcedura the tipoProcedura to set
	 */
	public void setTipoProcedura(TipoProcedura tipoProcedura) {
		this.tipoProcedura = tipoProcedura;
	}

	/**
	 * @return the consegnaRiferimento
	 */
	public String getConsegnaRiferimento() {
		return consegnaRiferimento;
	}

	/**
	 * @param consegnaRiferimento the consegnaRiferimento to set
	 */
	public void setConsegnaRiferimento(String consegnaRiferimento) {
		this.consegnaRiferimento = consegnaRiferimento;
	}

	/**
	 * @return the consegnaDataDa
	 */
	public Date getConsegnaDataDa() {
		return consegnaDataDa;
	}

	/**
	 * @param consegnaDataDa the consegnaDataDa to set
	 */
	public void setConsegnaDataDa(Date consegnaDataDa) {
		this.consegnaDataDa = consegnaDataDa;
	}

	/**
	 * @return the consegnaDataA
	 */
	public Date getConsegnaDataA() {
		return consegnaDataA;
	}

	/**
	 * @param consegnaDataA the consegnaDataA to set
	 */
	public void setConsegnaDataA(Date consegnaDataA) {
		this.consegnaDataA = consegnaDataA;
	}

	/**
	 * @return the consegnaIndirizzo
	 */
	public String getConsegnaIndirizzo() {
		return consegnaIndirizzo;
	}

	/**
	 * @param consegnaIndirizzo the consegnaIndirizzo to set
	 */
	public void setConsegnaIndirizzo(String consegnaIndirizzo) {
		this.consegnaIndirizzo = consegnaIndirizzo;
	}

	/**
	 * @return the consegnaCap
	 */
	public String getConsegnaCap() {
		return consegnaCap;
	}

	/**
	 * @param consegnaCap the consegnaCap to set
	 */
	public void setConsegnaCap(String consegnaCap) {
		this.consegnaCap = consegnaCap;
	}

	/**
	 * @return the consegnaLocalita
	 */
	public String getConsegnaLocalita() {
		return consegnaLocalita;
	}

	/**
	 * @param consegnaLocalita the consegnaLocalita to set
	 */
	public void setConsegnaLocalita(String consegnaLocalita) {
		this.consegnaLocalita = consegnaLocalita;
	}

	/**
	 * @return the numeroProcedura
	 */
	public String getNumeroProcedura() {
		return numeroProcedura;
	}

	/**
	 * @param numeroProcedura the numeroProcedura to set
	 */
	public void setNumeroProcedura(String numeroProcedura) {
		this.numeroProcedura = numeroProcedura;
	}

	/**
	 * @return the utenteCompilatore
	 */
	public Utente getUtenteCompilatore() {
		return utenteCompilatore;
	}

	/**
	 * @param utenteCompilatore the utenteCompilatore to set
	 */
	public void setUtenteCompilatore(Utente utenteCompilatore) {
		this.utenteCompilatore = utenteCompilatore;
	}

	/**
	 * @return the listImpegno
	 */
	public List<Impegno> getListImpegno() {
		return listImpegno;
	}

	/**
	 * @param listImpegno the listImpegno to set
	 */
	public void setListImpegno(List<Impegno> listImpegno) {
		this.listImpegno = listImpegno;
	}

	/**
	 * @return the listDestinatario
	 */
	public List<Destinatario> getListDestinatario() {
		return listDestinatario;
	}

	/**
	 * @param listDestinatario the listDestinatario to set
	 */
	public void setListDestinatario(List<Destinatario> listDestinatario) {
		this.listDestinatario = listDestinatario;
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
	 * @return the totaleNoIva
	 */
	public BigDecimal getTotaleNoIva() {
		return totaleNoIva;
	}

	/**
	 * @param totaleNoIva the totaleNoIva to set
	 */
	public void setTotaleNoIva(BigDecimal totaleNoIva) {
		this.totaleNoIva = totaleNoIva;
	}

	/**
	 * @return the dataAnnullamento
	 */
	public Date getDataAnnullamento() {
		return dataAnnullamento;
	}

	/**
	 * @param dataAnnullamento the dataAnnullamento to set
	 */
	public void setDataAnnullamento(Date dataAnnullamento) {
		this.dataAnnullamento = dataAnnullamento;
	}
	
}
