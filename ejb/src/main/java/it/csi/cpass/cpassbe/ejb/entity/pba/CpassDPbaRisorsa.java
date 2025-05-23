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
 * The persistent class for the cpass_d_risorsa database table.
 *
 */
@Entity
@Table(name="cpass_d_pba_risorsa")
@NamedQuery(name="CpassDPbaRisorsa.findAll", query="SELECT c FROM CpassDPbaRisorsa c")
public class CpassDPbaRisorsa implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The risorsa id. */
	@Id
	@SequenceGenerator(name="CPASS_D_RISORSA_RISORSAID_GENERATOR", sequenceName="CPASS_D_RISORSA_RISORSA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_RISORSA_RISORSAID_GENERATOR")
	@Column(name="risorsa_id", unique=true, nullable=false)
	private Integer risorsaId;

	/** The risorsa codice. */
	@Column(name="risorsa_codice", nullable=false)
	private String risorsaCodice;

	/** The risorsa descrizione. */
	@Column(name="risorsa_descrizione", nullable=false)
	private String risorsaDescrizione;

	/** risorsa_tag_trasmissione*/
	@Column(name="risorsa_tag_trasmissione")
	private String risorsaTagTrasmissione;

	/** risorsa_ordinamento*/
	@Column(name="risorsa_ordinamento")
	private Integer risorsaOrdinamento;

	/** The risorsa tipo. */
	@Column(name="risorsa_tipo")
	private String risorsaTipo;

	/** The cpass T intervento importis. */
	//bi-directional many-to-one association to CpassTPbaInterventoImporti
	@OneToMany(mappedBy="cpassDPbaRisorsa")
	private List<CpassTPbaInterventoImporti> cpassTPbaInterventoImportis;

	/**
	 * Gets the risorsa id.
	 *
	 * @return the risorsa id
	 */
	public Integer getRisorsaId() {
		return this.risorsaId;
	}


	/**
	 * @return the risorsaCodice
	 */
	public String getRisorsaCodice() {
		return risorsaCodice;
	}


	/**
	 * @param risorsaCodice the risorsaCodice to set
	 */
	public void setRisorsaCodice(String risorsaCodice) {
		this.risorsaCodice = risorsaCodice;
	}


	/**
	 * Sets the risorsa id.
	 *
	 * @param risorsaId the new risorsa id
	 */
	public void setRisorsaId(Integer risorsaId) {
		this.risorsaId = risorsaId;
	}

	/**
	 * Gets the risorsa descrizione.
	 *
	 * @return the risorsa descrizione
	 */
	public String getRisorsaDescrizione() {
		return this.risorsaDescrizione;
	}

	/**
	 * Sets the risorsa descrizione.
	 *
	 * @param risorsaDescrizione the new risorsa descrizione
	 */
	public void setRisorsaDescrizione(String risorsaDescrizione) {
		this.risorsaDescrizione = risorsaDescrizione;
	}

	/**
	 *
	 * @return string
	 */
	public String getRisorsaTipo() {
		return this.risorsaTipo;
	}

	/**
	 *
	 * @param risorsaTipo
	 */
	public void setRisorsaTipo(String risorsaTipo) {
		this.risorsaTipo = risorsaTipo;
	}

	/**
	 * Gets the cpass T intervento importis.
	 *
	 * @return the cpass T intervento importis
	 */
	public List<CpassTPbaInterventoImporti> getCpassTPbaInterventoImportis() {
		return this.cpassTPbaInterventoImportis;
	}

	/**
	 * Sets the cpass T intervento importis.
	 *
	 * @param cpassTPbaInterventoImportis the new cpass T intervento importis
	 */
	public void setCpassTPbaInterventoImportis(List<CpassTPbaInterventoImporti> cpassTPbaInterventoImportis) {
		this.cpassTPbaInterventoImportis = cpassTPbaInterventoImportis;
	}

	/**
	 * Adds the cpass T intervento importi.
	 *
	 * @param cpassTPbaInterventoImporti the cpass T intervento importi
	 * @return the cpass T intervento importi
	 */
	public CpassTPbaInterventoImporti addCpassTPbaInterventoImporti(CpassTPbaInterventoImporti cpassTPbaInterventoImporti) {
		getCpassTPbaInterventoImportis().add(cpassTPbaInterventoImporti);
		cpassTPbaInterventoImporti.setCpassDPbaRisorsa(this);

		return cpassTPbaInterventoImporti;
	}

	/**
	 * Removes the cpass T intervento importi.
	 *
	 * @param cpassTPbaInterventoImporti the cpass T intervento importi
	 * @return the cpass T intervento importi
	 */
	public CpassTPbaInterventoImporti removeCpassTPbaInterventoImporti(CpassTPbaInterventoImporti cpassTPbaInterventoImporti) {
		getCpassTPbaInterventoImportis().remove(cpassTPbaInterventoImporti);
		cpassTPbaInterventoImporti.setCpassDPbaRisorsa(null);

		return cpassTPbaInterventoImporti;
	}

	@Override
	public Integer getId() {
		return risorsaId;
	}


	/**
	 * @return the risorsaTagTrasmissione
	 */
	public String getRisorsaTagTrasmissione() {
		return risorsaTagTrasmissione;
	}

	/**
	 * @param risorsaTagTrasmissione the risorsaTagTrasmissione to set
	 */
	public void setRisorsaTagTrasmissione(String risorsaTagTrasmissione) {
		this.risorsaTagTrasmissione = risorsaTagTrasmissione;
	}


	/**
	 * @return the risorsaOrdinamento
	 */
	public Integer getRisorsaOrdinamento() {
		return risorsaOrdinamento;
	}

	/**
	 * @param risorsaOrdinamento the risorsaOrdinamento to set
	 */
	public void setRisorsaOrdinamento(Integer risorsaOrdinamento) {
		this.risorsaOrdinamento = risorsaOrdinamento;
	}


	@Override
	public void setId(Integer id) {
		risorsaId = id;
	}

}
