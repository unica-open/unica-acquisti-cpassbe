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
 * The persistent class for the cpass_flusso_t_subimpegni_esterni database table.
 *
 */
@Entity
@Table(name="cpass_t_flusso_subimpegni_esterni")
@NamedQuery(name="CpassFlussoTSubimpegniEsterni.findAll", query="SELECT c FROM CpassTFlussoSubimpegniEsterni c")
public class CpassTFlussoSubimpegniEsterni implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="cpass_t_flusso_subimpegni_esterni_flusso_subimpegni_esterni_id_seq_GENERATOR", sequenceName="cpass_t_flusso_subimpegni_este_flusso_subimpegni_esterni_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_t_flusso_subimpegni_esterni_flusso_subimpegni_esterni_id_seq_GENERATOR")
	@Column(name="FLUSSO_SUBIMPEGNI_ESTERNI_ID")
	private Integer flussoSubimpegniEsterniId;


	@Column(name="elaborazione_id")
	private Integer elaborazioneId;

	@Column(name="data_caricamento")
	private Date dataCaricamento;

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

	@Column(name="cod_cdc_atto_amministrativo")
	private String codCdcAttoAmministrativo;

	@Column(name="cod_cdr_atto_amministrativo")
	private String codCdrAttoAmministrativo;

	@Column(name="cod_classe_soggetto")
	private String codClasseSoggetto;

	@Column(name="cod_soggetto")
	private String codSoggetto;

	@Column(name="cod_stato_subimpegno")
	private String codStatoSubimpegno;

	@Column(name="cod_subimpegno")
	private String codSubimpegno;

	@Column(name="cod_tipo_atto_amministrativo")
	private String codTipoAttoAmministrativo;

	@Column(name="cod_tipo_impegno")
	private String codTipoImpegno;

	@Column(name="data_elaborazione")
	private String dataElaborazione;

	@Column(name="desc_cdc_atto_amministrativo")
	private String descCdcAttoAmministrativo;

	@Column(name="desc_cdr_atto_amministrativo")
	private String descCdrAttoAmministrativo;

	@Column(name="desc_classe_soggetto")
	private String descClasseSoggetto;

	@Column(name="desc_soggetto")
	private String descSoggetto;

	@Column(name="desc_subimpegno")
	private String descSubimpegno;

	@Column(name="desc_tipo_impegno")
	private String descTipoImpegno;

	@Column(name="ente_codice")
	private String enteCodice;

	private String errore;

	private String esito;

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

	@Column(name="num_impegno")
	private String numImpegno;

	private String numriaccertato;

	@Column(name="oggetto_atto_amministrativo")
	private String oggettoAttoAmministrativo;

	@Column(name="p_iva_soggetto")
	private String pIvaSoggetto;

	@Column(name="num_elaborazione_di_giornata")
	private Integer numElaborazioneDiGiornata;


	public CpassTFlussoSubimpegniEsterni() {
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

	public String getCodSoggetto() {
		return this.codSoggetto;
	}

	public void setCodSoggetto(String codSoggetto) {
		this.codSoggetto = codSoggetto;
	}

	public String getCodStatoSubimpegno() {
		return this.codStatoSubimpegno;
	}

	public void setCodStatoSubimpegno(String codStatoSubimpegno) {
		this.codStatoSubimpegno = codStatoSubimpegno;
	}

	public String getCodSubimpegno() {
		return this.codSubimpegno;
	}

	public void setCodSubimpegno(String codSubimpegno) {
		this.codSubimpegno = codSubimpegno;
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

	public String getDataElaborazione() {
		return this.dataElaborazione;
	}

	public void setDataElaborazione(String dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
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

	public String getDescSoggetto() {
		return this.descSoggetto;
	}

	public void setDescSoggetto(String descSoggetto) {
		this.descSoggetto = descSoggetto;
	}

	public String getDescSubimpegno() {
		return this.descSubimpegno;
	}

	public void setDescSubimpegno(String descSubimpegno) {
		this.descSubimpegno = descSubimpegno;
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
	 * @return the flussoSubimpegniEsterni
	 */
	public Integer getFlussoSubimpegniEsterniId() {
		return flussoSubimpegniEsterniId;
	}

	/**
	 * @param flussoSubimpegniEsterni the flussoSubimpegniEsterni to set
	 */
	public void setFlussoSubimpegniEsterniId(Integer flussoSubimpegniEsterniId) {
		this.flussoSubimpegniEsterniId = flussoSubimpegniEsterniId;
	}


	/**
	 * @return the numElaborazioneDiGiornata
	 */
	public Integer getNumElaborazioneDiGiornata() {
		return numElaborazioneDiGiornata;
	}

	/**
	 * @param numElaborazioneDiGiornata the numElaborazioneDiGiornata to set
	 */
	public void setNumElaborazioneDiGiornata(Integer numElaborazioneDiGiornata) {
		this.numElaborazioneDiGiornata = numElaborazioneDiGiornata;
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

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return flussoSubimpegniEsterniId;
	}

	@Override
	public void setId(Integer id) {
		flussoSubimpegniEsterniId=id;

	}

}
