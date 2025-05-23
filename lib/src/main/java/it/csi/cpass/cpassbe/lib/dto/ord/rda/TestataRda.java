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
package it.csi.cpass.cpassbe.lib.dto.ord.rda;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Ufficio;
import it.csi.cpass.cpassbe.lib.dto.Utente;
import it.csi.cpass.cpassbe.lib.dto.ord.TestataOrdine;

public class TestataRda extends BaseAuditedDto<UUID> implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer anno; // rda_anno
	private Date dataAutorizzazione; // data_autorizzazione
	private String descrizione; // descrizione
	private String note;
	private Integer numero; // rda_numero
	private Stato stato; // vedi stato_id
	private Ente ente; // vedi ente_id
	private Settore settoreEmittente; // vedi settore_emittente_id
	private Ufficio ufficio;
	private Utente utenteCompilatore; // vedi utente_compilatore_id
	private List<RigaRda> rigaRda;
	private List<TestataOrdine> testataOrdines;



	/** Default constructor */
	public TestataRda() {
	}

	/**
	 * Constructor
	 *
	 * @param id the id
	 */
	public TestataRda(UUID id) {
		super(id);
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Date getDataAutorizzazione() {
		return dataAutorizzazione;
	}

	public void setDataAutorizzazione(Date dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public Settore getSettoreEmittente() {
		return settoreEmittente;
	}

	public void setSettoreEmittente(Settore settoreEmittente) {
		this.settoreEmittente = settoreEmittente;
	}


	public Ufficio getUfficio() {
		return ufficio;
	}

	public void setUfficio(Ufficio ufficio) {
		this.ufficio = ufficio;
	}

	public Utente getUtenteCompilatore() {
		return utenteCompilatore;
	}

	public void setUtenteCompilatore(Utente utenteCompilatore) {
		this.utenteCompilatore = utenteCompilatore;
	}

	/**
	 * @return the rigaRda
	 */
	public List<RigaRda> getRigaRda() {
		return rigaRda;
	}

	/**
	 * @param rigaRda the rigaRda to set
	 */
	public void setRigaRda(List<RigaRda> rigaRda) {
		this.rigaRda = rigaRda;
	}

	public List<TestataOrdine> getTestataOrdines() {
		return testataOrdines;
	}

	public void setTestataOrdines(List<TestataOrdine> testataOrdines) {
		this.testataOrdines = testataOrdines;
	}

}
