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
package it.csi.cpass.cpassbe.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_flusso_impegni_esterni database table.
 *
 */
@Entity
@Table(name="cpass_t_flusso_impegni_esterni")
@NamedQuery(name="CpassTFlussoImpegniEsterni.findAll", query="SELECT c FROM CpassTFlussoImpegniEsterni c")
public class CpassTFlussoImpegniEsterni implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="cpass_t_flusso_impegni_esterni_flusso_impegni_esterni_id_seq_GENERATOR", sequenceName="cpass_t_flusso_impegni_esterni_flusso_impegni_esterni_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_t_flusso_impegni_esterni_flusso_impegni_esterni_id_seq_GENERATOR")
	@Column(name="FLUSSO_IMPEGNI_ESTERNI_ID")
	private Integer flussoImpegniEsterniId;

	@Column(name="elaborazione_id")
	private Integer elaborazioneId;

	@Column(name="anno_atto_amministrativo")
	private String annoAttoAmministrativo;

	@Column(name="anno_impegno")
	private String annoImpegno;

	private String annoriaccertato;

	@Column(name="bil_anno")
	private String bilAnno;

	@Column(name="cf_estero_soggetto")
	private String cfEsteroSoggetto;

	@Column(name="cf_soggetto")
	private String cfSoggetto;

	private String cig;

	@Column(name="cod_articolo")
	private String codArticolo;

	@Column(name="cod_capitolo")
	private String codCapitolo;

	@Column(name="cod_cdc_atto_amministrativo")
	private String codCdcAttoAmministrativo;

	@Column(name="cod_cdr_atto_amministrativo")
	private String codCdrAttoAmministrativo;

	@Column(name="cod_classe_soggetto")
	private String codClasseSoggetto;

	@Column(name="cod_impegno")
	private String codImpegno;

	@Column(name="cod_soggetto")
	private String codSoggetto;

	@Column(name="cod_stato_impegno")
	private String codStatoImpegno;

	@Column(name="cod_tipo_atto_amministrativo")
	private String codTipoAttoAmministrativo;

	@Column(name="cod_tipo_impegno")
	private String codTipoImpegno;

	@Column(name="cod_ueb")
	private String codUeb;

	private String cup;

	@Column(name="data_elaborazione")
	private String dataElaborazione;

	@Column(name="data_scadenza")
	private String dataScadenza;

	@Column(name="desc_articolo")
	private String descArticolo;

	@Column(name="desc_capitolo")
	private String descCapitolo;

	@Column(name="desc_cdc_atto_amministrativo")
	private String descCdcAttoAmministrativo;

	@Column(name="desc_cdr_atto_amministrativo")
	private String descCdrAttoAmministrativo;

	@Column(name="desc_classe_soggetto")
	private String descClasseSoggetto;

	@Column(name="desc_impegno")
	private String descImpegno;

	@Column(name="desc_soggetto")
	private String descSoggetto;

	@Column(name="desc_stato_impegno")
	private String descStatoImpegno;

	@Column(name="desc_tipo_impegno")
	private String descTipoImpegno;

	@Column(name="ente_codice")
	private String enteCodice;

	private String errore;

	private String esito;

	@Column(name="data_caricamento")
	private Date dataCaricamento;

	@Column(name="id_ente")
	private UUID idEnte;

	@Column(name="importo_attuale")
	private String importoAttuale;

	@Column(name="importo_iniziale")
	private String importoIniziale;

	@Column(name="importo_liquidato")
	private String importoLiquidato;

	@Column(name="importo_utilizzabile")
	private String importoUtilizzabile;

	@Column(name="num_atto_amministrativo")
	private String numAttoAmministrativo;

	@Column(name="num_elaborazione_di_giornata")
	private Integer numElaborazioneDiGiornata;

	@Column(name="num_impegno")
	private String numImpegno;

	private String numriaccertato;

	@Column(name="oggetto_atto_amministrativo")
	private String oggettoAttoAmministrativo;

	@Column(name="p_iva_soggetto")
	private String pIvaSoggetto;

	@Column(name="parere_finanziario")
	private String parereFinanziario;

	public CpassTFlussoImpegniEsterni() {
	}

	public String getAnnoAttoAmministrativo() {
		return this.annoAttoAmministrativo;
	}

	public void setAnnoAttoAmministrativo(String annoAttoAmministrativo) {
		this.annoAttoAmministrativo = annoAttoAmministrativo;
	}

	public String getAnnoImpegno() {
		return this.annoImpegno;
	}

	public void setAnnoImpegno(String annoImpegno) {
		this.annoImpegno = annoImpegno;
	}

	public String getAnnoriaccertato() {
		return this.annoriaccertato;
	}

	public void setAnnoriaccertato(String annoriaccertato) {
		this.annoriaccertato = annoriaccertato;
	}

	public String getBilAnno() {
		return this.bilAnno;
	}

	public void setBilAnno(String bilAnno) {
		this.bilAnno = bilAnno;
	}

	public String getCfEsteroSoggetto() {
		return this.cfEsteroSoggetto;
	}

	public void setCfEsteroSoggetto(String cfEsteroSoggetto) {
		this.cfEsteroSoggetto = cfEsteroSoggetto;
	}

	public String getCfSoggetto() {
		return this.cfSoggetto;
	}

	public void setCfSoggetto(String cfSoggetto) {
		this.cfSoggetto = cfSoggetto;
	}

	public String getCig() {
		return this.cig;
	}

	public void setCig(String cig) {
		this.cig = cig;
	}

	public String getCodArticolo() {
		return this.codArticolo;
	}

	public void setCodArticolo(String codArticolo) {
		this.codArticolo = codArticolo;
	}

	public String getCodCapitolo() {
		return this.codCapitolo;
	}

	public void setCodCapitolo(String codCapitolo) {
		this.codCapitolo = codCapitolo;
	}

	public String getCodCdcAttoAmministrativo() {
		return this.codCdcAttoAmministrativo;
	}

	public void setCodCdcAttoAmministrativo(String codCdcAttoAmministrativo) {
		this.codCdcAttoAmministrativo = codCdcAttoAmministrativo;
	}

	public String getCodCdrAttoAmministrativo() {
		return this.codCdrAttoAmministrativo;
	}

	public void setCodCdrAttoAmministrativo(String codCdrAttoAmministrativo) {
		this.codCdrAttoAmministrativo = codCdrAttoAmministrativo;
	}

	public String getCodClasseSoggetto() {
		return this.codClasseSoggetto;
	}

	public void setCodClasseSoggetto(String codClasseSoggetto) {
		this.codClasseSoggetto = codClasseSoggetto;
	}

	public String getCodImpegno() {
		return this.codImpegno;
	}

	public void setCodImpegno(String codImpegno) {
		this.codImpegno = codImpegno;
	}

	public String getCodSoggetto() {
		return this.codSoggetto;
	}

	public void setCodSoggetto(String codSoggetto) {
		this.codSoggetto = codSoggetto;
	}

	public String getCodStatoImpegno() {
		return this.codStatoImpegno;
	}

	public void setCodStatoImpegno(String codStatoImpegno) {
		this.codStatoImpegno = codStatoImpegno;
	}

	public String getCodTipoAttoAmministrativo() {
		return this.codTipoAttoAmministrativo;
	}

	public void setCodTipoAttoAmministrativo(String codTipoAttoAmministrativo) {
		this.codTipoAttoAmministrativo = codTipoAttoAmministrativo;
	}

	public String getCodTipoImpegno() {
		return this.codTipoImpegno;
	}

	public void setCodTipoImpegno(String codTipoImpegno) {
		this.codTipoImpegno = codTipoImpegno;
	}

	public String getCodUeb() {
		return this.codUeb;
	}

	public void setCodUeb(String codUeb) {
		this.codUeb = codUeb;
	}

	public String getCup() {
		return this.cup;
	}

	public void setCup(String cup) {
		this.cup = cup;
	}

	public String getDataElaborazione() {
		return this.dataElaborazione;
	}

	public void setDataElaborazione(String dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}

	public String getDataScadenza() {
		return this.dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getDescArticolo() {
		return this.descArticolo;
	}

	public void setDescArticolo(String descArticolo) {
		this.descArticolo = descArticolo;
	}

	public String getDescCapitolo() {
		return this.descCapitolo;
	}

	public void setDescCapitolo(String descCapitolo) {
		this.descCapitolo = descCapitolo;
	}

	public String getDescCdcAttoAmministrativo() {
		return this.descCdcAttoAmministrativo;
	}

	public void setDescCdcAttoAmministrativo(String descCdcAttoAmministrativo) {
		this.descCdcAttoAmministrativo = descCdcAttoAmministrativo;
	}

	public String getDescCdrAttoAmministrativo() {
		return this.descCdrAttoAmministrativo;
	}

	public void setDescCdrAttoAmministrativo(String descCdrAttoAmministrativo) {
		this.descCdrAttoAmministrativo = descCdrAttoAmministrativo;
	}

	public String getDescClasseSoggetto() {
		return this.descClasseSoggetto;
	}

	public void setDescClasseSoggetto(String descClasseSoggetto) {
		this.descClasseSoggetto = descClasseSoggetto;
	}

	public String getDescImpegno() {
		return this.descImpegno;
	}

	public void setDescImpegno(String descImpegno) {
		this.descImpegno = descImpegno;
	}

	public String getDescSoggetto() {
		return this.descSoggetto;
	}

	public void setDescSoggetto(String descSoggetto) {
		this.descSoggetto = descSoggetto;
	}

	public String getDescStatoImpegno() {
		return this.descStatoImpegno;
	}

	public void setDescStatoImpegno(String descStatoImpegno) {
		this.descStatoImpegno = descStatoImpegno;
	}

	public String getDescTipoImpegno() {
		return this.descTipoImpegno;
	}

	public void setDescTipoImpegno(String descTipoImpegno) {
		this.descTipoImpegno = descTipoImpegno;
	}

	public String getEnteCodice() {
		return this.enteCodice;
	}

	public void setEnteCodice(String enteCodice) {
		this.enteCodice = enteCodice;
	}

	public String getErrore() {
		return this.errore;
	}

	public void setErrore(String errore) {
		this.errore = errore;
	}

	public String getEsito() {
		return this.esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public UUID getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(UUID idEnte) {
		this.idEnte = idEnte;
	}

	public String getImportoAttuale() {
		return this.importoAttuale;
	}

	public void setImportoAttuale(String importoAttuale) {
		this.importoAttuale = importoAttuale;
	}

	public String getImportoIniziale() {
		return this.importoIniziale;
	}

	public void setImportoIniziale(String importoIniziale) {
		this.importoIniziale = importoIniziale;
	}

	public String getImportoLiquidato() {
		return this.importoLiquidato;
	}

	public void setImportoLiquidato(String importoLiquidato) {
		this.importoLiquidato = importoLiquidato;
	}

	public String getImportoUtilizzabile() {
		return this.importoUtilizzabile;
	}

	public void setImportoUtilizzabile(String importoUtilizzabile) {
		this.importoUtilizzabile = importoUtilizzabile;
	}

	public String getNumAttoAmministrativo() {
		return this.numAttoAmministrativo;
	}

	public void setNumAttoAmministrativo(String numAttoAmministrativo) {
		this.numAttoAmministrativo = numAttoAmministrativo;
	}

	public Integer getNumElaborazioneDiGiornata() {
		return this.numElaborazioneDiGiornata;
	}

	public void setNumElaborazioneDiGiornata(Integer numElaborazioneDiGiornata) {
		this.numElaborazioneDiGiornata = numElaborazioneDiGiornata;
	}

	public String getNumImpegno() {
		return this.numImpegno;
	}

	public void setNumImpegno(String numImpegno) {
		this.numImpegno = numImpegno;
	}

	public String getNumriaccertato() {
		return this.numriaccertato;
	}

	public void setNumriaccertato(String numriaccertato) {
		this.numriaccertato = numriaccertato;
	}

	public String getOggettoAttoAmministrativo() {
		return this.oggettoAttoAmministrativo;
	}

	public void setOggettoAttoAmministrativo(String oggettoAttoAmministrativo) {
		this.oggettoAttoAmministrativo = oggettoAttoAmministrativo;
	}

	public String getParereFinanziario() {
		return this.parereFinanziario;
	}

	public void setParereFinanziario(String parereFinanziario) {
		this.parereFinanziario = parereFinanziario;
	}


	public Integer getElaborazioneId() {
		return elaborazioneId;
	}

	public void setElaborazioneId(Integer elaborazioneId) {
		this.elaborazioneId = elaborazioneId;
	}

	/**
	 * @return the pIvaSoggetto
	 */
	public String getpIvaSoggetto() {
		return pIvaSoggetto;
	}

	/**
	 * @param pIvaSoggetto the pIvaSoggetto to set
	 */
	public void setpIvaSoggetto(String pIvaSoggetto) {
		this.pIvaSoggetto = pIvaSoggetto;
	}

	/**
	 * @return the flussoImpegniEsterniId
	 */
	public Integer getFlussoImpegniEsterniId() {
		return flussoImpegniEsterniId;
	}

	/**
	 * @return the dataCaricamento
	 */
	public Date getDataCaricamento() {
		return dataCaricamento;
	}

	/**
	 * @param dataCaricamento the dataCaricamento to set
	 */
	public void setDataCaricamento(Date dataCaricamento) {
		this.dataCaricamento = dataCaricamento;
	}

	/**
	 * @param flussoImpegniEsterniId the flussoImpegniEsterniId to set
	 */
	public void setFlussoImpegniEsterniId(Integer flussoImpegniEsterniId) {
		this.flussoImpegniEsterniId = flussoImpegniEsterniId;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return flussoImpegniEsterniId;
	}

	@Override
	public void setId(Integer id) {
		flussoImpegniEsterniId = id;
	}


}
