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
package it.csi.cpass.cpassbe.lib.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class SettoreRuoliPermessi.
 */
public class SettoreRuoliPermessi implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	private Settore settore;	
	private List<Ruolo> listRuoli = new ArrayList<>(); // aggiungere su yml
	private List<Permesso> listPermessi = new ArrayList<>(); // aggiungere su yml

	/**
	 * @return the getListRuoli
	 */
	public List<Ruolo> getListRuoli() {
		return this.listRuoli = listRuoli != null ? listRuoli : new ArrayList<>();
	}

	/**
	 * @param listCpv the listto set
	 */
	public void setListRuoli(List<Ruolo> listRuoli) {
		this.listRuoli = listRuoli;
	}

	/**
	 * @return the list
	 */
	public List<Permesso> getListPermessi() {
		return this.listPermessi = listPermessi != null ? listPermessi : new ArrayList<>();
	}
	
	/**
	 * 
	 * @param listPermessi
	 */
	public void setListPermessi(List<Permesso> listPermessi) {
		this.listPermessi = listPermessi;
	}


	
	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @param settore the settore to set
	 */
	public void setSettore(Settore settore) {
		this.settore = settore;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("SettoreRuoliPermessi [settore=").append(settore)
			.append(", listRuoli=").append(listRuoli)
			.append(", listPermessi=").append(listPermessi)
			.append("]")
			.toString();
	}


}
