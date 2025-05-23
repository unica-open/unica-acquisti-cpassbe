/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.rms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;
import it.csi.cpass.cpassbe.ejb.entity.mag.CpassTMagMagazzino;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSezione;


/**
 * The persistent class for the cpass_t_regole_smistamento_rms database table.
 *
 */
@Entity
@Table(name="cpass_t_regole_smistamento_rms")
@NamedQuery(name="CpassTRegoleSmistamentoRms.findAll", query="SELECT c FROM CpassTRegoleSmistamentoRms c")
public class CpassTRegoleSmistamentoRms implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_REGOLE_SMISTAMENTO_RMS_REGOLESMISTAMENTORMSID_GENERATOR", sequenceName="cpass_t_regole_smistamento_rms_regole_smistamento_rms_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_REGOLE_SMISTAMENTO_RMS_REGOLESMISTAMENTORMSID_GENERATOR")
	@Column(name="regole_smistamento_rms_id")
	private Integer regoleSmistamentoRmsId;

	@Column(name="centro_acquisto_codice")
	private String centroAcquistoCodice;

	@Column(name="cpv_codice")
	private String cpvCodice;

	@Column(name="livello_cpv")
	private String livelloCpv;

	@Column(name="magazzino_codice")
	private String magazzinoCodice;

	@Column(name="oggetti_spesa_codice")
	private String oggettiSpesaCodice;

	@Column(name="regola_cpv")
	private String regolaCpv;

	@Column(name="settore_codice")
	private String settoreCodice;

	@Column(name="sezione_acquisto_codice")
	private String sezioneAcquistoCodice;

	@Column(name="tutta_la_struttura")
	private Boolean tuttaLaStruttura;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	//bi-directional many-to-one association to CpassTMagMagazzino
	@ManyToOne
	@JoinColumn(name="magazzino_id")
	private CpassTMagMagazzino cpassTMagMagazzino;

	//bi-directional many-to-one association to CpassTOrdSezione
	@ManyToOne
	@JoinColumn(name="sezione_acquisto_id")
	private CpassTOrdSezione cpassTOrdSezione;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="centro_acquisto_id")
	private CpassTSettore cpassTSettoreAcquisto;

	public CpassTRegoleSmistamentoRms() {
	}

	public Integer getRegoleSmistamentoRmsId() {
		return this.regoleSmistamentoRmsId;
	}

	public void setRegoleSmistamentoRmsId(Integer regoleSmistamentoRmsId) {
		this.regoleSmistamentoRmsId = regoleSmistamentoRmsId;
	}

	public String getCpvCodice() {
		return this.cpvCodice;
	}

	public void setCpvCodice(String cpvCodice) {
		this.cpvCodice = cpvCodice;
	}

	public String getLivelloCpv() {
		return this.livelloCpv;
	}

	public void setLivelloCpv(String livelloCpv) {
		this.livelloCpv = livelloCpv;
	}

	public String getOggettiSpesaCodice() {
		return this.oggettiSpesaCodice;
	}

	public void setOggettiSpesaCodice(String oggettiSpesaCodice) {
		this.oggettiSpesaCodice = oggettiSpesaCodice;
	}

	public String getRegolaCpv() {
		return this.regolaCpv;
	}

	public void setRegolaCpv(String regolaCpv) {
		this.regolaCpv = regolaCpv;
	}

	public String getSettoreCodice() {
		return this.settoreCodice;
	}

	public void setSettoreCodice(String settoreCodice) {
		this.settoreCodice = settoreCodice;
	}

	public Boolean getTuttaLaStruttura() {
		return this.tuttaLaStruttura;
	}

	public void setTuttaLaStruttura(Boolean tuttaLaStruttura) {
		this.tuttaLaStruttura = tuttaLaStruttura;
	}

	public CpassTEnte getCpassTEnte() {
		return this.cpassTEnte;
	}

	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	public CpassTMagMagazzino getCpassTMagMagazzino() {
		return this.cpassTMagMagazzino;
	}

	public void setCpassTMagMagazzino(CpassTMagMagazzino cpassTMagMagazzino) {
		this.cpassTMagMagazzino = cpassTMagMagazzino;
	}

	public CpassTOrdSezione getCpassTOrdSezione() {
		return this.cpassTOrdSezione;
	}

	public void setCpassTOrdSezione(CpassTOrdSezione cpassTOrdSezione) {
		this.cpassTOrdSezione = cpassTOrdSezione;
	}


	/**
	 * @return the cpassTSettoreAcquisto
	 */
	public CpassTSettore getCpassTSettoreAcquisto() {
		return cpassTSettoreAcquisto;
	}

	/**
	 * @param cpassTSettoreAcquisto the cpassTSettoreAcquisto to set
	 */
	public void setCpassTSettoreAcquisto(CpassTSettore cpassTSettoreAcquisto) {
		this.cpassTSettoreAcquisto = cpassTSettoreAcquisto;
	}

	/**
	 * @return the centroAcquistoCodice
	 */
	public String getCentroAcquistoCodice() {
		return centroAcquistoCodice;
	}

	/**
	 * @param centroAcquistoCodice the centroAcquistoCodice to set
	 */
	public void setCentroAcquistoCodice(String centroAcquistoCodice) {
		this.centroAcquistoCodice = centroAcquistoCodice;
	}

	/**
	 * @return the magazzinoCodice
	 */
	public String getMagazzinoCodice() {
		return magazzinoCodice;
	}

	/**
	 * @param magazzinoCodice the magazzinoCodice to set
	 */
	public void setMagazzinoCodice(String magazzinoCodice) {
		this.magazzinoCodice = magazzinoCodice;
	}

	/**
	 * @return the sezioneAcquistoCodice
	 */
	public String getSezioneAcquistoCodice() {
		return sezioneAcquistoCodice;
	}

	/**
	 * @param sezioneAcquistoCodice the sezioneAcquistoCodice to set
	 */
	public void setSezioneAcquistoCodice(String sezioneAcquistoCodice) {
		this.sezioneAcquistoCodice = sezioneAcquistoCodice;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return regoleSmistamentoRmsId;
	}

	@Override
	public void setId(Integer id) {
		regoleSmistamentoRmsId = id;

	}

}
