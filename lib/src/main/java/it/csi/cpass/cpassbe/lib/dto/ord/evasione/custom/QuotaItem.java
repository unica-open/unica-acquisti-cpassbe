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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuotaItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -463942843691809042L;
	public Integer impegnoAnno = null;
	public Integer impegnoNumero = null;
	public Integer subimpegnoAnno = null;
	public Integer subimpegnoNumero = null;
	public String  cig = null;
	public BigDecimal importoQuota = null;

	public List<SospensioneItem> sospensioni = new ArrayList<>();

	public QuotaItem() {}


	/**
	 * @param impegnoAnno
	 * @param impegnoNumero
	 * @param subimpegnoAnno
	 * @param subimpegnoNumero
	 * @param cig
	 * @param importoQuota
	 * @param sospensioni
	 */
	public QuotaItem(Integer impegnoAnno, Integer impegnoNumero, Integer subimpegnoAnno, Integer subimpegnoNumero,String cig, BigDecimal importoQuota, List<SospensioneItem> sospensioni) {
		super();
		this.impegnoAnno = impegnoAnno;
		this.impegnoNumero = impegnoNumero;
		this.subimpegnoAnno = subimpegnoAnno;
		this.subimpegnoNumero = subimpegnoNumero;
		this.cig = cig;
		this.importoQuota = importoQuota;
		this.sospensioni = sospensioni;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		QuotaItem quotaItem = (QuotaItem) obj;
		return Objects.equals(this.impegnoAnno, quotaItem.impegnoAnno) && Objects.equals(this.impegnoNumero, quotaItem.impegnoNumero)
				&& Objects.equals(this.subimpegnoAnno, quotaItem.subimpegnoAnno) && Objects.equals(this.subimpegnoNumero, quotaItem.subimpegnoNumero)
				&& Objects.equals(this.sospensioni, quotaItem.sospensioni);
	}


	/**
	 * @return the impegnoAnno
	 */
	public Integer getImpegnoAnno() {
		return impegnoAnno;
	}


	/**
	 * @param impegnoAnno the impegnoAnno to set
	 */
	public void setImpegnoAnno(Integer impegnoAnno) {
		this.impegnoAnno = impegnoAnno;
	}


	/**
	 * @return the impegnoNumero
	 */
	public Integer getImpegnoNumero() {
		return impegnoNumero;
	}


	/**
	 * @param impegnoNumero the impegnoNumero to set
	 */
	public void setImpegnoNumero(Integer impegnoNumero) {
		this.impegnoNumero = impegnoNumero;
	}


	/**
	 * @return the subimpegnoAnno
	 */
	public Integer getSubimpegnoAnno() {
		return subimpegnoAnno;
	}


	/**
	 * @param subimpegnoAnno the subimpegnoAnno to set
	 */
	public void setSubimpegnoAnno(Integer subimpegnoAnno) {
		this.subimpegnoAnno = subimpegnoAnno;
	}


	/**
	 * @return the subimpegnoNumero
	 */
	public Integer getSubimpegnoNumero() {
		return subimpegnoNumero;
	}


	/**
	 * @param subimpegnoNumero the subimpegnoNumero to set
	 */
	public void setSubimpegnoNumero(Integer subimpegnoNumero) {
		this.subimpegnoNumero = subimpegnoNumero;
	}


	/**
	 * @return the cig
	 */
	public String getCig() {
		return cig;
	}


	/**
	 * @param cig the cig to set
	 */
	public void setCig(String cig) {
		this.cig = cig;
	}


	/**
	 * @return the importoQuota
	 */
	public BigDecimal getImportoQuota() {
		return importoQuota;
	}


	/**
	 * @param importoQuota the importoQuota to set
	 */
	public void setImportoQuota(BigDecimal importoQuota) {
		this.importoQuota = importoQuota;
	}


	/**
	 * @return the sospensioni
	 */
	public List<SospensioneItem> getSospensioni() {
		return sospensioni;
	}


	/**
	 * @param sospensioni the sospensioni to set
	 */
	public void setSospensioni(List<SospensioneItem> sospensioni) {
		this.sospensioni = sospensioni;
	}

	public QuotaItem clona(QuotaItem quotaItem) {
		QuotaItem ris = new QuotaItem();
		ris.setCig(quotaItem.getCig());
		ris.setImpegnoAnno(quotaItem.getImpegnoAnno());
		ris.setImpegnoNumero(quotaItem.getImpegnoNumero());
		ris.setImportoQuota(quotaItem.getImportoQuota());
		ris.setSospensioni(quotaItem.getSospensioni());
		ris.setSubimpegnoAnno(quotaItem.getSubimpegnoAnno());
		ris.setSubimpegnoNumero(quotaItem.getSubimpegnoNumero());
		return ris;
	}
}

