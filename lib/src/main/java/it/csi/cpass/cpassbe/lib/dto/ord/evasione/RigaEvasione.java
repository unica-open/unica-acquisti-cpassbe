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
package it.csi.cpass.cpassbe.lib.dto.ord.evasione;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.ord.RigaOrdine;


public class RigaEvasione  extends BaseAuditedDto<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal importoTotale;
	private BigDecimal prezzoUnitario;
	private Integer progressivo;
	private List<ImpegnoEvasione> impegnoEvasiones;
	private AliquoteIva aliquoteIva;
	private Ods oggettiSpesa;
	private Stato stato;
	//private DocumentoTrasportoRiga documentoTrasportoRiga;
	private ListinoFornitore listinoFornitore;
	private DestinatarioEvasione destinatarioEvasione;
	private RigaOrdine rigaOrdine;
	private BigDecimal totaleEvaso;
	private BigDecimal quantitaEvasa;
	private String note;

	public RigaEvasione() {
	}

	/**
	 * @return the importoTotale
	 */
	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	/**
	 * @param importoTotale the importoTotale to set
	 */
	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	/**
	 * @return the prezzoUnitario
	 */
	public BigDecimal getPrezzoUnitario() {
		return prezzoUnitario;
	}

	/**
	 * @param prezzoUnitario the prezzoUnitario to set
	 */
	public void setPrezzoUnitario(BigDecimal prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	/**
	 * @return the progressivo
	 */
	public Integer getProgressivo() {
		return progressivo;
	}

	/**
	 * @param progressivo the progressivo to set
	 */
	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
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
	 * @return the stato
	 */
	public Stato getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}


	/**
	 * @return the listinoFornitore
	 */
	public ListinoFornitore getListinoFornitore() {
		return listinoFornitore;
	}

	/**
	 * @param listinoFornitore the listinoFornitore to set
	 */
	public void setListinoFornitore(ListinoFornitore listinoFornitore) {
		this.listinoFornitore = listinoFornitore;
	}

	/**
	 * @return the destinatarioEvasione
	 */
	public DestinatarioEvasione getDestinatarioEvasione() {
		return destinatarioEvasione;
	}

	/**
	 * @param destinatarioEvasione the destinatarioEvasione to set
	 */
	public void setDestinatarioEvasione(DestinatarioEvasione destinatarioEvasione) {
		this.destinatarioEvasione = destinatarioEvasione;
	}

	/**
	 * @return the rigaOrdine
	 */
	public RigaOrdine getRigaOrdine() {
		return rigaOrdine;
	}

	/**
	 * @param rigaOrdine the rigaOrdine to set
	 */
	public void setRigaOrdine(RigaOrdine rigaOrdine) {
		this.rigaOrdine = rigaOrdine;
	}

	public BigDecimal getTotaleEvaso() {
		return totaleEvaso;
	}

	public void setTotaleEvaso(BigDecimal totaleEvaso) {
		this.totaleEvaso = totaleEvaso;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getQuantitaEvasa() {
		return quantitaEvasa;
	}

	public void setQuantitaEvasa(BigDecimal quantitaEvasa) {
		this.quantitaEvasa = quantitaEvasa;
	}



}
