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
package it.csi.cpass.cpassbe.ejb.entity.view;

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
 * Entity for VIEW cpass_v_evasione
 */
@Entity
@Table(name="cpass_v_evasione")
@NamedQuery(name="CpassVEvasione.findAll", query="SELECT c FROM CpassVEvasione c")
public class CpassVEvasione implements Serializable, BaseEntity<Long> {	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="evasione_id")
	private Long evasioneId;
	
	@Column(name="aliquote_iva_id")
	private Integer aliquoteIvaId;

	private String cap;

	@Column(name="causale_sospensione_id")
	private Integer causaleSospensioneId;

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

	@Column(name="data_consegna")
	private Date dataConsegna;

	@Column(name="data_creazione")
	private Date dataCreazione;

	@Column(name="data_inserimento")
	private Date dataInserimento;

	@Column(name="data_invio_contabilita")
	private Date dataInvioContabilita;

	@Column(name="data_ripartizione")
	private Date dataRipartizione;

	@Column(name="data_sospensione")
	private Date dataSospensione;

	private String descrizione;

	@Column(name="destinatario_evasione_id")
	private UUID destinatarioEvasioneId;

	@Column(name="destinatario_id")
	private UUID destinatarioId;

	@Column(name="documento_consegna")
	private String documentoConsegna;

	@Column(name="documento_data_consegna")
	private Date documentoDataConsegna;

	@Column(name="documento_trasporto_id")
	private Integer documentoTrasportoId;

	@Column(name="documento_trasporto_riga_id")
	private Integer documentoTrasportoRigaId;

	private String email;

	@Column(name="evasione_anno")
	private Integer evasioneAnno;

	@Column(name="evasione_numero")
	private Integer evasioneNumero;

	@Column(name="fattura_anno")
	private Integer fatturaAnno;

	@Column(name="fattura_codice")
	private String fatturaCodice;

	@Column(name="fattura_numero")
	private String fatturaNumero;

	@Column(name="fattura_protocollo_anno")
	private Integer fatturaProtocolloAnno;

	@Column(name="fattura_protocollo_numero")
	private Integer fatturaProtocolloNumero;

	@Column(name="fattura_tipo")
	private String fatturaTipo;

	@Column(name="fattura_totale")
	private BigDecimal fatturaTotale;

	@Column(name="fattura_totale_liquidabile")
	private BigDecimal fatturaTotaleLiquidabile;

	@Column(name="fornitore_id")
	private UUID fornitoreId;

	@Column(name="impegno_anno")
	private Integer impegnoAnno;

	@Column(name="impegno_anno_esercizio")
	private Integer impegnoAnnoEsercizio;

	@Column(name="impegno_evasione_id")
	private UUID impegnoEvasioneId;

	@Column(name="impegno_id")
	private UUID impegnoId;

	@Column(name="impegno_numero")
	private Integer impegnoNumero;

	@Column(name="impegno_ordine_id")
	private UUID impegnoOrdineId;

	@Column(name="impegno_progressivo")
	private Integer impegnoProgressivo;

	@Column(name="importo_liquidato")
	private BigDecimal importoLiquidato;

	@Column(name="importo_ripartito")
	private BigDecimal importoRipartito;

	@Column(name="importo_sospeso")
	private BigDecimal importoSospeso;

	@Column(name="importo_totale")
	private BigDecimal importoTotale;

	private String indirizzo;

	@Column(name="listino_fornitore_id")
	private Integer listinoFornitoreId;

	private String localita;

	private String note;

	@Column(name="num_civico")
	private String numCivico;

	@Column(name="numero_articolo")
	private Integer numeroArticolo;

	@Column(name="numero_capitolo")
	private Integer numeroCapitolo;

	@Column(name="oggetti_spesa_id")
	private Integer oggettiSpesaId;

	@Column(name="prezzo_unitario")
	private BigDecimal prezzoUnitario;

	@Column(name="progressivo_destinatario")
	private Integer progressivoDestinatario;

	@Column(name="progressivo_riga")
	private Integer progressivoRiga;

	private String provincia;

	@Column(name="riga_evasione_id")
	private UUID rigaEvasioneId;

	@Column(name="riga_ordine_id")
	private UUID rigaOrdineId;

	@Column(name="settore_competente_id")
	private UUID settoreCompetenteId;

	@Column(name="settore_destinatario_id")
	private UUID settoreDestinatarioId;

	@Column(name="sospensione_causale")
	private String sospensioneCausale;

	private Boolean sospeso;

	@Column(name="stato_el_ordine_id")
	private Integer statoElOrdineId;

	@Column(name="stato_el_ordine_id_destinatario")
	private Integer statoElOrdineIdDestinatario;

	@Column(name="stato_id")
	private Integer statoId;

	@Column(name="subimpegno_anno")
	private Integer subimpegnoAnno;

	@Column(name="subimpegno_evasione_id")
	private UUID subimpegnoEvasioneId;

	@Column(name="subimpegno_id")
	private UUID subimpegnoId;

	@Column(name="sub_importo_ripartito")
	private BigDecimal subImportoRipartito;

	@Column(name="sub_importo_sospeso")
	private BigDecimal subImportoSospeso;

	@Column(name="sub_importo_liquidato")
	private BigDecimal subImportoLiquidato;

	@Column(name="subimpegno_numero")
	private Integer subimpegnoNumero;

	@Column(name="subimpegno_ordine_id")
	private UUID subimpegnoOrdineId;

	private String telefono;

	@Column(name="testata_evasione_id")
	private UUID testataEvasioneId;

	@Column(name="tipo_evasione_id")
	private Integer tipoEvasioneId;

	@Column(name="totale_con_iva")
	private BigDecimal totaleConIva;

	@Column(name="ufficio_id")
	private Integer ufficioId;

	@Column(name="utente_compilatore_id")
	private UUID utenteCompilatoreId;

	@Column(name="causale_sospensione_codice")
	private String causaleSospensioneCodice;
	
	@Column(name="causale_sospensione_descrizione")
	private String causaleSospensioneDescrizione;

	public CpassVEvasione() {
	}

	public Integer getAliquoteIvaId() {
		return this.aliquoteIvaId;
	}

	public void setAliquoteIvaId(Integer aliquoteIvaId) {
		this.aliquoteIvaId = aliquoteIvaId;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public Integer getCausaleSospensioneId() {
		return this.causaleSospensioneId;
	}

	public void setCausaleSospensioneId(Integer causaleSospensioneId) {
		this.causaleSospensioneId = causaleSospensioneId;
	}

	public String getContatto() {
		return this.contatto;
	}

	public void setContatto(String contatto) {
		this.contatto = contatto;
	}

	public Date getDataAutorizzazione() {
		return this.dataAutorizzazione;
	}

	public void setDataAutorizzazione(Date dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}

	public Date getDataCancellazioneDestinatario() {
		return this.dataCancellazioneDestinatario;
	}

	public void setDataCancellazioneDestinatario(Date dataCancellazioneDestinatario) {
		this.dataCancellazioneDestinatario = dataCancellazioneDestinatario;
	}

	public Date getDataCancellazioneImpegno() {
		return this.dataCancellazioneImpegno;
	}

	public void setDataCancellazioneImpegno(Date dataCancellazioneImpegno) {
		this.dataCancellazioneImpegno = dataCancellazioneImpegno;
	}

	public Date getDataCancellazioneRiga() {
		return this.dataCancellazioneRiga;
	}

	public void setDataCancellazioneRiga(Date dataCancellazioneRiga) {
		this.dataCancellazioneRiga = dataCancellazioneRiga;
	}

	public Date getDataCancellazioneSubimpegno() {
		return this.dataCancellazioneSubimpegno;
	}

	public void setDataCancellazioneSubimpegno(Date dataCancellazioneSubimpegno) {
		this.dataCancellazioneSubimpegno = dataCancellazioneSubimpegno;
	}

	public Date getDataCancellazioneTestata() {
		return this.dataCancellazioneTestata;
	}

	public void setDataCancellazioneTestata(Date dataCancellazioneTestata) {
		this.dataCancellazioneTestata = dataCancellazioneTestata;
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

	public Date getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataInserimento() {
		return this.dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataInvioContabilita() {
		return this.dataInvioContabilita;
	}

	public void setDataInvioContabilita(Date dataInvioContabilita) {
		this.dataInvioContabilita = dataInvioContabilita;
	}

	public Date getDataRipartizione() {
		return this.dataRipartizione;
	}

	public void setDataRipartizione(Date dataRipartizione) {
		this.dataRipartizione = dataRipartizione;
	}

	public Date getDataSospensione() {
		return this.dataSospensione;
	}

	public void setDataSospensione(Date dataSospensione) {
		this.dataSospensione = dataSospensione;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public UUID getDestinatarioEvasioneId() {
		return this.destinatarioEvasioneId;
	}

	public void setDestinatarioEvasioneId(UUID destinatarioEvasioneId) {
		this.destinatarioEvasioneId = destinatarioEvasioneId;
	}

	public UUID getDestinatarioId() {
		return this.destinatarioId;
	}

	public void setDestinatarioId(UUID destinatarioId) {
		this.destinatarioId = destinatarioId;
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

	public Integer getDocumentoTrasportoId() {
		return this.documentoTrasportoId;
	}

	public void setDocumentoTrasportoId(Integer documentoTrasportoId) {
		this.documentoTrasportoId = documentoTrasportoId;
	}

	public Integer getDocumentoTrasportoRigaId() {
		return this.documentoTrasportoRigaId;
	}

	public void setDocumentoTrasportoRigaId(Integer documentoTrasportoRigaId) {
		this.documentoTrasportoRigaId = documentoTrasportoRigaId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEvasioneAnno() {
		return this.evasioneAnno;
	}

	public void setEvasioneAnno(Integer evasioneAnno) {
		this.evasioneAnno = evasioneAnno;
	}

	public Long getEvasioneId() {
		return this.evasioneId;
	}

	public void setEvasioneId(Long evasioneId) {
		this.evasioneId = evasioneId;
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

	public String getFatturaCodice() {
		return this.fatturaCodice;
	}

	public void setFatturaCodice(String fatturaCodice) {
		this.fatturaCodice = fatturaCodice;
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

	public UUID getFornitoreId() {
		return this.fornitoreId;
	}

	public void setFornitoreId(UUID fornitoreId) {
		this.fornitoreId = fornitoreId;
	}

	public Integer getImpegnoAnno() {
		return this.impegnoAnno;
	}

	public void setImpegnoAnno(Integer impegnoAnno) {
		this.impegnoAnno = impegnoAnno;
	}

	public Integer getImpegnoAnnoEsercizio() {
		return this.impegnoAnnoEsercizio;
	}

	public void setImpegnoAnnoEsercizio(Integer impegnoAnnoEsercizio) {
		this.impegnoAnnoEsercizio = impegnoAnnoEsercizio;
	}

	public UUID getImpegnoEvasioneId() {
		return this.impegnoEvasioneId;
	}

	public void setImpegnoEvasioneId(UUID impegnoEvasioneId) {
		this.impegnoEvasioneId = impegnoEvasioneId;
	}

	public UUID getImpegnoId() {
		return this.impegnoId;
	}

	public void setImpegnoId(UUID impegnoId) {
		this.impegnoId = impegnoId;
	}

	public Integer getImpegnoNumero() {
		return this.impegnoNumero;
	}

	public void setImpegnoNumero(Integer impegnoNumero) {
		this.impegnoNumero = impegnoNumero;
	}

	public UUID getImpegnoOrdineId() {
		return this.impegnoOrdineId;
	}

	public void setImpegnoOrdineId(UUID impegnoOrdineId) {
		this.impegnoOrdineId = impegnoOrdineId;
	}

	public Integer getImpegnoProgressivo() {
		return this.impegnoProgressivo;
	}

	public void setImpegnoProgressivo(Integer impegnoProgressivo) {
		this.impegnoProgressivo = impegnoProgressivo;
	}

	public BigDecimal getImportoLiquidato() {
		return this.importoLiquidato;
	}

	public void setImportoLiquidato(BigDecimal importoLiquidato) {
		this.importoLiquidato = importoLiquidato;
	}

	public BigDecimal getImportoRipartito() {
		return this.importoRipartito;
	}

	public void setImportoRipartito(BigDecimal importoRipartito) {
		this.importoRipartito = importoRipartito;
	}

	public BigDecimal getImportoSospeso() {
		return this.importoSospeso;
	}

	public void setImportoSospeso(BigDecimal importoSospeso) {
		this.importoSospeso = importoSospeso;
	}

	public BigDecimal getImportoTotale() {
		return this.importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Integer getListinoFornitoreId() {
		return this.listinoFornitoreId;
	}

	public void setListinoFornitoreId(Integer listinoFornitoreId) {
		this.listinoFornitoreId = listinoFornitoreId;
	}

	public String getLocalita() {
		return this.localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNumCivico() {
		return this.numCivico;
	}

	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}

	public Integer getNumeroArticolo() {
		return this.numeroArticolo;
	}

	public void setNumeroArticolo(Integer numeroArticolo) {
		this.numeroArticolo = numeroArticolo;
	}

	public Integer getNumeroCapitolo() {
		return this.numeroCapitolo;
	}

	public void setNumeroCapitolo(Integer numeroCapitolo) {
		this.numeroCapitolo = numeroCapitolo;
	}

	public Integer getOggettiSpesaId() {
		return this.oggettiSpesaId;
	}

	public void setOggettiSpesaId(Integer oggettiSpesaId) {
		this.oggettiSpesaId = oggettiSpesaId;
	}

	public BigDecimal getPrezzoUnitario() {
		return this.prezzoUnitario;
	}

	public void setPrezzoUnitario(BigDecimal prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public Integer getProgressivoDestinatario() {
		return this.progressivoDestinatario;
	}

	public void setProgressivoDestinatario(Integer progressivoDestinatario) {
		this.progressivoDestinatario = progressivoDestinatario;
	}

	public Integer getProgressivoRiga() {
		return this.progressivoRiga;
	}

	public void setProgressivoRiga(Integer progressivoRiga) {
		this.progressivoRiga = progressivoRiga;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public UUID getRigaEvasioneId() {
		return this.rigaEvasioneId;
	}

	public void setRigaEvasioneId(UUID rigaEvasioneId) {
		this.rigaEvasioneId = rigaEvasioneId;
	}

	public UUID getRigaOrdineId() {
		return this.rigaOrdineId;
	}

	public void setRigaOrdineId(UUID rigaOrdineId) {
		this.rigaOrdineId = rigaOrdineId;
	}

	public UUID getSettoreCompetenteId() {
		return this.settoreCompetenteId;
	}

	public void setSettoreCompetenteId(UUID settoreCompetenteId) {
		this.settoreCompetenteId = settoreCompetenteId;
	}

	public UUID getSettoreDestinatarioId() {
		return this.settoreDestinatarioId;
	}

	public void setSettoreDestinatarioId(UUID settoreDestinatarioId) {
		this.settoreDestinatarioId = settoreDestinatarioId;
	}

	public String getSospensioneCausale() {
		return this.sospensioneCausale;
	}

	public void setSospensioneCausale(String sospensioneCausale) {
		this.sospensioneCausale = sospensioneCausale;
	}

	public Boolean getSospeso() {
		return this.sospeso;
	}

	public void setSospeso(Boolean sospeso) {
		this.sospeso = sospeso;
	}

	public Integer getStatoElOrdineId() {
		return this.statoElOrdineId;
	}

	public void setStatoElOrdineId(Integer statoElOrdineId) {
		this.statoElOrdineId = statoElOrdineId;
	}

	public Integer getStatoElOrdineIdDestinatario() {
		return this.statoElOrdineIdDestinatario;
	}

	public void setStatoElOrdineIdDestinatario(Integer statoElOrdineIdDestinatario) {
		this.statoElOrdineIdDestinatario = statoElOrdineIdDestinatario;
	}

	public Integer getStatoId() {
		return this.statoId;
	}

	public void setStatoId(Integer statoId) {
		this.statoId = statoId;
	}

	public Integer getSubimpegnoAnno() {
		return this.subimpegnoAnno;
	}

	public void setSubimpegnoAnno(Integer subimpegnoAnno) {
		this.subimpegnoAnno = subimpegnoAnno;
	}

	public UUID getSubimpegnoEvasioneId() {
		return this.subimpegnoEvasioneId;
	}

	public void setSubimpegnoEvasioneId(UUID subimpegnoEvasioneId) {
		this.subimpegnoEvasioneId = subimpegnoEvasioneId;
	}

	public UUID getSubimpegnoId() {
		return this.subimpegnoId;
	}

	public void setSubimpegnoId(UUID subimpegnoId) {
		this.subimpegnoId = subimpegnoId;
	}

	public BigDecimal getSubImportoRipartito() {
		return subImportoRipartito;
	}

	public void setSubImportoRipartito(BigDecimal subImportoRipartito) {
		this.subImportoRipartito = subImportoRipartito;
	}

	public BigDecimal getSubImportoSospeso() {
		return subImportoSospeso;
	}

	/**
	 * @param subImportoSospeso the subImportoSospeso to set
	 */
	public void setSubImportoSospeso(BigDecimal subImportoSospeso) {
		this.subImportoSospeso = subImportoSospeso;
	}

	/**
	 * @return the subImportoLiquidato
	 */
	public BigDecimal getSubImportoLiquidato() {
		return subImportoLiquidato;
	}

	/**
	 * @param subImportoLiquidato the subImportoLiquidato to set
	 */
	public void setSubImportoLiquidato(BigDecimal subImportoLiquidato) {
		this.subImportoLiquidato = subImportoLiquidato;
	}

	public Integer getSubimpegnoNumero() {
		return this.subimpegnoNumero;
	}

	public void setSubimpegnoNumero(Integer subimpegnoNumero) {
		this.subimpegnoNumero = subimpegnoNumero;
	}

	public UUID getSubimpegnoOrdineId() {
		return this.subimpegnoOrdineId;
	}

	public void setSubimpegnoOrdineId(UUID subimpegnoOrdineId) {
		this.subimpegnoOrdineId = subimpegnoOrdineId;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public UUID getTestataEvasioneId() {
		return this.testataEvasioneId;
	}

	public void setTestataEvasioneId(UUID testataEvasioneId) {
		this.testataEvasioneId = testataEvasioneId;
	}

	public Integer getTipoEvasioneId() {
		return this.tipoEvasioneId;
	}

	public void setTipoEvasioneId(Integer tipoEvasioneId) {
		this.tipoEvasioneId = tipoEvasioneId;
	}

	public BigDecimal getTotaleConIva() {
		return this.totaleConIva;
	}

	public void setTotaleConIva(BigDecimal totaleConIva) {
		this.totaleConIva = totaleConIva;
	}

	public Integer getUfficioId() {
		return this.ufficioId;
	}

	public void setUfficioId(Integer ufficioId) {
		this.ufficioId = ufficioId;
	}

	public UUID getUtenteCompilatoreId() {
		return this.utenteCompilatoreId;
	}

	public void setUtenteCompilatoreId(UUID utenteCompilatoreId) {
		this.utenteCompilatoreId = utenteCompilatoreId;
	}

	
	/**
	 * @return the causaleSospensioneCodice
	 */
	public String getCausaleSospensioneCodice() {
		return causaleSospensioneCodice;
	}

	/**
	 * @param causaleSospensioneCodice the causaleSospensioneCodice to set
	 */
	public void setCausaleSospensioneCodice(String causaleSospensioneCodice) {
		this.causaleSospensioneCodice = causaleSospensioneCodice;
	}

	/**
	 * @return the causaleSospensioneDescrizione
	 */
	public String getCausaleSospensioneDescrizione() {
		return causaleSospensioneDescrizione;
	}

	/**
	 * @param causaleSospensioneDescrizione the causaleSospensioneDescrizione to set
	 */
	public void setCausaleSospensioneDescrizione(String causaleSospensioneDescrizione) {
		this.causaleSospensioneDescrizione = causaleSospensioneDescrizione;
	}

	@Override
	public Long getId() {
		return evasioneId;
	}

	@Override
	public void setId(Long id) {
		evasioneId = id;
	}

	@Override
	public void initId() {
		// Nothing to do
	}
}

