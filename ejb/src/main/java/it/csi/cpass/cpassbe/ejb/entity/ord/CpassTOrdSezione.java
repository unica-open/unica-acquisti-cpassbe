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
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
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

import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRegoleSmistamentoRms;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsRigaRms;


/**
 * The persistent class for the cpass_t_ord_sezione database table.
 *
 */
@Entity
@Table(name="cpass_t_ord_sezione")
@NamedQuery(name="CpassTOrdSezione.findAll", query="SELECT c FROM CpassTOrdSezione c")
public class CpassTOrdSezione extends BaseAuditedEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_ORD_SEZIONE_SEZIONEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_ORD_SEZIONE_SEZIONEID_GENERATOR")
	@Column(name="sezione_id")
	private Integer sezioneId;

	@Column(name="sezione_codice")
	private String sezioneCodice;

	@Column(name="sezione_descrizione")
	private String sezioneDescrizione;

	//bi-directional many-to-one association to CpassROrdUtenteSezione
	@OneToMany(mappedBy="cpassTOrdSezione")
	private List<CpassROrdUtenteSezione> cpassROrdUtenteSeziones;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="settore_id")
	private CpassTSettore cpassTSettore;

	//bi-directional many-to-one association to CpassTRegoleSmistamentoRm
	@OneToMany(mappedBy="cpassTOrdSezione")
	private List<CpassTRegoleSmistamentoRms> cpassTRegoleSmistamentoRms;

	//bi-directional many-to-one association to CpassTRmsRigaRm
	@OneToMany(mappedBy="cpassTOrdSezione")
	private List<CpassTRmsRigaRms> cpassTRmsRigaRms;

	public CpassTOrdSezione() {
	}

	public Integer getSezioneId() {
		return this.sezioneId;
	}

	public void setSezioneId(Integer sezioneId) {
		this.sezioneId = sezioneId;
	}

	public String getSezioneCodice() {
		return this.sezioneCodice;
	}

	public void setSezioneCodice(String sezioneCodice) {
		this.sezioneCodice = sezioneCodice;
	}

	public String getSezioneDescrizione() {
		return this.sezioneDescrizione;
	}

	public void setSezioneDescrizione(String sezioneDescrizione) {
		this.sezioneDescrizione = sezioneDescrizione;
	}

	public List<CpassROrdUtenteSezione> getCpassROrdUtenteSeziones() {
		return this.cpassROrdUtenteSeziones;
	}

	public void setCpassROrdUtenteSeziones(List<CpassROrdUtenteSezione> cpassROrdUtenteSeziones) {
		this.cpassROrdUtenteSeziones = cpassROrdUtenteSeziones;
	}

	public CpassROrdUtenteSezione addCpassROrdUtenteSezione(CpassROrdUtenteSezione cpassROrdUtenteSezione) {
		getCpassROrdUtenteSeziones().add(cpassROrdUtenteSezione);
		cpassROrdUtenteSezione.setCpassTOrdSezione(this);

		return cpassROrdUtenteSezione;
	}

	public CpassROrdUtenteSezione removeCpassROrdUtenteSezione(CpassROrdUtenteSezione cpassROrdUtenteSezione) {
		getCpassROrdUtenteSeziones().remove(cpassROrdUtenteSezione);
		cpassROrdUtenteSezione.setCpassTOrdSezione(null);

		return cpassROrdUtenteSezione;
	}

	public CpassTEnte getCpassTEnte() {
		return this.cpassTEnte;
	}

	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	public List<CpassTRegoleSmistamentoRms> getCpassTRegoleSmistamentoRms() {
		return this.cpassTRegoleSmistamentoRms;
	}

	public void setCpassTRegoleSmistamentoRms(List<CpassTRegoleSmistamentoRms> cpassTRegoleSmistamentoRms) {
		this.cpassTRegoleSmistamentoRms = cpassTRegoleSmistamentoRms;
	}

	public CpassTRegoleSmistamentoRms addCpassTRegoleSmistamentoRm(CpassTRegoleSmistamentoRms cpassTRegoleSmistamentoRm) {
		getCpassTRegoleSmistamentoRms().add(cpassTRegoleSmistamentoRm);
		cpassTRegoleSmistamentoRm.setCpassTOrdSezione(this);

		return cpassTRegoleSmistamentoRm;
	}

	public CpassTRegoleSmistamentoRms removeCpassTRegoleSmistamentoRm(CpassTRegoleSmistamentoRms cpassTRegoleSmistamentoRm) {
		getCpassTRegoleSmistamentoRms().remove(cpassTRegoleSmistamentoRm);
		cpassTRegoleSmistamentoRm.setCpassTOrdSezione(null);

		return cpassTRegoleSmistamentoRm;
	}

	public List<CpassTRmsRigaRms> getCpassTRmsRigaRms() {
		return this.cpassTRmsRigaRms;
	}

	public void setCpassTRmsRigaRms(List<CpassTRmsRigaRms> cpassTRmsRigaRms) {
		this.cpassTRmsRigaRms = cpassTRmsRigaRms;
	}

	public CpassTRmsRigaRms addCpassTRmsRigaRm(CpassTRmsRigaRms cpassTRmsRigaRm) {
		getCpassTRmsRigaRms().add(cpassTRmsRigaRm);
		cpassTRmsRigaRm.setCpassTOrdSezione(this);

		return cpassTRmsRigaRm;
	}

	public CpassTRmsRigaRms removeCpassTRmsRigaRm(CpassTRmsRigaRms cpassTRmsRigaRms) {
		getCpassTRmsRigaRms().remove(cpassTRmsRigaRms);
		cpassTRmsRigaRms.setCpassTOrdSezione(null);

		return cpassTRmsRigaRms;
	}


	/**
	 * @return the cpassTSettore
	 */
	public CpassTSettore getCpassTSettore() {
		return cpassTSettore;
	}

	/**
	 * @param cpassTSettore the cpassTSettore to set
	 */
	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return sezioneId;
	}

	@Override
	public void setId(Integer id) {
		sezioneId = id;
	}

}
