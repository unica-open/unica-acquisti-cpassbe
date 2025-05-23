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
package it.csi.cpass.cpassbe.ejb.entity.pba;

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


@Entity
@Table(name="cpass_d_pba_tipo_acquisto")
@NamedQuery(name="CpassDPbaTipoAcquisto.findAll", query="SELECT c FROM CpassDPbaTipoAcquisto c")
public class CpassDPbaTipoAcquisto implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_D_PBA_TIPO_ACQUISTO_TIPOACQUISTOID_GENERATOR", sequenceName="CPASS_D_PBA_TIPO_ACQUISTO_TIPO_ACQUISTO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_PBA_TIPO_ACQUISTO_TIPOACQUISTOID_GENERATOR")
	@Column(name="tipo_acquisto_id")
	private Integer tipoAcquistoId;

	@Column(name="tipo_acquisto_codice")
	private String tipoAcquistoCodice;

	@Column(name="tipo_acquisto_default")
	private Boolean tipoAcquistoDefault;

	@Column(name="tipo_acquisto_descrizione")
	private String tipoAcquistoDescrizione;

	//bi-directional many-to-one association to CpassTPbaInterventoAltriDati
	//@OneToMany(mappedBy="cpassDPbaTipoAcquisto1")
	//private List<CpassTPbaInterventoAltriDati> cpassTPbaInterventoAltriDatis1;

	//bi-directional many-to-one association to CpassTPbaInterventoAltriDati
	//@OneToMany(mappedBy="cpassDPbaTipoAcquisto2")
	//private List<CpassTPbaInterventoAltriDati> cpassTPbaInterventoAltriDatis2;

	public CpassDPbaTipoAcquisto() {
	}

	public Integer getTipoAcquistoId() {
		return this.tipoAcquistoId;
	}

	public void setTipoAcquistoId(Integer tipoAcquistoId) {
		this.tipoAcquistoId = tipoAcquistoId;
	}

	public String getTipoAcquistoCodice() {
		return this.tipoAcquistoCodice;
	}

	public void setTipoAcquistoCodice(String tipoAcquistoCodice) {
		this.tipoAcquistoCodice = tipoAcquistoCodice;
	}

	public Boolean getTipoAcquistoDefault() {
		return this.tipoAcquistoDefault;
	}

	public void setTipoAcquistoDefault(Boolean tipoAcquistoDefault) {
		this.tipoAcquistoDefault = tipoAcquistoDefault;
	}

	public String getTipoAcquistoDescrizione() {
		return this.tipoAcquistoDescrizione;
	}

	public void setTipoAcquistoDescrizione(String tipoAcquistoDescrizione) {
		this.tipoAcquistoDescrizione = tipoAcquistoDescrizione;
	}
	/*
	public List<CpassTPbaInterventoAltriDati> getCpassTPbaInterventoAltriDatis1() {
		return this.cpassTPbaInterventoAltriDatis1;
	}

	public void setCpassTPbaInterventoAltriDatis1(List<CpassTPbaInterventoAltriDati> cpassTPbaInterventoAltriDatis1) {
		this.cpassTPbaInterventoAltriDatis1 = cpassTPbaInterventoAltriDatis1;
	}

	public CpassTPbaInterventoAltriDati addCpassTPbaInterventoAltriDatis1(CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDatis1) {
		getCpassTPbaInterventoAltriDatis1().add(cpassTPbaInterventoAltriDatis1);
		cpassTPbaInterventoAltriDatis1.setCpassDPbaTipoAcquisto1(this);

		return cpassTPbaInterventoAltriDatis1;
	}

	public CpassTPbaInterventoAltriDati removeCpassTPbaInterventoAltriDatis1(CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDatis1) {
		getCpassTPbaInterventoAltriDatis1().remove(cpassTPbaInterventoAltriDatis1);
		cpassTPbaInterventoAltriDatis1.setCpassDPbaTipoAcquisto1(null);

		return cpassTPbaInterventoAltriDatis1;
	}

	public List<CpassTPbaInterventoAltriDati> getCpassTPbaInterventoAltriDatis2() {
		return this.cpassTPbaInterventoAltriDatis2;
	}

	public void setCpassTPbaInterventoAltriDatis2(List<CpassTPbaInterventoAltriDati> cpassTPbaInterventoAltriDatis2) {
		this.cpassTPbaInterventoAltriDatis2 = cpassTPbaInterventoAltriDatis2;
	}

	public CpassTPbaInterventoAltriDati addCpassTPbaInterventoAltriDatis2(CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDatis2) {
		getCpassTPbaInterventoAltriDatis2().add(cpassTPbaInterventoAltriDatis2);
		cpassTPbaInterventoAltriDatis2.setCpassDPbaTipoAcquisto2(this);

		return cpassTPbaInterventoAltriDatis2;
	}

	public CpassTPbaInterventoAltriDati removeCpassTPbaInterventoAltriDatis2(CpassTPbaInterventoAltriDati cpassTPbaInterventoAltriDatis2) {
		getCpassTPbaInterventoAltriDatis2().remove(cpassTPbaInterventoAltriDatis2);
		cpassTPbaInterventoAltriDatis2.setCpassDPbaTipoAcquisto2(null);

		return cpassTPbaInterventoAltriDatis2;
	}
	 */

	@Override
	public Integer getId() {
		return tipoAcquistoId;
	}

	@Override
	public void setId(Integer id) {
		tipoAcquistoId = id;
	}
}
