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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassDAliquoteIva;
import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.entity.CpassDUnitaMisura;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTListinoFornitore;


/**
 * The persistent class for the cpass_d_oggetti_spesa database table.
 * 
 */
@Entity
@Table(name="cpass_d_oggetti_spesa")
@NamedQuery(name="CpassDOggettiSpesa.findAll", query="SELECT c FROM CpassDOggettiSpesa c")
public class CpassDOggettiSpesa extends BaseAuditedEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_D_OGGETTI_SPESA_OGGETTISPESAID_GENERATOR", sequenceName="CPASS_D_OGGETTI_SPESA_OGGETTI_SPESA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_OGGETTI_SPESA_OGGETTISPESAID_GENERATOR")
	@Column(name="oggetti_spesa_id")
	private Integer oggettiSpesaId;

	private Boolean inventariabile;

	@Column(name="oggetti_spesa_codice")
	private String oggettiSpesaCodice;

	@Column(name="oggetti_spesa_descrizione")
	private String oggettiSpesaDescrizione;

	/** The data validita fine. */
	@Column(name="data_validita_fine")
	private Date dataValiditaFine;
	
	/** The data validita inizio. */
	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;
	
	/** The prezzo_unitario. */
	@Column(name="prezzo_unitario")
	private BigDecimal prezzoUnitario;

	//bi-directional many-to-one association to CpassTListinoFornitore
	@OneToMany(mappedBy="cpassDOggettiSpesa")
	private List<CpassTListinoFornitore> cpassTListinoFornitores;

	//bi-directional many-to-one association to CpassDAliquoteIva
	@ManyToOne
	@JoinColumn(name="aliquote_iva_id")
	private CpassDAliquoteIva cpassDAliquoteIva;

	//bi-directional many-to-one association to CpassDCpv
	@ManyToOne
	@JoinColumn(name="cpv_id")
	private CpassDCpv cpassDCpv;

	//bi-directional many-to-one association to CpassDUnitaMisura
	@ManyToOne
	@JoinColumn(name="unita_misura_id")
	private CpassDUnitaMisura cpassDUnitaMisura;

	//bi-directional many-to-one association to CpassTOrdRigaOrdine
	@OneToMany(mappedBy="cpassDOggettiSpesa")
	private List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines;

	public CpassDOggettiSpesa() {
	}

	/**
	 * @return
	 */
	public Integer getOggettiSpesaId() {
		return this.oggettiSpesaId;
	}

	public void setOggettiSpesaId(Integer oggettiSpesaId) {
		this.oggettiSpesaId = oggettiSpesaId;
	}

	public Boolean getInventariabile() {
		return this.inventariabile;
	}

	public void setInventariabile(Boolean inventariabile) {
		this.inventariabile = inventariabile;
	}

	public String getOggettiSpesaCodice() {
		return this.oggettiSpesaCodice;
	}

	public void setOggettiSpesaCodice(String oggettiSpesaCodice) {
		this.oggettiSpesaCodice = oggettiSpesaCodice;
	}

	public String getOggettiSpesaDescrizione() {
		return this.oggettiSpesaDescrizione;
	}

	public void setOggettiSpesaDescrizione(String oggettiSpesaDescrizione) {
		this.oggettiSpesaDescrizione = oggettiSpesaDescrizione;
	}
	
	
	public CpassDAliquoteIva getCpassDAliquoteIva() {
		return this.cpassDAliquoteIva;
	}

	public void setCpassDAliquoteIva(CpassDAliquoteIva cpassDAliquoteIva) {
		this.cpassDAliquoteIva = cpassDAliquoteIva;
	}

	public CpassDCpv getCpassDCpv() {
		return this.cpassDCpv;
	}

	public void setCpassDCpv(CpassDCpv cpassDCpv) {
		this.cpassDCpv = cpassDCpv;
	}

	public CpassDUnitaMisura getCpassDUnitaMisura() {
		return this.cpassDUnitaMisura;
	}

	public void setCpassDUnitaMisura(CpassDUnitaMisura cpassDUnitaMisura) {
		this.cpassDUnitaMisura = cpassDUnitaMisura;
	}

	public List<CpassTOrdRigaOrdine> getCpassTOrdRigaOrdines() {
		return this.cpassTOrdRigaOrdines;
	}

	public void setCpassTOrdRigaOrdines(List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines) {
		this.cpassTOrdRigaOrdines = cpassTOrdRigaOrdines;
	}
	
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}

	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}

	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}


	public BigDecimal getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(BigDecimal prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public CpassTOrdRigaOrdine addCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		getCpassTOrdRigaOrdines().add(cpassTOrdRigaOrdine);
		cpassTOrdRigaOrdine.setCpassDOggettiSpesa(this);

		return cpassTOrdRigaOrdine;
	}

	public CpassTOrdRigaOrdine removeCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		getCpassTOrdRigaOrdines().remove(cpassTOrdRigaOrdine);
		cpassTOrdRigaOrdine.setCpassDOggettiSpesa(null);

		return cpassTOrdRigaOrdine;
	}



	/**
	 * @return the cpassTListinoFornitores
	 */
	public List<CpassTListinoFornitore> getCpassTListinoFornitores() {
		return cpassTListinoFornitores;
	}

	/**
	 * @param cpassTListinoFornitores the cpassTListinoFornitores to set
	 */
	public void setCpassTListinoFornitores(List<CpassTListinoFornitore> cpassTListinoFornitores) {
		this.cpassTListinoFornitores = cpassTListinoFornitores;
	}

	@Override
	public Integer getId() {
		return oggettiSpesaId;
	}

	@Override
	public void setId(Integer id) {
		oggettiSpesaId = id;
		
	}

}
