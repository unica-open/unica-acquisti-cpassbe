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
package it.csi.cpass.cpassbe.ejb.entity;

import java.io.Serializable;
import java.util.Date;
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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;


/**
 * The persistent class for the cpass_d_unita_misura database table.
 * 
 */
@Entity
@Table(name="cpass_d_unita_misura")
@NamedQuery(name="CpassDUnitaMisura.findAll", query="SELECT c FROM CpassDUnitaMisura c")
public class CpassDUnitaMisura extends BaseAuditedEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_D_UNITA_MISURA_UNITAMISURAID_GENERATOR", sequenceName="CPASS_D_UNITA_MISURA_UNITA_MISURA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_UNITA_MISURA_UNITAMISURAID_GENERATOR")
	@Column(name="unita_misura_id")
	private Integer unitaMisuraId;

	@Column(name="unita_misura_ambito_utilizzo")
	private String unitaMisuraAmbitoUtilizzo;

	@Column(name="unita_misura_codice")
	private String unitaMisuraCodice;

	@Column(name="unita_misura_descrizione")
	private String unitaMisuraDescrizione;

	//bi-directional many-to-one association to CpassDOggettiSpesa
	@OneToMany(mappedBy="cpassDUnitaMisura")
	private List<CpassDOggettiSpesa> cpassDOggettiSpesas;

	//bi-directional many-to-one association to CpassTOrdRigaOrdine
	@OneToMany(mappedBy="cpassDUnitaMisura")
	private List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines;

	/** The data modifica. */
	@Column(name="data_validita_fine")
	private Date dataValiditaFine;
	
	/** The data modifica. */
	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;
	
	public CpassDUnitaMisura() {
	}

	public Integer getUnitaMisuraId() {
		return this.unitaMisuraId;
	}

	public void setUnitaMisuraId(Integer unitaMisuraId) {
		this.unitaMisuraId = unitaMisuraId;
	}

	public String getUnitaMisuraAmbitoUtilizzo() {
		return this.unitaMisuraAmbitoUtilizzo;
	}

	public void setUnitaMisuraAmbitoUtilizzo(String unitaMisuraAmbitoUtilizzo) {
		this.unitaMisuraAmbitoUtilizzo = unitaMisuraAmbitoUtilizzo;
	}

	public String getUnitaMisuraCodice() {
		return this.unitaMisuraCodice;
	}

	public void setUnitaMisuraCodice(String unitaMisuraCodice) {
		this.unitaMisuraCodice = unitaMisuraCodice;
	}

	public String getUnitaMisuraDescrizione() {
		return this.unitaMisuraDescrizione;
	}

	public void setUnitaMisuraDescrizione(String unitaMisuraDescrizione) {
		this.unitaMisuraDescrizione = unitaMisuraDescrizione;
	}

	public List<CpassDOggettiSpesa> getCpassDOggettiSpesas() {
		return this.cpassDOggettiSpesas;
	}

	public void setCpassDOggettiSpesas(List<CpassDOggettiSpesa> cpassDOggettiSpesas) {
		this.cpassDOggettiSpesas = cpassDOggettiSpesas;
	}

	public CpassDOggettiSpesa addCpassDOggettiSpesa(CpassDOggettiSpesa cpassDOggettiSpesa) {
		getCpassDOggettiSpesas().add(cpassDOggettiSpesa);
		cpassDOggettiSpesa.setCpassDUnitaMisura(this);

		return cpassDOggettiSpesa;
	}

	public CpassDOggettiSpesa removeCpassDOggettiSpesa(CpassDOggettiSpesa cpassDOggettiSpesa) {
		getCpassDOggettiSpesas().remove(cpassDOggettiSpesa);
		cpassDOggettiSpesa.setCpassDUnitaMisura(null);

		return cpassDOggettiSpesa;
	}

	public List<CpassTOrdRigaOrdine> getCpassTOrdRigaOrdines() {
		return this.cpassTOrdRigaOrdines;
	}

	public void setCpassTOrdRigaOrdines(List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines) {
		this.cpassTOrdRigaOrdines = cpassTOrdRigaOrdines;
	}

	public CpassTOrdRigaOrdine addCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		getCpassTOrdRigaOrdines().add(cpassTOrdRigaOrdine);
		cpassTOrdRigaOrdine.setCpassDUnitaMisura(this);

		return cpassTOrdRigaOrdine;
	}

	public CpassTOrdRigaOrdine removeCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		getCpassTOrdRigaOrdines().remove(cpassTOrdRigaOrdine);
		cpassTOrdRigaOrdine.setCpassDUnitaMisura(null);

		return cpassTOrdRigaOrdine;
	}

	
	/**
	 * @return the dataValiditaFine
	 */
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}

	/**
	 * @param dataValiditaFine the dataValiditaFine to set
	 */
	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}

	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	@Override
	public Integer getId() {
		return unitaMisuraId;
	}

	@Override
	public void setId(Integer id) {
		unitaMisuraId = id;
		
	}

}
