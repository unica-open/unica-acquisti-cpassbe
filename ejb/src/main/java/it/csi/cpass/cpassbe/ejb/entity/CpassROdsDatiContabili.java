/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity;

import java.math.BigDecimal;

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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the cpass_r_ods_dati_contabili database table.
 *
 */
@Entity
@Table(name="cpass_r_ods_dati_contabili")
@NamedQuery(name="CpassROdsDatiContabili.findAll", query="SELECT c FROM CpassROdsDatiContabili c")
public class CpassROdsDatiContabili extends BaseAuditedEntity<Integer> {

	@Id
	@SequenceGenerator(name="CPASS_R_ODS_DATI_CONTABILI_DATICONTABILIID_GENERATOR" , sequenceName="cpass_r_ods_dati_contabili_dati_contabili_id_seq", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_ODS_DATI_CONTABILI_DATICONTABILIID_GENERATOR")
	@Column(name="dati_contabili_id")
	private Integer datiContabiliId;

	@Column(name="prezzo_massimo")
	private BigDecimal prezzoMassimo;

	@Column(name="prezzo_minimo")
	private BigDecimal prezzoMinimo;

	@Column(name="ultimo_prezzo")
	private BigDecimal ultimoPrezzo;

	//bi-directional many-to-one association to CpassDOggettiSpesa
	@ManyToOne
	@JoinColumn(name="oggetti_spesa_id")
	private CpassDOggettiSpesa cpassDOggettiSpesa;

	public CpassROdsDatiContabili() {
	}

	public Integer getDatiContabiliId() {
		return this.datiContabiliId;
	}

	public void setDatiContabiliId(Integer datiContabiliId) {
		this.datiContabiliId = datiContabiliId;
	}

	public BigDecimal getPrezzoMassimo() {
		return this.prezzoMassimo;
	}

	public void setPrezzoMassimo(BigDecimal prezzoMassimo) {
		this.prezzoMassimo = prezzoMassimo;
	}

	public BigDecimal getPrezzoMinimo() {
		return this.prezzoMinimo;
	}

	public void setPrezzoMinimo(BigDecimal prezzoMinimo) {
		this.prezzoMinimo = prezzoMinimo;
	}

	public BigDecimal getUltimoPrezzo() {
		return this.ultimoPrezzo;
	}

	public void setUltimoPrezzo(BigDecimal ultimoPrezzo) {
		this.ultimoPrezzo = ultimoPrezzo;
	}

	public CpassDOggettiSpesa getCpassDOggettiSpesa() {
		return this.cpassDOggettiSpesa;
	}

	public void setCpassDOggettiSpesa(CpassDOggettiSpesa cpassDOggettiSpesa) {
		this.cpassDOggettiSpesa = cpassDOggettiSpesa;
	}


	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return datiContabiliId;
	}

	@Override
	public void setId(Integer id) {
		datiContabiliId = id;

	}

}
