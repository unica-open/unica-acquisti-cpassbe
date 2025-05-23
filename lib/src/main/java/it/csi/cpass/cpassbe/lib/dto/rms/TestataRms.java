/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.rms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.SettoreIndirizzo;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;


/**
 * The persistent class for the cpass_t_rms_testata_rms database table.
 *
 */
public class TestataRms extends BaseAuditedDto<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date dataAutorizzazione;
	private Date dataConferma;
	private String destinatarioCap;
	private String destinatarioContatto;
	private String destinatarioEmail;
	private String sede;
	private String destinatarioIndirizzo;
	private String destinatarioLocalita;
	private String destinatarioNumCivico;
	private String destinatarioProvincia;
	private String destinatarioTelefono;
	private String note;
	private String noteRichiedente;
	private Boolean richiestaMagazzino;
	private Integer rmsAnno;
	private String rmsDescrizione;
	private Integer rmsNumero;
	private List<RigaRms> rigaRms;
	private Stato stato;
	private Ente ente;
	private Settore settoreDestinatario;
	private Settore settoreEmittente;
	private Utente utente;
	private String motivoRifiuto;
	private SettoreIndirizzo settoreIndirizzo;
	private UUID utenteAutorizzatoreId;

	/**
	 * Default constructor
	 */
	public TestataRms() {
		super();
	}

	/**
	 * Constructor
	 * @param id
	 */
	public TestataRms(UUID id) {
		super(id);
	}

	/**
	 * @return the dataAutorizzazione
	 */
	public Date getDataAutorizzazione() {
		return dataAutorizzazione;
	}

	/**
	 * @param dataAutorizzazione the dataAutorizzazione to set
	 */
	public void setDataAutorizzazione(Date dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}

	/**
	 * @return the dataConferma
	 */
	public Date getDataConferma() {
		return dataConferma;
	}

	/**
	 * @param dataConferma the dataConferma to set
	 */
	public void setDataConferma(Date dataConferma) {
		this.dataConferma = dataConferma;
	}

	/**
	 * @return the destinatarioCap
	 */
	public String getDestinatarioCap() {
		return destinatarioCap;
	}

	/**
	 * @param destinatarioCap the destinatarioCap to set
	 */
	public void setDestinatarioCap(String destinatarioCap) {
		this.destinatarioCap = destinatarioCap;
	}

	/**
	 * @return the destinatarioContatto
	 */
	public String getDestinatarioContatto() {
		return destinatarioContatto;
	}

	/**
	 * @param destinatarioContatto the destinatarioContatto to set
	 */
	public void setDestinatarioContatto(String destinatarioContatto) {
		this.destinatarioContatto = destinatarioContatto;
	}

	/**
	 * @return the destinatarioEmail
	 */
	public String getDestinatarioEmail() {
		return destinatarioEmail;
	}

	/**
	 * @param destinatarioEmail the destinatarioEmail to set
	 */
	public void setDestinatarioEmail(String destinatarioEmail) {
		this.destinatarioEmail = destinatarioEmail;
	}


	/**
	 * @return the destinatarioSede
	 */
	public String getSede() {
		return sede;
	}

	/**
	 * @param destinatarioSede the destinatarioSede to set
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}

	/**
	 * @return the destinatarioIndirizzo
	 */
	public String getDestinatarioIndirizzo() {
		return destinatarioIndirizzo;
	}

	/**
	 * @param destinatarioIndirizzo the destinatarioIndirizzo to set
	 */
	public void setDestinatarioIndirizzo(String destinatarioIndirizzo) {
		this.destinatarioIndirizzo = destinatarioIndirizzo;
	}

	/**
	 * @return the destinatarioLocalita
	 */
	public String getDestinatarioLocalita() {
		return destinatarioLocalita;
	}

	/**
	 * @param destinatarioLocalita the destinatarioLocalita to set
	 */
	public void setDestinatarioLocalita(String destinatarioLocalita) {
		this.destinatarioLocalita = destinatarioLocalita;
	}

	/**
	 * @return the destinatarioNumCivico
	 */
	public String getDestinatarioNumCivico() {
		return destinatarioNumCivico;
	}

	/**
	 * @param destinatarioNumCivico the destinatarioNumCivico to set
	 */
	public void setDestinatarioNumCivico(String destinatarioNumCivico) {
		this.destinatarioNumCivico = destinatarioNumCivico;
	}

	/**
	 * @return the destinatarioProvincia
	 */
	public String getDestinatarioProvincia() {
		return destinatarioProvincia;
	}

	/**
	 * @param destinatarioProvincia the destinatarioProvincia to set
	 */
	public void setDestinatarioProvincia(String destinatarioProvincia) {
		this.destinatarioProvincia = destinatarioProvincia;
	}

	/**
	 * @return the destinatarioTelefono
	 */
	public String getDestinatarioTelefono() {
		return destinatarioTelefono;
	}

	/**
	 * @param destinatarioTelefono the destinatarioTelefono to set
	 */
	public void setDestinatarioTelefono(String destinatarioTelefono) {
		this.destinatarioTelefono = destinatarioTelefono;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the noteRichiedente
	 */
	public String getNoteRichiedente() {
		return noteRichiedente;
	}

	/**
	 * @param noteRichiedente the noteRichiedente to set
	 */
	public void setNoteRichiedente(String noteRichiedente) {
		this.noteRichiedente = noteRichiedente;
	}

	/**
	 * @return the richiestaMagazzino
	 */
	public Boolean getRichiestaMagazzino() {
		return richiestaMagazzino;
	}

	/**
	 * @param richiestaMagazzino the richiestaMagazzino to set
	 */
	public void setRichiestaMagazzino(Boolean richiestaMagazzino) {
		this.richiestaMagazzino = richiestaMagazzino;
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
	 * @return the rmsDescrizione
	 */
	public String getRmsDescrizione() {
		return rmsDescrizione;
	}

	/**
	 * @param rmsDescrizione the rmsDescrizione to set
	 */
	public void setRmsDescrizione(String rmsDescrizione) {
		this.rmsDescrizione = rmsDescrizione;
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
	 * @return the rigaRms
	 */
	public List<RigaRms> getRigaRms() {
		return rigaRms;
	}

	/**
	 * @param rigaRms the rigaRms to set
	 */
	public void setRigaRms(List<RigaRms> rigaRms) {
		this.rigaRms = rigaRms;
	}

	/**
	 * @return the stato
	 */
	public Stato getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}

	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}



	/**
	 * @return the settoreDestinatario
	 */
	public Settore getSettoreDestinatario() {
		return settoreDestinatario;
	}

	/**
	 * @param settoreDestinatario the settoreDestinatario to set
	 */
	public void setSettoreDestinatario(Settore settoreDestinatario) {
		this.settoreDestinatario = settoreDestinatario;
	}

	/**
	 * @return the settoreEmittente
	 */
	public Settore getSettoreEmittente() {
		return settoreEmittente;
	}

	/**
	 * @param settoreEmittente the settoreEmittente to set
	 */
	public void setSettoreEmittente(Settore settoreEmittente) {
		this.settoreEmittente = settoreEmittente;
	}

	/**
	 * @return the utente
	 */
	public Utente getUtente() {
		return utente;
	}

	/**
	 * @param utente the utente to set
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String getMotivoRifiuto() {
		return motivoRifiuto;
	}

	public void setMotivoRifiuto(String motivoRifiuto) {
		this.motivoRifiuto = motivoRifiuto;
	}

	/**
	 * @return the settoreIndirizzo
	 */
	public SettoreIndirizzo getSettoreIndirizzo() {
		return settoreIndirizzo;
	}

	/**
	 * @param settoreIndirizzo the settoreIndirizzo to set
	 */
	public void setSettoreIndirizzo(SettoreIndirizzo settoreIndirizzo) {
		this.settoreIndirizzo = settoreIndirizzo;
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



}
