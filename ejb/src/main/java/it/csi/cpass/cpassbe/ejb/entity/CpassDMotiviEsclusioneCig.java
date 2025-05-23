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
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;


/**
 * The persistent class for the cpass_d_motivi_esclusione_cig database table.
 *
 */
@Entity
@Table(name="cpass_d_motivi_esclusione_cig")
@NamedQuery(name="CpassDMotiviEsclusioneCig.findAll", query="SELECT c FROM CpassDMotiviEsclusioneCig c")
public class CpassDMotiviEsclusioneCig implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_D_MOTIVI_ESCLUSIONE_CIG_MOTIVIESCLUSIONEID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_MOTIVI_ESCLUSIONE_CIG_MOTIVIESCLUSIONEID_GENERATOR")
	@Column(name="motivi_esclusione_id")
	private Integer motiviEsclusioneId;

	@Column(name="codice_nso")
	private String codiceNso;

	@Column(name="codice_siope")
	private String codiceSiope;

	private String descrizione;

	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@OneToMany(mappedBy="cpassDMotiviEsclusioneCig")
	private List<CpassTOrdTestataOrdine> cpassTOrdTestataOrdines;

	public CpassDMotiviEsclusioneCig() {
	}

	public Integer getMotiviEsclusioneId() {
		return this.motiviEsclusioneId;
	}

	public void setMotiviEsclusioneId(Integer motiviEsclusioneId) {
		this.motiviEsclusioneId = motiviEsclusioneId;
	}

	public String getCodiceNso() {
		return this.codiceNso;
	}

	public void setCodiceNso(String codiceNso) {
		this.codiceNso = codiceNso;
	}

	public String getCodiceSiope() {
		return this.codiceSiope;
	}

	public void setCodiceSiope(String codiceSiope) {
		this.codiceSiope = codiceSiope;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<CpassTOrdTestataOrdine> getCpassTOrdTestataOrdines() {
		return this.cpassTOrdTestataOrdines;
	}

	public void setCpassTOrdTestataOrdines(List<CpassTOrdTestataOrdine> cpassTOrdTestataOrdines) {
		this.cpassTOrdTestataOrdines = cpassTOrdTestataOrdines;
	}

	public CpassTOrdTestataOrdine addCpassTOrdTestataOrdine(CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		getCpassTOrdTestataOrdines().add(cpassTOrdTestataOrdine);
		cpassTOrdTestataOrdine.setCpassDMotiviEsclusioneCig(this);

		return cpassTOrdTestataOrdine;
	}

	public CpassTOrdTestataOrdine removeCpassTOrdTestataOrdine(CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		getCpassTOrdTestataOrdines().remove(cpassTOrdTestataOrdine);
		cpassTOrdTestataOrdine.setCpassDMotiviEsclusioneCig(null);

		return cpassTOrdTestataOrdine;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return motiviEsclusioneId;
	}

	@Override
	public void setId(Integer id) {
		motiviEsclusioneId = id;
	}

}
