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
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_v_settore database view.
 *
 */
@Entity
@Table(name="cpass_v_ultimo_programma_biennio")
@NamedQuery(name="CpassVUltimoProgrammaBiennio.findAll", query="SELECT c FROM CpassVUltimoProgrammaBiennio c")
public class CpassVUltimoProgrammaBiennio implements Serializable, BaseEntity<UUID>  {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="programma_id")
	private UUID programmaId;

	@Column(name="data_approvazione")
	private Date dataApprovazione;

	@Column(name="data_cancellazione")
	private Date dataCancellazione;

	@Column(name="data_creazione")
	private Date dataCreazione;

	@Column(name="data_modifica")
	private Date dataModifica;

	@Column(name="data_provvedimento")
	private Date dataProvvedimento;

	@Column(name="data_pubblicazione")
	private Date dataPubblicazione;

	@Column(name="data_trasmissione_mit")
	private Date dataTrasmissioneMit;

	@Column(name="descrizione_provvedimento")
	private String descrizioneProvvedimento;

	@Column(name="ente_id")
	private UUID enteId;

	@Column(name="id_ricevuto_mit")
	private Long idRicevutoMit;

	@Column(name="numero_provvedimento")
	private String numeroProvvedimento;

	private UUID optlock;

	@Column(name="programma_anno")
	private Integer programmaAnno;

	@Column(name="programma_codice_mit")
	private String programmaCodiceMit;

	@Column(name="programma_descrizione")
	private String programmaDescrizione;


	@Column(name="programma_versione")
	private Integer programmaVersione;

	@Column(name="stato_id")
	private Integer statoId;

	private String url;

	@Column(name="utente_cancellazione")
	private String utenteCancellazione;

	@Column(name="utente_creazione")
	private String utenteCreazione;

	@Column(name="utente_modifica")
	private String utenteModifica;

	@Column(name="utente_referente_id")
	private UUID utenteReferenteId;

	public CpassVUltimoProgrammaBiennio() {
	}

	public Date getDataApprovazione() {
		return this.dataApprovazione;
	}

	public void setDataApprovazione(Date dataApprovazione) {
		this.dataApprovazione = dataApprovazione;
	}

	public Date getDataCancellazione() {
		return this.dataCancellazione;
	}

	public void setDataCancellazione(Date dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	public Date getDataCreazione() {
		return this.dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public Date getDataProvvedimento() {
		return this.dataProvvedimento;
	}

	public void setDataProvvedimento(Date dataProvvedimento) {
		this.dataProvvedimento = dataProvvedimento;
	}

	public Date getDataPubblicazione() {
		return this.dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public Date getDataTrasmissioneMit() {
		return this.dataTrasmissioneMit;
	}

	public void setDataTrasmissioneMit(Date dataTrasmissioneMit) {
		this.dataTrasmissioneMit = dataTrasmissioneMit;
	}

	public String getDescrizioneProvvedimento() {
		return this.descrizioneProvvedimento;
	}

	public void setDescrizioneProvvedimento(String descrizioneProvvedimento) {
		this.descrizioneProvvedimento = descrizioneProvvedimento;
	}

	public UUID getEnteId() {
		return this.enteId;
	}

	public void setEnteId(UUID enteId) {
		this.enteId = enteId;
	}

	public Long getIdRicevutoMit() {
		return this.idRicevutoMit;
	}

	public void setIdRicevutoMit(Long idRicevutoMit) {
		this.idRicevutoMit = idRicevutoMit;
	}

	public String getNumeroProvvedimento() {
		return this.numeroProvvedimento;
	}

	public void setNumeroProvvedimento(String numeroProvvedimento) {
		this.numeroProvvedimento = numeroProvvedimento;
	}

	public UUID getOptlock() {
		return this.optlock;
	}

	public void setOptlock(UUID optlock) {
		this.optlock = optlock;
	}

	public Integer getProgrammaAnno() {
		return this.programmaAnno;
	}

	public void setProgrammaAnno(Integer programmaAnno) {
		this.programmaAnno = programmaAnno;
	}

	public String getProgrammaCodiceMit() {
		return this.programmaCodiceMit;
	}

	public void setProgrammaCodiceMit(String programmaCodiceMit) {
		this.programmaCodiceMit = programmaCodiceMit;
	}

	public String getProgrammaDescrizione() {
		return this.programmaDescrizione;
	}

	public void setProgrammaDescrizione(String programmaDescrizione) {
		this.programmaDescrizione = programmaDescrizione;
	}

	public UUID getProgrammaId() {
		return this.programmaId;
	}

	public void setProgrammaId(UUID programmaId) {
		this.programmaId = programmaId;
	}

	public Integer getProgrammaVersione() {
		return this.programmaVersione;
	}

	public void setProgrammaVersione(Integer programmaVersione) {
		this.programmaVersione = programmaVersione;
	}

	public Integer getStatoId() {
		return this.statoId;
	}

	public void setStatoId(Integer statoId) {
		this.statoId = statoId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUtenteCancellazione() {
		return this.utenteCancellazione;
	}

	public void setUtenteCancellazione(String utenteCancellazione) {
		this.utenteCancellazione = utenteCancellazione;
	}

	public String getUtenteCreazione() {
		return this.utenteCreazione;
	}

	public void setUtenteCreazione(String utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}

	public String getUtenteModifica() {
		return this.utenteModifica;
	}

	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}

	public UUID getUtenteReferenteId() {
		return this.utenteReferenteId;
	}

	public void setUtenteReferenteId(UUID utenteReferenteId) {
		this.utenteReferenteId = utenteReferenteId;
	}

	@Override
	public UUID getId() {
		return programmaId;
	}

	@Override
	public void setId(UUID id) {
		programmaId = id;
	}

}