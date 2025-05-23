/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.rms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Settore;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.mag.Magazzino;
import it.csi.cpass.cpassbe.lib.dto.ord.Sezione;
import it.csi.cpass.cpassbe.lib.dto.ord.rda.RigaRda;



public class RigaRms extends BaseAuditedDto<UUID> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean consegnaParziale;
	private String cui;
	private Date dataFineConsegna;
	private Date dataInizioConsegna;
	private Integer magazzinoId;
	private String motivoRifiuto;
	private String note;
	private Integer progressivoRiga;
	private BigDecimal quantita;
	private BigDecimal quantitaSuRda;
	private Ods oggettiSpesa;
	private Stato stato;
	private List<Stato> statiRigaRms;
	private Ente ente;
	private TestataRms testataRms;
	private Settore settoreAcquisto;
	private Magazzino magazzino;
	private Sezione sezione;
	//private RigaRda rigaRda;
	private String motivoEvasioneManuale;
	private Date dataAtttesaGara;
	private String utenteAttesaGara;
	private String destinatario;
	private Boolean flgNonCompetenza;
	private List<RigaRda> rigaRda;

	/** Default constructor */
	public RigaRms() {
	}

	/**
	 * Constructor
	 * @param id
	 */
	public RigaRms(UUID id) {
		super(id);
	}

	/**
	 * @return the consegnaParziale
	 */
	public Boolean getConsegnaParziale() {
		return consegnaParziale;
	}

	/**
	 * @param consegnaParziale the consegnaParziale to set
	 */
	public void setConsegnaParziale(Boolean consegnaParziale) {
		this.consegnaParziale = consegnaParziale;
	}

	/**
	 * @return the cui
	 */
	public String getCui() {
		return cui;
	}

	/**
	 * @param cui the cui to set
	 */
	public void setCui(String cui) {
		this.cui = cui;
	}

	/**
	 * @return the dataFineConsegna
	 */
	public Date getDataFineConsegna() {
		return dataFineConsegna;
	}

	/**
	 * @param dataFineConsegna the dataFineConsegna to set
	 */
	public void setDataFineConsegna(Date dataFineConsegna) {
		this.dataFineConsegna = dataFineConsegna;
	}

	/**
	 * @return the dataInizioConsegna
	 */
	public Date getDataInizioConsegna() {
		return dataInizioConsegna;
	}

	/**
	 * @param dataInizioConsegna the dataInizioConsegna to set
	 */
	public void setDataInizioConsegna(Date dataInizioConsegna) {
		this.dataInizioConsegna = dataInizioConsegna;
	}

	/**
	 * @return the magazzinoId
	 */
	public Integer getMagazzinoId() {
		return magazzinoId;
	}

	/**
	 * @param magazzinoId the magazzinoId to set
	 */
	public void setMagazzinoId(Integer magazzinoId) {
		this.magazzinoId = magazzinoId;
	}

	/**
	 * @return the motivoRifiuto
	 */
	public String getMotivoRifiuto() {
		return motivoRifiuto;
	}

	/**
	 * @param motivoRifiuto the motivoRifiuto to set
	 */
	public void setMotivoRifiuto(String motivoRifiuto) {
		this.motivoRifiuto = motivoRifiuto;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the progressivoRiga
	 */
	public Integer getProgressivoRiga() {
		return progressivoRiga;
	}

	/**
	 * @param progressivoRiga the progressivoRiga to set
	 */
	public void setProgressivoRiga(Integer progressivoRiga) {
		this.progressivoRiga = progressivoRiga;
	}

	/**
	 * @return the quantita
	 */
	public BigDecimal getQuantita() {
		return quantita;
	}

	/**
	 * @param quantita the quantita to set
	 */
	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}


	/**
	 * @return the quantitaSuRda
	 */
	public BigDecimal getQuantitaSuRda() {
		return quantitaSuRda;
	}

	/**
	 * @param quantitaSuRda the quantitaSuRda to set
	 */
	public void setQuantitaSuRda(BigDecimal quantitaSuRda) {
		this.quantitaSuRda = quantitaSuRda;
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
	 * @return the statiRigaRms
	 */
	public List<Stato> getStatiRigaRms() {
		return statiRigaRms;
	}

	/**
	 * @param statiRigaRms the statiRigaRms to set
	 */
	public void setStatiRigaRms(List<Stato> statiRigaRms) {
		this.statiRigaRms = statiRigaRms;
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
	 * @return the testataRm
	 */
	public TestataRms getTestataRms() {
		return testataRms;
	}

	/**
	 * @param testataRms the testataRm to set
	 */
	public void setTestataRms(TestataRms testataRms) {
		this.testataRms = testataRms;
	}

	/**
	 * @return the settoreAcquisto
	 */
	public Settore getSettoreAcquisto() {
		return settoreAcquisto;
	}

	/**
	 * @param settoreAcquisto the settoreAcquisto to set
	 */
	public void setSettoreAcquisto(Settore settoreAcquisto) {
		this.settoreAcquisto = settoreAcquisto;
	}

	/**
	 * @return the magazzino
	 */
	public Magazzino getMagazzino() {
		return magazzino;
	}

	/**
	 * @param magazzino the magazzino to set
	 */
	public void setMagazzino(Magazzino magazzino) {
		this.magazzino = magazzino;
	}

	/**
	 * @return the sezione
	 */
	public Sezione getSezione() {
		return sezione;
	}

	/**
	 * @param sezione the sezione to set
	 */
	public void setSezione(Sezione sezione) {
		this.sezione = sezione;
	}

	/*
	public RigaRda getRigaRda() {
		return rigaRda;
	}

	public void setRigaRda(RigaRda rigaRda) {
		this.rigaRda = rigaRda;
	}
    */

	/**
	 * @return the motivoEvasioneManuale
	 */
	public String getMotivoEvasioneManuale() {
		return motivoEvasioneManuale;
	}

	/**
	 * @param motivoEvasioneManuale the motivoEvasioneManuale to set
	 */
	public void setMotivoEvasioneManuale(String motivoEvasioneManuale) {
		this.motivoEvasioneManuale = motivoEvasioneManuale;
	}
	/**
	 * @return the dataAtttesaGara
	 */
	public Date getDataAtttesaGara() {
		return dataAtttesaGara;
	}

	/**
	 * @param dataAtttesaGara the dataAtttesaGara to set
	 */
	public void setDataAtttesaGara(Date dataAtttesaGara) {
		this.dataAtttesaGara = dataAtttesaGara;
	}

	/**
	 * @return the utenteAttesaGara
	 */
	public String getUtenteAttesaGara() {
		return utenteAttesaGara;
	}

	/**
	 * @param utenteAttesaGara the utenteAttesaGara to set
	 */
	public void setUtenteAttesaGara(String utenteAttesaGara) {
		this.utenteAttesaGara = utenteAttesaGara;
	}

	/**
	 * @return the destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * @return the flgNonCompetenza
	 */
	public Boolean getFlgNonCompetenza() {
		return flgNonCompetenza;
	}

	/**
	 * @param flgNonCompetenza the flgNonCompetenza to set
	 */
	public void setFlgNonCompetenza(Boolean flgNonCompetenza) {
		this.flgNonCompetenza = flgNonCompetenza;
	}

	/**
	 * @return the rigaRda
	 */
	public List<RigaRda> getRigaRda() {
		return rigaRda;
	}

	/**
	 * @param rigaRda the rigaRda to set
	 */
	public void setRigaRda(List<RigaRda> rigaRda) {
		this.rigaRda = rigaRda;
	}


}
