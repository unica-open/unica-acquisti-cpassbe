/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

/**
 * The Class TipoOrdine.
 */
public class TipoOrdine extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The tipologiaDocumentoCodice. */
	private String tipologiaDocumentoCodice;
	/** The tipologiaDocumentoDescrizione. */
	private String tipologiaDocumentoDescrizione;
	private Boolean flagTrasmNso;
	
	/** Default constructor */
	public TipoOrdine() {
	}

	/**
	 * Constructor
	 * 
	 * @param id the id
	 */
	public TipoOrdine(Integer id) {
		super(id);
	}

	/**
	 * @return the tipologiaDocumentoCodice
	 */
	public String getTipologiaDocumentoCodice() {
		return tipologiaDocumentoCodice;
	}

	/**
	 * @param tipologiaDocumentoCodice the tipologiaDocumentoCodice to set
	 */
	public void setTipologiaDocumentoCodice(String tipologiaDocumentoCodice) {
		this.tipologiaDocumentoCodice = tipologiaDocumentoCodice;
	}

	/**
	 * @return the tipologiaDocumentoDescrizione
	 */
	public String getTipologiaDocumentoDescrizione() {
		return tipologiaDocumentoDescrizione;
	}

	/**
	 * @param tipologiaDocumentoDescrizione the tipologiaDocumentoDescrizione to set
	 */
	public void setTipologiaDocumentoDescrizione(String tipologiaDocumentoDescrizione) {
		this.tipologiaDocumentoDescrizione = tipologiaDocumentoDescrizione;
	}

	
	/**
	 * @return the flagTrasmNso
	 */
	public Boolean getFlagTrasmNso() {
		return flagTrasmNso;
	}

	/**
	 * @param flagTrasmNso the flagTrasmNso to set
	 */
	public void setFlagTrasmNso(Boolean flagTrasmNso) {
		this.flagTrasmNso = flagTrasmNso;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("TipoOrdine [tipologiaDocumentoCodice=").append(tipologiaDocumentoCodice)
				.append(", tipologiaDocumentoDescrizione=").append(tipologiaDocumentoDescrizione)
				.append(", id=").append(id)
				.append("]").toString();
	}

}
