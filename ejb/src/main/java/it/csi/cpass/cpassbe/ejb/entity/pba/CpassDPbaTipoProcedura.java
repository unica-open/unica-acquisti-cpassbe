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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassTEnte;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the cpass_d_ord_tipo_procedura database table.
 *
 */
@Entity
@Table(name="cpass_d_pba_tipo_procedura")
@NamedQuery(name="CpassDPbaTipoProcedura.findAll", query="SELECT c FROM CpassDPbaTipoProcedura c")
public class CpassDPbaTipoProcedura extends BaseAuditedEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_D_PBA_TIPO_PROCEDURA_TIPOPROCEDURAID_GENERATOR", sequenceName="CPASS_D_PBA_TIPO_PROCEDURA_TIPO_PROCEDURA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_PBA_TIPO_PROCEDURA_TIPOPROCEDURAID_GENERATOR")
	@Column(name="tipo_procedura_id")
	private Integer tipoProceduraId;

	@Column(name="tipo_procedura_codice")
	private String tipoProceduraCodice;

	@Column(name="tipo_procedura_descrizione")
	private String tipoProceduraDescrizione;

	@ManyToOne
	@JoinColumn(name="ente_id", nullable=false)
	private CpassTEnte  cpassTEnte;


	//bi-directional many-to-one association to CpassTPbaIntervento
	//@OneToMany(mappedBy="cpassDPbaTipoProcedura")
	//private List<CpassTPbaIntervento> cpassTPbaIntervento;

	public CpassDPbaTipoProcedura() {
	}

	public Integer getTipoProceduraId() {
		return this.tipoProceduraId;
	}

	public void setTipoProceduraId(Integer tipoProceduraId) {
		this.tipoProceduraId = tipoProceduraId;
	}

	public String getTipoProceduraCodice() {
		return this.tipoProceduraCodice;
	}

	public void setTipoProceduraCodice(String tipoProceduraCodice) {
		this.tipoProceduraCodice = tipoProceduraCodice;
	}

	public String getTipoProceduraDescrizione() {
		return this.tipoProceduraDescrizione;
	}

	public void setTipoProceduraDescrizione(String tipoProceduraDescrizione) {
		this.tipoProceduraDescrizione = tipoProceduraDescrizione;
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
		return tipoProceduraId;
	}

	@Override
	public void setId(Integer id) {
		tipoProceduraId=id;
	}

}
