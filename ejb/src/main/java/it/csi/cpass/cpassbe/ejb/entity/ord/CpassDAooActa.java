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
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
 * The persistent class for the cpass_d_aoo_acta database table.
 *
 */
@Entity
@Table(name="cpass_d_aoo_acta")
@NamedQuery(name="CpassDAooActa.findAll", query="SELECT c FROM CpassDAooActa c")
public class CpassDAooActa implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="CPASS_D_AOO_ACTA_AOOACTAID_GENERATOR", sequenceName="cpass_d_aoo_acta_aoo_acta_id_seq", allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_AOO_ACTA_AOOACTAID_GENERATOR")
	@Column(name="aoo_acta_id")
	private Integer aooActaId;

	@Column(name="aoo_acta_orig_id")
	private Integer aooActaOrigId;

	@Column(name="ente_id")
	private UUID enteId;

	@Column(name="aoo_codice")
	private String aooCodice;

	@Column(name="aoo_descrizione")
	private String aooDescrizione;

	@Column(name="data_fine_validita")
	private Date dataFineValidita;



	//bi-directional many-to-one association to CpassRSettoreAooActa
	@OneToMany(mappedBy="cpassDAooActa")
	private List<CpassRSettoreAooActa> cpassRSettoreAooActas;

	//bi-directional many-to-one association to CpassTOrdProtocolloOrdine
	@OneToMany(mappedBy="cpassDAooActa")
	private List<CpassTOrdProtocolloOrdine> cpassTOrdProtocolloOrdines;

	public CpassDAooActa() {
	}

	public Integer getAooActaId() {
		return this.aooActaId;
	}

	public void setAooActaId(Integer aooActaId) {
		this.aooActaId = aooActaId;
	}

	public String getAooDescrizione() {
		return this.aooDescrizione;
	}

	public void setAooDescrizione(String aooDescrizione) {
		this.aooDescrizione = aooDescrizione;
	}

	public Date getDataFineValidita() {
		return this.dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public List<CpassRSettoreAooActa> getCpassRSettoreAooActas() {
		return this.cpassRSettoreAooActas;
	}

	public void setCpassRSettoreAooActas(List<CpassRSettoreAooActa> cpassRSettoreAooActas) {
		this.cpassRSettoreAooActas = cpassRSettoreAooActas;
	}

	public CpassRSettoreAooActa addCpassRSettoreAooActa(CpassRSettoreAooActa cpassRSettoreAooActa) {
		getCpassRSettoreAooActas().add(cpassRSettoreAooActa);
		cpassRSettoreAooActa.setCpassDAooActa(this);

		return cpassRSettoreAooActa;
	}

	public CpassRSettoreAooActa removeCpassRSettoreAooActa(CpassRSettoreAooActa cpassRSettoreAooActa) {
		getCpassRSettoreAooActas().remove(cpassRSettoreAooActa);
		cpassRSettoreAooActa.setCpassDAooActa(null);

		return cpassRSettoreAooActa;
	}

	public List<CpassTOrdProtocolloOrdine> getCpassTOrdProtocolloOrdines() {
		return this.cpassTOrdProtocolloOrdines;
	}

	public void setCpassTOrdProtocolloOrdines(List<CpassTOrdProtocolloOrdine> cpassTOrdProtocolloOrdines) {
		this.cpassTOrdProtocolloOrdines = cpassTOrdProtocolloOrdines;
	}

	public CpassTOrdProtocolloOrdine addCpassTOrdProtocolloOrdine(CpassTOrdProtocolloOrdine cpassTOrdProtocolloOrdine) {
		getCpassTOrdProtocolloOrdines().add(cpassTOrdProtocolloOrdine);
		cpassTOrdProtocolloOrdine.setCpassDAooActa(this);

		return cpassTOrdProtocolloOrdine;
	}

	public CpassTOrdProtocolloOrdine removeCpassTOrdProtocolloOrdine(CpassTOrdProtocolloOrdine cpassTOrdProtocolloOrdine) {
		getCpassTOrdProtocolloOrdines().remove(cpassTOrdProtocolloOrdine);
		cpassTOrdProtocolloOrdine.setCpassDAooActa(null);

		return cpassTOrdProtocolloOrdine;
	}


	/**
	 * @return the aooActaOrigId
	 */
	public Integer getAooActaOrigId() {
		return aooActaOrigId;
	}

	/**
	 * @param aooActaOrigId the aooActaOrigId to set
	 */
	public void setAooActaOrigId(Integer aooActaOrigId) {
		this.aooActaOrigId = aooActaOrigId;
	}

	/**
	 * @return the enteId
	 */
	public UUID getEnteId() {
		return enteId;
	}

	/**
	 * @param enteId the enteId to set
	 */
	public void setEnteId(UUID enteId) {
		this.enteId = enteId;
	}

	/**
	 * @return the aooCodice
	 */
	public String getAooCodice() {
		return aooCodice;
	}

	/**
	 * @param aooCodice the aooCodice to set
	 */
	public void setAooCodice(String aooCodice) {
		this.aooCodice = aooCodice;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return aooActaId;
	}

	@Override
	public void setId(Integer id) {
		aooActaId = id;
	}

}
