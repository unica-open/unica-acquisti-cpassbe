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
package it.csi.cpass.cpassbe.lib.dto.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.AliquoteIva;
import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.ListinoFornitore;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.pba.SettoreInterventi;

public class RigaOrdine extends BaseAuditedDto<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

//	private UUID id;
	private Boolean consegnaParziale;
	private BigDecimal importoNetto;
	private BigDecimal importoIva;
	private BigDecimal importoSconto;
	private BigDecimal importoSconto2;
	private BigDecimal importoTotale;
	private BigDecimal percentualeSconto;
	private BigDecimal percentualeSconto2;
	private BigDecimal prezzoUnitario;
	private Integer progressivo;
	private BigDecimal quantita;
	private AliquoteIva aliquoteIva;
	private Ods ods;
	private Stato stato;
	private UnitaMisura unitaMisura;
	private Destinatario destinatario;
	private ListinoFornitore listinoFornitore;
	private String note;
	private BigDecimal importoDaEvadere;
	private BigDecimal quantitaEvadibile;
	private SettoreInterventi tipoAcquisto;

	public RigaOrdine() {
	}

	public RigaOrdine(UUID id) {
		super(id);
	}

//	public UUID getId() {
//		return id;
//	}
//	public void setId(UUID id) {
//		this.id = id;
//	}
	public Boolean getConsegnaParziale() {
		return consegnaParziale;
	}
	public void setConsegnaParziale(Boolean consegnaParziale) {
		this.consegnaParziale = consegnaParziale;
	}
	public BigDecimal getImportoNetto() {
		return importoNetto;
	}
	public void setImportoNetto(BigDecimal importoNetto) {
		this.importoNetto = importoNetto;
	}
	public BigDecimal getImportoIva() {
		return importoIva;
	}
	public void setImportoIva(BigDecimal importoIva) {
		this.importoIva = importoIva;
	}
	public BigDecimal getImportoSconto() {
		return importoSconto;
	}
	public void setImportoSconto(BigDecimal importoSconto) {
		this.importoSconto = importoSconto;
	}
	public BigDecimal getImportoSconto2() {
		return importoSconto2;
	}
	public void setImportoSconto2(BigDecimal importoSconto2) {
		this.importoSconto2 = importoSconto2;
	}
	public BigDecimal getImportoTotale() {
		return importoTotale;
	}
	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}
	public BigDecimal getPercentualeSconto() {
		return percentualeSconto;
	}
	public void setPercentualeSconto(BigDecimal percentualeSconto) {
		this.percentualeSconto = percentualeSconto;
	}
	public BigDecimal getPercentualeSconto2() {
		return percentualeSconto2;
	}
	public void setPercentualeSconto2(BigDecimal percentualeSconto2) {
		this.percentualeSconto2 = percentualeSconto2;
	}
	public BigDecimal getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public void setPrezzoUnitario(BigDecimal prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	public Integer getProgressivo() {
		return progressivo;
	}
	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}
	public BigDecimal getQuantita() {
		return quantita;
	}
	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}
	public AliquoteIva getAliquoteIva() {
		return aliquoteIva;
	}
	public void setAliquoteIva(AliquoteIva aliquoteIva) {
		this.aliquoteIva = aliquoteIva;
	}
	public Ods getOds() {
		return ods;
	}
	public void setOds(Ods ods) {
		this.ods = ods;
	}

	public UnitaMisura getUnitaMisura() {
		return unitaMisura;
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

	public void setUnitaMisura(UnitaMisura unitaMisura) {
		this.unitaMisura = unitaMisura;
	}
	public Destinatario getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	public BigDecimal getImportoDaEvadere() {
		return importoDaEvadere;
	}
	public void setImportoDaEvadere(BigDecimal importoDaEvadere) {
		this.importoDaEvadere = importoDaEvadere;
	}

	public BigDecimal getQuantitaEvadibile() {
		return quantitaEvadibile;
	}

	public void setQuantitaEvadibile(BigDecimal quantitaEvadibile) {
		this.quantitaEvadibile = quantitaEvadibile;
	}

	public SettoreInterventi getTipoAcquisto() {
		return tipoAcquisto;
	}

	public void setTipoAcquisto(SettoreInterventi tipoAcquisto) {
		this.tipoAcquisto = tipoAcquisto;
	}

}
