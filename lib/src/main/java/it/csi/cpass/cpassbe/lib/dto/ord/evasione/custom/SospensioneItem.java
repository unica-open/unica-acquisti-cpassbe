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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione.custom;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SospensioneItem implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -2651118666791784234L;
	public Date dataSospensioneQuota = null;
	public String causaleSospensioneQuota = null;
	public Date dataRiattivazione = null;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		SospensioneItem sospensioneItem = (SospensioneItem) obj;
		return Objects.equals(this.dataSospensioneQuota, sospensioneItem.dataSospensioneQuota)
				&& Objects.equals(this.causaleSospensioneQuota, sospensioneItem.causaleSospensioneQuota)
				&& Objects.equals(this.dataRiattivazione, sospensioneItem.dataRiattivazione);
	}

	/**
	 * @return the dataSospensioneQuota
	 */
	public Date getDataSospensioneQuota() {
		return dataSospensioneQuota;
	}

	/**
	 * @param dataSospensioneQuota the dataSospensioneQuota to set
	 */
	public void setDataSospensioneQuota(Date dataSospensioneQuota) {
		this.dataSospensioneQuota = dataSospensioneQuota;
	}

	/**
	 * @return the causaleSospensioneQuota
	 */
	public String getCausaleSospensioneQuota() {
		return causaleSospensioneQuota;
	}

	/**
	 * @param causaleSospensioneQuota the causaleSospensioneQuota to set
	 */
	public void setCausaleSospensioneQuota(String causaleSospensioneQuota) {
		this.causaleSospensioneQuota = causaleSospensioneQuota;
	}

	/**
	 * @return the dataRiattivazione
	 */
	public Date getDataRiattivazione() {
		return dataRiattivazione;
	}

	/**
	 * @param dataRiattivazione the dataRiattivazione to set
	 */
	public void setDataRiattivazione(Date dataRiattivazione) {
		this.dataRiattivazione = dataRiattivazione;
	}



}

