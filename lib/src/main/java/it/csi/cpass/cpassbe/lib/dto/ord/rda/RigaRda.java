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
package it.csi.cpass.cpassbe.lib.dto.ord.rda;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseAuditedDto;
import it.csi.cpass.cpassbe.lib.dto.Ente;
import it.csi.cpass.cpassbe.lib.dto.Ods;
import it.csi.cpass.cpassbe.lib.dto.Stato;
import it.csi.cpass.cpassbe.lib.dto.UnitaMisura;
import it.csi.cpass.cpassbe.lib.dto.rms.RigaRms;

public class RigaRda extends BaseAuditedDto<UUID> implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String note;
	private Integer progressivoRiga;
	private BigDecimal quantita;
	private BigDecimal quantitaSuRdaDaEvadere;

	private Ods oggettiSpesa;
	private Stato stato;
	private UnitaMisura unitaMisura;
	private Ente ente;
	private TestataRda testataRda;
	private List<RigaRms> rigaRms;

	private List<RigaRda> listaRelazioniConRms;

	/** Default constructor */
	public RigaRda() {
	}

	/**
	 * Constructor
	 *
	 * @param id the id
	 */
	public RigaRda(UUID id) {
		super(id);
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
	 * @return the testataRda
	 */
	public TestataRda getTestataRda() {
		return testataRda;
	}

	/**
	 * @param testataRda the testataRda to set
	 */
	public void setTestataRda(TestataRda testataRda) {
		this.testataRda = testataRda;
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


	/**
	 * @return the listaRelazioniConRms
	 */
	public List<RigaRda> getListaRelazioniConRms() {
		return listaRelazioniConRms;
	}

	/**
	 * @param listaRelazioniConRms the listaRelazioniConRms to set
	 */
	public void setListaRelazioniConRms(List<RigaRda> listaRelazioniConRms) {
		this.listaRelazioniConRms = listaRelazioniConRms;
	}

	/**
	 * @return the quantitaSuRdaDaEvadere
	 */
	public BigDecimal getQuantitaSuRdaDaEvadere() {
		return quantitaSuRdaDaEvadere;
	}

	/**
	 * @param quantitaSuRdaDaEvadere the quantitaSuRdaDaEvadere to set
	 */
	public void setQuantitaSuRdaDaEvadere(BigDecimal quantitaSuRdaDaEvadere) {
		this.quantitaSuRdaDaEvadere = quantitaSuRdaDaEvadere;
	}



}
