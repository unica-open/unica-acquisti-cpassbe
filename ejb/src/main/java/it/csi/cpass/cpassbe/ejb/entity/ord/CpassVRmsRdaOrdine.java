/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_v_rms_rda_ordine database table.
 *
 */
@Entity
@Table(name="cpass_v_rms_rda_ordine")
@NamedQuery(name="CpassVRmsRdaOrdine.findAll", query="SELECT c FROM CpassVRmsRdaOrdine c")
public class CpassVRmsRdaOrdine implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="rms_rda_ordine_id")
	private Integer rmsRdaOrdineId;

	@Column(name="oggetti_spesa_id")
	private Integer oggettiSpesaId;

	@Column(name="ordine_anno")
	private Integer ordineAnno;

	@Column(name="ordine_numero")
	private Integer ordineNumero;

	@Column(name="progressivo_riga")
	private Integer progressivoRiga;

	@Column(name="quantita_riga_rda")
	private BigDecimal quantitaRigaRda;

	@Column(name="quantita_riga_rms")
	private BigDecimal quantitaRigaRms;

	@Column(name="quantita_su_rda")
	private BigDecimal quantitaSuRda;

	@Column(name="rda_anno")
	private Integer rdaAnno;

	@Column(name="rda_numero")
	private Integer rdaNumero;

	@Column(name="riga_rda_id")
	private UUID rigaRdaId;

	@Column(name="riga_rms_id")
	private UUID rigaRmsId;

	@Column(name="rms_anno")
	private Integer rmsAnno;

	@Column(name="rms_descrizione")
	private String rmsDescrizione;

	@Column(name="rms_numero")
	private Integer rmsNumero;

	@Column(name="stato_riga_rms")
	private String statoRigaRms;

	@Column(name="settore_acquisto_id")
	private UUID settoreAcquistoId;

	@Column(name="settore_destinatario_id")
	private UUID settoreDestinatarioId;

	@Column(name="settore_emittente_id")
	private UUID settoreEmittenteId;

	@Column(name="settore_indirizzo_id")
	private Integer settoreIndirizzoId;

	@Column(name="sezione_id")
	private Integer sezioneId;

	@Column(name="stato_testata_ordine")
	private String statoTestataOrdine;

	@Column(name="stato_testata_rda")
	private String statoTestataRda;

	@Column(name="testata_ordine_id")
	private UUID testataOrdineId;

	@Column(name="testata_rda_id")
	private UUID testataRdaId;

	@Column(name="testata_rms_id")
	private UUID testataRmsId;

	public CpassVRmsRdaOrdine() {
	}

	/**
	 * @return the rmsRdaOrdineId
	 */
	public Integer getRmsRdaOrdineId() {
		return rmsRdaOrdineId;
	}

	/**
	 * @param rmsRdaOrdineId the rmsRdaOrdineId to set
	 */
	public void setRmsRdaOrdineId(Integer rmsRdaOrdineId) {
		this.rmsRdaOrdineId = rmsRdaOrdineId;
	}

	public Integer getOggettiSpesaId() {
		return this.oggettiSpesaId;
	}

	public void setOggettiSpesaId(Integer oggettiSpesaId) {
		this.oggettiSpesaId = oggettiSpesaId;
	}

	public Integer getOrdineAnno() {
		return this.ordineAnno;
	}

	public void setOrdineAnno(Integer ordineAnno) {
		this.ordineAnno = ordineAnno;
	}

	public Integer getOrdineNumero() {
		return this.ordineNumero;
	}

	public void setOrdineNumero(Integer ordineNumero) {
		this.ordineNumero = ordineNumero;
	}

	public Integer getProgressivoRiga() {
		return this.progressivoRiga;
	}

	public void setProgressivoRiga(Integer progressivoRiga) {
		this.progressivoRiga = progressivoRiga;
	}

	public BigDecimal getQuantitaRigaRda() {
		return this.quantitaRigaRda;
	}

	public void setQuantitaRigaRda(BigDecimal quantitaRigaRda) {
		this.quantitaRigaRda = quantitaRigaRda;
	}

	public BigDecimal getQuantitaRigaRms() {
		return this.quantitaRigaRms;
	}

	public void setQuantitaRigaRms(BigDecimal quantitaRigaRms) {
		this.quantitaRigaRms = quantitaRigaRms;
	}

	public BigDecimal getQuantitaSuRda() {
		return this.quantitaSuRda;
	}

	public void setQuantitaSuRda(BigDecimal quantitaSuRda) {
		this.quantitaSuRda = quantitaSuRda;
	}

	public Integer getRdaAnno() {
		return this.rdaAnno;
	}

	public void setRdaAnno(Integer rdaAnno) {
		this.rdaAnno = rdaAnno;
	}

	public Integer getRdaNumero() {
		return this.rdaNumero;
	}

	public void setRdaNumero(Integer rdaNumero) {
		this.rdaNumero = rdaNumero;
	}

	public UUID getRigaRdaId() {
		return this.rigaRdaId;
	}

	public void setRigaRdaId(UUID rigaRdaId) {
		this.rigaRdaId = rigaRdaId;
	}

	public UUID getRigaRmsId() {
		return this.rigaRmsId;
	}

	public void setRigaRmsId(UUID rigaRmsId) {
		this.rigaRmsId = rigaRmsId;
	}

	public Integer getRmsAnno() {
		return this.rmsAnno;
	}

	public void setRmsAnno(Integer rmsAnno) {
		this.rmsAnno = rmsAnno;
	}

	public String getRmsDescrizione() {
		return this.rmsDescrizione;
	}

	public void setRmsDescrizione(String rmsDescrizione) {
		this.rmsDescrizione = rmsDescrizione;
	}

	public Integer getRmsNumero() {
		return this.rmsNumero;
	}

	public void setRmsNumero(Integer rmsNumero) {
		this.rmsNumero = rmsNumero;
	}

	public UUID getSettoreAcquistoId() {
		return this.settoreAcquistoId;
	}

	public void setSettoreAcquistoId(UUID settoreAcquistoId) {
		this.settoreAcquistoId = settoreAcquistoId;
	}

	public UUID getSettoreDestinatarioId() {
		return this.settoreDestinatarioId;
	}

	public void setSettoreDestinatarioId(UUID settoreDestinatarioId) {
		this.settoreDestinatarioId = settoreDestinatarioId;
	}

	public UUID getSettoreEmittenteId() {
		return this.settoreEmittenteId;
	}

	public void setSettoreEmittenteId(UUID settoreEmittenteId) {
		this.settoreEmittenteId = settoreEmittenteId;
	}

	public Integer getSettoreIndirizzoId() {
		return this.settoreIndirizzoId;
	}

	public void setSettoreIndirizzoId(Integer settoreIndirizzoId) {
		this.settoreIndirizzoId = settoreIndirizzoId;
	}

	public Integer getSezioneId() {
		return this.sezioneId;
	}

	public void setSezioneId(Integer sezioneId) {
		this.sezioneId = sezioneId;
	}

	public String getStatoTestataOrdine() {
		return this.statoTestataOrdine;
	}

	public void setStatoTestataOrdine(String statoTestataOrdine) {
		this.statoTestataOrdine = statoTestataOrdine;
	}

	public String getStatoTestataRda() {
		return this.statoTestataRda;
	}

	public void setStatoTestataRda(String statoTestataRda) {
		this.statoTestataRda = statoTestataRda;
	}

	public UUID getTestataOrdineId() {
		return this.testataOrdineId;
	}

	public void setTestataOrdineId(UUID testataOrdineId) {
		this.testataOrdineId = testataOrdineId;
	}

	public UUID getTestataRdaId() {
		return this.testataRdaId;
	}

	public void setTestataRdaId(UUID testataRdaId) {
		this.testataRdaId = testataRdaId;
	}

	public UUID getTestataRmsId() {
		return this.testataRmsId;
	}

	public void setTestataRmsId(UUID testataRmsId) {
		this.testataRmsId = testataRmsId;
	}

	/**
	 * @return the statoRigaRms
	 */
	public String getStatoRigaRms() {
		return statoRigaRms;
	}

	/**
	 * @param statoRigaRms the statoRigaRms to set
	 */
	public void setStatoRigaRms(String statoRigaRms) {
		this.statoRigaRms = statoRigaRms;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return rmsRdaOrdineId;
	}

	@Override
	public void setId(Integer id) {
		rmsRdaOrdineId = id;

	}

}
