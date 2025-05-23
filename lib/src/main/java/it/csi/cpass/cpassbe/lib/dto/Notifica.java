/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.Date;

public class Notifica extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Boolean flgLetto;

	private String entita;

	private String entitaTipo;

	private String fonte;

	private Date dataFine;

	private Date dataInizio;

	private Boolean flgGenerico;

	private String parametri;

	private TestoNotifica testoNotifica;

	public Boolean getFlgLetto() {
		return flgLetto;
	}

	public void setFlagLetto(Boolean flgLetto) {
		this.flgLetto = flgLetto;
	}

	public String getEntita() {
		return entita;
	}

	public void setEntita(String entita) {
		this.entita = entita;
	}

	public String getEntitaTipo() {
		return entitaTipo;
	}

	public void setEntitaTipo(String entitaTipo) {
		this.entitaTipo = entitaTipo;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Boolean getFlgGenerico() {
		return flgGenerico;
	}

	public void setFlgGenerico(Boolean flgGenerico) {
		this.flgGenerico = flgGenerico;
	}

	public String getParametri() {
		return parametri;
	}

	public void setParametri(String parametri) {
		this.parametri = parametri;
	}

	public TestoNotifica getTestoNotifica() {
		return testoNotifica;
	}

	public void setTestoNotifica(TestoNotifica testoNotifica) {
		this.testoNotifica = testoNotifica;
	}



}
