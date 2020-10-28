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
 * The persistent class for the cpass_d_acquisto_variato database table.
 *
 */
@Entity
@Table(name="cpass_d_pba_acquisto_variato")
@NamedQuery(name="CpassDPbaAcquistoVariato.findAll", query="SELECT c FROM CpassDPbaAcquistoVariato c")
public class CpassDPbaAcquistoVariato implements Serializable, BaseEntity<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The acquisto variato id. */
	@Id
	@SequenceGenerator(name="CPASS_D_ACQUISTO_VARIATO_ACQUISTOVARIATOID_GENERATOR", sequenceName="CPASS_D_ACQUISTO_VARIATO_ACQUISTO_VARIATO_ID_SEQ" , allocationSize = 1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CPASS_D_ACQUISTO_VARIATO_ACQUISTOVARIATOID_GENERATOR")
	@Column(name="acquisto_variato_id")
	private Integer acquistoVariatoId;

	/** The acquisto variato codice. */
	@Column(name="acquisto_variato_codice")
	private String acquistoVariatoCodice;

	/** The acquisto variato descrizione. */
	@Column(name="acquisto_variato_descrizione")
	private String acquistoVariatoDescrizione;

	/** The acquisto variato descrizione. */
	@Column(name="acquisto_variato_descrizione_estesa")
	private String acquistoVariatoDescrizioneEstesa;

	/** The cpass T interventos. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@OneToMany(mappedBy="cpassDPbaAcquistoVariato")
	private List<CpassTPbaIntervento> cpassTPbaInterventos;

	/**
	 * Gets the acquisto variato id.
	 *
	 * @return the acquistoVariatoid
	 */
	public Integer getAcquistoVariatoId() {
		return this.acquistoVariatoId;
	}

	/**
	 * Sets the acquisto variato id.
	 *
	 * @param acquistoVariatoId the new acquisto variato id
	 */
	public void setAcquistoVariatoId(Integer acquistoVariatoId) {
		this.acquistoVariatoId = acquistoVariatoId;
	}


	/**
	 * Gets the acquisto variato codice.
	 *
	 * @return the acquistoVariatoCodice
	 */
	public String getAcquistoVariatoCodice() {
		return acquistoVariatoCodice;
	}

	/**
	 * Sets the acquisto variato codice.
	 *
	 * @param acquistoVariatoCodice the acquistoVariatoCodice to set
	 */
	public void setAcquistoVariatoCodice(String acquistoVariatoCodice) {
		this.acquistoVariatoCodice = acquistoVariatoCodice;
	}

	/**
	 * Gets the acquisto variato descrizione.
	 *
	 * @return the acquistoVariatoDescrizione
	 */
	public String getAcquistoVariatoDescrizione() {
		return acquistoVariatoDescrizione;
	}

	/**
	 * Sets the acquisto variato descrizione.
	 *
	 * @param acquistoVariatoDescrizione the acquistoVariatoDescrizione to set
	 */
	public void setAcquistoVariatoDescrizione(String acquistoVariatoDescrizione) {
		this.acquistoVariatoDescrizione = acquistoVariatoDescrizione;
	}

	
	/**
	 * @return the acquistoVariatoDescrizioneEstesa
	 */
	public String getAcquistoVariatoDescrizioneEstesa() {
		return acquistoVariatoDescrizioneEstesa;
	}

	/**
	 * @param acquistoVariatoDescrizioneEstesa the acquistoVariatoDescrizioneEstesa to set
	 */
	public void setAcquistoVariatoDescrizioneEstesa(String acquistoVariatoDescrizioneEstesa) {
		this.acquistoVariatoDescrizioneEstesa = acquistoVariatoDescrizioneEstesa;
	}

	/**
	 * Gets the cpass T interventos.
	 *
	 * @return the cpassTPbaInterventos
	 */
	public List<CpassTPbaIntervento> getCpassTPbaInterventos() {
		return cpassTPbaInterventos;
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
		cpassTPbaIntervento.setCpassDPbaAcquistoVariato(this);

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
		cpassTPbaIntervento.setCpassDPbaAcquistoVariato(null);
		return cpassTPbaIntervento;
	}

	
	@Override
	public Integer getId() {
		return acquistoVariatoId;
	}

	@Override
	public void setId(Integer id) {
		acquistoVariatoId = id;
	}

}
