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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Cpv;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;

/**
 * Impegno
 */
public class OggettiSpesa extends BaseAuditedDto<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//private Long oggettiSpesaId;

	private Boolean inventariabile;

	private String codice;

	private String descrizione;

	private Date dataValiditaFine;
	
	private Date dataValiditaInizio;
	
	private AliquoteIva aliquoteIva;

	private Cpv cpv;

	private UnitaMisura unitaMisura;
	
	private BigDecimal prezzoUnitario;

	private List<ListinoFornitore> listinoFornitores;
	/**
	 * @return the inventariabile
	 */
	public Boolean getInventariabile() {
		return inventariabile;
	}

	/**
	 * @param inventariabile the inventariabile to set
	 */
	public void setInventariabile(Boolean inventariabile) {
		this.inventariabile = inventariabile;
	}

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
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
	 * @return the dataValiditaFine
	 */
	public Date getDataValiditaFine() {
		return dataValiditaFine;
	}

	/**
	 * @param dataValiditaFine the dataValiditaFine to set
	 */
	public void setDataValiditaFine(Date dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	/**
	 * @return the dataValiditaInizio
	 */
	public Date getDataValiditaInizio() {
		return dataValiditaInizio;
	}

	/**
	 * @param dataValiditaInizio the dataValiditaInizio to set
	 */
	public void setDataValiditaInizio(Date dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	/**
	 * @return the aliquoteIva
	 */
	public AliquoteIva getAliquoteIva() {
		return aliquoteIva;
	}

	/**
	 * @param aliquoteIva the aliquoteIva to set
	 */
	public void setAliquoteIva(AliquoteIva aliquoteIva) {
		this.aliquoteIva = aliquoteIva;
	}

	/**
	 * @return the cpv
	 */
	public Cpv getCpv() {
		return cpv;
	}

	/**
	 * @param cpv the cpv to set
	 */
	public void setCpv(Cpv cpv) {
		this.cpv = cpv;
	}

	/**
	 * @return the unitaMisura
	 */
	public UnitaMisura getUnitaMisura() {
		return unitaMisura;
	}

	/**
	 * @param unitaMisura the unitaMisura to set
	 */
	public void setUnitaMisura(UnitaMisura unitaMisura) {
		this.unitaMisura = unitaMisura;
	}
	
	public BigDecimal getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(BigDecimal prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}


	/**
	 * @return the listinoFornitores
	 */
	public List<ListinoFornitore> getListinoFornitores() {
		return listinoFornitores;
	}

	/**
	 * @param listinoFornitores the listinoFornitores to set
	 */
	public void setListinoFornitores(List<ListinoFornitore> listinoFornitores) {
		this.listinoFornitores = listinoFornitores;
	}

	@Override
	public String toString() {
//		Date now = new Date();
		return new StringBuilder()
			.append("OggettiSpesa [cui=").append(id)
			.append(", inventariabile=").append(inventariabile)
			
			.append(innerToString())
			.append("]")
			.toString();
	}
	//private List<CpassTOrdRigaOrdine> cpassTOrdRigaOrdines;
	/*
	private List<Subimpegno> subimpegni = new ArrayList<>();

	public List<Subimpegno> getSubimpegni() {
		return subimpegni;
	}

	public void setSubimpegni(List<Subimpegno> subimpegni) {
		this.subimpegni = subimpegni != null ? subimpegni : new ArrayList<>();
	}
*/
}






