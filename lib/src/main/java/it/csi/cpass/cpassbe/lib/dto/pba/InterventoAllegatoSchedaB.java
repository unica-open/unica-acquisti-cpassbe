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
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;

/**
 * Scheda B dell'allegato
 */
public class InterventoAllegatoSchedaB extends BaseDto<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String acquistoVariatoCodice;
	private String acquistoVariatoDescrizione;
	private String ausa;
	private String ausaDescrizione;
	private BigDecimal capPrivatiImportoAnniSuccessivi;
	private BigDecimal capPrivatiImportoAnnoPrimo;
	private BigDecimal capPrivatiImportoAnnoSecondo;
	private BigDecimal capPrivatiImportoAnnoTerzo;
	private BigDecimal capPrivatiTotaleImporti;
	private String cpvCodice;
	private String cpvDescrizione;
	private BigDecimal importoAnniSuccessivi;
	private BigDecimal importoAnnoPrimo;
	private BigDecimal importoAnnoSecondo;
	private BigDecimal importoAnnoTerzo;
	private Integer interventoAnnoAvvio;
	private String interventoCui;
	private String interventoCup;
	private String interventoDescrizioneAcquisto;
	private Integer interventoDurataMesi;
	private Boolean interventoLottoFunzionale;
	private Boolean interventoNuovoAffid;
	private String nutsCodice;
	private String nutsDescrizione;
	private String prioritaCodice;
	private String prioritaDescrizione;
	private UUID programmaId;
	private Integer programmaAnno;
	private String programmaReferente;
	private UUID enteId;
	private String enteCodiceFiscale;
	private String enteDenominazione;
	private String ricompresoTipoCodice;
	private String ricompresoTipoDescrizione;
	private String settoreInterventiCodice;
	private String settoreInterventiDescrizione;
	private String tipologia;
	private BigDecimal totaleImporti;
	private String utenteCodiceFiscale;
	private String utenteCognome;
	private String utenteNome;
	/**
	 * @return the acquistoVariatoCodice
	 */
	public String getAcquistoVariatoCodice() {
		return acquistoVariatoCodice;
	}
	/**
	 * @param acquistoVariatoCodice the acquistoVariatoCodice to set
	 */
	public void setAcquistoVariatoCodice(String acquistoVariatoCodice) {
		this.acquistoVariatoCodice = acquistoVariatoCodice;
	}
	/**
	 * @return the acquistoVariatoDescrizione
	 */
	public String getAcquistoVariatoDescrizione() {
		return acquistoVariatoDescrizione;
	}
	/**
	 * @param acquistoVariatoDescrizione the acquistoVariatoDescrizione to set
	 */
	public void setAcquistoVariatoDescrizione(String acquistoVariatoDescrizione) {
		this.acquistoVariatoDescrizione = acquistoVariatoDescrizione;
	}
	/**
	 * @return the ausa
	 */
	public String getAusa() {
		return ausa;
	}
	/**
	 * @param ausa the ausa to set
	 */
	public void setAusa(String ausa) {
		this.ausa = ausa;
	}
	/**
	 * @return the ausaDescrizione
	 */
	public String getAusaDescrizione() {
		return ausaDescrizione;
	}
	/**
	 * @param ausaDescrizione the ausaDescrizione to set
	 */
	public void setAusaDescrizione(String ausaDescrizione) {
		this.ausaDescrizione = ausaDescrizione;
	}
	/**
	 * @return the capPrivatiImportoAnniSuccessivi
	 */
	public BigDecimal getCapPrivatiImportoAnniSuccessivi() {
		return capPrivatiImportoAnniSuccessivi;
	}
	/**
	 * @param capPrivatiImportoAnniSuccessivi the capPrivatiImportoAnniSuccessivi to set
	 */
	public void setCapPrivatiImportoAnniSuccessivi(BigDecimal capPrivatiImportoAnniSuccessivi) {
		this.capPrivatiImportoAnniSuccessivi = capPrivatiImportoAnniSuccessivi;
	}
	/**
	 * @return the capPrivatiImportoAnnoPrimo
	 */
	public BigDecimal getCapPrivatiImportoAnnoPrimo() {
		return capPrivatiImportoAnnoPrimo;
	}
	/**
	 * @param capPrivatiImportoAnnoPrimo the capPrivatiImportoAnnoPrimo to set
	 */
	public void setCapPrivatiImportoAnnoPrimo(BigDecimal capPrivatiImportoAnnoPrimo) {
		this.capPrivatiImportoAnnoPrimo = capPrivatiImportoAnnoPrimo;
	}
	/**
	 * @return the capPrivatiImportoAnnoSecondo
	 */
	public BigDecimal getCapPrivatiImportoAnnoSecondo() {
		return capPrivatiImportoAnnoSecondo;
	}
	/**
	 * @param capPrivatiImportoAnnoSecondo the capPrivatiImportoAnnoSecondo to set
	 */
	public void setCapPrivatiImportoAnnoSecondo(BigDecimal capPrivatiImportoAnnoSecondo) {
		this.capPrivatiImportoAnnoSecondo = capPrivatiImportoAnnoSecondo;
	}

	/**
	 * @return the capPrivatiImportoAnnoTerzo
	 */
	public BigDecimal getCapPrivatiImportoAnnoTerzo() {
		return capPrivatiImportoAnnoTerzo;
	}
	/**
	 * @param capPrivatiImportoAnnoTerzo the capPrivatiImportoAnnoTerzo to set
	 */
	public void setCapPrivatiImportoAnnoTerzo(BigDecimal capPrivatiImportoAnnoTerzo) {
		this.capPrivatiImportoAnnoTerzo = capPrivatiImportoAnnoTerzo;
	}
	/**
	 * @return the capPrivatiTotaleImporti
	 */
	public BigDecimal getCapPrivatiTotaleImporti() {
		return capPrivatiTotaleImporti;
	}
	/**
	 * @param capPrivatiTotaleImporti the capPrivatiTotaleImporti to set
	 */
	public void setCapPrivatiTotaleImporti(BigDecimal capPrivatiTotaleImporti) {
		this.capPrivatiTotaleImporti = capPrivatiTotaleImporti;
	}
	/**
	 * @return the cpvCodice
	 */
	public String getCpvCodice() {
		return cpvCodice;
	}
	/**
	 * @param cpvCodice the cpvCodice to set
	 */
	public void setCpvCodice(String cpvCodice) {
		this.cpvCodice = cpvCodice;
	}
	/**
	 * @return the cpvDescrizione
	 */
	public String getCpvDescrizione() {
		return cpvDescrizione;
	}
	/**
	 * @param cpvDescrizione the cpvDescrizione to set
	 */
	public void setCpvDescrizione(String cpvDescrizione) {
		this.cpvDescrizione = cpvDescrizione;
	}
	/**
	 * @return the importoAnniSuccessivi
	 */
	public BigDecimal getImportoAnniSuccessivi() {
		return importoAnniSuccessivi;
	}
	/**
	 * @param importoAnniSuccessivi the importoAnniSuccessivi to set
	 */
	public void setImportoAnniSuccessivi(BigDecimal importoAnniSuccessivi) {
		this.importoAnniSuccessivi = importoAnniSuccessivi;
	}
	/**
	 * @return the importoAnnoPrimo
	 */
	public BigDecimal getImportoAnnoPrimo() {
		return importoAnnoPrimo;
	}
	/**
	 * @param importoAnnoPrimo the importoAnnoPrimo to set
	 */
	public void setImportoAnnoPrimo(BigDecimal importoAnnoPrimo) {
		this.importoAnnoPrimo = importoAnnoPrimo;
	}
	/**
	 * @return the importoAnnoSecondo
	 */
	public BigDecimal getImportoAnnoSecondo() {
		return importoAnnoSecondo;
	}
	/**
	 * @param importoAnnoSecondo the importoAnnoSecondo to set
	 */
	public void setImportoAnnoSecondo(BigDecimal importoAnnoSecondo) {
		this.importoAnnoSecondo = importoAnnoSecondo;
	}

	/**
	 * @return the importoAnnoTerzo
	 */
	public BigDecimal getImportoAnnoTerzo() {
		return importoAnnoTerzo;
	}
	/**
	 * @param importoAnnoTerzo the importoAnnoTerzo to set
	 */
	public void setImportoAnnoTerzo(BigDecimal importoAnnoTerzo) {
		this.importoAnnoTerzo = importoAnnoTerzo;
	}
	/**
	 * @return the interventoAnnoAvvio
	 */
	public Integer getInterventoAnnoAvvio() {
		return interventoAnnoAvvio;
	}
	/**
	 * @param interventoAnnoAvvio the interventoAnnoAvvio to set
	 */
	public void setInterventoAnnoAvvio(Integer interventoAnnoAvvio) {
		this.interventoAnnoAvvio = interventoAnnoAvvio;
	}
	/**
	 * @return the interventoCui
	 */
	public String getInterventoCui() {
		return interventoCui;
	}
	/**
	 * @param interventoCui the interventoCui to set
	 */
	public void setInterventoCui(String interventoCui) {
		this.interventoCui = interventoCui;
	}
	/**
	 * @return the interventoCup
	 */
	public String getInterventoCup() {
		return interventoCup;
	}
	/**
	 * @param interventoCup the interventoCup to set
	 */
	public void setInterventoCup(String interventoCup) {
		this.interventoCup = interventoCup;
	}
	/**
	 * @return the interventoDescrizioneAcquisto
	 */
	public String getInterventoDescrizioneAcquisto() {
		return interventoDescrizioneAcquisto;
	}
	/**
	 * @param interventoDescrizioneAcquisto the interventoDescrizioneAcquisto to set
	 */
	public void setInterventoDescrizioneAcquisto(String interventoDescrizioneAcquisto) {
		this.interventoDescrizioneAcquisto = interventoDescrizioneAcquisto;
	}
	/**
	 * @return the interventoDurataMesi
	 */
	public Integer getInterventoDurataMesi() {
		return interventoDurataMesi;
	}
	/**
	 * @param interventoDurataMesi the interventoDurataMesi to set
	 */
	public void setInterventoDurataMesi(Integer interventoDurataMesi) {
		this.interventoDurataMesi = interventoDurataMesi;
	}
	/**
	 * @return the interventoLottoFunzionale
	 */
	public Boolean getInterventoLottoFunzionale() {
		return interventoLottoFunzionale;
	}
	/**
	 * @param interventoLottoFunzionale the interventoLottoFunzionale to set
	 */
	public void setInterventoLottoFunzionale(Boolean interventoLottoFunzionale) {
		this.interventoLottoFunzionale = interventoLottoFunzionale;
	}
	/**
	 * @return the interventoNuovoAffid
	 */
	public Boolean getInterventoNuovoAffid() {
		return interventoNuovoAffid;
	}
	/**
	 * @param interventoNuovoAffid the interventoNuovoAffid to set
	 */
	public void setInterventoNuovoAffid(Boolean interventoNuovoAffid) {
		this.interventoNuovoAffid = interventoNuovoAffid;
	}
	/**
	 * @return the nutsCodice
	 */
	public String getNutsCodice() {
		return nutsCodice;
	}
	/**
	 * @param nutsCodice the nutsCodice to set
	 */
	public void setNutsCodice(String nutsCodice) {
		this.nutsCodice = nutsCodice;
	}
	/**
	 * @return the nutsDescrizione
	 */
	public String getNutsDescrizione() {
		return nutsDescrizione;
	}
	/**
	 * @param nutsDescrizione the nutsDescrizione to set
	 */
	public void setNutsDescrizione(String nutsDescrizione) {
		this.nutsDescrizione = nutsDescrizione;
	}
	/**
	 * @return the prioritaCodice
	 */
	public String getPrioritaCodice() {
		return prioritaCodice;
	}
	/**
	 * @param prioritaCodice the prioritaCodice to set
	 */
	public void setPrioritaCodice(String prioritaCodice) {
		this.prioritaCodice = prioritaCodice;
	}
	/**
	 * @return the prioritaDescrizione
	 */
	public String getPrioritaDescrizione() {
		return prioritaDescrizione;
	}
	/**
	 * @param prioritaDescrizione the prioritaDescrizione to set
	 */
	public void setPrioritaDescrizione(String prioritaDescrizione) {
		this.prioritaDescrizione = prioritaDescrizione;
	}
	/**
	 * @return the programmaId
	 */
	public UUID getProgrammaId() {
		return programmaId;
	}
	/**
	 * @param programmaId the programmaId to set
	 */
	public void setProgrammaId(UUID programmaId) {
		this.programmaId = programmaId;
	}


	/**
	 * @return the programmaAnno
	 */
	public Integer getProgrammaAnno() {
		return programmaAnno;
	}
	/**
	 * @param programmaAnno the programmaAnno to set
	 */
	public void setProgrammaAnno(Integer programmaAnno) {
		this.programmaAnno = programmaAnno;
	}
	/**
	 * @return the programmaReferente
	 */
	public String getProgrammaReferente() {
		return programmaReferente;
	}
	/**
	 * @param programmaReferente the programmaReferente to set
	 */
	public void setProgrammaReferente(String programmaReferente) {
		this.programmaReferente = programmaReferente;
	}
	/**
	 * @return the enteId
	 */
	public UUID getEnteId() {
		return enteId;
	}

	/**
	 * @return the enteCodiceFiscale
	 */
	public String getEnteCodiceFiscale() {
		return enteCodiceFiscale;
	}
	/**
	 * @param enteCodiceFiscale the enteCodiceFiscale to set
	 */
	public void setEnteCodiceFiscale(String enteCodiceFiscale) {
		this.enteCodiceFiscale = enteCodiceFiscale;
	}
	/**
	 * @return the enteDenominazione
	 */
	public String getEnteDenominazione() {
		return enteDenominazione;
	}
	/**
	 * @param enteDenominazione the enteDenominazione to set
	 */
	public void setEnteDenominazione(String enteDenominazione) {
		this.enteDenominazione = enteDenominazione;
	}
	/**
	 * @param enteId the enteId to set
	 */
	public void setEnteId(UUID enteId) {
		this.enteId = enteId;
	}
	/**
	 * @return the ricompresoTipoCodice
	 */
	public String getRicompresoTipoCodice() {
		return ricompresoTipoCodice;
	}
	/**
	 * @param ricompresoTipoCodice the ricompresoTipoCodice to set
	 */
	public void setRicompresoTipoCodice(String ricompresoTipoCodice) {
		this.ricompresoTipoCodice = ricompresoTipoCodice;
	}
	/**
	 * @return the ricompresoTipoDescrizione
	 */
	public String getRicompresoTipoDescrizione() {
		return ricompresoTipoDescrizione;
	}
	/**
	 * @param ricompresoTipoDescrizione the ricompresoTipoDescrizione to set
	 */
	public void setRicompresoTipoDescrizione(String ricompresoTipoDescrizione) {
		this.ricompresoTipoDescrizione = ricompresoTipoDescrizione;
	}
	/**
	 * @return the settoreInterventiCodice
	 */
	public String getSettoreInterventiCodice() {
		return settoreInterventiCodice;
	}
	/**
	 * @param settoreInterventiCodice the settoreInterventiCodice to set
	 */
	public void setSettoreInterventiCodice(String settoreInterventiCodice) {
		this.settoreInterventiCodice = settoreInterventiCodice;
	}
	/**
	 * @return the settoreInterventiDescrizione
	 */
	public String getSettoreInterventiDescrizione() {
		return settoreInterventiDescrizione;
	}
	/**
	 * @param settoreInterventiDescrizione the settoreInterventiDescrizione to set
	 */
	public void setSettoreInterventiDescrizione(String settoreInterventiDescrizione) {
		this.settoreInterventiDescrizione = settoreInterventiDescrizione;
	}
	/**
	 * @return the tipologia
	 */
	public String getTipologia() {
		return tipologia;
	}
	/**
	 * @param tipologia the tipologia to set
	 */
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	/**
	 * @return the totaleImporti
	 */
	public BigDecimal getTotaleImporti() {
		return totaleImporti;
	}
	/**
	 * @param totaleImporti the totaleImporti to set
	 */
	public void setTotaleImporti(BigDecimal totaleImporti) {
		this.totaleImporti = totaleImporti;
	}
	/**
	 * @return the utenteCodiceFiscale
	 */
	public String getUtenteCodiceFiscale() {
		return utenteCodiceFiscale;
	}
	/**
	 * @param utenteCodiceFiscale the utenteCodiceFiscale to set
	 */
	public void setUtenteCodiceFiscale(String utenteCodiceFiscale) {
		this.utenteCodiceFiscale = utenteCodiceFiscale;
	}
	/**
	 * @return the utenteCognome
	 */
	public String getUtenteCognome() {
		return utenteCognome;
	}
	/**
	 * @param utenteCognome the utenteCognome to set
	 */
	public void setUtenteCognome(String utenteCognome) {
		this.utenteCognome = utenteCognome;
	}
	/**
	 * @return the utenteNome
	 */
	public String getUtenteNome() {
		return utenteNome;
	}
	/**
	 * @param utenteNome the utenteNome to set
	 */
	public void setUtenteNome(String utenteNome) {
		this.utenteNome = utenteNome;
	}
	@Override
	public String toString() {
		return new StringBuilder()
				.append("InterventoAllegatoSchedaB [acquistoVariatoCodice=").append(acquistoVariatoCodice)
				.append(", acquistoVariatoDescrizione=").append(acquistoVariatoDescrizione)
				.append(", ausa=").append(ausa)
				.append(", ausaDescrizione=").append(ausaDescrizione)
				.append(", capPrivatiImportoAnniSuccessivi=").append(capPrivatiImportoAnniSuccessivi)
				.append(", capPrivatiImportoAnnoPrimo=").append(capPrivatiImportoAnnoPrimo)
				.append(", capPrivatiImportoAnnoSecondo=").append(capPrivatiImportoAnnoSecondo)
				.append(", capPrivatiTotaleImporti=").append(capPrivatiTotaleImporti)
				.append(", cpvCodice=").append(cpvCodice)
				.append(", cpvDescrizione=").append(cpvDescrizione)
				.append(", importoAnniSuccessivi=").append(importoAnniSuccessivi)
				.append(", importoAnnoPrimo=").append(importoAnnoPrimo)
				.append(", importoAnnoSecondo=").append(importoAnnoSecondo)
				.append(", interventoAnnoAvvio=").append(interventoAnnoAvvio)
				.append(", interventoCui=").append(interventoCui)
				.append(", interventoCup=").append(interventoCup)
				.append(", interventoDescrizioneAcquisto=").append(interventoDescrizioneAcquisto)
				.append(", interventoDurataMesi=").append(interventoDurataMesi)
				.append(", interventoLottoFunzionale=").append(interventoLottoFunzionale)
				.append(", interventoNuovoAffid=").append(interventoNuovoAffid)
				.append(", nutsCodice=").append(nutsCodice)
				.append(", nutsDescrizione=").append(nutsDescrizione)
				.append(", prioritaCodice=").append(prioritaCodice)
				.append(", prioritaDescrizione=").append(prioritaDescrizione)
				.append(", programmaId=").append(programmaId)
				.append(", programmaAnno=").append(programmaAnno)
				.append(", programmaReferente=").append(programmaReferente)
				.append(", enteId=").append(enteId)
				.append(", ricompresoTipoCodice=").append(ricompresoTipoCodice)
				.append(", ricompresoTipoDescrizione=").append(ricompresoTipoDescrizione)
				.append(", settoreInterventiCodice=").append(settoreInterventiCodice)
				.append(", settoreInterventiDescrizione=").append(settoreInterventiDescrizione)
				.append(", tipologia=").append(tipologia)
				.append(", totaleImporti=").append(totaleImporti)
				.append(", utenteCodiceFiscale=").append(utenteCodiceFiscale)
				.append(", utenteCognome=").append(utenteCognome)
				.append(", utenteNome=").append(utenteNome)
				.append(", id=").append(id)
				.append("]")
				.toString();
	}

}
