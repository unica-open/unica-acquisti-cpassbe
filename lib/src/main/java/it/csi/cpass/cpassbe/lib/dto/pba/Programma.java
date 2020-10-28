/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.Utente;

/**
 * The Class Programma.
 */
public class Programma extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The anno. */
	private Integer anno;
	/** The descrizione. */
	private String descrizione;	
	/** The utente referente. */	
	private Utente utenteReferente;
	/** The ente. */
	private Ente ente;
	/** The stato. */
	private Stato stato;
	/** The numeroProvvedimento. */
	private Integer numeroProvvedimento;
	/** The descrizioneProvvedimento. */
	private String descrizioneProvvedimento;
	/** The dataProvvedimento. */
	private Date dataProvvedimento;
	/** The dataPubblicazione. */
	private Date dataPubblicazione;
	/** The url. */
	private String url ;
	/** The versione. */
	private Integer versione;
	/** The programmaCodiceMit*/
	private String codiceMit;
	/** the idRicevutoMit */
	private Long idRicevutoMit;
	/** The dataApprovazione. */
	private Date dataApprovazione;

	/** Default constructor */
	public Programma() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public Programma(UUID id) {
		super(id);
	}

	/**
	 * Gets the anno.
	 * @return the anno
	 */
	public Integer getAnno() {
		return anno;
	}

	/**
	 * Sets the anno.
	 * @param anno the new anno
	 */
	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	/**
	 * Gets the ente.
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}

	/**
	 * Sets the ente.
	 * @param ente the new ente
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
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
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	
	/**
	 * @return the utenteReferente
	 */
	public Utente getUtenteReferente() {
		return utenteReferente;
	}

	/**
	 * @param utenteReferente the utenteReferente to set
	 */
	public void setUtenteReferente(Utente utenteReferente) {
		this.utenteReferente = utenteReferente;
	}

	
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
	/**
	 * @return the versione
	 */
	public Integer getVersione() {
		return versione;
	}

	/**
	 * @param versione the versione to set
	 */
	public void setVersione(Integer versione) {
		this.versione = versione;
	}

	/**
	 * @return the codiceMit
	 */
	public String getCodiceMit() {
		return codiceMit;
	}

	/**
	 * @param codiceMit the codiceMit to set
	 */
	public void setCodiceMit(String codiceMit) {
		this.codiceMit = codiceMit;
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
	public String toString() {
		return new StringBuilder()
			.append("Programma [anno=").append(anno)
			.append(", utenteReferente=").append(utenteReferente)
			.append(", ente=").append(ente)
			.append(", stato=").append(stato)		
			.append(", numeroProvvedimento=").append(numeroProvvedimento)
			.append(", descrizioneProvvedimento=").append(descrizioneProvvedimento)
			.append(", dataProvvedimento=").append(dataProvvedimento)
			.append(", dataPubblicazione=").append(dataPubblicazione)
			.append(", url=").append(url)			
			.append(", descrizione=").append(descrizione)			
			.append(", versione=").append(versione)	
			.append(", codiceMit=").append(codiceMit)	
			.append(", idRicevutoMit=").append(idRicevutoMit)	
			.append(", dataApprovazione=").append(dataApprovazione)	
			.append(innerToString())
			.append("]")
			.toString();
	}

}
