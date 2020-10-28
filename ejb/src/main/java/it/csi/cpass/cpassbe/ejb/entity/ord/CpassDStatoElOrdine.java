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
package it.csi.cpass.cpassbe.ejb.entity.ord;

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
 * The persistent class for the cpass_d_stato_el_ordine database table.
 * 
 */
@Entity
@Table(name="cpass_d_stato_el_ordine")
@NamedQuery(name="CpassDStatoElOrdine.findAll", query="SELECT c FROM CpassDStatoElOrdine c")
public class CpassDStatoElOrdine implements BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_D_STATO_EL_ORDINE_STATOELORDINEID_GENERATOR", sequenceName="CPASS_D_STATO_EL_ORDINE_STATO_EL_ORDINE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_STATO_EL_ORDINE_STATOELORDINEID_GENERATOR")
	@Column(name="stato_el_ordine_id")
	private Integer statoElOrdineId;

	@Column(name="stato_codice")
	private String statoCodice;

	@Column(name="stato_descrizione")
	private String statoDescrizione;

	@Column(name="stato_tipo")
	private String statoTipo;

	//bi-directional many-to-one association to CpassTOrdDestinatarioOrdine
	@OneToMany(mappedBy="cpassDStatoElOrdine")
	private List<CpassTOrdDestinatarioOrdine> cpassTOrdDestinatarios;

	//bi-directional many-to-one association to CpassTOrdRigaOrdine
	@OneToMany(mappedBy="cpassDStatoElOrdine")
	private List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines;

	public CpassDStatoElOrdine() {
	}

	public Integer getStatoElOrdineId() {
		return this.statoElOrdineId;
	}

	public void setStatoElOrdineId(Integer statoElOrdineId) {
		this.statoElOrdineId = statoElOrdineId;
	}

	public String getStatoCodice() {
		return this.statoCodice;
	}

	public void setStatoCodice(String statoCodice) {
		this.statoCodice = statoCodice;
	}

	public String getStatoDescrizione() {
		return this.statoDescrizione;
	}

	public void setStatoDescrizione(String statoDescrizione) {
		this.statoDescrizione = statoDescrizione;
	}

	
	/**
	 * @return the statoTipo
	 */
	public String getStatoTipo() {
		return statoTipo;
	}

	/**
	 * @param statoTipo the statoTipo to set
	 */
	public void setStatoTipo(String statoTipo) {
		this.statoTipo = statoTipo;
	}

	public List<CpassTOrdDestinatarioOrdine> getCpassTOrdDestinatarios() {
		return this.cpassTOrdDestinatarios;
	}

	public void setCpassTOrdDestinatarios(List<CpassTOrdDestinatarioOrdine> cpassTOrdDestinatarios) {
		this.cpassTOrdDestinatarios = cpassTOrdDestinatarios;
	}

	public CpassTOrdDestinatarioOrdine addCpassTOrdDestinatario(CpassTOrdDestinatarioOrdine cpassTOrdDestinatario) {
		getCpassTOrdDestinatarios().add(cpassTOrdDestinatario);
		cpassTOrdDestinatario.setCpassDStatoElOrdine(this);

		return cpassTOrdDestinatario;
	}

	public CpassTOrdDestinatarioOrdine removeCpassTOrdDestinatario(CpassTOrdDestinatarioOrdine cpassTOrdDestinatario) {
		getCpassTOrdDestinatarios().remove(cpassTOrdDestinatario);
		cpassTOrdDestinatario.setCpassDStatoElOrdine(null);

		return cpassTOrdDestinatario;
	}

	public List<CpassTOrdRigaOrdine> getCpassTOrdRigaOrdines() {
		return this.cpassTOrdRigaOrdines;
	}

	public void setCpassTOrdRigaOrdines(List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines) {
		this.cpassTOrdRigaOrdines = cpassTOrdRigaOrdines;
	}

	public CpassTOrdRigaOrdine addCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		getCpassTOrdRigaOrdines().add(cpassTOrdRigaOrdine);
		cpassTOrdRigaOrdine.setCpassDStatoElOrdine(this);

		return cpassTOrdRigaOrdine;
	}

	public CpassTOrdRigaOrdine removeCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		getCpassTOrdRigaOrdines().remove(cpassTOrdRigaOrdine);
		cpassTOrdRigaOrdine.setCpassDStatoElOrdine(null);

		return cpassTOrdRigaOrdine;
	}

	@Override
	public Integer getId() {
		return statoElOrdineId;
	}

	@Override
	public void setId(Integer id) {
		statoElOrdineId = id;
		
	}

}
