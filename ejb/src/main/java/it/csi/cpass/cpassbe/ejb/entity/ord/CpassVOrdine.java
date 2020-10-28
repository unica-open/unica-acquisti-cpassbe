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
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;

/**
 * Entity for VIEW cpass_v_ordine
 */
@Entity
@Table(name="cpass_v_ordine")
@NamedQuery(name="CpassVOrdine.findAll", query="SELECT c FROM CpassVOrdine c")
public class CpassVOrdine implements Serializable, BaseEntity<Long> {	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ordine_id")
	private Long ordineId;
	
	@Column(name="aliquote_iva_id")
	private Integer aliquoteIvaId;
	
	private String cap;
	
	@Column(name="consegna_cap")
	private String consegnaCap;
	
	@Column(name="consegna_data_a")
	private Date consegnaDataA;
	
	@Column(name="consegna_data_da")
	private Date consegnaDataDa;
	
	@Column(name="consegna_indirizzo")
	private String consegnaIndirizzo;
	
	@Column(name="consegna_localita")
	private String consegnaLocalita;
	
	@Column(name="consegna_parziale")
	private Boolean consegnaParziale;
	
	@Column(name="consegna_riferimento")
	private String consegnaRiferimento;
	
	private String contatto;
	
	@Column(name="data_autorizzazione")
	private Date dataAutorizzazione;
	
	@Column(name="data_cancellazione_destinatario")
	private Date dataCancellazioneDestinatario;
	
	@Column(name="data_cancellazione_impegno")
	private Date dataCancellazioneImpegno;
	
	@Column(name="data_cancellazione_riga")
	private Date dataCancellazioneRiga;
	
	@Column(name="data_cancellazione_subimpegno")
	private Date dataCancellazioneSubimpegno;
	
	@Column(name="data_cancellazione_testata")
	private Date dataCancellazioneTestata;
	
	@Column(name="data_conferma")
	private Date dataConferma;
	
	@Column(name="data_emissione")
	private Date dataEmissione;
	
	@Column(name="data_invio_nso")
	private Date dataInvioNso;
	
	@Column(name="descrizione_acquisto")
	private String descrizioneAcquisto;
	
	@Column(name="destinatario_id")
	private UUID destinatarioId;
	
	private String email;
	
	@Column(name="fornitore_id")
	private UUID fornitoreId;
		
	@Column(name="impegno_anno")
	private Integer impegnoAnno;
	
	@Column(name="impegno_anno_esercizio")
	private Integer impegnoAnnoEsercizio;
	
	@Column(name="impegno_ordine_id")
	private UUID impegnoOrdineId;
	
	@Column(name="impegno_id")
	private UUID impegnoId;
	
	@Column(name="impegno_numero")
	private Integer impegnoNumero;
		
	@Column(name="impegno_progressivo")
	private Integer impegnoProgressivo;
	
	@Column(name="importo_impegno")
	private BigDecimal importoImpegno;
	
	@Column(name="importo_iva")
	private BigDecimal importoIva;
	
	@Column(name="importo_netto")
	private BigDecimal importoNetto;
	
	@Column(name="importo_sconto")
	private BigDecimal importoSconto;
	
	@Column(name="importo_sconto2")
	private BigDecimal importoSconto2;
	
	@Column(name="importo_totale")
	private BigDecimal importoTotale;
	
	private String indirizzo;
	
	private String localita;
	
	@Column(name="lotto_anno")
	private Integer lottoAnno;
	
	@Column(name="lotto_numero")
	private Integer lottoNumero;
	
	private String note;
	
	@Column(name="note_riga")
	private String noteRiga;
	
	@Column(name="num_civico")
	private String numCivico;
	
	@Column(name="numero_articolo")
	private Integer numeroArticolo;
	
	@Column(name="numero_capitolo")
	private Integer numeroCapitolo;
	
	@Column(name="numero_procedura")
	private String numeroProcedura;
	
	@Column(name="oggetti_spesa_id")
	private Integer oggettiSpesaId;
	
	@Column(name="ordine_anno")
	private Integer ordineAnno;
	
	@Column(name="ordine_numero")
	private Integer ordineNumero;
	
	@Column(name="percentuale_sconto")
	private BigDecimal percentualeSconto;
	
	@Column(name="percentuale_sconto2")
	private BigDecimal percentualeSconto2;
	
	@Column(name="prezzo_unitario")
	private BigDecimal prezzoUnitario;
	
	@Column(name="progressivo_destinatario")
	private Integer progressivoDestinatario;
	
	@Column(name="progressivo_riga")
	private Integer progressivoRiga;
	
	private String provincia;
	
	@Column(name="provvedimento_anno")
	private Integer provvedimentoAnno;
	
	@Column(name="provvedimento_numero")
	private String provvedimentoNumero;
	
	private BigDecimal quantita;
	
	@Column(name="riga_ordine_id")
	private UUID rigaOrdineId;
	
	@Column(name="settore_destinatario_id")
	private UUID settoreDestinatarioId;
	
	@Column(name="settore_emittente_id")
	private UUID settoreEmittenteId;
	
	@Column(name="stato_el_ordine_id_destinatario")
	private Integer statoElOrdineIdDestinatario;
	
	@Column(name="stato_el_ordine_id_riga")
	private Integer statoElOrdineIdRiga;
	
	@Column(name="stato_id")
	private Integer statoId;
	
	@Column(name="stato_invio_nso_destinatario")
	private String statoInvioNsoDestinatario;
	
	@Column(name="stato_invio_nso_testata")
	private String statoInvioNsoTestata;
	
	@Column(name="subimpegno_anno")
	private Integer subimpegnoAnno;
	
	@Column(name="subimpegno_id")
	private UUID subimpegnoId;
	
	@Column(name="subimpegno_importo")
	private BigDecimal subimpegnoImporto;
	
	@Column(name="subimpegno_numero")
	private Integer subimpegnoNumero;
	
	@Column(name="subimpegno_ordine_id")
	private UUID subimpegnoOrdineId;
	
	private String telefono;
	
	@Column(name="testata_ordine_id")
	private UUID testataOrdineId;
	
	@Column(name="tipo_ordine_id")
	private Integer tipoOrdineId;
	
	@Column(name="tipo_procedura_id")
	private Integer tipoProceduraId;
	
	@Column(name="totale_con_iva")
	private BigDecimal totaleConIva;
	
	@Column(name="totale_no_iva")
	private BigDecimal totaleNoIva;
	
	@Column(name="ufficio_id")
	private Integer ufficioId;
	
	@Column(name="unita_misura_id")
	private Integer unitaMisuraId;
	
	@Column(name="utente_compilatore_id")
	private UUID utenteCompilatoreId;
	
	
	
	
	
		/**
	 * @return the ordineId
	 */
	public Long getOrdineId() {
		return ordineId;
	}
	
	/**
	 * @param ordineId the ordineId to set
	 */
	public void setOrdineId(Long ordineId) {
		this.ordineId = ordineId;
	}
	
	/**
	 * @return the aliquoteIvaId
	 */
	public Integer getAliquoteIvaId() {
		return aliquoteIvaId;
	}
	
	/**
	 * @param aliquoteIvaId the aliquoteIvaId to set
	 */
	public void setAliquoteIvaId(Integer aliquoteIvaId) {
		this.aliquoteIvaId = aliquoteIvaId;
	}
	
	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}
	
	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
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
	 * @return the consegnaParziale
	 */
	public Boolean getConsegnaParziale() {
		return consegnaParziale;
	}
	
	/**
	 * @param consegnaParziale the consegnaParziale to set
	 */
	public void setConsegnaParziale(Boolean consegnaParziale) {
		this.consegnaParziale = consegnaParziale;
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
	 * @return the contatto
	 */
	public String getContatto() {
		return contatto;
	}
	
	/**
	 * @param contatto the contatto to set
	 */
	public void setContatto(String contatto) {
		this.contatto = contatto;
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
	 * @return the dataCancellazioneDestinatario
	 */
	public Date getDataCancellazioneDestinatario() {
		return dataCancellazioneDestinatario;
	}
	
	/**
	 * @param dataCancellazioneDestinatario the dataCancellazioneDestinatario to set
	 */
	public void setDataCancellazioneDestinatario(Date dataCancellazioneDestinatario) {
		this.dataCancellazioneDestinatario = dataCancellazioneDestinatario;
	}
	
	/**
	 * @return the dataCancellazioneImpegno
	 */
	public Date getDataCancellazioneImpegno() {
		return dataCancellazioneImpegno;
	}
	
	/**
	 * @param dataCancellazioneImpegno the dataCancellazioneImpegno to set
	 */
	public void setDataCancellazioneImpegno(Date dataCancellazioneImpegno) {
		this.dataCancellazioneImpegno = dataCancellazioneImpegno;
	}
	
	/**
	 * @return the dataCancellazioneRiga
	 */
	public Date getDataCancellazioneRiga() {
		return dataCancellazioneRiga;
	}
	
	/**
	 * @param dataCancellazioneRiga the dataCancellazioneRiga to set
	 */
	public void setDataCancellazioneRiga(Date dataCancellazioneRiga) {
		this.dataCancellazioneRiga = dataCancellazioneRiga;
	}
	
	/**
	 * @return the dataCancellazioneSubimpegno
	 */
	public Date getDataCancellazioneSubimpegno() {
		return dataCancellazioneSubimpegno;
	}
	
	/**
	 * @param dataCancellazioneSubimpegno the dataCancellazioneSubimpegno to set
	 */
	public void setDataCancellazioneSubimpegno(Date dataCancellazioneSubimpegno) {
		this.dataCancellazioneSubimpegno = dataCancellazioneSubimpegno;
	}
	
	/**
	 * @return the dataCancellazioneTestata
	 */
	public Date getDataCancellazioneTestata() {
		return dataCancellazioneTestata;
	}
	
	/**
	 * @param dataCancellazioneTestata the dataCancellazioneTestata to set
	 */
	public void setDataCancellazioneTestata(Date dataCancellazioneTestata) {
		this.dataCancellazioneTestata = dataCancellazioneTestata;
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
	 * @return the dataInvioNso
	 */
	public Date getDataInvioNso() {
		return dataInvioNso;
	}
	
	/**
	 * @param dataInvioNso the dataInvioNso to set
	 */
	public void setDataInvioNso(Date dataInvioNso) {
		this.dataInvioNso = dataInvioNso;
	}
	
	/**
	 * @return the descrizioneAcquisto
	 */
	public String getDescrizioneAcquisto() {
		return descrizioneAcquisto;
	}
	
	/**
	 * @param descrizioneAcquisto the descrizioneAcquisto to set
	 */
	public void setDescrizioneAcquisto(String descrizioneAcquisto) {
		this.descrizioneAcquisto = descrizioneAcquisto;
	}
	
	/**
	 * @return the destinatarioId
	 */
	public UUID getDestinatarioId() {
		return destinatarioId;
	}
	
	/**
	 * @param destinatarioId the destinatarioId to set
	 */
	public void setDestinatarioId(UUID destinatarioId) {
		this.destinatarioId = destinatarioId;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the fornitoreId
	 */
	public UUID getFornitoreId() {
		return fornitoreId;
	}
	
	/**
	 * @param fornitoreId the fornitoreId to set
	 */
	public void setFornitoreId(UUID fornitoreId) {
		this.fornitoreId = fornitoreId;
	}
	
	/**
	 * @return the impegnoAnno
	 */
	public Integer getImpegnoAnno() {
		return impegnoAnno;
	}
	
	/**
	 * @param impegnoAnno the impegnoAnno to set
	 */
	public void setImpegnoAnno(Integer impegnoAnno) {
		this.impegnoAnno = impegnoAnno;
	}
	
	/**
	 * @return the impegnoAnnoEsercizio
	 */
	public Integer getImpegnoAnnoEsercizio() {
		return impegnoAnnoEsercizio;
	}
	
	/**
	 * @param impegnoAnnoEsercizio the impegnoAnnoEsercizio to set
	 */
	public void setImpegnoAnnoEsercizio(Integer impegnoAnnoEsercizio) {
		this.impegnoAnnoEsercizio = impegnoAnnoEsercizio;
	}
	
	/**
	 * @return the impegnoId
	 */
	public UUID getImpegnoId() {
		return impegnoId;
	}
	
	/**
	 * @param impegnoId the impegnoId to set
	 */
	public void setImpegnoId(UUID impegnoId) {
		this.impegnoId = impegnoId;
	}
	
	/**
	 * @return the impegnoNumero
	 */
	public Integer getImpegnoNumero() {
		return impegnoNumero;
	}
	
	/**
	 * @param impegnoNumero the impegnoNumero to set
	 */
	public void setImpegnoNumero(Integer impegnoNumero) {
		this.impegnoNumero = impegnoNumero;
	}
	
	/**
	 * @return the impegnoOrdineId
	 */
	public UUID getImpegnoOrdineId() {
		return impegnoOrdineId;
	}
	
	/**
	 * @param impegnoOrdineId the impegnoOrdineId to set
	 */
	public void setImpegnoOrdineId(UUID impegnoOrdineId) {
		this.impegnoOrdineId = impegnoOrdineId;
	}
	
	/**
	 * @return the impegnoProgressivo
	 */
	public Integer getImpegnoProgressivo() {
		return impegnoProgressivo;
	}
	
	/**
	 * @param impegnoProgressivo the impegnoProgressivo to set
	 */
	public void setImpegnoProgressivo(Integer impegnoProgressivo) {
		this.impegnoProgressivo = impegnoProgressivo;
	}
	
	/**
	 * @return the importoImpegno
	 */
	public BigDecimal getImportoImpegno() {
		return importoImpegno;
	}
	
	/**
	 * @param importoImpegno the importoImpegno to set
	 */
	public void setImportoImpegno(BigDecimal importoImpegno) {
		this.importoImpegno = importoImpegno;
	}
	
	/**
	 * @return the importoIva
	 */
	public BigDecimal getImportoIva() {
		return importoIva;
	}
	
	/**
	 * @param importoIva the importoIva to set
	 */
	public void setImportoIva(BigDecimal importoIva) {
		this.importoIva = importoIva;
	}
	
	/**
	 * @return the importoNetto
	 */
	public BigDecimal getImportoNetto() {
		return importoNetto;
	}
	
	/**
	 * @param importoNetto the importoNetto to set
	 */
	public void setImportoNetto(BigDecimal importoNetto) {
		this.importoNetto = importoNetto;
	}
	
	/**
	 * @return the importoSconto
	 */
	public BigDecimal getImportoSconto() {
		return importoSconto;
	}
	
	/**
	 * @param importoSconto the importoSconto to set
	 */
	public void setImportoSconto(BigDecimal importoSconto) {
		this.importoSconto = importoSconto;
	}
	
	/**
	 * @return the importoSconto2
	 */
	public BigDecimal getImportoSconto2() {
		return importoSconto2;
	}
	
	/**
	 * @param importoSconto2 the importoSconto2 to set
	 */
	public void setImportoSconto2(BigDecimal importoSconto2) {
		this.importoSconto2 = importoSconto2;
	}
	
	/**
	 * @return the importoTotale
	 */
	public BigDecimal getImportoTotale() {
		return importoTotale;
	}
	
	/**
	 * @param importoTotale the importoTotale to set
	 */
	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}
	
	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	/**
	 * @return the localita
	 */
	public String getLocalita() {
		return localita;
	}
	
	/**
	 * @param localita the localita to set
	 */
	public void setLocalita(String localita) {
		this.localita = localita;
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
	 * @return the noteRiga
	 */
	public String getNoteRiga() {
		return noteRiga;
	}
	
	/**
	 * @param noteRiga the noteRiga to set
	 */
	public void setNoteRiga(String noteRiga) {
		this.noteRiga = noteRiga;
	}
	
	/**
	 * @return the numCivico
	 */
	public String getNumCivico() {
		return numCivico;
	}
	
	/**
	 * @param numCivico the numCivico to set
	 */
	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}
	
	/**
	 * @return the numeroArticolo
	 */
	public Integer getNumeroArticolo() {
		return numeroArticolo;
	}
	
	/**
	 * @param numeroArticolo the numeroArticolo to set
	 */
	public void setNumeroArticolo(Integer numeroArticolo) {
		this.numeroArticolo = numeroArticolo;
	}
	
	/**
	 * @return the numeroCapitolo
	 */
	public Integer getNumeroCapitolo() {
		return numeroCapitolo;
	}
	
	/**
	 * @param numeroCapitolo the numeroCapitolo to set
	 */
	public void setNumeroCapitolo(Integer numeroCapitolo) {
		this.numeroCapitolo = numeroCapitolo;
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
	 * @return the oggettiSpesaId
	 */
	public Integer getOggettiSpesaId() {
		return oggettiSpesaId;
	}
	
	/**
	 * @param oggettiSpesaId the oggettiSpesaId to set
	 */
	public void setOggettiSpesaId(Integer oggettiSpesaId) {
		this.oggettiSpesaId = oggettiSpesaId;
	}
	
	/**
	 * @return the ordineAnno
	 */
	public Integer getOrdineAnno() {
		return ordineAnno;
	}
	
	/**
	 * @param ordineAnno the ordineAnno to set
	 */
	public void setOrdineAnno(Integer ordineAnno) {
		this.ordineAnno = ordineAnno;
	}
	
	/**
	 * @return the ordineNumero
	 */
	public Integer getOrdineNumero() {
		return ordineNumero;
	}
	
	/**
	 * @param ordineNumero the ordineNumero to set
	 */
	public void setOrdineNumero(Integer ordineNumero) {
		this.ordineNumero = ordineNumero;
	}
	
	/**
	 * @return the percentualeSconto
	 */
	public BigDecimal getPercentualeSconto() {
		return percentualeSconto;
	}
	
	/**
	 * @param percentualeSconto the percentualeSconto to set
	 */
	public void setPercentualeSconto(BigDecimal percentualeSconto) {
		this.percentualeSconto = percentualeSconto;
	}
	
	/**
	 * @return the percentualeSconto2
	 */
	public BigDecimal getPercentualeSconto2() {
		return percentualeSconto2;
	}
	
	/**
	 * @param percentualeSconto2 the percentualeSconto2 to set
	 */
	public void setPercentualeSconto2(BigDecimal percentualeSconto2) {
		this.percentualeSconto2 = percentualeSconto2;
	}
	
	/**
	 * @return the prezzoUnitario
	 */
	public BigDecimal getPrezzoUnitario() {
		return prezzoUnitario;
	}
	
	/**
	 * @param prezzoUnitario the prezzoUnitario to set
	 */
	public void setPrezzoUnitario(BigDecimal prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	
	/**
	 * @return the progressivoDestinatario
	 */
	public Integer getProgressivoDestinatario() {
		return progressivoDestinatario;
	}
	
	/**
	 * @param progressivoDestinatario the progressivoDestinatario to set
	 */
	public void setProgressivoDestinatario(Integer progressivoDestinatario) {
		this.progressivoDestinatario = progressivoDestinatario;
	}
	
	/**
	 * @return the progressivoRiga
	 */
	public Integer getProgressivoRiga() {
		return progressivoRiga;
	}
	
	/**
	 * @param progressivoRiga the progressivoRiga to set
	 */
	public void setProgressivoRiga(Integer progressivoRiga) {
		this.progressivoRiga = progressivoRiga;
	}
	
	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	/**
	 * @return the provvedimentoAnno
	 */
	public Integer getProvvedimentoAnno() {
		return provvedimentoAnno;
	}
	
	/**
	 * @param provvedimentoAnno the provvedimentoAnno to set
	 */
	public void setProvvedimentoAnno(Integer provvedimentoAnno) {
		this.provvedimentoAnno = provvedimentoAnno;
	}
	
	/**
	 * @return the provvedimentoNumero
	 */
	public String getProvvedimentoNumero() {
		return provvedimentoNumero;
	}
	
	/**
	 * @param provvedimentoNumero the provvedimentoNumero to set
	 */
	public void setProvvedimentoNumero(String provvedimentoNumero) {
		this.provvedimentoNumero = provvedimentoNumero;
	}
	
	/**
	 * @return the quantita
	 */
	public BigDecimal getQuantita() {
		return quantita;
	}
	
	/**
	 * @param quantita the quantita to set
	 */
	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}
	
	/**
	 * @return the rigaOrdineId
	 */
	public UUID getRigaOrdineId() {
		return rigaOrdineId;
	}
	
	/**
	 * @param rigaOrdineId the rigaOrdineId to set
	 */
	public void setRigaOrdineId(UUID rigaOrdineId) {
		this.rigaOrdineId = rigaOrdineId;
	}
	
	/**
	 * @return the settoreDestinatarioId
	 */
	public UUID getSettoreDestinatarioId() {
		return settoreDestinatarioId;
	}
	
	/**
	 * @param settoreDestinatarioId the settoreDestinatarioId to set
	 */
	public void setSettoreDestinatarioId(UUID settoreDestinatarioId) {
		this.settoreDestinatarioId = settoreDestinatarioId;
	}
	
	/**
	 * @return the settoreEmittenteId
	 */
	public UUID getSettoreEmittenteId() {
		return settoreEmittenteId;
	}
	
	/**
	 * @param settoreEmittenteId the settoreEmittenteId to set
	 */
	public void setSettoreEmittenteId(UUID settoreEmittenteId) {
		this.settoreEmittenteId = settoreEmittenteId;
	}
	
	/**
	 * @return the statoElOrdineIdDestinatario
	 */
	public Integer getStatoElOrdineIdDestinatario() {
		return statoElOrdineIdDestinatario;
	}
	
	/**
	 * @param statoElOrdineIdDestinatario the statoElOrdineIdDestinatario to set
	 */
	public void setStatoElOrdineIdDestinatario(Integer statoElOrdineIdDestinatario) {
		this.statoElOrdineIdDestinatario = statoElOrdineIdDestinatario;
	}
	
	/**
	 * @return the statoElOrdineIdRiga
	 */
	public Integer getStatoElOrdineIdRiga() {
		return statoElOrdineIdRiga;
	}
	
	/**
	 * @param statoElOrdineIdRiga the statoElOrdineIdRiga to set
	 */
	public void setStatoElOrdineIdRiga(Integer statoElOrdineIdRiga) {
		this.statoElOrdineIdRiga = statoElOrdineIdRiga;
	}
	
	/**
	 * @return the statoId
	 */
	public Integer getStatoId() {
		return statoId;
	}
	
	/**
	 * @param statoId the statoId to set
	 */
	public void setStatoId(Integer statoId) {
		this.statoId = statoId;
	}
	
	/**
	 * @return the statoInvioNsoDestinatario
	 */
	public String getStatoInvioNsoDestinatario() {
		return statoInvioNsoDestinatario;
	}
	
	/**
	 * @param statoInvioNsoDestinatario the statoInvioNsoDestinatario to set
	 */
	public void setStatoInvioNsoDestinatario(String statoInvioNsoDestinatario) {
		this.statoInvioNsoDestinatario = statoInvioNsoDestinatario;
	}
	
	/**
	 * @return the statoInvioNsoTestata
	 */
	public String getStatoInvioNsoTestata() {
		return statoInvioNsoTestata;
	}
	
	/**
	 * @param statoInvioNsoTestata the statoInvioNsoTestata to set
	 */
	public void setStatoInvioNsoTestata(String statoInvioNsoTestata) {
		this.statoInvioNsoTestata = statoInvioNsoTestata;
	}
	
	/**
	 * @return the subimpegnoAnno
	 */
	public Integer getSubimpegnoAnno() {
		return subimpegnoAnno;
	}
	
	/**
	 * @param subimpegnoAnno the subimpegnoAnno to set
	 */
	public void setSubimpegnoAnno(Integer subimpegnoAnno) {
		this.subimpegnoAnno = subimpegnoAnno;
	}
	
	/**
	 * @return the subimpegnoId
	 */
	public UUID getSubimpegnoId() {
		return subimpegnoId;
	}
	
	/**
	 * @param subimpegnoId the subimpegnoId to set
	 */
	public void setSubimpegnoId(UUID subimpegnoId) {
		this.subimpegnoId = subimpegnoId;
	}
	
	/**
	 * @return the subimpegnoImporto
	 */
	public BigDecimal getSubimpegnoImporto() {
		return subimpegnoImporto;
	}
	
	/**
	 * @param subimpegnoImporto the subimpegnoImporto to set
	 */
	public void setSubimpegnoImporto(BigDecimal subimpegnoImporto) {
		this.subimpegnoImporto = subimpegnoImporto;
	}
	
	/**
	 * @return the subimpegnoNumero
	 */
	public Integer getSubimpegnoNumero() {
		return subimpegnoNumero;
	}
	
	/**
	 * @param subimpegnoNumero the subimpegnoNumero to set
	 */
	public void setSubimpegnoNumero(Integer subimpegnoNumero) {
		this.subimpegnoNumero = subimpegnoNumero;
	}
	
	/**
	 * @return the subimpegnoOrdineId
	 */
	public UUID getSubimpegnoOrdineId() {
		return subimpegnoOrdineId;
	}
	
	/**
	 * @param subimpegnoOrdineId the subimpegnoOrdineId to set
	 */
	public void setSubimpegnoOrdineId(UUID subimpegnoOrdineId) {
		this.subimpegnoOrdineId = subimpegnoOrdineId;
	}
	
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * @return the testataOrdineId
	 */
	public UUID getTestataOrdineId() {
		return testataOrdineId;
	}
	
	/**
	 * @param testataOrdineId the testataOrdineId to set
	 */
	public void setTestataOrdineId(UUID testataOrdineId) {
		this.testataOrdineId = testataOrdineId;
	}
	
	/**
	 * @return the tipoOrdineId
	 */
	public Integer getTipoOrdineId() {
		return tipoOrdineId;
	}
	
	/**
	 * @param tipoOrdineId the tipoOrdineId to set
	 */
	public void setTipoOrdineId(Integer tipoOrdineId) {
		this.tipoOrdineId = tipoOrdineId;
	}
	
	/**
	 * @return the tipoProceduraId
	 */
	public Integer getTipoProceduraId() {
		return tipoProceduraId;
	}
	
	/**
	 * @param tipoProceduraId the tipoProceduraId to set
	 */
	public void setTipoProceduraId(Integer tipoProceduraId) {
		this.tipoProceduraId = tipoProceduraId;
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
	 * @return the ufficioId
	 */
	public Integer getUfficioId() {
		return ufficioId;
	}
	
	/**
	 * @param ufficioId the ufficioId to set
	 */
	public void setUfficioId(Integer ufficioId) {
		this.ufficioId = ufficioId;
	}
	
	/**
	 * @return the unitaMisuraId
	 */
	public Integer getUnitaMisuraId() {
		return unitaMisuraId;
	}
	
	/**
	 * @param unitaMisuraId the unitaMisuraId to set
	 */
	public void setUnitaMisuraId(Integer unitaMisuraId) {
		this.unitaMisuraId = unitaMisuraId;
	}
	
	/**
	 * @return the utenteCompilatoreId
	 */
	public UUID getUtenteCompilatoreId() {
		return utenteCompilatoreId;
	}
	
	/**
	 * @param utenteCompilatoreId the utenteCompilatoreId to set
	 */
	public void setUtenteCompilatoreId(UUID utenteCompilatoreId) {
		this.utenteCompilatoreId = utenteCompilatoreId;
	}

	
	@Override
	public Long getId() {
		return ordineId;
	}

	@Override
	public void setId(Long id) {
		ordineId = id;
	}

	@Override
	public void initId() {
		// Nothing to do
	}
}

