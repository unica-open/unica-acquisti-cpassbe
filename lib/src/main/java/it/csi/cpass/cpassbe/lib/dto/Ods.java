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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;
import it.csi.cpass.cpassbe.lib.dto.ord.evasione.RigaEvasione;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;

/**
 * Ods
 */
public class Ods extends BaseAuditedDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	//private Long oggettiSpesaId;

	private Boolean inventariabile;
	private Boolean generico;
	private String genericoStr;
	private String codice;
	private String descrizione;
	private Date dataValiditaFine;
	private Date dataValiditaInizio;
	private AliquoteIva aliquoteIva;
	private Cpv cpv;
	private UnitaMisura unitaMisura;
	private BigDecimal prezzoUnitario;
	private BigDecimal quantitaMaxRichiedibile;
	private List<ListinoFornitore> listinoFornitores;
	private List<OdsDatiContabili> odsDatiContabilis;

	private List<RigaEvasione> rigaEvasiones;
	private List<RigaOrdine> rigaOrdines;
	private List<RigaRda> rigaRdas;
	private List<RigaRms> rigaRms;

	private Ente ente;
	private Boolean valido;

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

	public Boolean getGenerico() {
		return generico;
	}

	public void setGenerico(Boolean generico) {
		this.generico = generico;
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

	public BigDecimal getQuantitaMaxRichiedibile() {
		return quantitaMaxRichiedibile;
	}

	public void setQuantitaMaxRichiedibile(BigDecimal quantitaMaxRichiedibile) {
		this.quantitaMaxRichiedibile = quantitaMaxRichiedibile;
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


	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}



	/**
	 * @return the validi
	 */
	public Boolean getValido() {
		return valido;
	}

	/**
	 * @param validi the validi to set
	 */
	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	/**
	 * @return the odsDatiContabilis
	 */
	public List<OdsDatiContabili> getOdsDatiContabilis() {
		return odsDatiContabilis;
	}

	/**
	 * @param odsDatiContabilis the odsDatiContabilis to set
	 */
	public void setOdsDatiContabilis(List<OdsDatiContabili> odsDatiContabilis) {
		this.odsDatiContabilis = odsDatiContabilis;
	}


	/**
	 * @return the rigaEvasiones
	 */
	public List<RigaEvasione> getRigaEvasiones() {
		return rigaEvasiones;
	}

	/**
	 * @param rigaEvasiones the rigaEvasiones to set
	 */
	public void setRigaEvasiones(List<RigaEvasione> rigaEvasiones) {
		this.rigaEvasiones = rigaEvasiones;
	}

	/**
	 * @return the rigaOrdines
	 */
	public List<RigaOrdine> getRigaOrdines() {
		return rigaOrdines;
	}

	/**
	 * @param rigaOrdines the rigaOrdines to set
	 */
	public void setRigaOrdines(List<RigaOrdine> rigaOrdines) {
		this.rigaOrdines = rigaOrdines;
	}

	/**
	 * @return the rigaRdas
	 */
	public List<RigaRda> getRigaRdas() {
		return rigaRdas;
	}

	/**
	 * @param rigaRdas the rigaRdas to set
	 */
	public void setRigaRdas(List<RigaRda> rigaRdas) {
		this.rigaRdas = rigaRdas;
	}

	/**
	 * @return the rigaRms
	 */
	public List<RigaRms> getRigaRms() {
		return rigaRms;
	}

	/**
	 * @param rigaRms the rigaRms to set
	 */
	public void setRigaRms(List<RigaRms> rigaRms) {
		this.rigaRms = rigaRms;
	}


	public String getGenericoStr() {
		return genericoStr;
	}

	public void setGenericoStr(String genericoStr) {
		this.genericoStr = genericoStr;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("OggettiSpesa [cui=").append(id)
			.append(", inventariabile=").append(inventariabile)

			.append(innerToString())
			.append("]")
			.toString();
	}
}






