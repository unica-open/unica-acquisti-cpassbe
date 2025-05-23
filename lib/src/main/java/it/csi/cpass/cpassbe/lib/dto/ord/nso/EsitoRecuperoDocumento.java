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
package it.csi.cpass.cpassbe.lib.dto.ord.nso;

import java.io.Serializable;

public class EsitoRecuperoDocumento implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7043471263487087944L;
	private String codEsito;
	private String codErrore;
	private String descErrore;
	private String identificativoTrasmissione; // verificare se anche il tipo restituito per ricezione_di_consegna ha il dato , altrimenti spostare su oggetto NotificaMdn ( da creare )
	private String messaggioTrasmissione; // verificare se anche il tipo restituito per ricezione_di_consegna ha il dato , altrimenti spostare su oggetto NotificaMdn ( da creare )

	/**
	 * @return the codEsito
	 */
	public String getCodEsito() {
		return codEsito;
	}

	/**
	 * @param codEsito the codEsito to set
	 */
	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	/**
	 * @return the codErrore
	 */
	public String getCodErrore() {
		return codErrore;
	}

	/**
	 * @param codErrore the codErrore to set
	 */
	public void setCodErrore(String codErrore) {
		this.codErrore = codErrore;
	}

	/**
	 * @return the descErrore
	 */
	public String getDescErrore() {
		return descErrore;
	}

	/**
	 * @param descErrore the descErrore to set
	 */
	public void setDescErrore(String descErrore) {
		this.descErrore = descErrore;
	}

	/**
	 * @return the identificativoTrasmissione
	 */
	public String getIdentificativoTrasmissione() {
		return identificativoTrasmissione;
	}

	/**
	 * @param identificativoTrasmissione the identificativoTrasmissione to set
	 */
	public void setIdentificativoTrasmissione(String identificativoTrasmissione) {
		this.identificativoTrasmissione = identificativoTrasmissione;
	}

	/**
	 * @return the messaggioTrasmissione
	 */
	public String getMessaggioTrasmissione() {
		return messaggioTrasmissione;
	}

	/**
	 * @param messaggioTrasmissione the messaggioTrasmissione to set
	 */
	public void setMessaggioTrasmissione(String messaggioTrasmissione) {
		this.messaggioTrasmissione = messaggioTrasmissione;
	}

}
