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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the CpassTMetadatiFunzione database table.
 *
 */
@Entity
@Table(name="cpass_t_metadati_funzione")
@NamedQuery(name="CpassTMetadatiFunzione.findAll", query="SELECT c FROM CpassTMetadatiFunzione c")
public class CpassTMetadatiFunzione implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The modulo id. */
	@Id
	@SequenceGenerator(name="CPASS_T_METADATI_FUNZIONEID_GENERATOR", sequenceName="CPASS_T_METADATI_FUNZIONE_METADATI_FUNZIONE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_METADATI_FUNZIONEID_GENERATOR")
	@Column(name="metadati_funzione_id", unique=true, nullable=false)
	private Integer metadatiFunzioneId;

	//bi-directional many-to-one association to CpassTUtente
	//@ManyToOne
	//@JoinColumn(name="utente_id")
	//private CpassTUtente cpassTUtente;
	
	@Column(name="modulo")
	private String modulo;
	
	@Column(name="funzione")
	private String funzione;
	
	@Column(name="chiave_colonna")
	private String chiaveColonna;

	@Column(name="descrizione_colonna")
	private String descrizioneColonna;

	@Column(name="stringa_sql")
	private String stringaSql;
	
	@Column(name="jpql")
	private String jpql;
 
	@Column(name="ordinamento_layout")
	private Integer ordinamentoLayout;

	/**
	 * @return the modulo
	 */
	public String getModulo() {
		return modulo;
	}

	/**
	 * @param modulo the modulo to set
	 */
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	/**
	 * @return the funzione
	 */
	public String getFunzione() {
		return funzione;
	}

	/**
	 * @return the chiaveColonna
	 */
	public String getChiaveColonna() {
		return chiaveColonna;
	}

	/**
	 * @param chiaveColonna the chiaveColonna to set
	 */
	public void setChiaveColonna(String chiaveColonna) {
		this.chiaveColonna = chiaveColonna;
	}

	/**
	 * @return the descrizioneColonna
	 */
	public String getDescrizioneColonna() {
		return descrizioneColonna;
	}

	/**
	 * @param descrizioneColonna the descrizioneColonna to set
	 */
	public void setDescrizioneColonna(String descrizioneColonna) {
		this.descrizioneColonna = descrizioneColonna;
	}

	/**
	 * @param funzione the funzione to set
	 */
	public void setFunzione(String funzione) {
		this.funzione = funzione;
	}

	/**
	 * @return the metadatiFunzioneId
	 */
	public Integer getMetadatiFunzioneId() {
		return metadatiFunzioneId;
	}

	/**
	 * @param metadatiFunzioneId the metadatiFunzioneId to set
	 */
	public void setMetadatiFunzioneId(Integer metadatiFunzioneId) {
		this.metadatiFunzioneId = metadatiFunzioneId;
	}

	/**
	 * @return the stringaSql
	 */
	public String getStringaSql() {
		return stringaSql;
	}

	/**
	 * @param stringaSql the stringaSql to set
	 */
	public void setStringaSql(String stringaSql) {
		this.stringaSql = stringaSql;
	}

	/**
	 * @return the jpql
	 */
	public String getJpql() {
		return jpql;
	}

	/**
	 * @param jpql the jpql to set
	 */
	public void setJpql(String jpql) {
		this.jpql = jpql;
	}

	
	/**
	 * @return the ordinamentoLayout
	 */
	public Integer getOrdinamentoLayout() {
		return ordinamentoLayout;
	}

	/**
	 * @param ordinamentoLayout the ordinamentoLayout to set
	 */
	public void setOrdinamentoLayout(Integer ordinamentoLayout) {
		this.ordinamentoLayout = ordinamentoLayout;
	}

	@Override
	public Integer getId() {
		return metadatiFunzioneId;
	}

	@Override
	public void setId(Integer id) {
		metadatiFunzioneId = id;
	}

}
