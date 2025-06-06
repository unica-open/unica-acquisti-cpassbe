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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassRUfficioSerie;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdTestataOrdine;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassTOrdTestataEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdTestataRda;


/**
 * The persistent class for the cpass_t_ufficio database table.
 *
 */
@Entity
@Table(name="cpass_t_ufficio")
@NamedQuery(name="CpassTUfficio.findAll", query="SELECT c FROM CpassTUfficio c")
public class CpassTUfficio extends BaseAuditedEntity<Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CPASS_T_UFFICIO_UFFICIOID_GENERATOR", sequenceName="CPASS_T_UFFICIO_UFFICIO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_T_UFFICIO_UFFICIOID_GENERATOR")
	@Column(name="ufficio_id")
	private Integer ufficioId;

	@Column(name="ufficio_codice")
	private String ufficioCodice;

	@Column(name="ufficio_descrizione")
	private String ufficioDescrizione;

	@Column(name="id_notier")
	private String idNotier;

	/** The cpass T ente. */
	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id", nullable=false)
	private CpassTEnte cpassTEnte;

	//bi-directional many-to-one association to CpassRUfficioSettore
	@OneToMany(mappedBy="cpassTUfficio")
	private List<CpassRUfficioSettore> cpassRUfficioSettores;

	//bi-directional many-to-one association to CpassRUfficioSerie
	@OneToMany(mappedBy="cpassTUfficio")
	private List<CpassRUfficioSerie> cpassRUfficioSeries;

	//bi-directional many-to-one association to CpassTOrdTestataEvasione
	@OneToMany(mappedBy="cpassTUfficio")
	private List<CpassTOrdTestataEvasione> cpassTOrdTestataEvasiones;

	//bi-directional many-to-one association to CpassTOrdTestataOrdine
	@OneToMany(mappedBy="cpassTUfficio")
	private List<CpassTOrdTestataOrdine> cpassTOrdTestataOrdines;

	//bi-directional many-to-one association to CpassTOrdTestataRda
	@OneToMany(mappedBy="cpassTUfficio")
	private List<CpassTOrdTestataRda> cpassTOrdTestataRdas;

	public CpassTUfficio() {
	}

	public Integer getUfficioId() {
		return this.ufficioId;
	}

	public void setUfficioId(Integer ufficioId) {
		this.ufficioId = ufficioId;
	}

	public String getUfficioCodice() {
		return this.ufficioCodice;
	}

	public void setUfficioCodice(String ufficioCodice) {
		this.ufficioCodice = ufficioCodice;
	}

	public String getUfficioDescrizione() {
		return this.ufficioDescrizione;
	}

	public void setUfficioDescrizione(String ufficioDescrizione) {
		this.ufficioDescrizione = ufficioDescrizione;
	}

	public List<CpassRUfficioSettore> getCpassRUfficioSettores() {
		return this.cpassRUfficioSettores;
	}

	public void setCpassRUfficioSettores(List<CpassRUfficioSettore> cpassRUfficioSettores) {
		this.cpassRUfficioSettores = cpassRUfficioSettores;
	}

	public CpassRUfficioSettore addCpassRUfficioSettore(CpassRUfficioSettore cpassRUfficioSettore) {
		getCpassRUfficioSettores().add(cpassRUfficioSettore);
		cpassRUfficioSettore.setCpassTUfficio(this);

		return cpassRUfficioSettore;
	}

	public CpassRUfficioSettore removeCpassRUfficioSettore(CpassRUfficioSettore cpassRUfficioSettore) {
		getCpassRUfficioSettores().remove(cpassRUfficioSettore);
		cpassRUfficioSettore.setCpassTUfficio(null);

		return cpassRUfficioSettore;
	}


	/**
	 * @return the idNotier
	 */
	public String getIdNotier() {
		return idNotier;
	}

	/**
	 * @param idNotier the idNotier to set
	 */
	public void setIdNotier(String idNotier) {
		this.idNotier = idNotier;
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
		return ufficioId;
	}

	@Override
	public void setId(Integer id) {
		ufficioId = id;
	}

}
