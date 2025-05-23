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
package it.csi.cpass.cpassbe.ejb.entity.view;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_v_rms_da_smistare database view.
 *
 */
@Entity
@Table(name="cpass_v_rms_da_smistare")
@NamedQuery(name="CpassVRmsDaSmistare.findAll", query="SELECT c FROM CpassVRmsDaSmistare c")
public class CpassVRmsDaSmistare implements Serializable, BaseEntity<Long> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="rms_da_smistare_id")
	private Long rmsDaSmistareId;

	@Column(name="codice_cpv")
	private String codiceCpv;

	@Column(name="codice_ods")
	private String codiceOds;

	@Column(name="cpv_id")
	private Integer cpvId;

	@Column(name="ente_id")
	private UUID enteId;

	@Column(name="oggetti_spesa_id")
	private Integer oggettiSpesaId;

	@Column(name="richiesta_magazzino")
	private Boolean richiestaMagazzino;

	@Column(name="riga_rms_id")
	private UUID rigaRmsId;


	@Column(name="struttura_padre")
	private String strutturaPadre;

	@Column(name="struttura_padre_id")
	private UUID strutturaPadreId;

	@Column(name="struttura_richiedente")
	private String strutturaRichiedente;

	@Column(name="struttura_richiedente_id")
	private UUID strutturaRichiedenteId;

	@Column(name="testata_rms_id")
	private UUID testataRmsId;

	@Column(name="rms_anno")
	private Integer rmsAnno;

	@Column(name="rms_numero")
	private Integer rmsNumero;

	@Column(name="progressivo_riga")
	private Integer progressivoRiga;

	public CpassVRmsDaSmistare() {
	}

	public String getCodiceCpv() {
		return this.codiceCpv;
	}

	public void setCodiceCpv(String codiceCpv) {
		this.codiceCpv = codiceCpv;
	}

	public String getCodiceOds() {
		return this.codiceOds;
	}

	public void setCodiceOds(String codiceOds) {
		this.codiceOds = codiceOds;
	}

	public Integer getCpvId() {
		return this.cpvId;
	}

	public void setCpvId(Integer cpvId) {
		this.cpvId = cpvId;
	}

	public UUID getEnteId() {
		return this.enteId;
	}

	public void setEnteId(UUID enteId) {
		this.enteId = enteId;
	}

	public Integer getOggettiSpesaId() {
		return this.oggettiSpesaId;
	}

	public void setOggettiSpesaId(Integer oggettiSpesaId) {
		this.oggettiSpesaId = oggettiSpesaId;
	}

	public Boolean getRichiestaMagazzino() {
		return this.richiestaMagazzino;
	}

	public void setRichiestaMagazzino(Boolean richiestaMagazzino) {
		this.richiestaMagazzino = richiestaMagazzino;
	}

	public UUID getRigaRmsId() {
		return this.rigaRmsId;
	}

	public void setRigaRmsId(UUID rigaRmsId) {
		this.rigaRmsId = rigaRmsId;
	}

	public Long getRmsDaSmistareId() {
		return this.rmsDaSmistareId;
	}

	public void setRmsDaSmistareId(Long rmsDaSmistareId) {
		this.rmsDaSmistareId = rmsDaSmistareId;
	}

	public String getStrutturaPadre() {
		return this.strutturaPadre;
	}

	public void setStrutturaPadre(String strutturaPadre) {
		this.strutturaPadre = strutturaPadre;
	}

	public UUID getStrutturaPadreId() {
		return this.strutturaPadreId;
	}

	public void setStrutturaPadreId(UUID strutturaPadreId) {
		this.strutturaPadreId = strutturaPadreId;
	}

	public String getStrutturaRichiedente() {
		return this.strutturaRichiedente;
	}

	public void setStrutturaRichiedente(String strutturaRichiedente) {
		this.strutturaRichiedente = strutturaRichiedente;
	}

	public UUID getStrutturaRichiedenteId() {
		return this.strutturaRichiedenteId;
	}

	public void setStrutturaRichiedenteId(UUID strutturaRichiedenteId) {
		this.strutturaRichiedenteId = strutturaRichiedenteId;
	}

	public UUID getTestataRmsId() {
		return this.testataRmsId;
	}

	public void setTestataRmsId(UUID testataRmsId) {
		this.testataRmsId = testataRmsId;
	}

	/**
	 * @return the rmsAnno
	 */
	public Integer getRmsAnno() {
		return rmsAnno;
	}

	/**
	 * @param rmsAnno the rmsAnno to set
	 */
	public void setRmsAnno(Integer rmsAnno) {
		this.rmsAnno = rmsAnno;
	}

	/**
	 * @return the rmsNumero
	 */
	public Integer getRmsNumero() {
		return rmsNumero;
	}

	/**
	 * @param rmsNumero the rmsNumero to set
	 */
	public void setRmsNumero(Integer rmsNumero) {
		this.rmsNumero = rmsNumero;
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

	@Override
	public Long getId() {
		return rmsDaSmistareId;
	}

	@Override
	public void setId(Long id) {
		rmsDaSmistareId = id;
	}

	@Override
	public void initId() {
	}
}
