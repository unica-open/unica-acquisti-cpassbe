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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaSettoreInterventi;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassRInterventoCpv;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaIntervento;

/**
 * The persistent class for the cpass_d_cpv database table.
 *
 */
@Entity
@Table(name="cpass_d_cpv")
@NamedQuery(name="CpassDCpv.findAll", query="SELECT c FROM CpassDCpv c")
public class CpassDCpv implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cpv id. */
	@Id
	@SequenceGenerator(name="CPASS_D_CPV_CPVID_GENERATOR", sequenceName="CPASS_D_CPV_CPV_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_CPV_CPVID_GENERATOR")
	@Column(name="cpv_id", unique=true, nullable=false)
	private Integer cpvId;

	/** The cpv categoria. */
	@Column(name="cpv_categoria", nullable=false, length=50)
	private String cpvCategoria;

	/** The cpv classe. */
	@Column(name="cpv_classe", nullable=false, length=50)
	private String cpvClasse;

	/** The cpv codice. */
	@Column(name="cpv_codice", nullable=false, length=50)
	private String cpvCodice;

	/** The cpv codice padre. */
	@Column(name="cpv_codice_padre", length=50)
	private String cpvCodicePadre;

	/** The cpv descrizione. */
	@Column(name="cpv_descrizione", nullable=false, length=200)
	private String cpvDescrizione;

	/** The cpv divisione. */
	@Column(name="cpv_divisione", nullable=false, length=50)
	private String cpvDivisione;

	/** The cpv gruppo. */
	@Column(name="cpv_gruppo", nullable=false, length=50)
	private String cpvGruppo;

	/** The cpv tipologia. */
	@Column(name="cpv_tipologia", nullable=false, length=50)
	private String cpvTipologia;

	/** The cpass T interventos. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@OneToMany(mappedBy="cpassDCpv")
	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/** The cpass D settore interventi. */
	//bi-directional many-to-one association to CpassDPbaSettoreInterventi
	@ManyToOne
	@JoinColumn(name="settore_interventi_id")
	private CpassDPbaSettoreInterventi cpassDPbaSettoreInterventi;

	//bi-directional many-to-one association to CpassRInterventoCpv
		@OneToMany(mappedBy="cpassDCpv")
		private List<CpassRInterventoCpv> cpassRInterventoCpvs;

	/**
	 * Gets the cpv id.
	 *
	 * @return the cpv id
	 */
	public Integer getCpvId() {
		return this.cpvId;
	}

	/**
	 * Sets the cpv id.
	 *
	 * @param cpvId the new cpv id
	 */
	public void setCpvId(Integer cpvId) {
		this.cpvId = cpvId;
	}

	/**
	 * Gets the cpv categoria.
	 *
	 * @return the cpv categoria
	 */
	public String getCpvCategoria() {
		return this.cpvCategoria;
	}

	/**
	 * Sets the cpv categoria.
	 *
	 * @param cpvCategoria the new cpv categoria
	 */
	public void setCpvCategoria(String cpvCategoria) {
		this.cpvCategoria = cpvCategoria;
	}

	/**
	 * Gets the cpv classe.
	 *
	 * @return the cpv classe
	 */
	public String getCpvClasse() {
		return this.cpvClasse;
	}

	/**
	 * Sets the cpv classe.
	 *
	 * @param cpvClasse the new cpv classe
	 */
	public void setCpvClasse(String cpvClasse) {
		this.cpvClasse = cpvClasse;
	}

	/**
	 * Gets the cpv codice.
	 *
	 * @return the cpv codice
	 */
	public String getCpvCodice() {
		return this.cpvCodice;
	}

	/**
	 * Sets the cpv codice.
	 *
	 * @param cpvCodice the new cpv codice
	 */
	public void setCpvCodice(String cpvCodice) {
		this.cpvCodice = cpvCodice;
	}

	/**
	 * Gets the cpv codice padre.
	 *
	 * @return the cpv codice padre
	 */
	public String getCpvCodicePadre() {
		return this.cpvCodicePadre;
	}

	/**
	 * Sets the cpv codice padre.
	 *
	 * @param cpvCodicePadre the new cpv codice padre
	 */
	public void setCpvCodicePadre(String cpvCodicePadre) {
		this.cpvCodicePadre = cpvCodicePadre;
	}

	/**
	 * Gets the cpv descrizione.
	 *
	 * @return the cpv descrizione
	 */
	public String getCpvDescrizione() {
		return this.cpvDescrizione;
	}

	/**
	 * Sets the cpv descrizione.
	 *
	 * @param cpvDescrizione the new cpv descrizione
	 */
	public void setCpvDescrizione(String cpvDescrizione) {
		this.cpvDescrizione = cpvDescrizione;
	}

	/**
	 * Gets the cpv divisione.
	 *
	 * @return the cpv divisione
	 */
	public String getCpvDivisione() {
		return this.cpvDivisione;
	}

	/**
	 * Sets the cpv divisione.
	 *
	 * @param cpvDivisione the new cpv divisione
	 */
	public void setCpvDivisione(String cpvDivisione) {
		this.cpvDivisione = cpvDivisione;
	}

	/**
	 * Gets the cpv gruppo.
	 *
	 * @return the cpv gruppo
	 */
	public String getCpvGruppo() {
		return this.cpvGruppo;
	}

	/**
	 * Sets the cpv gruppo.
	 *
	 * @param cpvGruppo the new cpv gruppo
	 */
	public void setCpvGruppo(String cpvGruppo) {
		this.cpvGruppo = cpvGruppo;
	}

	/**
	 * Gets the cpv tipologia.
	 *
	 * @return the cpv tipologia
	 */
	public String getCpvTipologia() {
		return this.cpvTipologia;
	}

	/**
	 * Sets the cpv tipologia.
	 *
	 * @param cpvTipologia the new cpv tipologia
	 */
	public void setCpvTipologia(String cpvTipologia) {
		this.cpvTipologia = cpvTipologia;
	}

	/**
	 * Gets the cpass T interventos.
	 *
	 * @return the cpass T interventos
	 */
	public List<CpassTPbaIntervento> getCpassTPbaInterventos() {
		return this.cpassTPbaInterventos;
	}

	/**
	 * Sets the cpass T interventos.
	 *
	 * @param cpassTPbaInterventos the new cpass T interventos
	 */
	public void setCpassTPbaInterventos(List<CpassTPbaIntervento> cpassTPbaInterventos) {
		this.cpassTPbaInterventos = cpassTPbaInterventos;
	}

	/**
	 * Adds the cpass T intervento.
	 *
	 * @param cpassTPbaIntervento the cpass T intervento
	 * @return the cpass T intervento
	 */
	public CpassTPbaIntervento addCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		getCpassTPbaInterventos().add(cpassTPbaIntervento);
		cpassTPbaIntervento.setCpassDCpv(this);

		return cpassTPbaIntervento;
	}

	/**
	 * Removes the cpass T intervento.
	 *
	 * @param cpassTPbaIntervento the cpass T intervento
	 * @return the cpass T intervento
	 */
	public CpassTPbaIntervento removeCpassTPbaIntervento(CpassTPbaIntervento cpassTPbaIntervento) {
		getCpassTPbaInterventos().remove(cpassTPbaIntervento);
		cpassTPbaIntervento.setCpassDCpv(null);

		return cpassTPbaIntervento;
	}

	/**
	 * Gets the cpass D settore interventi.
	 *
	 * @return the cpass D settore interventi
	 */
	public CpassDPbaSettoreInterventi getCpassDPbaSettoreInterventi() {
		return this.cpassDPbaSettoreInterventi;
	}

	/**
	 * set SettoreInterventi.
	 *
	 * @param cpassDPbaSettoreInterventi the new cpass D settore interventi
	 */
	public void setCpassDPbaSettoreInterventi(CpassDPbaSettoreInterventi cpassDPbaSettoreInterventi) {
		this.cpassDPbaSettoreInterventi = cpassDPbaSettoreInterventi;
	}

	public List<CpassRInterventoCpv> getCpassRInterventoCpvs() {
		return this.cpassRInterventoCpvs;
	}

	public void setCpassRInterventoCpvs(List<CpassRInterventoCpv> cpassRInterventoCpvs) {
		this.cpassRInterventoCpvs = cpassRInterventoCpvs;
	}

	public CpassRInterventoCpv addCpassRInterventoCpv(CpassRInterventoCpv cpassRInterventoCpv) {
		getCpassRInterventoCpvs().add(cpassRInterventoCpv);
		cpassRInterventoCpv.setCpassDCpv(this);

		return cpassRInterventoCpv;
	}

	public CpassRInterventoCpv removeCpassRInterventoCpv(CpassRInterventoCpv cpassRInterventoCpv) {
		getCpassRInterventoCpvs().remove(cpassRInterventoCpv);
		cpassRInterventoCpv.setCpassDCpv(null);

		return cpassRInterventoCpv;
	}
	
	@Override
	public Integer getId() {
		return cpvId;
	}

	@Override
	public void setId(Integer id) {
		cpvId = id;
	}

}
