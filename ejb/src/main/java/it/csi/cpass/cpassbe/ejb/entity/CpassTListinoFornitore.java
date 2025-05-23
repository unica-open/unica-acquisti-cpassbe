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
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;


/**
 * The persistent class for the cpass_d_aliquote_iva database table.
 *
 */
@Entity
@Table(name="cpass_t_listino_fornitore")
@NamedQuery(name="CpassTListinoFornitore.findAll", query="SELECT c FROM CpassTListinoFornitore c")
public class CpassTListinoFornitore extends BaseAuditedEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_LISTINO_FORNITORE_LISTINO_FORNITORE_ID_GENERATOR", sequenceName="CPASS_T_LISTINO_FORNITORE_LISTINO_FORNITORE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_LISTINO_FORNITORE_LISTINO_FORNITORE_ID_GENERATOR")
	@Column(name="listino_fornitore_id")
	private Integer listinoFornitoreId;

	@Column(name="listino_fornitore_codice_ods")
	private String listinoFornitoreCodiceOds;

	@Column(name="listino_fornitore_descrizione")
	private String listinoFornitoreDescrizione;

	//bi-directional many-to-one association to CpassDOggettiSpesa
	@ManyToOne
	@JoinColumn(name="oggetti_spesa_id")
	private CpassDOggettiSpesa cpassDOggettiSpesa;

	//bi-directional many-to-one association to CpassTFornitore
	@ManyToOne
	@JoinColumn(name="fornitore_id")
	private CpassTFornitore cpassTFornitore;

	public CpassTListinoFornitore() {
	}

	public Integer getListinoFornitoreId() {
		return this.listinoFornitoreId;
	}

	public void setListinoFornitoreId(Integer listinoFornitoreId) {
		this.listinoFornitoreId = listinoFornitoreId;
	}

	public String getListinoFornitoreCodiceOds() {
		return this.listinoFornitoreCodiceOds;
	}

	public void setListinoFornitoreCodiceOds(String listinoFornitoreCodiceOds) {
		this.listinoFornitoreCodiceOds = listinoFornitoreCodiceOds;
	}

	public String getListinoFornitoreDescrizione() {
		return this.listinoFornitoreDescrizione;
	}

	public void setListinoFornitoreDescrizione(String listinoFornitoreDescrizione) {
		this.listinoFornitoreDescrizione = listinoFornitoreDescrizione;
	}

	public CpassDOggettiSpesa getCpassDOggettiSpesa() {
		return this.cpassDOggettiSpesa;
	}

	public void setCpassDOggettiSpesa(CpassDOggettiSpesa cpassDOggettiSpesa) {
		this.cpassDOggettiSpesa = cpassDOggettiSpesa;
	}

	public CpassTFornitore getCpassTFornitore() {
		return this.cpassTFornitore;
	}

	public void setCpassTFornitore(CpassTFornitore cpassTFornitore) {
		this.cpassTFornitore = cpassTFornitore;
	}

	@Override
	public Integer getId() {
		return listinoFornitoreId;
	}

	@Override
	public void setId(Integer id) {
		listinoFornitoreId = id;
	}

}
