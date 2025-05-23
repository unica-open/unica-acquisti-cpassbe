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
import java.math.BigDecimal;
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
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;


/**
 * The persistent class for the cpass_d_aliquote_iva database table.
 *
 */
@Entity
@Table(name="cpass_d_aliquote_iva")
@NamedQuery(name="CpassDAliquoteIva.findAll", query="SELECT c FROM CpassDAliquoteIva c")
public class CpassDAliquoteIva extends BaseAuditedEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_D_ALIQUOTE_IVA_ALIQUOTEIVAID_GENERATOR", sequenceName="CPASS_D_ALIQUOTE_IVA_ALIQUOTE_IVA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_ALIQUOTE_IVA_ALIQUOTEIVAID_GENERATOR")
	@Column(name="aliquote_iva_id")
	private Integer aliquoteIvaId;

	@Column(name="aliquote_iva_codice")
	private String aliquoteIvaCodice;

	@Column(name="aliquote_iva_descrizione")
	private String aliquoteIvaDescrizione;

	@Column(name="codifica_peppol")
	private String codificaPeppol;

	private BigDecimal percentuale;

	/** The data modifica. */
	@Column(name="data_validita_fine")
	private Date dataValiditaFine;

	/** The data modifica. */
	@Column(name="data_validita_inizio")
	private Date dataValiditaInizio;


	//bi-directional many-to-one association to CpassDOggettiSpesa
	@OneToMany(mappedBy="cpassDAliquoteIva")
	private List<CpassDOggettiSpesa> cpassDOggettiSpesas;

	//bi-directional many-to-one association to CpassTOrdRigaOrdine
	@OneToMany(mappedBy="cpassDAliquoteIva")
	private List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines;

	public CpassDAliquoteIva() {
	}

	public Integer getAliquoteIvaId() {
		return this.aliquoteIvaId;
	}

	public void setAliquoteIvaId(Integer aliquoteIvaId) {
		this.aliquoteIvaId = aliquoteIvaId;
	}

	public String getAliquoteIvaCodice() {
		return this.aliquoteIvaCodice;
	}

	public void setAliquoteIvaCodice(String aliquoteIvaCodice) {
		this.aliquoteIvaCodice = aliquoteIvaCodice;
	}

	public String getAliquoteIvaDescrizione() {
		return this.aliquoteIvaDescrizione;
	}

	public void setAliquoteIvaDescrizione(String aliquoteIvaDescrizione) {
		this.aliquoteIvaDescrizione = aliquoteIvaDescrizione;
	}

	public String getCodificaPeppol() {
		return this.codificaPeppol;
	}

	public void setCodificaPeppol(String codificaPeppol) {
		this.codificaPeppol = codificaPeppol;
	}

	public BigDecimal getPercentuale() {
		return this.percentuale;
	}

	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
	}

	public List<CpassDOggettiSpesa> getCpassDOggettiSpesas() {
		return this.cpassDOggettiSpesas;
	}

	public void setCpassDOggettiSpesas(List<CpassDOggettiSpesa> cpassDOggettiSpesas) {
		this.cpassDOggettiSpesas = cpassDOggettiSpesas;
	}

	public CpassDOggettiSpesa addCpassDOggettiSpesa(CpassDOggettiSpesa cpassDOggettiSpesa) {
		getCpassDOggettiSpesas().add(cpassDOggettiSpesa);
		cpassDOggettiSpesa.setCpassDAliquoteIva(this);

		return cpassDOggettiSpesa;
	}

	public CpassDOggettiSpesa removeCpassDOggettiSpesa(CpassDOggettiSpesa cpassDOggettiSpesa) {
		getCpassDOggettiSpesas().remove(cpassDOggettiSpesa);
		cpassDOggettiSpesa.setCpassDAliquoteIva(null);

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
		cpassTOrdRigaOrdine.setCpassDAliquoteIva(this);

		return cpassTOrdRigaOrdine;
	}

	public CpassTOrdRigaOrdine removeCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		getCpassTOrdRigaOrdines().remove(cpassTOrdRigaOrdine);
		cpassTOrdRigaOrdine.setCpassDAliquoteIva(null);

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
		return aliquoteIvaId;
	}

	@Override
	public void setId(Integer id) {
		aliquoteIvaId = id;
	}

}
