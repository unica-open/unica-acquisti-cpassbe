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
package it.csi.cpass.cpassbe.lib.dto.custom;

import com.opencsv.bean.CsvBindByPosition;



public class AcquistiCsvAdapter implements CsvAdapter {
	@CsvBindByPosition(position = 0) String cui;
	@CsvBindByPosition(position = 1) String annoAvvioString;
	@CsvBindByPosition(position = 2) String cup;
	@CsvBindByPosition(position = 3) String acquistoRicompreso;
	@CsvBindByPosition(position = 4) String cuiInterventoRicompreso;
	@CsvBindByPosition(position = 5) String lottoFunzionaleString;
	@CsvBindByPosition(position = 6) String durataMesiString;
	@CsvBindByPosition(position = 7) String nuovoAffidamentoString;
	@CsvBindByPosition(position = 8) String codiceAusa;
	@CsvBindByPosition(position = 9) String acquistoVariato;
	@CsvBindByPosition(position = 10) String codiceFiscaleRup;
	@CsvBindByPosition(position = 11) String descrizioneAcquisto;
	@CsvBindByPosition(position = 12) String settoreInterventi;
	@CsvBindByPosition(position = 13) String cpv;
	@CsvBindByPosition(position = 14) String nuts;
	@CsvBindByPosition(position = 15) String priorita;
	@CsvBindByPosition(position = 16) String modalitaAffidamento;
	@CsvBindByPosition(position = 17) String stato;
	@CsvBindByPosition(position = 18) String settore;
	@CsvBindByPosition(position = 19) String codiceInterno;
	@CsvBindByPosition(position = 20) String motivazioneNonRiproposto;
	@CsvBindByPosition(position = 21) String note;
	@CsvBindByPosition(position = 22) String tipoProcedura;





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
	 * @return the annoAvvioString
	 */
	public String getAnnoAvvioString() {
		return annoAvvioString;
	}

	/**
	 * @param annoAvvioString the annoAvvioString to set
	 */
	public void setAnnoAvvioString(String annoAvvioString) {
		this.annoAvvioString = annoAvvioString;
	}

	/**
	 * @return the cup
	 */
	public String getCup() {
		return cup;
	}

	/**
	 * @param cup the cup to set
	 */
	public void setCup(String cup) {
		this.cup = cup;
	}

	/**
	 * @return the acquistoRicompreso
	 */
	public String getAcquistoRicompreso() {
		return acquistoRicompreso;
	}

	/**
	 * @param acquistoRicompreso the acquistoRicompreso to set
	 */
	public void setAcquistoRicompreso(String acquistoRicompreso) {
		this.acquistoRicompreso = acquistoRicompreso;
	}

	/**
	 * @return the cuiInterventoRicompreso
	 */
	public String getCuiInterventoRicompreso() {
		return cuiInterventoRicompreso;
	}

	/**
	 * @param cuiInterventoRicompreso the cuiInterventoRicompreso to set
	 */
	public void setCuiInterventoRicompreso(String cuiInterventoRicompreso) {
		this.cuiInterventoRicompreso = cuiInterventoRicompreso;
	}

	/**
	 * @return the lottoFunzionaleString
	 */
	public String getLottoFunzionaleString() {
		return lottoFunzionaleString;
	}

	/**
	 * @param lottoFunzionaleString the lottoFunzionaleString to set
	 */
	public void setLottoFunzionaleString(String lottoFunzionaleString) {
		this.lottoFunzionaleString = lottoFunzionaleString;
	}

	/**
	 * @return the durataMesiString
	 */
	public String getDurataMesiString() {
		return durataMesiString;
	}

	/**
	 * @param durataMesiString the durataMesiString to set
	 */
	public void setDurataMesiString(String durataMesiString) {
		this.durataMesiString = durataMesiString;
	}

	/**
	 * @return the nuovoAffidamentoString
	 */
	public String getNuovoAffidamentoString() {
		return nuovoAffidamentoString;
	}

	/**
	 * @param nuovoAffidamentoString the nuovoAffidamentoString to set
	 */
	public void setNuovoAffidamentoString(String nuovoAffidamentoString) {
		this.nuovoAffidamentoString = nuovoAffidamentoString;
	}

	/**
	 * @return the codiceAusa
	 */
	public String getCodiceAusa() {
		return codiceAusa;
	}

	/**
	 * @param codiceAusa the codiceAusa to set
	 */
	public void setCodiceAusa(String codiceAusa) {
		this.codiceAusa = codiceAusa;
	}

	/**
	 * @return the acquistoVariato
	 */
	public String getAcquistoVariato() {
		return acquistoVariato;
	}

	/**
	 * @param acquistoVariato the acquistoVariato to set
	 */
	public void setAcquistoVariato(String acquistoVariato) {
		this.acquistoVariato = acquistoVariato;
	}

	/**
	 * @return the codiceFiscaleRup
	 */
	public String getCodiceFiscaleRup() {
		return codiceFiscaleRup;
	}

	/**
	 * @param codiceFiscaleRup the codiceFiscaleRup to set
	 */
	public void setCodiceFiscaleRup(String codiceFiscaleRup) {
		this.codiceFiscaleRup = codiceFiscaleRup;
	}

	/**
	 * @return the descrizioneAcquisto
	 */
	public String getDescrizioneAcquisto() {
		return descrizioneAcquisto;
	}

	/**
	 * @param descrizioneAcquisto the descrizioneAcquisto to set
	 */
	public void setDescrizioneAcquisto(String descrizioneAcquisto) {
		this.descrizioneAcquisto = descrizioneAcquisto;
	}

	/**
	 * @return the settoreInterventi
	 */
	public String getSettoreInterventi() {
		return settoreInterventi;
	}

	/**
	 * @param settoreInterventi the settoreInterventi to set
	 */
	public void setSettoreInterventi(String settoreInterventi) {
		this.settoreInterventi = settoreInterventi;
	}

	/**
	 * @return the cpv
	 */
	public String getCpv() {
		return cpv;
	}

	/**
	 * @param cpv the cpv to set
	 */
	public void setCpv(String cpv) {
		this.cpv = cpv;
	}

	/**
	 * @return the nuts
	 */
	public String getNuts() {
		return nuts;
	}

	/**
	 * @param nuts the nuts to set
	 */
	public void setNuts(String nuts) {
		this.nuts = nuts;
	}

	/**
	 * @return the priorita
	 */
	public String getPriorita() {
		return priorita;
	}

	/**
	 * @param priorita the priorita to set
	 */
	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}

	/**
	 * @return the modalitaAffidamento
	 */
	public String getModalitaAffidamento() {
		return modalitaAffidamento;
	}

	/**
	 * @param modalitaAffidamento the modalitaAffidamento to set
	 */
	public void setModalitaAffidamento(String modalitaAffidamento) {
		this.modalitaAffidamento = modalitaAffidamento;
	}

	/**
	 * @return the stato
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * @return the settore
	 */
	public String getSettore() {
		return settore;
	}

	/**
	 * @param settore the settore to set
	 */
	public void setSettore(String settore) {
		this.settore = settore;
	}


	/**
	 * @return the codiceInterno
	 */
	public String getCodiceInterno() {
		return codiceInterno;
	}

	/**
	 * @param codiceInterno the codiceInterno to set
	 */
	public void setCodiceInterno(String codiceInterno) {
		this.codiceInterno = codiceInterno;
	}

	@Override
	public boolean validate() {
		return true;
	}


	/**
	 * @return the motivazioneNonRiproposto
	 */
	public String getMotivazioneNonRiproposto() {
		return motivazioneNonRiproposto;
	}

	/**
	 * @param motivazioneNonRiproposto the motivazioneNonRiproposto to set
	 */
	public void setMotivazioneNonRiproposto(String motivazioneNonRiproposto) {
		this.motivazioneNonRiproposto = motivazioneNonRiproposto;
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
	 * @return the tipoProcedura
	 */
	public String getTipoProcedura() {
		return tipoProcedura;
	}

	/**
	 * @param tipoProcedura the tipoProcedura to set
	 */
	public void setTipoProcedura(String tipoProcedura) {
		this.tipoProcedura = tipoProcedura;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AcquistiAdapter [cui=").append(cui).append(", cup=").append(cup)
				.append(", acquistoRicompreso=").append(acquistoRicompreso).append(", cuiInterventoRicompreso=")
				.append(cuiInterventoRicompreso).append(", codiceAusa=").append(codiceAusa)
				.append(", acquistoVariato=").append(acquistoVariato).append(", codiceFiscaleRup=")
				.append(codiceFiscaleRup).append(", descrizioneAcquisto=").append(descrizioneAcquisto)
				.append(", settoreInterventi=").append(settoreInterventi).append(", cpv=").append(cpv)
				.append(", nuts=").append(nuts).append(", priorita=").append(priorita)
				.append(", modalitaAffidamento=").append(modalitaAffidamento).append(", stato=").append(stato)
				;
		return builder.toString();
	}



}
