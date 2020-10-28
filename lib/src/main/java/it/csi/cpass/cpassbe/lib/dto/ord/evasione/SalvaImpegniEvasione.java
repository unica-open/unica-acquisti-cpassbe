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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.util.List;

public class SalvaImpegniEvasione implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8240350346537190251L;
	private TestataEvasione testataEvasione;
	private RigaEvasione rigaEvasione;
	private List<ImpegnoEvasione> impegnoEvasiones;
	private Boolean ignoreWarnings = false;

	/**
	 * @return the testataEvasione
	 */
	public TestataEvasione getTestataEvasione() {
		return testataEvasione;
	}

	/**
	 * @param testataEvasione the testataEvasione to set
	 */
	public void setTestataEvasione(TestataEvasione testataEvasione) {
		this.testataEvasione = testataEvasione;
	}

	/**
	 * @return the impegnoEvasiones
	 */
	public List<ImpegnoEvasione> getImpegnoEvasiones() {
		return impegnoEvasiones;
	}

	/**
	 * @param impegnoEvasiones the impegnoEvasiones to set
	 */
	public void setImpegnoEvasiones(List<ImpegnoEvasione> impegnoEvasiones) {
		this.impegnoEvasiones = impegnoEvasiones;
	}

	/**
	 * @return the rigaEvasione
	 */
	public RigaEvasione getRigaEvasione() {
		return rigaEvasione;
	}

	/**
	 * @param rigaEvasione the rigaEvasione to set
	 */
	public void setRigaEvasione(RigaEvasione rigaEvasione) {
		this.rigaEvasione = rigaEvasione;
	}

	/**
	 * @return the ignoreWarnings
	 */
	public Boolean getIgnoreWarnings() {
		return ignoreWarnings;
	}

	/**
	 * @param ignoreWarnings the ignoreWarnings to set
	 */
	public void setIgnoreWarnings(Boolean ignoreWarnings) {
		this.ignoreWarnings = ignoreWarnings;
	}
	
}
