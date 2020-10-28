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
package it.csi.cpass.cpassbe.ejb.entity.pba;

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
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_programma database table.
 *
 */
@Entity
@Table(name="cpass_t_pba_programma")
@NamedQuery(name="CpassTPbaProgramma.findAll", query="SELECT c FROM CpassTPbaProgramma c")
public class CpassTPbaProgramma extends BaseAuditedEntity<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4253381669229016849L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_programma");

	/** The programma id. */
	@Id
	@Column(name="programma_id", unique=true, nullable=false)
	private UUID programmaId;

	/** The programma anno. */
	@Column(name="programma_anno", nullable=false)
	private Integer programmaAnno;

	/** The programmaDescrizione. */
	@Column(name="programma_descrizione")
	private String programmaDescrizione;
	
	/** The programma numeroProvvedimento. */
	@Column(name="numero_provvedimento", nullable=false)
	private Integer numeroProvvedimento;

	/** The descrizioneProvvedimento. */
	@Column(name="descrizione_provvedimento", nullable=false)
	private String descrizioneProvvedimento;
	
	/** The dataProvvedimento. */
	@Column(name="data_provvedimento", nullable=false)
	private Date dataProvvedimento;
	
	/** The dataPubblicazione. */
	@Column(name="data_pubblicazione", nullable=false)
	private Date dataPubblicazione;
	
	/** The url. */
	@Column(name="url", nullable=false)
	private String url ;

	@Column(name="programma_versione")
	private Integer programmaVersione;

	/** The programma_codice_mit. */
	@Column(name="programma_codice_mit", nullable=false)
	private String programmaCodiceMit ;
	
	/** The dataApprovazione. */
	@Column(name="data_approvazione")
	private Date dataApprovazione;
	
	/**
	 * @return the programmaDescrizione
	 */
	public String getProgrammaDescrizione() {
		return programmaDescrizione;
	}

	/**
	 * @param programmaDescrizione the programmaDescrizione to set
	 */
	public void setProgrammaDescrizione(String programmaDescrizione) {
		this.programmaDescrizione = programmaDescrizione;
	}

	/** The cpass T interventos. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@OneToMany(mappedBy="cpassTPbaProgramma")
	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/** The cpass T ente. */
	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id", nullable=false)
	private CpassTEnte cpassTEnte;

	/** The cpass D stato. */
	//bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name="stato_id", nullable=false)
	private CpassDStato cpassDStato;

	/** The Cpass T Utente. */
	//bi-directional many-to-one association to CpassDCpv
	@ManyToOne
	@JoinColumn(name="utente_referente_id", nullable=false)
	private CpassTUtente cpassTUtenteReferente;

	/** The url. */
	@Column(name="id_ricevuto_mit")
	private Long idRicevutoMit ;
	
	
	/**
	 * Gets the programma id.
	 *
	 * @return the programma id
	 */
	public UUID getProgrammaId() {
		return this.programmaId;
	}

	/**
	 * Sets the programma id.
	 *
	 * @param programmaId the new programma id
	 */
	public void setProgrammaId(UUID programmaId) {
		this.programmaId = programmaId;
	}

	/**
	 * Gets the programma anno.
	 *
	 * @return the programma anno
	 */
	public Integer getProgrammaAnno() {
		return this.programmaAnno;
	}

	/**
	 * Sets the programma anno.
	 *
	 * @param programmaAnno the new programma anno
	 */
	public void setProgrammaAnno(Integer programmaAnno) {
		this.programmaAnno = programmaAnno;
	}

	/**
	 * Gets the cpass T interventos.
	 *
	 * @return the cpass T interventos
	 */
	public List<CpassTPbaIntervento> getCpassTPbaInterventos() {
		return this.cpassTPbaInterventos;
	}

	/**
	 * Sets the cpass T interventos.
	 *
	 * @param cpassTPbaInterventos the new cpass T interventos
	 */
	public void setCpassTPbaInterventos(List<CpassTPbaIntervento> cpassTPbaInterventos) {
		this.cpassTPbaInterventos = cpassTPbaInterventos;
	}

	
	/**
	 * @return the idRicevutoMit
	 */
	public Long getIdRicevutoMit() {
		return idRicevutoMit;
	}

	/**
	 * @param idRicevutoMit the idRicevutoMit to set
	 */
	public void setIdRicevutoMit(Long idRicevutoMit) {
		this.idRicevutoMit = idRicevutoMit;
	}

	/**
	 * Adds the cpass T intervento.
	 *
	 * @param cpassTPbaIntervento the cpass T intervento
	 * @return the cpass T intervento
	 */
	public CpassTPbaIntervento addCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		getCpassTPbaInterventos().add(cpassTPbaIntervento);
		cpassTPbaIntervento.setCpassTPbaProgramma(this);

		return cpassTPbaIntervento;
	}

	/**
	 * Removes the cpass T intervento.
	 *
	 * @param cpassTPbaIntervento the cpass T intervento
	 * @return the cpass T intervento
	 */
	public CpassTPbaIntervento removeCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		getCpassTPbaInterventos().remove(cpassTPbaIntervento);
		cpassTPbaIntervento.setCpassTPbaProgramma(null);

		return cpassTPbaIntervento;
	}

	/**
	 * Gets the cpass T ente.
	 *
	 * @return the cpass T ente
	 */
	public CpassTEnte getCpassTEnte() {
		return this.cpassTEnte;
	}

	/**
	 * Sets the cpass T ente.
	 *
	 * @param cpassTEnte the new cpass T ente
	 */
	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	/**
	 * @return the cpassDStato
	 */
	public CpassDStato getCpassDStato() {
		return cpassDStato;
	}

	/**
	 * @param cpassDStato the cpassDStato to set
	 */
	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	/**
	 * @return the numeroProvvedimento
	 */
	public Integer getNumeroProvvedimento() {
		return numeroProvvedimento;
	}

	/**
	 * @param numeroProvvedimento the numeroProvvedimento to set
	 */
	public void setNumeroProvvedimento(Integer numeroProvvedimento) {
		this.numeroProvvedimento = numeroProvvedimento;
	}

	/**
	 * @return the descrizioneProvvedimento
	 */
	public String getDescrizioneProvvedimento() {
		return descrizioneProvvedimento;
	}

	/**
	 * @param descrizioneProvvedimento the descrizioneProvvedimento to set
	 */
	public void setDescrizioneProvvedimento(String descrizioneProvvedimento) {
		this.descrizioneProvvedimento = descrizioneProvvedimento;
	}

	/**
	 * @return the dataProvvedimento
	 */
	public Date getDataProvvedimento() {
		return dataProvvedimento;
	}

	/**
	 * @param dataProvvedimento the dataProvvedimento to set
	 */
	public void setDataProvvedimento(Date dataProvvedimento) {
		this.dataProvvedimento = dataProvvedimento;
	}

	/**
	 * @return the dataPubblicazione
	 */
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	/**
	 * @param dataPubblicazione the dataPubblicazione to set
	 */
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	
	/**
	 * @return the cpassTUtenteReferente
	 */
	public CpassTUtente getCpassTUtenteReferente() {
		return cpassTUtenteReferente;
	}

	/**
	 * @param cpassTUtenteReferente the cpassTUtenteReferente to set
	 */
	public void setCpassTUtenteReferente(CpassTUtente cpassTUtenteReferente) {
		this.cpassTUtenteReferente = cpassTUtenteReferente;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	
	@Override
	public UUID getId() {
		return programmaId;
	}

	@Override
	public void setId(UUID id) {
		programmaId = id;
	}

	/**
	 * @return the programmaVersione
	 */
	public Integer getProgrammaVersione() {
		return programmaVersione;
	}

	/**
	 * @param programmaVersione the programmaVersione to set
	 */
	public void setProgrammaVersione(Integer programmaVersione) {
		this.programmaVersione = programmaVersione;
	}

	
	/**
	 * @return the programmaCodiceMit
	 */
	public String getProgrammaCodiceMit() {
		return programmaCodiceMit;
	}

	/**
	 * @param programmaCodiceMit the programmaCodiceMit to set
	 */
	public void setProgrammaCodiceMit(String programmaCodiceMit) {
		this.programmaCodiceMit = programmaCodiceMit;
	}

	
	/**
	 * @return the dataApprovazione
	 */
	public Date getDataApprovazione() {
		return dataApprovazione;
	}

	/**
	 * @param dataApprovazione the dataApprovazione to set
	 */
	public void setDataApprovazione(Date dataApprovazione) {
		this.dataApprovazione = dataApprovazione;
	}

	@Override
	public void initId() {
		if(cpassTEnte != null && cpassTEnte.getEnteId() != null) {
			this.programmaId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTEnte.getId().toString() + "_" + programmaAnno + "_"+programmaVersione);
		}
	}
}
