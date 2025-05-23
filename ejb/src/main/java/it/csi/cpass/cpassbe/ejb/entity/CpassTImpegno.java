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
package it.csi.cpass.cpassbe.ejb.entity;

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

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdImpegnoOrdine;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_impegno database table.
 *
 */

@Entity
@Table(name="cpass_t_impegno")
@NamedQuery(name="CpassTImpegno.findAll", query="SELECT c FROM CpassTImpegno c")
public class CpassTImpegno extends BaseAuditedEntity<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_impegno");

	@Id
	@Column(name="impegno_id")
	private UUID impegnoId;


	@Column(name="descrizione_capitolo")
	private String descrizioneCapitolo;

	@Column(name="impegno_anno")
	private Integer impegnoAnno;

	@Column(name="impegno_anno_esercizio")
	private Integer impegnoAnnoEsercizio;

	@Column(name="impegno_numero")
	private Integer impegnoNumero;

	@Column(name="impegno_descrizione")
	private String impegnoDescrizione;

	@Column(name="importo_attuale")
	private BigDecimal importoAttuale;

	@Column(name="importo_iniziale")
	private BigDecimal importoIniziale;

	@Column(name="liq_anno_prec")
	private BigDecimal liqAnnoPrec;

	@Column(name="numero_articolo")
	private Integer numeroArticolo;

	@Column(name="numero_capitolo")
	private Integer numeroCapitolo;

	@Column(name="provvedimento_anno")
	private Integer provvedimentoAnno;

	@Column(name="provvedimento_numero")
	private Integer provvedimentoNumero;

	@Column(name="provvedimento_settore")
	private String provvedimentoSettore;

	@Column(name="pdc_codice")
	private String pdcCodice;

	@Column(name="pdc_descrizione")
	private String pdcDescrizione;

	private String stato;

	//bi-directional many-to-one association to CpassTEnte
	@ManyToOne
	@JoinColumn(name="ente_id")
	private CpassTEnte cpassTEnte;

	//bi-directional many-to-one association to CpassTFornitore
	@ManyToOne
	@JoinColumn(name="fornitore_id")
	private CpassTFornitore cpassTFornitore;

	//bi-directional many-to-one association to CpassTOrdImpegnoOrdine
	@OneToMany(mappedBy="cpassTImpegno")
	private List<CpassTOrdImpegnoOrdine> cpassTOrdImpegnoOrdines;

	//bi-directional many-to-one association to CpassTSubimpegno
	@OneToMany(mappedBy="cpassTImpegno")
	private List<CpassTSubimpegno> cpassTSubimpegnos;

	public CpassTImpegno() {
	}

	public UUID getImpegnoId() {
		return this.impegnoId;
	}

	public void setImpegnoId(UUID impegnoId) {
		this.impegnoId = impegnoId;
	}

	public String getDescrizioneCapitolo() {
		return this.descrizioneCapitolo;
	}

	public void setDescrizioneCapitolo(String descrizioneCapitolo) {
		this.descrizioneCapitolo = descrizioneCapitolo;
	}

	public Integer getImpegnoAnno() {
		return this.impegnoAnno;
	}

	public void setImpegnoAnno(Integer impegnoAnno) {
		this.impegnoAnno = impegnoAnno;
	}

	public Integer getImpegnoAnnoEsercizio() {
		return this.impegnoAnnoEsercizio;
	}

	public void setImpegnoAnnoEsercizio(Integer impegnoAnnoEsercizio) {
		this.impegnoAnnoEsercizio = impegnoAnnoEsercizio;
	}

	public String getImpegnoDescrizione() {
		return this.impegnoDescrizione;
	}

	public void setImpegnoDescrizione(String impegnoDescrizione) {
		this.impegnoDescrizione = impegnoDescrizione;
	}

	public Integer getImpegnoNumero() {
		return this.impegnoNumero;
	}

	public void setImpegnoNumero(Integer impegnoNumero) {
		this.impegnoNumero = impegnoNumero;
	}

	public BigDecimal getImportoAttuale() {
		return this.importoAttuale;
	}

	public void setImportoAttuale(BigDecimal importoAttuale) {
		this.importoAttuale = importoAttuale;
	}

	public BigDecimal getImportoIniziale() {
		return this.importoIniziale;
	}

	public void setImportoIniziale(BigDecimal importoIniziale) {
		this.importoIniziale = importoIniziale;
	}

	public BigDecimal getLiqAnnoPrec() {
		return this.liqAnnoPrec;
	}

	public void setLiqAnnoPrec(BigDecimal liqAnnoPrec) {
		this.liqAnnoPrec = liqAnnoPrec;
	}

	public Integer getNumeroArticolo() {
		return this.numeroArticolo;
	}

	public void setNumeroArticolo(Integer numeroArticolo) {
		this.numeroArticolo = numeroArticolo;
	}

	public Integer getNumeroCapitolo() {
		return this.numeroCapitolo;
	}

	public void setNumeroCapitolo(Integer numeroCapitolo) {
		this.numeroCapitolo = numeroCapitolo;
	}

	public Integer getProvvedimentoAnno() {
		return this.provvedimentoAnno;
	}

	public void setProvvedimentoAnno(Integer provvedimentoAnno) {
		this.provvedimentoAnno = provvedimentoAnno;
	}

	public Integer getProvvedimentoNumero() {
		return this.provvedimentoNumero;
	}

	public void setProvvedimentoNumero(Integer provvedimentoNumero) {
		this.provvedimentoNumero = provvedimentoNumero;
	}

	public String getProvvedimentoSettore() {
		return this.provvedimentoSettore;
	}

	public void setProvvedimentoSettore(String provvedimentoSettore) {
		this.provvedimentoSettore = provvedimentoSettore;
	}

	/**
	 * @return the pdcCodice
	 */
	public String getPdcCodice() {
		return pdcCodice;
	}

	/**
	 * @param pdcCodice the pdcCodice to set
	 */
	public void setPdcCodice(String pdcCodice) {
		this.pdcCodice = pdcCodice;
	}

	/**
	 * @return the pdcDescrizione
	 */
	public String getPdcDescrizione() {
		return pdcDescrizione;
	}

	/**
	 * @param pdcDescrizione the pdcDescrizione to set
	 */
	public void setPdcDescrizione(String pdcDescrizione) {
		this.pdcDescrizione = pdcDescrizione;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public CpassTEnte getCpassTEnte() {
		return this.cpassTEnte;
	}

	public void setCpassTEnte(CpassTEnte cpassTEnte) {
		this.cpassTEnte = cpassTEnte;
	}

	public CpassTFornitore getCpassTFornitore() {
		return this.cpassTFornitore;
	}

	public void setCpassTFornitore(CpassTFornitore cpassTFornitore) {
		this.cpassTFornitore = cpassTFornitore;
	}

	public List<CpassTOrdImpegnoOrdine> getCpassTOrdImpegnoOrdines() {
		return this.cpassTOrdImpegnoOrdines;
	}

	public void setCpassTOrdImpegnoOrdines(List<CpassTOrdImpegnoOrdine> cpassTOrdImpegnoOrdines) {
		this.cpassTOrdImpegnoOrdines = cpassTOrdImpegnoOrdines;
	}

	public CpassTOrdImpegnoOrdine addCpassTOrdImpegnoOrdine(CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine) {
		getCpassTOrdImpegnoOrdines().add(cpassTOrdImpegnoOrdine);
		cpassTOrdImpegnoOrdine.setCpassTImpegno(this);

		return cpassTOrdImpegnoOrdine;
	}

	public CpassTOrdImpegnoOrdine removeCpassTOrdImpegnoOrdine(CpassTOrdImpegnoOrdine cpassTOrdImpegnoOrdine) {
		getCpassTOrdImpegnoOrdines().remove(cpassTOrdImpegnoOrdine);
		cpassTOrdImpegnoOrdine.setCpassTImpegno(null);

		return cpassTOrdImpegnoOrdine;
	}

	public List<CpassTSubimpegno> getCpassTSubimpegnos() {
		return this.cpassTSubimpegnos;
	}

	public void setCpassTSubimpegnos(List<CpassTSubimpegno> cpassTSubimpegnos) {
		this.cpassTSubimpegnos = cpassTSubimpegnos;
	}

	public CpassTSubimpegno addCpassTSubimpegno(CpassTSubimpegno cpassTSubimpegno) {
		getCpassTSubimpegnos().add(cpassTSubimpegno);
		cpassTSubimpegno.setCpassTImpegno(this);

		return cpassTSubimpegno;
	}

	public CpassTSubimpegno removeCpassTSubimpegno(CpassTSubimpegno cpassTSubimpegno) {
		getCpassTSubimpegnos().remove(cpassTSubimpegno);
		cpassTSubimpegno.setCpassTImpegno(null);

		return cpassTSubimpegno;
	}

	@Override
	public UUID getId() {
		return impegnoId;
	}

	@Override
	public void setId(UUID id) {
		impegnoId = id;
	}

	@Override
	public void initId() {
		this.impegnoId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, impegnoAnno+"|"+ impegnoNumero+"|"+impegnoAnnoEsercizio+"|"+ cpassTEnte.getId());
	}

}
