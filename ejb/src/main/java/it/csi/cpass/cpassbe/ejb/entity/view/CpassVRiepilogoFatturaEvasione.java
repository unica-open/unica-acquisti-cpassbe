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
import javax.persistence.*;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


/**
 * The persistent class for the cpass_v_riepilogo_fattura_evasione database table.
 * 
 */
@Entity
@Table(name="cpass_v_riepilogo_fattura_evasione")
@NamedQuery(name="CpassVRiepilogoFatturaEvasione.findAll", query="SELECT c FROM CpassVRiepilogoFatturaEvasione c")
public class CpassVRiepilogoFatturaEvasione implements Serializable, BaseEntity<Long> {	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="riepilogo_fattura_evasione_id")
	private Long riepilogoFatturaEvasioneId;
		
	@Column(name="testata_evasione_id")
	private UUID testataEvasioneId;
	
	@Column(name="impegno_anno")
	private Integer impegnoAnno;

	@Column(name="impegno_anno_esercizio")
	private Integer impegnoAnnoEsercizio;

	@Column(name="impegno_numero")
	private Integer impegnoNumero;

	private BigDecimal ripartito;

	private BigDecimal sospeso;

	@Column(name="subimpegno_anno")
	private Integer subimpegnoAnno;

	@Column(name="subimpegno_numero")
	private Integer subimpegnoNumero;

	@Column(name="causale_sospensione_codice")
	private String causaleSospensioneCodice;
	
	@Column(name="causale_sospensione_descrizione")
	private String causaleSospensioneDescrizione;
	
	@Column(name="data_sospensione")
	private Date dataSospensione;
	
	public CpassVRiepilogoFatturaEvasione() {
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

	public Integer getImpegnoNumero() {
		return this.impegnoNumero;
	}

	public void setImpegnoNumero(Integer impegnoNumero) {
		this.impegnoNumero = impegnoNumero;
	}

	public BigDecimal getRipartito() {
		return this.ripartito;
	}

	public void setRipartito(BigDecimal ripartito) {
		this.ripartito = ripartito;
	}

	public BigDecimal getSospeso() {
		return this.sospeso;
	}

	public void setSospeso(BigDecimal sospeso) {
		this.sospeso = sospeso;
	}

	public Integer getSubimpegnoAnno() {
		return this.subimpegnoAnno;
	}

	public void setSubimpegnoAnno(Integer subimpegnoAnno) {
		this.subimpegnoAnno = subimpegnoAnno;
	}

	public Integer getSubimpegnoNumero() {
		return this.subimpegnoNumero;
	}

	public void setSubimpegnoNumero(Integer subimpegnoNumero) {
		this.subimpegnoNumero = subimpegnoNumero;
	}

	
	/**
	 * @return the riepilogoFatturaEvasioneId
	 */
	public Long getRiepilogoFatturaEvasioneId() {
		return riepilogoFatturaEvasioneId;
	}

	/**
	 * @param riepilogoFatturaEvasioneId the riepilogoFatturaEvasioneId to set
	 */
	public void setRiepilogoFatturaEvasioneId(Long riepilogoFatturaEvasioneId) {
		this.riepilogoFatturaEvasioneId = riepilogoFatturaEvasioneId;
	}

	/**
	 * @return the testataEvasioneId
	 */
	public UUID getTestataEvasioneId() {
		return testataEvasioneId;
	}

	/**
	 * @param testataEvasioneId the testataEvasioneId to set
	 */
	public void setTestataEvasioneId(UUID testataEvasioneId) {
		this.testataEvasioneId = testataEvasioneId;
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

	/**
	 * @return the dataSospensione
	 */
	public Date getDataSospensione() {
		return dataSospensione;
	}

	/**
	 * @param dataSospensione the dataSospensione to set
	 */
	public void setDataSospensione(Date dataSospensione) {
		this.dataSospensione = dataSospensione;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return riepilogoFatturaEvasioneId;
	}

	@Override
	public void setId(Long id) {
		riepilogoFatturaEvasioneId = id;
	}

	@Override
	public void initId() {
		// Nothing to do
	}
}
