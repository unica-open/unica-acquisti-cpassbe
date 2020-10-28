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
package it.csi.cpass.cpassbe.ejb.entity;

import java.io.Serializable;
import java.util.Date;
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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;

/**
 * The persistent class for the cpass_d_ausa database table.
 *
 */
@Entity
@Table(name="cpass_t_elaborazione")
@NamedQuery(name="CpassTElaborazione.findAll", query="SELECT c FROM CpassTElaborazione c")
public class CpassTElaborazione implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ausa id. */
	@Id
	@SequenceGenerator(name="CPASS_T_ELABORAZIONE_ELABORAZIONEID_GENERATOR", sequenceName="CPASS_T_ELABORAZIONE_ELABORAZIONE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_ELABORAZIONE_ELABORAZIONEID_GENERATOR")
	@Column(name="elaborazione_id")
	private Integer elaborazioneId;

	@Column(name="entita_id") 
	private String entitaId;
	
	@Column(name="elaborazione_id_esterno")
	private String elaborazione_id_esterno;
	
	@Column(name="elaborazione_utente")
	private String elaborazioneUtente;
	
	@Column(name="elaborazione_stato") 
	private String elaborazioneStato;
	
	@Column(name="elaborazione_data") 
	private Date elaborazioneData;
	
	@Column(name="elaborazione_esito")
	private String elaborazioneEsito;

	/** The cpass T ElaborazioneMessaggio. */
	//bi-directional many-to-one association to ElaborazioneMessaggio
	@OneToMany(mappedBy="cpassTElaborazione")
	private List<CpassTElaborazioneMessaggio> cpassTElaborazioneMessaggios;
	
	/** The cpass T ElaborazioneMessaggio. */
	//bi-directional many-to-one association to ElaborazioneMessaggio
	@OneToMany(mappedBy="cpassTElaborazione")
	private List<CpassTElaborazioneParametro> cpassTElaborazioneParametros;
	
	/** CpassDElaborazioneTipo. */
	//bi-directional many-to-one association to CpassDElaborazioneTipo
	@ManyToOne
	@JoinColumn(name="elaborazione_tipo_id", nullable=false)
	private CpassDElaborazioneTipo cpassDElaborazioneTipo;

	/**
	 * @return the elaborazioneId
	 */
	public Integer getElaborazioneId() {
		return elaborazioneId;
	}

	/**
	 * @param elaborazioneId the elaborazioneId to set
	 */
	public void setElaborazioneId(Integer elaborazioneId) {
		this.elaborazioneId = elaborazioneId;
	}

	/**
	 * @return the entitaId
	 */
	public String getEntitaId() {
		return entitaId;
	}

	/**
	 * @param entitaId the entitaId to set
	 */
	public void setEntitaId(String entitaId) {
		this.entitaId = entitaId;
	}

	/**
	 * @return the elaborazioneUtente
	 */
	public String getElaborazioneUtente() {
		return elaborazioneUtente;
	}

	/**
	 * @param elaborazioneUtente the elaborazioneUtente to set
	 */
	public void setElaborazioneUtente(String elaborazioneUtente) {
		this.elaborazioneUtente = elaborazioneUtente;
	}

	/**
	 * @return the elaborazioneStato
	 */
	public String getElaborazioneStato() {
		return elaborazioneStato;
	}

	/**
	 * @param elaborazioneStato the elaborazioneStato to set
	 */
	public void setElaborazioneStato(String elaborazioneStato) {
		this.elaborazioneStato = elaborazioneStato;
	}

	/**
	 * @return the elaborazioneData
	 */
	public Date getElaborazioneData() {
		return elaborazioneData;
	}

	/**
	 * @param elaborazioneData the elaborazioneData to set
	 */
	public void setElaborazioneData(Date elaborazioneData) {
		this.elaborazioneData = elaborazioneData;
	}

	/**
	 * @return the elaborazioneEsito
	 */
	public String getElaborazioneEsito() {
		return elaborazioneEsito;
	}

	/**
	 * @param elaborazioneEsito the elaborazioneEsito to set
	 */
	public void setElaborazioneEsito(String elaborazioneEsito) {
		this.elaborazioneEsito = elaborazioneEsito;
	}

	
	/**
	 * @return the cpassTbaElaborazioneMessaggios
	 */
	public List<CpassTElaborazioneMessaggio> getCpassTElaborazioneMessaggios() {
		return cpassTElaborazioneMessaggios;
	}

	/**
	 * @param cpassTbaElaborazioneMessaggios the cpassTbaElaborazioneMessaggios to set
	 */
	public void setCpassTElaborazioneMessaggios(List<CpassTElaborazioneMessaggio> cpassTElaborazioneMessaggios) {
		this.cpassTElaborazioneMessaggios = cpassTElaborazioneMessaggios;
	}

	/**
	 * @return the cpassTElaborazioneParametros
	 */
	public List<CpassTElaborazioneParametro> getCpassTElaborazioneParametros() {
		return cpassTElaborazioneParametros;
	}

	/**
	 * @param cpassTElaborazioneParametros the cpassTElaborazioneParametros to set
	 */
	public void setCpassTElaborazioneParametros(List<CpassTElaborazioneParametro> cpassTElaborazioneParametros) {
		this.cpassTElaborazioneParametros = cpassTElaborazioneParametros;
	}

	/**
	 * @return the cpassDElaborazioneTipo
	 */
	public CpassDElaborazioneTipo getCpassDElaborazioneTipo() {
		return cpassDElaborazioneTipo;
	}

	/**
	 * @param cpassDElaborazioneTipo the cpassDElaborazioneTipo to set
	 */
	public void setCpassDElaborazioneTipo(CpassDElaborazioneTipo cpassDElaborazioneTipo) {
		this.cpassDElaborazioneTipo = cpassDElaborazioneTipo;
	}

	
	/**
	 * @return the elaborazione_id_esterno
	 */
	public String getElaborazione_id_esterno() {
		return elaborazione_id_esterno;
	}

	/**
	 * @param elaborazione_id_esterno the elaborazione_id_esterno to set
	 */
	public void setElaborazione_id_esterno(String elaborazione_id_esterno) {
		this.elaborazione_id_esterno = elaborazione_id_esterno;
	}

	@Override
	public Integer getId() {
		return elaborazioneId;
	}

	@Override
	public void setId(Integer id) {
		elaborazioneId = id;
	}

}
