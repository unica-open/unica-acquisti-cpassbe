/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.lib.dto.pba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import it.csi.cpass.cpassbe.lib.dto.BaseDto;


/**
 * The persistent class for the cpass_t_pba_acquisti_da_trasmettere database table.
 *
 */

public class AcquistiDaTrasmettere extends BaseDto<Integer> implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer acquistiDaTrasmettereId;

	private String acquistoVariatoCodice;

	private String acquistoVariatoDescrizione;

	private String ausaCodice;

	private String ausaDescrizione;

	private String codiceRisorsa;

	private String cpvCodice;

	private String cpvDescrizione;

	private String descrizioneRisorsa;

	private String enteCodiceFiscale;

	private String enteDenominazione;

	private UUID enteId;

	private Boolean esenteCup;

	private BigDecimal importoAnniSuccessivi;

	private BigDecimal importoAnnoPrimo;

	private BigDecimal importoAnnoSecondo;

	private BigDecimal importoAnnoTerzo;

	private Integer interventoAnnoAvvio;

	private UUID interventoCapofilaId;

	private String interventoCopiaTipo;

	private String interventoCui;

	private String interventoCup;

	private String interventoDescrizioneAcquisto;

	private Integer interventoDurataMesi;

	private UUID interventoId;

	private Boolean interventoLottoFunzionale;

	private Boolean interventoNuovoAffid;

	private String interventoRicompresoCui;

	private BigDecimal ivaAnniSuccessivi;

	private BigDecimal ivaPrimoAnno;

	private BigDecimal ivaSecondoAnno;

	private BigDecimal ivaTerzoAnno;

	private String modalitaAffidamento;

	private String motivazioneNonRiproposto;

	private String note;

	private String nutsCodice;

	private String nutsDescrizione;

	private String prioritaCodice;

	private String prioritaDescrizione;

	private Integer programmaAnno;

	private Integer programmaAnnoFine;

	private UUID programmaId;

	private String referenteCognome;

	private String referenteNome;

	private String ricompresoTipoCodice;

	private Boolean ricompresoTipoConteggioImporti;

	private String risorsaTagTrasmissione;

	private String rupUtenteCodiceFiscale;

	private String rupUtenteCognome;

	private String rupUtenteNome;

	private String settoreInterventiCodice;

	private String settoreInterventiDescrizione;

	private String tipologiaRisorsa;

	public Integer getAcquistiDaTrasmettereId() {
		return this.acquistiDaTrasmettereId;
	}

	public void setAcquistiDaTrasmettereId(Integer acquistiDaTrasmettereId) {
		this.acquistiDaTrasmettereId = acquistiDaTrasmettereId;
	}

	public String getAcquistoVariatoCodice() {
		return this.acquistoVariatoCodice;
	}

	public void setAcquistoVariatoCodice(String acquistoVariatoCodice) {
		this.acquistoVariatoCodice = acquistoVariatoCodice;
	}

	public String getAcquistoVariatoDescrizione() {
		return this.acquistoVariatoDescrizione;
	}

	public void setAcquistoVariatoDescrizione(String acquistoVariatoDescrizione) {
		this.acquistoVariatoDescrizione = acquistoVariatoDescrizione;
	}

	public String getAusaCodice() {
		return this.ausaCodice;
	}

	public void setAusaCodice(String ausaCodice) {
		this.ausaCodice = ausaCodice;
	}

	public String getAusaDescrizione() {
		return this.ausaDescrizione;
	}

	public void setAusaDescrizione(String ausaDescrizione) {
		this.ausaDescrizione = ausaDescrizione;
	}

	public String getCodiceRisorsa() {
		return this.codiceRisorsa;
	}

	public void setCodiceRisorsa(String codiceRisorsa) {
		this.codiceRisorsa = codiceRisorsa;
	}

	public String getCpvCodice() {
		return this.cpvCodice;
	}

	public void setCpvCodice(String cpvCodice) {
		this.cpvCodice = cpvCodice;
	}

	public String getCpvDescrizione() {
		return this.cpvDescrizione;
	}

	public void setCpvDescrizione(String cpvDescrizione) {
		this.cpvDescrizione = cpvDescrizione;
	}

	public String getDescrizioneRisorsa() {
		return this.descrizioneRisorsa;
	}

	public void setDescrizioneRisorsa(String descrizioneRisorsa) {
		this.descrizioneRisorsa = descrizioneRisorsa;
	}

	public String getEnteCodiceFiscale() {
		return this.enteCodiceFiscale;
	}

	public void setEnteCodiceFiscale(String enteCodiceFiscale) {
		this.enteCodiceFiscale = enteCodiceFiscale;
	}

	public String getEnteDenominazione() {
		return this.enteDenominazione;
	}

	public void setEnteDenominazione(String enteDenominazione) {
		this.enteDenominazione = enteDenominazione;
	}

	public UUID getEnteId() {
		return this.enteId;
	}

	public void setEnteId(UUID enteId) {
		this.enteId = enteId;
	}

	public Boolean getEsenteCup() {
		return this.esenteCup;
	}

	public void setEsenteCup(Boolean esenteCup) {
		this.esenteCup = esenteCup;
	}

	public BigDecimal getImportoAnniSuccessivi() {
		return this.importoAnniSuccessivi;
	}

	public void setImportoAnniSuccessivi(BigDecimal importoAnniSuccessivi) {
		this.importoAnniSuccessivi = importoAnniSuccessivi;
	}

	public BigDecimal getImportoAnnoPrimo() {
		return this.importoAnnoPrimo;
	}

	public void setImportoAnnoPrimo(BigDecimal importoAnnoPrimo) {
		this.importoAnnoPrimo = importoAnnoPrimo;
	}

	public BigDecimal getImportoAnnoSecondo() {
		return this.importoAnnoSecondo;
	}

	public void setImportoAnnoSecondo(BigDecimal importoAnnoSecondo) {
		this.importoAnnoSecondo = importoAnnoSecondo;
	}

	public BigDecimal getImportoAnnoTerzo() {
		return this.importoAnnoTerzo;
	}

	public void setImportoAnnoTerzo(BigDecimal importoAnnoTerzo) {
		this.importoAnnoTerzo = importoAnnoTerzo;
	}

	public Integer getInterventoAnnoAvvio() {
		return this.interventoAnnoAvvio;
	}

	public void setInterventoAnnoAvvio(Integer interventoAnnoAvvio) {
		this.interventoAnnoAvvio = interventoAnnoAvvio;
	}

	public UUID getInterventoCapofilaId() {
		return this.interventoCapofilaId;
	}

	public void setInterventoCapofilaId(UUID interventoCapofilaId) {
		this.interventoCapofilaId = interventoCapofilaId;
	}

	public String getInterventoCopiaTipo() {
		return this.interventoCopiaTipo;
	}

	public void setInterventoCopiaTipo(String interventoCopiaTipo) {
		this.interventoCopiaTipo = interventoCopiaTipo;
	}

	public String getInterventoCui() {
		return this.interventoCui;
	}

	public void setInterventoCui(String interventoCui) {
		this.interventoCui = interventoCui;
	}

	public String getInterventoCup() {
		return this.interventoCup;
	}

	public void setInterventoCup(String interventoCup) {
		this.interventoCup = interventoCup;
	}

	public String getInterventoDescrizioneAcquisto() {
		return this.interventoDescrizioneAcquisto;
	}

	public void setInterventoDescrizioneAcquisto(String interventoDescrizioneAcquisto) {
		this.interventoDescrizioneAcquisto = interventoDescrizioneAcquisto;
	}

	public Integer getInterventoDurataMesi() {
		return this.interventoDurataMesi;
	}

	public void setInterventoDurataMesi(Integer interventoDurataMesi) {
		this.interventoDurataMesi = interventoDurataMesi;
	}

	public UUID getInterventoId() {
		return this.interventoId;
	}

	public void setInterventoId(UUID interventoId) {
		this.interventoId = interventoId;
	}

	public Boolean getInterventoLottoFunzionale() {
		return this.interventoLottoFunzionale;
	}

	public void setInterventoLottoFunzionale(Boolean interventoLottoFunzionale) {
		this.interventoLottoFunzionale = interventoLottoFunzionale;
	}

	public Boolean getInterventoNuovoAffid() {
		return this.interventoNuovoAffid;
	}

	public void setInterventoNuovoAffid(Boolean interventoNuovoAffid) {
		this.interventoNuovoAffid = interventoNuovoAffid;
	}

	public String getInterventoRicompresoCui() {
		return this.interventoRicompresoCui;
	}

	public void setInterventoRicompresoCui(String interventoRicompresoCui) {
		this.interventoRicompresoCui = interventoRicompresoCui;
	}

	public BigDecimal getIvaAnniSuccessivi() {
		return this.ivaAnniSuccessivi;
	}

	public void setIvaAnniSuccessivi(BigDecimal ivaAnniSuccessivi) {
		this.ivaAnniSuccessivi = ivaAnniSuccessivi;
	}

	public BigDecimal getIvaPrimoAnno() {
		return this.ivaPrimoAnno;
	}

	public void setIvaPrimoAnno(BigDecimal ivaPrimoAnno) {
		this.ivaPrimoAnno = ivaPrimoAnno;
	}

	public BigDecimal getIvaSecondoAnno() {
		return this.ivaSecondoAnno;
	}

	public void setIvaSecondoAnno(BigDecimal ivaSecondoAnno) {
		this.ivaSecondoAnno = ivaSecondoAnno;
	}

	public BigDecimal getIvaTerzoAnno() {
		return this.ivaTerzoAnno;
	}

	public void setIvaTerzoAnno(BigDecimal ivaTerzoAnno) {
		this.ivaTerzoAnno = ivaTerzoAnno;
	}

	public String getModalitaAffidamento() {
		return this.modalitaAffidamento;
	}

	public void setModalitaAffidamento(String modalitaAffidamento) {
		this.modalitaAffidamento = modalitaAffidamento;
	}

	public String getMotivazioneNonRiproposto() {
		return this.motivazioneNonRiproposto;
	}

	public void setMotivazioneNonRiproposto(String motivazioneNonRiproposto) {
		this.motivazioneNonRiproposto = motivazioneNonRiproposto;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNutsCodice() {
		return this.nutsCodice;
	}

	public void setNutsCodice(String nutsCodice) {
		this.nutsCodice = nutsCodice;
	}

	public String getNutsDescrizione() {
		return this.nutsDescrizione;
	}

	public void setNutsDescrizione(String nutsDescrizione) {
		this.nutsDescrizione = nutsDescrizione;
	}

	public String getPrioritaCodice() {
		return this.prioritaCodice;
	}

	public void setPrioritaCodice(String prioritaCodice) {
		this.prioritaCodice = prioritaCodice;
	}

	public String getPrioritaDescrizione() {
		return this.prioritaDescrizione;
	}

	public void setPrioritaDescrizione(String prioritaDescrizione) {
		this.prioritaDescrizione = prioritaDescrizione;
	}

	public Integer getProgrammaAnno() {
		return this.programmaAnno;
	}

	public void setProgrammaAnno(Integer programmaAnno) {
		this.programmaAnno = programmaAnno;
	}

	public Integer getProgrammaAnnoFine() {
		return this.programmaAnnoFine;
	}

	public void setProgrammaAnnoFine(Integer programmaAnnoFine) {
		this.programmaAnnoFine = programmaAnnoFine;
	}

	public UUID getProgrammaId() {
		return this.programmaId;
	}

	public void setProgrammaId(UUID programmaId) {
		this.programmaId = programmaId;
	}

	public String getReferenteCognome() {
		return this.referenteCognome;
	}

	public void setReferenteCognome(String referenteCognome) {
		this.referenteCognome = referenteCognome;
	}

	public String getReferenteNome() {
		return this.referenteNome;
	}

	public void setReferenteNome(String referenteNome) {
		this.referenteNome = referenteNome;
	}

	public String getRicompresoTipoCodice() {
		return this.ricompresoTipoCodice;
	}

	public void setRicompresoTipoCodice(String ricompresoTipoCodice) {
		this.ricompresoTipoCodice = ricompresoTipoCodice;
	}

	public Boolean getRicompresoTipoConteggioImporti() {
		return this.ricompresoTipoConteggioImporti;
	}

	public void setRicompresoTipoConteggioImporti(Boolean ricompresoTipoConteggioImporti) {
		this.ricompresoTipoConteggioImporti = ricompresoTipoConteggioImporti;
	}

	public String getRisorsaTagTrasmissione() {
		return this.risorsaTagTrasmissione;
	}

	public void setRisorsaTagTrasmissione(String risorsaTagTrasmissione) {
		this.risorsaTagTrasmissione = risorsaTagTrasmissione;
	}

	public String getRupUtenteCodiceFiscale() {
		return this.rupUtenteCodiceFiscale;
	}

	public void setRupUtenteCodiceFiscale(String rupUtenteCodiceFiscale) {
		this.rupUtenteCodiceFiscale = rupUtenteCodiceFiscale;
	}

	public String getRupUtenteCognome() {
		return this.rupUtenteCognome;
	}

	public void setRupUtenteCognome(String rupUtenteCognome) {
		this.rupUtenteCognome = rupUtenteCognome;
	}

	public String getRupUtenteNome() {
		return this.rupUtenteNome;
	}

	public void setRupUtenteNome(String rupUtenteNome) {
		this.rupUtenteNome = rupUtenteNome;
	}

	public String getSettoreInterventiCodice() {
		return this.settoreInterventiCodice;
	}

	public void setSettoreInterventiCodice(String settoreInterventiCodice) {
		this.settoreInterventiCodice = settoreInterventiCodice;
	}

	public String getSettoreInterventiDescrizione() {
		return this.settoreInterventiDescrizione;
	}

	public void setSettoreInterventiDescrizione(String settoreInterventiDescrizione) {
		this.settoreInterventiDescrizione = settoreInterventiDescrizione;
	}

	public String getTipologiaRisorsa() {
		return this.tipologiaRisorsa;
	}

	public void setTipologiaRisorsa(String tipologiaRisorsa) {
		this.tipologiaRisorsa = tipologiaRisorsa;
	}

}
