/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.ord.evasione;

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
import it.csi.cpass.cpassbe.ejb.entity.CpassDOggettiSpesa;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTListinoFornitore;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdRigaOrdine;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_ord_riga_evasione database table.
 *
 */
@Entity
@Table(name="cpass_t_ord_riga_evasione")
@NamedQuery(name="CpassTOrdRigaEvasione.findAll", query="SELECT c FROM CpassTOrdRigaEvasione c")
public class CpassTOrdRigaEvasione  extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ord_riga_evasionee");

	@Id
	@Column(name="riga_evasione_id")
	private UUID rigaEvasioneId;

	@Column(name="importo_totale")
	private BigDecimal importoTotale;

	@Column(name="quantita_evasa")
	private BigDecimal quantitaEvasa;

	@Column(name="prezzo_unitario")
	private BigDecimal prezzoUnitario;

	private Integer progressivo;

	@Column(name="note")
	private String note;

	//bi-directional many-to-one association to CpassTOrdImpegnoEvasione
	@OneToMany(mappedBy="cpassTOrdRigaEvasione")
	private List<CpassTOrdImpegnoEvasione> cpassTOrdImpegnoEvasiones;

	//bi-directional many-to-one association to CpassDAliquoteIva
	@ManyToOne
	@JoinColumn(name="aliquote_iva_id")
	private CpassDAliquoteIva cpassDAliquoteIva;

	//bi-directional many-to-one association to CpassDOggettiSpesa
	@ManyToOne
	@JoinColumn(name="oggetti_spesa_id")
	private CpassDOggettiSpesa cpassDOggettiSpesa;

	//bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name="stato_id")
	private CpassDStato cpassDStato;

	//bi-directional many-to-one association to CpassTListinoFornitore
	@ManyToOne
	@JoinColumn(name="listino_fornitore_id")
	private CpassTListinoFornitore cpassTListinoFornitore;

	//bi-directional many-to-one association to CpassTOrdDestinatarioEvasione
	@ManyToOne
	@JoinColumn(name="destinatario_evasione_id")
	private CpassTOrdDestinatarioEvasione cpassTOrdDestinatarioEvasione;

	//bi-directional many-to-one association to CpassTOrdRigaOrdine
	@ManyToOne
	@JoinColumn(name="riga_ordine_id")
	private CpassTOrdRigaOrdine cpassTOrdRigaOrdine;

	public CpassTOrdRigaEvasione() {
	}

	public UUID getRigaEvasioneId() {
		return this.rigaEvasioneId;
	}

	public void setRigaEvasioneId(UUID rigaEvasioneId) {
		this.rigaEvasioneId = rigaEvasioneId;
	}

	public BigDecimal getImportoTotale() {
		return this.importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<CpassTOrdImpegnoEvasione> getCpassTOrdImpegnoEvasiones() {
		return this.cpassTOrdImpegnoEvasiones;
	}

	public void setCpassTOrdImpegnoEvasiones(List<CpassTOrdImpegnoEvasione> cpassTOrdImpegnoEvasiones) {
		this.cpassTOrdImpegnoEvasiones = cpassTOrdImpegnoEvasiones;
	}

	public CpassTOrdImpegnoEvasione addCpassTOrdImpegnoEvasione(CpassTOrdImpegnoEvasione cpassTOrdImpegnoEvasione) {
		getCpassTOrdImpegnoEvasiones().add(cpassTOrdImpegnoEvasione);
		cpassTOrdImpegnoEvasione.setCpassTOrdRigaEvasione(this);

		return cpassTOrdImpegnoEvasione;
	}

	public CpassTOrdImpegnoEvasione removeCpassTOrdImpegnoEvasione(CpassTOrdImpegnoEvasione cpassTOrdImpegnoEvasione) {
		getCpassTOrdImpegnoEvasiones().remove(cpassTOrdImpegnoEvasione);
		cpassTOrdImpegnoEvasione.setCpassTOrdRigaEvasione(null);

		return cpassTOrdImpegnoEvasione;
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


	public CpassTListinoFornitore getCpassTListinoFornitore() {
		return this.cpassTListinoFornitore;
	}

	/**
	 * @return the cpassDStato
	 */
	public CpassDStato getCpassDStato() {
		return cpassDStato;
	}

	/**
	 * @param cpassDStato the cpassDStato to set
	 */
	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	public void setCpassTListinoFornitore(CpassTListinoFornitore cpassTListinoFornitore) {
		this.cpassTListinoFornitore = cpassTListinoFornitore;
	}

	public CpassTOrdDestinatarioEvasione getCpassTOrdDestinatarioEvasione() {
		return this.cpassTOrdDestinatarioEvasione;
	}

	public void setCpassTOrdDestinatarioEvasione(CpassTOrdDestinatarioEvasione cpassTOrdDestinatarioEvasione) {
		this.cpassTOrdDestinatarioEvasione = cpassTOrdDestinatarioEvasione;
	}

	public CpassTOrdRigaOrdine getCpassTOrdRigaOrdine() {
		return this.cpassTOrdRigaOrdine;
	}

	public void setCpassTOrdRigaOrdine(CpassTOrdRigaOrdine cpassTOrdRigaOrdine) {
		this.cpassTOrdRigaOrdine = cpassTOrdRigaOrdine;
	}

	public BigDecimal getQuantitaEvasa() {
		return quantitaEvasa;
	}

	public void setQuantitaEvasa(BigDecimal quantitaEvasa) {
		this.quantitaEvasa = quantitaEvasa;
	}

	@Override
	public UUID getId() {
		return rigaEvasioneId;
	}

	@Override
	public void setId(UUID id) {
		rigaEvasioneId = id;
	}

	@Override
	public void initId() {
		this.rigaEvasioneId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTOrdDestinatarioEvasione.getId()+"|"+ progressivo);
	}

}
