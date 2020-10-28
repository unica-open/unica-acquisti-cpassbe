/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.CpassDAliquoteIva;
import it.csi.cpass.cpassbe.ejb.entity.CpassDUnitaMisura;
import it.csi.cpass.cpassbe.ejb.entity.CpassTListinoFornitore;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_ord_riga_ordine database table.
 * 
 */
@Entity
@Table(name="cpass_t_ord_riga_ordine")
@NamedQuery(name="CpassTOrdRigaOrdine.findAll", query="SELECT c FROM CpassTOrdRigaOrdine c")
public class CpassTOrdRigaOrdine extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_riga_ordine");
	
	@Id
	@Column(name="riga_ordine_id")
	private UUID rigaOrdineId;

	@Column(name="consegna_parziale")
	private Boolean consegnaParziale;

	@Column(name="importo_netto")
	private BigDecimal importoNetto;

	@Column(name="importo_iva")
	private BigDecimal importoIva;

	@Column(name="importo_sconto")
	private BigDecimal importoSconto;

	@Column(name="importo_sconto2")
	private BigDecimal importoSconto2;

	@Column(name="importo_totale")
	private BigDecimal importoTotale;

	@Column(name="percentuale_sconto")
	private BigDecimal percentualeSconto;

	@Column(name="percentuale_sconto2")
	private BigDecimal percentualeSconto2;

	@Column(name="prezzo_unitario")
	private BigDecimal prezzoUnitario;
	
	private String note;

	private Integer progressivo;

	private BigDecimal quantita;

	//bi-directional many-to-one association to CpassTOrdImpegnoOrdine
	@OneToMany(mappedBy="cpassTOrdRigaOrdine")
	private List<CpassTOrdImpegnoOrdine> cpassTOrdImpegnoOrdines;

	//bi-directional many-to-one association to CpassDAliquoteIva
	@ManyToOne
	@JoinColumn(name="aliquote_iva_id")
	private CpassDAliquoteIva cpassDAliquoteIva;

	//bi-directional many-to-one association to CpassDOggettiSpesa
	@ManyToOne
	@JoinColumn(name="oggetti_spesa_id")
	private CpassDOggettiSpesa cpassDOggettiSpesa;

	//bi-directional many-to-one association to CpassDStatoElOrdine
	@ManyToOne
	@JoinColumn(name="stato_el_ordine_id")
	private CpassDStatoElOrdine cpassDStatoElOrdine;

	//bi-directional many-to-one association to CpassDUnitaMisura
	@ManyToOne
	@JoinColumn(name="unita_misura_id")
	private CpassDUnitaMisura cpassDUnitaMisura;

	//bi-directional many-to-one association to CpassTOrdDestinatarioOrdine
	@ManyToOne
	@JoinColumn(name="destinatario_id")
	private CpassTOrdDestinatarioOrdine cpassTOrdDestinatario;

	//bi-directional many-to-one association to CpassTOrdDestinatarioOrdine
	@ManyToOne
	@JoinColumn(name="listino_fornitore_id")
	private CpassTListinoFornitore cpassTListinoFornitore;

	public CpassTOrdRigaOrdine() {
	}

	public UUID getRigaOrdineId() {
		return this.rigaOrdineId;
	}

	public void setRigaOrdineId(UUID rigaOrdineId) {
		this.rigaOrdineId = rigaOrdineId;
	}

	/**
	 * @return the importoIva
	 */
	public BigDecimal getImportoIva() {
		return importoIva;
	}

	/**
	 * @param importoIva the importoIva to set
	 */
	public void setImportoIva(BigDecimal importoIva) {
		this.importoIva = importoIva;
	}

	public Boolean getConsegnaParziale() {
		return this.consegnaParziale;
	}

	public void setConsegnaParziale(Boolean consegnaParziale) {
		this.consegnaParziale = consegnaParziale;
	}

	public BigDecimal getImportoNetto() {
		return this.importoNetto;
	}

	public void setImportoNetto(BigDecimal importoNetto) {
		this.importoNetto = importoNetto;
	}

	public BigDecimal getImportoSconto() {
		return this.importoSconto;
	}

	public void setImportoSconto(BigDecimal importoSconto) {
		this.importoSconto = importoSconto;
	}

	public BigDecimal getImportoSconto2() {
		return this.importoSconto2;
	}

	public void setImportoSconto2(BigDecimal importoSconto2) {
		this.importoSconto2 = importoSconto2;
	}

	public BigDecimal getImportoTotale() {
		return this.importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}


	public BigDecimal getPercentualeSconto() {
		return this.percentualeSconto;
	}

	public void setPercentualeSconto(BigDecimal percentualeSconto) {
		this.percentualeSconto = percentualeSconto;
	}

	public BigDecimal getPercentualeSconto2() {
		return this.percentualeSconto2;
	}

	public void setPercentualeSconto2(BigDecimal percentualeSconto2) {
		this.percentualeSconto2 = percentualeSconto2;
	}

	public BigDecimal getPrezzoUnitario() {
		return this.prezzoUnitario;
	}

	public void setPrezzoUnitario(BigDecimal prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public Integer getProgressivo() {
		return this.progressivo;
	}

	public void setProgressivo(Integer progressivo) {
		this.progressivo = progressivo;
	}

	public BigDecimal getQuantita() {
		return this.quantita;
	}

	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}

	public List<CpassTOrdImpegnoOrdine> getCpassTOrdImpegnoOrdines() {
		return this.cpassTOrdImpegnoOrdines;
	}

	public void setCpassTOrdImpegnoOrdines(List<CpassTOrdImpegnoOrdine> cpassTOrdImpegnoOrdines) {
		this.cpassTOrdImpegnoOrdines = cpassTOrdImpegnoOrdines;
	}

	public CpassTOrdImpegnoOrdine addCpassTOrdImpegnoOrdine(CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine) {
		getCpassTOrdImpegnoOrdines().add(cpassTOrdImpegnoOrdine);
		cpassTOrdImpegnoOrdine.setCpassTOrdRigaOrdine(this);

		return cpassTOrdImpegnoOrdine;
	}

	public CpassTOrdImpegnoOrdine removeCpassTOrdImpegnoOrdine(CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine) {
		getCpassTOrdImpegnoOrdines().remove(cpassTOrdImpegnoOrdine);
		cpassTOrdImpegnoOrdine.setCpassTOrdRigaOrdine(null);

		return cpassTOrdImpegnoOrdine;
	}

	public CpassDAliquoteIva getCpassDAliquoteIva() {
		return this.cpassDAliquoteIva;
	}

	public void setCpassDAliquoteIva(CpassDAliquoteIva cpassDAliquoteIva) {
		this.cpassDAliquoteIva = cpassDAliquoteIva;
	}

	public CpassDOggettiSpesa getCpassDOggettiSpesa() {
		return this.cpassDOggettiSpesa;
	}

	public void setCpassDOggettiSpesa(CpassDOggettiSpesa cpassDOggettiSpesa) {
		this.cpassDOggettiSpesa = cpassDOggettiSpesa;
	}

	public CpassDStatoElOrdine getCpassDStatoElOrdine() {
		return this.cpassDStatoElOrdine;
	}

	public void setCpassDStatoElOrdine(CpassDStatoElOrdine cpassDStatoElOrdine) {
		this.cpassDStatoElOrdine = cpassDStatoElOrdine;
	}

	public CpassDUnitaMisura getCpassDUnitaMisura() {
		return this.cpassDUnitaMisura;
	}

	public void setCpassDUnitaMisura(CpassDUnitaMisura cpassDUnitaMisura) {
		this.cpassDUnitaMisura = cpassDUnitaMisura;
	}

	public CpassTOrdDestinatarioOrdine getCpassTOrdDestinatario() {
		return this.cpassTOrdDestinatario;
	}

	public void setCpassTOrdDestinatario(CpassTOrdDestinatarioOrdine cpassTOrdDestinatario) {
		this.cpassTOrdDestinatario = cpassTOrdDestinatario;
	}

	
	/**
	 * @return the cpassTListinoFornitore
	 */
	public CpassTListinoFornitore getCpassTListinoFornitore() {
		return cpassTListinoFornitore;
	}

	/**
	 * @param cpassTListinoFornitore the cpassTListinoFornitore to set
	 */
	public void setCpassTListinoFornitore(CpassTListinoFornitore cpassTListinoFornitore) {
		this.cpassTListinoFornitore = cpassTListinoFornitore;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public UUID getId() {
		return rigaOrdineId;
	}

	@Override
	public void setId(UUID id) {
		rigaOrdineId = id;
	}

	@Override
	public void initId() {
		this.rigaOrdineId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTOrdDestinatario.getId()+"|"+ progressivo);
	}

}
