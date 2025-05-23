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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the cpass_d_provvedimento_tipo database table.
 *
 */
@Entity
@Table(name="cpass_d_provvedimento_tipo")
@NamedQuery(name="CpassDProvvedimentoTipo.findAll", query="SELECT c FROM CpassDProvvedimentoTipo c")
public class CpassDProvvedimentoTipo implements Serializable , BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="provvedimento_tipo_id")
	private Integer provvedimentoTipoId;

	@Column(name="provvedimento_tipo_codice")
	private String provvedimentoTipoCodice;

	@Column(name="provvedimento_tipo_descrizione")
	private String provvedimentoTipoDescrizione;

	/** The cpass T ente. */
	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id", nullable=false)
	private CpassTEnte cpassTEnte;


	//bi-directional many-to-one association to CpassTProvvedimento
	@OneToMany(mappedBy="cpassDProvvedimentoTipo")
	private List<CpassTProvvedimento> cpassTProvvedimentos;

	public CpassDProvvedimentoTipo() {
	}

	public Integer getProvvedimentoTipoId() {
		return this.provvedimentoTipoId;
	}

	public void setProvvedimentoTipoId(Integer provvedimentoTipoId) {
		this.provvedimentoTipoId = provvedimentoTipoId;
	}

	public String getProvvedimentoTipoCodice() {
		return this.provvedimentoTipoCodice;
	}

	public void setProvvedimentoTipoCodice(String provvedimentoTipoCodice) {
		this.provvedimentoTipoCodice = provvedimentoTipoCodice;
	}

	public String getProvvedimentoTipoDescrizione() {
		return this.provvedimentoTipoDescrizione;
	}

	public void setProvvedimentoTipoDescrizione(String provvedimentoTipoDescrizione) {
		this.provvedimentoTipoDescrizione = provvedimentoTipoDescrizione;
	}

	public List<CpassTProvvedimento> getCpassTProvvedimentos() {
		return this.cpassTProvvedimentos;
	}

	public void setCpassTProvvedimentos(List<CpassTProvvedimento> cpassTProvvedimentos) {
		this.cpassTProvvedimentos = cpassTProvvedimentos;
	}

	public CpassTProvvedimento addCpassTProvvedimento(CpassTProvvedimento cpassTProvvedimento) {
		getCpassTProvvedimentos().add(cpassTProvvedimento);
		cpassTProvvedimento.setCpassDProvvedimentoTipo(this);

		return cpassTProvvedimento;
	}

	public CpassTProvvedimento removeCpassTProvvedimento(CpassTProvvedimento cpassTProvvedimento) {
		getCpassTProvvedimentos().remove(cpassTProvvedimento);
		cpassTProvvedimento.setCpassDProvvedimentoTipo(null);

		return cpassTProvvedimento;
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
		return provvedimentoTipoId;
	}

	@Override
	public void setId(Integer id) {
		provvedimentoTipoId = id;
	}
}
