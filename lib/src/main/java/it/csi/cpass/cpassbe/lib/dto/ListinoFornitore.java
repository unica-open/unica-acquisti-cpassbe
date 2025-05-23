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

public class ListinoFornitore  extends BaseAuditedDto<Integer> implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** Default constructor */
	public ListinoFornitore() {}

	/**
	 * Constructor
	 * @param id the id
	 */
	public ListinoFornitore(Integer id) {
		super(id);
	}

	private String codiceOds;
	private String descrizione;
	private Ods oggettiSpesa;
	private Fornitore fornitore;



	/**
	 * @return the codiceOds
	 */
	public String getCodiceOds() {
		return codiceOds;
	}

	/**
	 * @param codiceOds the codiceOds to set
	 */
	public void setCodiceOds(String codiceOds) {
		this.codiceOds = codiceOds;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the oggettiSpesa
	 */
	public Ods getOggettiSpesa() {
		return oggettiSpesa;
	}

	/**
	 * @param oggettiSpesa the oggettiSpesa to set
	 */
	public void setOggettiSpesa(Ods oggettiSpesa) {
		this.oggettiSpesa = oggettiSpesa;
	}

	/**
	 * @return the fornitore
	 */
	public Fornitore getFornitore() {
		return fornitore;
	}

	/**
	 * @param fornitore the fornitore to set
	 */
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("ListinoFornitore [listinoFornitoreCodiceOds=").append(codiceOds)
			.append(", listinoFornitoreDescrizione=").append(descrizione)
			.append(", oggettiSpesa=").append(oggettiSpesa)
			.append(", fornitore=").append(fornitore)
			.append(", id=").append(id)
			.append("]")
			.toString();
	}
}
