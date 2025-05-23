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
package it.csi.cpass.cpassbe.ejb.entity.mag;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the cpass_d_stato database table.
 *
 */
@Entity
@Table(name="cpass_t_mag_magazzino")
@NamedQuery(name="CpassTMagMagazzino.findAll", query="SELECT c FROM CpassTMagMagazzino c")
public class CpassTMagMagazzino extends BaseAuditedEntity<Integer> implements Serializable {
	//public class CpassTMagMagazzino implements BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The magazzino id. */
	@Id
	@SequenceGenerator(name="cpass_t_mag_magazzino_magazzino_id_seq_GENERATOR", sequenceName="cpass_t_mag_magazzino_magazzino_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cpass_t_mag_magazzino_magazzino_id_seq_GENERATOR")
	@Column(name="magazzino_id", unique=true, nullable=false)
	private Integer magazzinoId;

	/** The magazzino codice. */
	@Column(name="magazzino_codice", nullable=false, length=50)
	private String magazzinoCodice;

	/** The magazzino descrizione. */
	@Column(name="magazzino_descrizione", nullable=false, length=200)
	private String magazzinoDescrizione;

	/** The cpass T ente. */
	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id", nullable=false)
	private CpassTEnte cpassTEnte;

	/**
	 * @return the magazzinoId
	 */
	public Integer getMagazzinoId() {
		return magazzinoId;
	}

	/**
	 * @param magazzinoId the magazzinoId to set
	 */
	public void setMagazzinoId(Integer magazzinoId) {
		this.magazzinoId = magazzinoId;
	}

	/**
	 * @return the magazzinoCodice
	 */
	public String getMagazzinoCodice() {
		return magazzinoCodice;
	}

	/**
	 * @param magazzinoCodice the magazzinoCodice to set
	 */
	public void setMagazzinoCodice(String magazzinoCodice) {
		this.magazzinoCodice = magazzinoCodice;
	}

	/**
	 * @return the magazzinoDescrizione
	 */
	public String getMagazzinoDescrizione() {
		return magazzinoDescrizione;
	}

	/**
	 * @param magazzinoDescrizione the magazzinoDescrizione to set
	 */
	public void setMagazzinoDescrizione(String magazzinoDescrizione) {
		this.magazzinoDescrizione = magazzinoDescrizione;
	}

	/**
	 * @return the cpassTEnte
	 */
	public CpassTEnte getCpassTEnte() {
		return cpassTEnte;
	}

	/**
	 * @param cpassTEnte the cpassTEnte to set
	 */
	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	@Override
	public Integer getId() {
		return magazzinoId;
	}

	@Override
	public void setId(Integer id) {
		magazzinoId = id;
	}

}
