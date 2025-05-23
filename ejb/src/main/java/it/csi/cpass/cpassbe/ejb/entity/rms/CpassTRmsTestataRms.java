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

import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTSettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_rms_testata_rms database table.
 *
 */
@Entity
@Table(name="cpass_t_rms_testata_rms")
@NamedQuery(name="CpassTRmsTestataRms.findAll", query="SELECT c FROM CpassTRmsTestataRms c")
public class CpassTRmsTestataRms extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_rms_testata_rms");

	@Id
	@Column(name="testata_rms_id")
	private UUID testataRmsId;

	@Column(name="data_autorizzazione")
	private Date dataAutorizzazione;

	@Column(name="data_conferma")
	private Date dataConferma;

	@Column(name="destinatario_cap")
	private String destinatarioCap;

	@Column(name="destinatario_contatto")
	private String destinatarioContatto;

	@Column(name="destinatario_email")
	private String destinatarioEmail;

	@Column(name="destinatario_indirizzo")
	private String destinatarioIndirizzo;

	@Column(name="destinatario_localita")
	private String destinatarioLocalita;

	@Column(name="destinatario_num_civico")
	private String destinatarioNumCivico;

	@Column(name="destinatario_provincia")
	private String destinatarioProvincia;

	@Column(name="destinatario_telefono")
	private String destinatarioTelefono;

	private String note;

	@Column(name="note_richiedente")
	private String noteRichiedente;

	@Column(name="richiesta_magazzino")
	private Boolean richiestaMagazzino;

	@Column(name="rms_anno")
	private Integer rmsAnno;

	@Column(name="rms_descrizione")
	private String rmsDescrizione;

	@Column(name="rms_numero")
	private Integer rmsNumero;

	@Column(name="motivo_rifiuto")
	private String motivoRifiuto;

	@Column(name="utente_autorizzatore_id")
	private UUID utenteAutorizzatoreId;

	//bi-directional many-to-one association to CpassTRmsRigaRm
	@OneToMany(mappedBy="cpassTRmsTestataRms")
	private List<CpassTRmsRigaRms> cpassTRmsRigaRms;

	//bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name="stato_id")
	private CpassDStato cpassDStato;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_destinatario_id")
	private CpassTSettore cpassTSettoreDestinatario;

	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_emittente_id")
	private CpassTSettore cpassTSettoreEmittente;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_richiedente_id")
	private CpassTUtente cpassTUtente;

	//bi-directional many-to-one association to CpassTSettoreIndirizzo
	@ManyToOne
	@JoinColumn(name = "settore_indirizzo_id")
	private CpassTSettoreIndirizzo cpassTSettoreIndirizzo;

	public CpassTRmsTestataRms() {
	}

	public UUID getTestataRmsId() {
		return this.testataRmsId;
	}

	public void setTestataRmsId(UUID testataRmsId) {
		this.testataRmsId = testataRmsId;
	}

	public Date getDataAutorizzazione() {
		return this.dataAutorizzazione;
	}

	public void setDataAutorizzazione(Date dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}

	public Date getDataConferma() {
		return this.dataConferma;
	}

	public void setDataConferma(Date dataConferma) {
		this.dataConferma = dataConferma;
	}


	public String getDestinatarioCap() {
		return this.destinatarioCap;
	}

	public void setDestinatarioCap(String destinatarioCap) {
		this.destinatarioCap = destinatarioCap;
	}

	public String getDestinatarioContatto() {
		return this.destinatarioContatto;
	}

	public void setDestinatarioContatto(String destinatarioContatto) {
		this.destinatarioContatto = destinatarioContatto;
	}

	public String getDestinatarioEmail() {
		return this.destinatarioEmail;
	}

	public void setDestinatarioEmail(String destinatarioEmail) {
		this.destinatarioEmail = destinatarioEmail;
	}

	public String getDestinatarioIndirizzo() {
		return this.destinatarioIndirizzo;
	}

	public void setDestinatarioIndirizzo(String destinatarioIndirizzo) {
		this.destinatarioIndirizzo = destinatarioIndirizzo;
	}

	public String getDestinatarioLocalita() {
		return this.destinatarioLocalita;
	}

	public void setDestinatarioLocalita(String destinatarioLocalita) {
		this.destinatarioLocalita = destinatarioLocalita;
	}

	public String getDestinatarioNumCivico() {
		return this.destinatarioNumCivico;
	}

	public void setDestinatarioNumCivico(String destinatarioNumCivico) {
		this.destinatarioNumCivico = destinatarioNumCivico;
	}

	public String getDestinatarioProvincia() {
		return this.destinatarioProvincia;
	}

	public void setDestinatarioProvincia(String destinatarioProvincia) {
		this.destinatarioProvincia = destinatarioProvincia;
	}

	public String getDestinatarioTelefono() {
		return this.destinatarioTelefono;
	}

	public void setDestinatarioTelefono(String destinatarioTelefono) {
		this.destinatarioTelefono = destinatarioTelefono;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNoteRichiedente() {
		return this.noteRichiedente;
	}

	public void setNoteRichiedente(String noteRichiedente) {
		this.noteRichiedente = noteRichiedente;
	}

	public Boolean getRichiestaMagazzino() {
		return this.richiestaMagazzino;
	}

	public void setRichiestaMagazzino(Boolean richiestaMagazzino) {
		this.richiestaMagazzino = richiestaMagazzino;
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

	/**
	 * @return the utenteAutorizzatoreId
	 */
	public UUID getUtenteAutorizzatoreId() {
		return utenteAutorizzatoreId;
	}

	/**
	 * @param utenteAutorizzatoreId the utenteAutorizzatoreId to set
	 */
	public void setUtenteAutorizzatoreId(UUID utenteAutorizzatoreId) {
		this.utenteAutorizzatoreId = utenteAutorizzatoreId;
	}

	public List<CpassTRmsRigaRms> getCpassTRmsRigaRms() {
		return this.cpassTRmsRigaRms;
	}

	public void setCpassTRmsRigaRms(List<CpassTRmsRigaRms> cpassTRmsRigaRms) {
		this.cpassTRmsRigaRms = cpassTRmsRigaRms;
	}

	public CpassTRmsRigaRms addCpassTRmsRigaRms(CpassTRmsRigaRms cpassTRmsRigaRms) {
		getCpassTRmsRigaRms().add(cpassTRmsRigaRms);
		cpassTRmsRigaRms.setCpassTRmsTestataRms(this);

		return cpassTRmsRigaRms;
	}

	public CpassTRmsRigaRms removeCpassTRmsRigaRms(CpassTRmsRigaRms cpassTRmsRigaRms) {
		getCpassTRmsRigaRms().remove(cpassTRmsRigaRms);
		cpassTRmsRigaRms.setCpassTRmsTestataRms(null);

		return cpassTRmsRigaRms;
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



	/**
	 * @return the cpassTSettoreDestinatario
	 */
	public CpassTSettore getCpassTSettoreDestinatario() {
		return cpassTSettoreDestinatario;
	}

	/**
	 * @param cpassTSettoreDestinatario the cpassTSettoreDestinatario to set
	 */
	public void setCpassTSettoreDestinatario(CpassTSettore cpassTSettoreDestinatario) {
		this.cpassTSettoreDestinatario = cpassTSettoreDestinatario;
	}

	/**
	 * @return the cpassTSettoreEmittente
	 */
	public CpassTSettore getCpassTSettoreEmittente() {
		return cpassTSettoreEmittente;
	}

	/**
	 * @param cpassTSettoreEmittente the cpassTSettoreEmittente to set
	 */
	public void setCpassTSettoreEmittente(CpassTSettore cpassTSettoreEmittente) {
		this.cpassTSettoreEmittente = cpassTSettoreEmittente;
	}

	public CpassTUtente getCpassTUtente() {
		return this.cpassTUtente;
	}

	public void setCpassTUtente(CpassTUtente cpassTUtente) {
		this.cpassTUtente = cpassTUtente;
	}


	public String getMotivoRifiuto() {
		return motivoRifiuto;
	}

	public void setMotivoRifiuto(String motivoRifiuto) {
		this.motivoRifiuto = motivoRifiuto;
	}


	/**
	 * @return the cpassTSettoreIndirizzo
	 */
	public CpassTSettoreIndirizzo getCpassTSettoreIndirizzo() {
		return cpassTSettoreIndirizzo;
	}

	/**
	 * @param cpassTSettoreIndirizzo the cpassTSettoreIndirizzo to set
	 */
	public void setCpassTSettoreIndirizzo(CpassTSettoreIndirizzo cpassTSettoreIndirizzo) {
		this.cpassTSettoreIndirizzo = cpassTSettoreIndirizzo;
	}

	@Override
	public UUID getId() {
		return testataRmsId;
	}

	@Override
	public void setId(UUID id) {
		testataRmsId = id;
	}

	@Override
	public void initId() {
		this.testataRmsId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, rmsAnno+"|" + rmsNumero + "|" + cpassTEnte.getId());
	}

}
