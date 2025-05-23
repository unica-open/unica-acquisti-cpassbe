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
package it.csi.cpass.cpassbe.ejb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_t_fruitore database table.
 *
 */
@Entity
@Table(name="cpass_t_fruitore")
@NamedQuery(name="CpassTFruitore.findAll", query="SELECT c FROM CpassTFruitore c")
public class CpassTFruitore implements Serializable, BaseEntity<Integer>{
	private static final long serialVersionUID = 1L;

	/** The fruitore id. */
	@Id
	@SequenceGenerator(name="CPASS_T_FRUITORE_FRUITOREID_GENERATOR", sequenceName="CPASS_T_FRUITORE_FRUITORE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_FRUITORE_FRUITOREID_GENERATOR")
	@Column(name="fruitore_id", unique=true, nullable=false)
	private Integer fruitoreId;

	@Column(name="fruitore_codice")
	private String fruitoreCodice;

	@Column(name="fruitore_descrizione")
	private String fruitoreDescrizione;

	@Column(name="fruitore_ente_codice_fiscale")
	private String fruitoreEnteCodiceFiscale;

	//bi-directional many-to-one association to CpassRFruitoreServizio
	@OneToMany(mappedBy="cpassTFruitore")
	private List<CpassRFruitoreServizio> cpassRFruitoreServizios;

	public CpassTFruitore() {
	}

	public Integer getFruitoreId() {
		return this.fruitoreId;
	}

	public void setFruitoreId(Integer fruitoreId) {
		this.fruitoreId = fruitoreId;
	}

	public String getFruitoreCodice() {
		return this.fruitoreCodice;
	}

	public void setFruitoreCodice(String fruitoreCodice) {
		this.fruitoreCodice = fruitoreCodice;
	}

	public String getFruitoreDescrizione() {
		return this.fruitoreDescrizione;
	}

	public void setFruitoreDescrizione(String fruitoreDescrizione) {
		this.fruitoreDescrizione = fruitoreDescrizione;
	}

	public String getFruitoreEnteCodiceFiscale() {
		return this.fruitoreEnteCodiceFiscale;
	}

	public void setFruitoreEnteCodiceFiscale(String fruitoreEnteCodiceFiscale) {
		this.fruitoreEnteCodiceFiscale = fruitoreEnteCodiceFiscale;
	}

	public List<CpassRFruitoreServizio> getCpassRFruitoreServizios() {
		return this.cpassRFruitoreServizios;
	}

	public void setCpassRFruitoreServizios(List<CpassRFruitoreServizio> cpassRFruitoreServizios) {
		this.cpassRFruitoreServizios = cpassRFruitoreServizios;
	}

	public CpassRFruitoreServizio addCpassRFruitoreServizio(CpassRFruitoreServizio cpassRFruitoreServizio) {
		getCpassRFruitoreServizios().add(cpassRFruitoreServizio);
		cpassRFruitoreServizio.setCpassTFruitore(this);

		return cpassRFruitoreServizio;
	}

	public CpassRFruitoreServizio removeCpassRFruitoreServizio(CpassRFruitoreServizio cpassRFruitoreServizio) {
		getCpassRFruitoreServizios().remove(cpassRFruitoreServizio);
		cpassRFruitoreServizio.setCpassTFruitore(null);

		return cpassRFruitoreServizio;
	}

	@Override
	public Integer getId() {
		return fruitoreId;
	}

	@Override
	public void setId(Integer id) {
		fruitoreId = id;
	}

}
