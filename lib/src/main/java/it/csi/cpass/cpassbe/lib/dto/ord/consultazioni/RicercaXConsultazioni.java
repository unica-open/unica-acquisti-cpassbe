/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord.consultazioni;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.Provvedimento;
import it.csi.cpass.cpassbe.lib.dto.ord.TipoProceduraOrd;

public class RicercaXConsultazioni implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -3141470283073750960L;
	private String tipoConsultazione;
	private String tipoVista;
	private Integer annoEsercizio;
	private Provvedimento provvedimento;
	private String numeroProcedura;
	private TipoProceduraOrd tipoProceduraOrd;

	/**
	 * @return the tipoConsultazione
	 */
	public String getTipoConsultazione() {
		return tipoConsultazione;
	}
	/**
	 * @param tipoConsultazione the tipoConsultazione to set
	 */
	public void setTipoConsultazione(String tipoConsultazione) {
		this.tipoConsultazione = tipoConsultazione;
	}
	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}
	/**
	 * @param tipoVista the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}
	/**
	 * @return the annoEsercizio
	 */
	public Integer getAnnoEsercizio() {
		return annoEsercizio;
	}
	/**
	 * @param annoEsercizio the annoEsercizio to set
	 */
	public void setAnnoEsercizio(Integer annoEsercizio) {
		this.annoEsercizio = annoEsercizio;
	}
	/**
	 * @return the provvedimento
	 */
	public Provvedimento getProvvedimento() {
		return provvedimento;
	}
	/**
	 * @param provvedimento the provvedimento to set
	 */
	public void setProvvedimento(Provvedimento provvedimento) {
		this.provvedimento = provvedimento;
	}

	/**
	 * @return the numeroProcedura
	 */
	public String getNumeroProcedura() {
		return numeroProcedura;
	}
	/**
	 * @param numeroProcedura the numeroProcedura to set
	 */
	public void setNumeroProcedura(String numeroProcedura) {
		this.numeroProcedura = numeroProcedura;
	}
	/**
	 * @return the tipoProcedura
	 */
	public TipoProceduraOrd getTipoProcedura() {
		return tipoProceduraOrd;
	}
	/**
	 * @param tipoProceduraOrd the tipoProcedura to set
	 */
	public void setTipoProcedura(TipoProceduraOrd tipoProceduraOrd) {
		this.tipoProceduraOrd = tipoProceduraOrd;
	}





}
