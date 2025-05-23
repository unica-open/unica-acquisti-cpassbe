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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;

/**
 * The Class InterventoXcopia.
 */
public class CopiaInterventoWrapper implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2512027880331566576L;
	private Intervento intervento;
	private String tipoCopiaCodice;
	private String tipoCopiaDescrizione;

	/**
	 * @return the intervento
	 */
	public Intervento getIntervento() {
		return intervento;
	}
	/**
	 * @param intervento the intervento to set
	 */
	public void setIntervento(Intervento intervento) {
		this.intervento = intervento;
	}
	/**
	 * @return the tipoCopiaCodice
	 */
	public String getTipoCopiaCodice() {
		return tipoCopiaCodice;
	}
	/**
	 * @param tipoCopiaCodice the tipoCopiaCodice to set
	 */
	public void setTipoCopiaCodice(String tipoCopiaCodice) {
		this.tipoCopiaCodice = tipoCopiaCodice;
	}
	/**
	 * @return the tipoCopiaDescrizione
	 */
	public String getTipoCopiaDescrizione() {
		return tipoCopiaDescrizione;
	}
	/**
	 * @param tipoCopiaDescrizione the tipoCopiaDescrizione to set
	 */
	public void setTipoCopiaDescrizione(String tipoCopiaDescrizione) {
		this.tipoCopiaDescrizione = tipoCopiaDescrizione;
	}

}
