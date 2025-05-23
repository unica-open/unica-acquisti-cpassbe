/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.pba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;

/**
 * The persistent class for the cpass_t_pba_acquisti_cap_privati_da_trasmettere database table.
 *
 */
@Entity
@Table(name="cpass_t_pba_acquisti_cap_privati_da_trasmettere")
@NamedQuery(name="CpassTPbaAcquistiCapPrivatiDaTrasmettere.findAll", query="SELECT c FROM CpassTPbaAcquistiCapPrivatiDaTrasmettere c")
public class CpassTPbaAcquistiCapPrivatiDaTrasmettere implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="acquisti_cap_privati_da_trasmettere_id_GENERATOR", sequenceName="cpass_t_pba_acquisti_cap_priv_acquisti_cap_privati_da_trasm_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="acquisti_cap_privati_da_trasmettere_id_GENERATOR")
	@Column(name="acquisti_cap_privati_da_trasmettere_id")
	private Integer acquistiCapPrivatiDaTrasmettereId;

	@Column(name="codice_risorsa")
	private String codiceRisorsa;

	@Column(name="descrizione_risorsa")
	private String descrizioneRisorsa;

	@Column(name="intervento_id")
	private UUID interventoId;

	@Column(name="programma_id")
	private UUID programmaId;

	@Column(name="tot_capitale_privato")
	private BigDecimal totCapitalePrivato;

	public CpassTPbaAcquistiCapPrivatiDaTrasmettere() {
	}


	/**
	 * @return the programmaId
	 */
	public UUID getProgrammaId() {
		return programmaId;
	}


	/**
	 * @param programmaId the programmaId to set
	 */
	public void setProgrammaId(UUID programmaId) {
		this.programmaId = programmaId;
	}


	public Integer getAcquistiCapPrivatiDaTrasmettereId() {
		return this.acquistiCapPrivatiDaTrasmettereId;
	}

	public void setAcquistiCapPrivatiDaTrasmettereId(Integer acquistiCapPrivatiDaTrasmettereId) {
		this.acquistiCapPrivatiDaTrasmettereId = acquistiCapPrivatiDaTrasmettereId;
	}

	public String getCodiceRisorsa() {
		return this.codiceRisorsa;
	}

	public void setCodiceRisorsa(String codiceRisorsa) {
		this.codiceRisorsa = codiceRisorsa;
	}

	public String getDescrizioneRisorsa() {
		return this.descrizioneRisorsa;
	}

	public void setDescrizioneRisorsa(String descrizioneRisorsa) {
		this.descrizioneRisorsa = descrizioneRisorsa;
	}

	public UUID getInterventoId() {
		return this.interventoId;
	}

	public void setInterventoId(UUID interventoId) {
		this.interventoId = interventoId;
	}

	public BigDecimal getTotCapitalePrivato() {
		return this.totCapitalePrivato;
	}

	public void setTotCapitalePrivato(BigDecimal totCapitalePrivato) {
		this.totCapitalePrivato = totCapitalePrivato;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return acquistiCapPrivatiDaTrasmettereId;
	}

	@Override
	public void setId(Integer id) {
		acquistiCapPrivatiDaTrasmettereId = id;
	}

}
