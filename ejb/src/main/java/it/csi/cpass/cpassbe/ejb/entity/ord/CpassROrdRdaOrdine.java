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
package it.csi.cpass.cpassbe.ejb.entity.ord;

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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdTestataRda;


/**
 * The persistent class for the cpass_r_ord_rda_ordine database table.
 *
 */
@Entity
@Table(name="cpass_r_ord_rda_ordine")
@NamedQuery(name="CpassROrdRdaOrdine.findAll", query="SELECT c FROM CpassROrdRdaOrdine c")
public class CpassROrdRdaOrdine implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_R_ORD_RDA_ORDINE_RDAORDINEID_GENERATOR", sequenceName="cpass_r_ord_rda_ordine_rda_ordine_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_R_ORD_RDA_ORDINE_RDAORDINEID_GENERATOR")
	@Column(name="rda_ordine_id")
	private Integer rdaOrdineId;

	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@ManyToOne
	@JoinColumn(name="testata_ordine_id")
	private CpassTOrdTestataOrdine cpassTOrdTestataOrdine;

	//bi-directional many-to-one association to CpassTOrdTestataRda
	@ManyToOne
	@JoinColumn(name="testata_rda_id")
	private CpassTOrdTestataRda cpassTOrdTestataRda;

	public CpassROrdRdaOrdine() {
	}

	public Integer getRdaOrdineId() {
		return this.rdaOrdineId;
	}

	public void setRdaOrdineId(Integer rdaOrdineId) {
		this.rdaOrdineId = rdaOrdineId;
	}

	public CpassTOrdTestataOrdine getCpassTOrdTestataOrdine() {
		return this.cpassTOrdTestataOrdine;
	}

	public void setCpassTOrdTestataOrdine(CpassTOrdTestataOrdine cpassTOrdTestataOrdine) {
		this.cpassTOrdTestataOrdine = cpassTOrdTestataOrdine;
	}

	public CpassTOrdTestataRda getCpassTOrdTestataRda() {
		return this.cpassTOrdTestataRda;
	}

	public void setCpassTOrdTestataRda(CpassTOrdTestataRda cpassTOrdTestataRda) {
		this.cpassTOrdTestataRda = cpassTOrdTestataRda;
	}

	@Override
	public Integer getId() {
		return this.rdaOrdineId;
	}

	@Override
	public void setId(Integer id) {
		this.rdaOrdineId = id;

	}

}
