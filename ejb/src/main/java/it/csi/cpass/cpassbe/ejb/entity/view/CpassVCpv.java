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
package it.csi.cpass.cpassbe.ejb.entity.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;

/**
 * Entity for VIEW cpass_v_cpv
 */
@Entity
@Table(name="cpass_v_cpv")
@NamedQuery(name="CpassVCpv.findAll", query="SELECT c FROM CpassVCpv c")
public class CpassVCpv implements Serializable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_v_cpv")
	private Long idVCpv;

	@Column(name="cpv_categoria")
	private String cpvCategoria;

	@Column(name="cpv_classe")
	private String cpvClasse;

	@Column(name="cpv_codice")
	private String cpvCodice;

	@Column(name="cpv_codice_padre")
	private String cpvCodicePadre;

	@Column(name="cpv_descrizione")
	private String cpvDescrizione;

	@Column(name="cpv_divisione")
	private String cpvDivisione;

	@Column(name="cpv_gruppo")
	private String cpvGruppo;

	@Column(name="cpv_id")
	private Integer cpvId;

	@Column(name="cpv_id_padre")
	private Integer cpvIdPadre;

	@Column(name="cpv_tipologia")
	private String cpvTipologia;

	private Integer livello;

	@Column(name="settore_interventi_codice")
	private String settoreInterventiCodice;

	@Column(name="settore_interventi_descrizione")
	private String settoreInterventiDescrizione;

	@Column(name="settore_interventi_id")
	private Integer settoreInterventiId;

	/**
	 * @return the idVCpv
	 */
	public Long getIdVCpv() {
		return idVCpv;
	}

	/**
	 * @param idVCpv the idVCpv to set
	 */
	public void setIdVCpv(Long idVCpv) {
		this.idVCpv = idVCpv;
	}

	/**
	 * @return the cpvCategoria
	 */
	public String getCpvCategoria() {
		return cpvCategoria;
	}

	/**
	 * @param cpvCategoria the cpvCategoria to set
	 */
	public void setCpvCategoria(String cpvCategoria) {
		this.cpvCategoria = cpvCategoria;
	}

	/**
	 * @return the cpvClasse
	 */
	public String getCpvClasse() {
		return cpvClasse;
	}

	/**
	 * @param cpvClasse the cpvClasse to set
	 */
	public void setCpvClasse(String cpvClasse) {
		this.cpvClasse = cpvClasse;
	}

	/**
	 * @return the cpvCodice
	 */
	public String getCpvCodice() {
		return cpvCodice;
	}

	/**
	 * @param cpvCodice the cpvCodice to set
	 */
	public void setCpvCodice(String cpvCodice) {
		this.cpvCodice = cpvCodice;
	}

	/**
	 * @return the cpvCodicePadre
	 */
	public String getCpvCodicePadre() {
		return cpvCodicePadre;
	}

	/**
	 * @param cpvCodicePadre the cpvCodicePadre to set
	 */
	public void setCpvCodicePadre(String cpvCodicePadre) {
		this.cpvCodicePadre = cpvCodicePadre;
	}

	/**
	 * @return the cpvDescrizione
	 */
	public String getCpvDescrizione() {
		return cpvDescrizione;
	}

	/**
	 * @param cpvDescrizione the cpvDescrizione to set
	 */
	public void setCpvDescrizione(String cpvDescrizione) {
		this.cpvDescrizione = cpvDescrizione;
	}

	/**
	 * @return the cpvDivisione
	 */
	public String getCpvDivisione() {
		return cpvDivisione;
	}

	/**
	 * @param cpvDivisione the cpvDivisione to set
	 */
	public void setCpvDivisione(String cpvDivisione) {
		this.cpvDivisione = cpvDivisione;
	}

	/**
	 * @return the cpvGruppo
	 */
	public String getCpvGruppo() {
		return cpvGruppo;
	}

	/**
	 * @param cpvGruppo the cpvGruppo to set
	 */
	public void setCpvGruppo(String cpvGruppo) {
		this.cpvGruppo = cpvGruppo;
	}

	/**
	 * @return the cpvId
	 */
	public Integer getCpvId() {
		return cpvId;
	}

	/**
	 * @param cpvId the cpvId to set
	 */
	public void setCpvId(Integer cpvId) {
		this.cpvId = cpvId;
	}

	/**
	 * @return the cpvIdPadre
	 */
	public Integer getCpvIdPadre() {
		return cpvIdPadre;
	}

	/**
	 * @param cpvIdPadre the cpvIdPadre to set
	 */
	public void setCpvIdPadre(Integer cpvIdPadre) {
		this.cpvIdPadre = cpvIdPadre;
	}

	/**
	 * @return the cpvTipologia
	 */
	public String getCpvTipologia() {
		return cpvTipologia;
	}

	/**
	 * @param cpvTipologia the cpvTipologia to set
	 */
	public void setCpvTipologia(String cpvTipologia) {
		this.cpvTipologia = cpvTipologia;
	}

	/**
	 * @return the livello
	 */
	public Integer getLivello() {
		return livello;
	}

	/**
	 * @param livello the livello to set
	 */
	public void setLivello(Integer livello) {
		this.livello = livello;
	}

	/**
	 * @return the settoreInterventiCodice
	 */
	public String getSettoreInterventiCodice() {
		return settoreInterventiCodice;
	}

	/**
	 * @param settoreInterventiCodice the settoreInterventiCodice to set
	 */
	public void setSettoreInterventiCodice(String settoreInterventiCodice) {
		this.settoreInterventiCodice = settoreInterventiCodice;
	}

	/**
	 * @return the settoreInterventiDescrizione
	 */
	public String getSettoreInterventiDescrizione() {
		return settoreInterventiDescrizione;
	}

	/**
	 * @param settoreInterventiDescrizione the settoreInterventiDescrizione to set
	 */
	public void setSettoreInterventiDescrizione(String settoreInterventiDescrizione) {
		this.settoreInterventiDescrizione = settoreInterventiDescrizione;
	}

	/**
	 * @return the settoreInterventiId
	 */
	public Integer getSettoreInterventiId() {
		return settoreInterventiId;
	}

	/**
	 * @param settoreInterventiId the settoreInterventiId to set
	 */
	public void setSettoreInterventiId(Integer settoreInterventiId) {
		this.settoreInterventiId = settoreInterventiId;
	}

	@Override
	public Long getId() {
		return idVCpv;
	}

	@Override
	public void setId(Long id) {
		idVCpv = id;
	}

	@Override
	public void initId() {
		// Nothing to do
	}
}

