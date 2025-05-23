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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.mag.CpassTMagMagazzino;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSezione;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;



/**
 * The persistent class for the cpass_t_rms_riga_rms database table.
 *
 */
@Entity
@Table(name="cpass_t_rms_riga_rms")
@NamedQuery(name="CpassTRmsRigaRms.findAll", query="SELECT c FROM CpassTRmsRigaRms c")
public class CpassTRmsRigaRms extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_rms_riga_rms");

	@Id
	@Column(name="riga_rms_id")
	private UUID rigaRmsId;

	@Column(name="consegna_parziale")
	private Boolean consegnaParziale;

	private String cui;

	@Column(name="data_fine_consegna")
	private Date dataFineConsegna;

	@Column(name="data_inizio_consegna")
	private Date dataInizioConsegna;

	@Column(name="motivo_rifiuto")
	private String motivoRifiuto;

	private String note;

	@Column(name="progressivo_riga")
	private Integer progressivoRiga;

	private BigDecimal quantita;

	@Column(name="quantita_su_rda")
	private BigDecimal quantitaSuRda;

	@Column(name="motivo_evasione_manuale")
	private String motivoEvasioneManuale;

	@Column(name="DATA_ATTESA_GARA")
	private Date dataAtttesaGara;

	@Column(name="UTENTE_ATTESA_GARA")
	private String utenteAttesaGara;

	@Column(name="flg_non_competenza")
	private Boolean flgNonCompetenza;

	//bi-directional many-to-one association to CpassRRmsStatiRigaRm
	@OneToMany(mappedBy="cpassTRmsRigaRms")
	private List<CpassRRmsStatiRigaRms> cpassRRmsStatiRigaRms;

	//bi-directional many-to-one association to CpassRRmsRigaRda
	@OneToMany(mappedBy="cpassTRmsRigaRms")
	private List<CpassRRmsRigaRda> cpassRRmsRigaRdas;

	//@Column(name="data_autorizzazione")
	//private Date dataAutorizzazione;

	//bi-directional many-to-one association to CpassDOggettiSpesa
	@ManyToOne
	@JoinColumn(name="oggetti_spesa_id")
	private CpassDOggettiSpesa cpassDOggettiSpesa;

	//bi-directional many-to-one association to CpassTMagMagazzino
	@ManyToOne
	@JoinColumn(name="magazzino_id")
	private CpassTMagMagazzino cpassTMagMagazzino;

	//bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name="stato_id")
	private CpassDStato cpassDStato;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	//bi-directional many-to-one association to CpassTRmsTestataRm
	@ManyToOne
	@JoinColumn(name="testata_rms_id")
	private CpassTRmsTestataRms cpassTRmsTestataRms;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_acquisto_id")
	private CpassTSettore cpassTSettoreAcquisto;

	//bi-directional many-to-one association to CpassTOrdSezione
	@ManyToOne
	@JoinColumn(name="sezione_id")
	private CpassTOrdSezione cpassTOrdSezione;

	//bi-directional many-to-one association to CpassDOggettiSpesa
	//@ManyToOne
	//@JoinColumn(name="riga_rda_id")
	//private CpassTOrdRigaRda cpassTOrdRigaRda;

	public CpassTRmsRigaRms() {
	}

	public UUID getRigaRmsId() {
		return this.rigaRmsId;
	}

	public void setRigaRmsId(UUID rigaRmsId) {
		this.rigaRmsId = rigaRmsId;
	}

	public Boolean getConsegnaParziale() {
		return this.consegnaParziale;
	}

	public void setConsegnaParziale(Boolean consegnaParziale) {
		this.consegnaParziale = consegnaParziale;
	}

	public String getCui() {
		return this.cui;
	}

	public void setCui(String cui) {
		this.cui = cui;
	}


	public Date getDataFineConsegna() {
		return this.dataFineConsegna;
	}

	public void setDataFineConsegna(Date dataFineConsegna) {
		this.dataFineConsegna = dataFineConsegna;
	}

	public Date getDataInizioConsegna() {
		return this.dataInizioConsegna;
	}

	public void setDataInizioConsegna(Date dataInizioConsegna) {
		this.dataInizioConsegna = dataInizioConsegna;
	}

	public String getMotivoRifiuto() {
		return this.motivoRifiuto;
	}

	public void setMotivoRifiuto(String motivoRifiuto) {
		this.motivoRifiuto = motivoRifiuto;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getProgressivoRiga() {
		return this.progressivoRiga;
	}

	public void setProgressivoRiga(Integer progressivoRiga) {
		this.progressivoRiga = progressivoRiga;
	}

	public BigDecimal getQuantita() {
		return this.quantita;
	}

	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}

	/**
	 * @return the quantitaSuRda
	 */
	public BigDecimal getQuantitaSuRda() {
		return quantitaSuRda;
	}

	/**
	 * @param quantitaSuRda the quantitaSuRda to set
	 */
	public void setQuantitaSuRda(BigDecimal quantitaSuRda) {
		this.quantitaSuRda = quantitaSuRda;
	}

	public CpassDOggettiSpesa getCpassDOggettiSpesa() {
		return this.cpassDOggettiSpesa;
	}

	public void setCpassDOggettiSpesa(CpassDOggettiSpesa cpassDOggettiSpesa) {
		this.cpassDOggettiSpesa = cpassDOggettiSpesa;
	}

	public CpassDStato getCpassDStato() {
		return this.cpassDStato;
	}

	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	public CpassTEnte getCpassTEnte() {
		return this.cpassTEnte;
	}

	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	public CpassTRmsTestataRms getCpassTRmsTestataRms() {
		return this.cpassTRmsTestataRms;
	}

	public void setCpassTRmsTestataRms(CpassTRmsTestataRms cpassTRmsTestataRms) {
		this.cpassTRmsTestataRms = cpassTRmsTestataRms;
	}

	public CpassTSettore getCpassTSettoreAcquisto() {
		return this.cpassTSettoreAcquisto;
	}

	public void setCpassTSettoreAcquisto(CpassTSettore cpassTSettoreAcquisto) {
		this.cpassTSettoreAcquisto = cpassTSettoreAcquisto;
	}

	/**
	 * @return the cpassTMagMagazzino
	 */
	public CpassTMagMagazzino getCpassTMagMagazzino() {
		return cpassTMagMagazzino;
	}

	/**
	 * @param cpassTMagMagazzino the cpassTMagMagazzino to set
	 */
	public void setCpassTMagMagazzino(CpassTMagMagazzino cpassTMagMagazzino) {
		this.cpassTMagMagazzino = cpassTMagMagazzino;
	}


	/**
	 * @return the cpassTOrdSezione
	 */
	public CpassTOrdSezione getCpassTOrdSezione() {
		return cpassTOrdSezione;
	}

	/**
	 * @param cpassTOrdSezione the cpassTOrdSezione to set
	 */
	public void setCpassTOrdSezione(CpassTOrdSezione cpassTOrdSezione) {
		this.cpassTOrdSezione = cpassTOrdSezione;
	}
	/*
	public CpassTOrdRigaRda getCpassTOrdRigaRda() {
		return cpassTOrdRigaRda;
	}

	public void setCpassTOrdRigaRda(CpassTOrdRigaRda cpassTOrdRigaRda) {
		this.cpassTOrdRigaRda = cpassTOrdRigaRda;
	}

	 */

	@Override
	public UUID getId() {
		return rigaRmsId;
	}

	@Override
	public void setId(UUID id) {
		rigaRmsId = id;
	}

	@Override
	public void initId() {
		this.rigaRmsId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTRmsTestataRms.getId()+"|"+ progressivoRiga);
	}

	/**
	 * @return the motivoEvasioneManuale
	 */
	public String getMotivoEvasioneManuale() {
		return motivoEvasioneManuale;
	}

	/**
	 * @param motivoEvasioneManuale the motivoEvasioneManuale to set
	 */
	public void setMotivoEvasioneManuale(String motivoEvasioneManuale) {
		this.motivoEvasioneManuale = motivoEvasioneManuale;
	}

	/**
	 * @return the dataAtttesaGara
	 */
	public Date getDataAtttesaGara() {
		return dataAtttesaGara;
	}

	/**
	 * @param dataAtttesaGara the dataAtttesaGara to set
	 */
	public void setDataAtttesaGara(Date dataAtttesaGara) {
		this.dataAtttesaGara = dataAtttesaGara;
	}

	/**
	 * @return the utenteAttesaGara
	 */
	public String getUtenteAttesaGara() {
		return utenteAttesaGara;
	}

	/**
	 * @param utenteAttesaGara the utenteAttesaGara to set
	 */
	public void setUtenteAttesaGara(String utenteAttesaGara) {
		this.utenteAttesaGara = utenteAttesaGara;
	}

	/**
	 * @return the flgNonCompetenza
	 */
	public Boolean getFlgNonCompetenza() {
		return flgNonCompetenza;
	}

	/**
	 * @param flgNonCompetenza the flgNonCompetenza to set
	 */
	public void setFlgNonCompetenza(Boolean flgNonCompetenza) {
		this.flgNonCompetenza = flgNonCompetenza;
	}

	/**
	 * @return the cpassRRmsRigaRdas
	 */
	public List<CpassRRmsRigaRda> getCpassRRmsRigaRdas() {
		return cpassRRmsRigaRdas;
	}

	/**
	 * @param cpassRRmsRigaRdas the cpassRRmsRigaRdas to set
	 */
	public void setCpassRRmsRigaRdas(List<CpassRRmsRigaRda> cpassRRmsRigaRdas) {
		this.cpassRRmsRigaRdas = cpassRRmsRigaRdas;
	}

	/**
	 * @return the cpassRRmsStatiRigaRms
	 */
	public List<CpassRRmsStatiRigaRms> getCpassRRmsStatiRigaRms() {
		return cpassRRmsStatiRigaRms;
	}

	/**
	 * @param cpassRRmsStatiRigaRms the cpassRRmsStatiRigaRms to set
	 */
	public void setCpassRRmsStatiRigaRms(List<CpassRRmsStatiRigaRms> cpassRRmsStatiRigaRms) {
		this.cpassRRmsStatiRigaRms = cpassRRmsStatiRigaRms;
	}



}
