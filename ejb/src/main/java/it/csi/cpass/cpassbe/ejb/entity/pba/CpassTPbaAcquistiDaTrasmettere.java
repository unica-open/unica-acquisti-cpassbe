/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.ejb.entity.pba;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseEntity;


@Entity
@Table(name="cpass_t_pba_acquisti_da_trasmettere")
@NamedQuery(name="CpassTPbaAcquistiDaTrasmettere.findAll", query="SELECT c FROM CpassTPbaAcquistiDaTrasmettere c")
public class CpassTPbaAcquistiDaTrasmettere implements Serializable, BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="acquisti_da_trasmettere_id_GENERATOR", sequenceName="cpass_t_pba_acquisti_da_trasmett_acquisti_da_trasmettere_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="acquisti_da_trasmettere_id_GENERATOR")
	@Column(name="acquisti_da_trasmettere_id")
	private Integer acquistiDaTrasmettereId;

	@Column(name="acquisto_variato_codice")
	private String acquistoVariatoCodice;

	@Column(name="acquisto_variato_descrizione")
	private String acquistoVariatoDescrizione;

	@Column(name="ausa_codice")
	private String ausaCodice;

	@Column(name="ausa_descrizione")
	private String ausaDescrizione;

	@Column(name="codice_risorsa")
	private String codiceRisorsa;

	@Column(name="cpv_codice")
	private String cpvCodice;

	@Column(name="cpv_descrizione")
	private String cpvDescrizione;

	@Column(name="descrizione_risorsa")
	private String descrizioneRisorsa;

	@Column(name="ente_codice_fiscale")
	private String enteCodiceFiscale;

	@Column(name="ente_denominazione")
	private String enteDenominazione;

	@Column(name="ente_id")
	private UUID enteId;

	@Column(name="esente_cup")
	private Boolean esenteCup;

	@Column(name="importo_anni_successivi")
	private BigDecimal importoAnniSuccessivi;

	@Column(name="importo_anno_primo")
	private BigDecimal importoAnnoPrimo;

	@Column(name="importo_anno_secondo")
	private BigDecimal importoAnnoSecondo;

	@Column(name="importo_anno_terzo")
	private BigDecimal importoAnnoTerzo;

	@Column(name="intervento_anno_avvio")
	private Integer interventoAnnoAvvio;

	@Column(name="intervento_capofila_id")
	private UUID interventoCapofilaId;

	@Column(name="intervento_copia_tipo")
	private String interventoCopiaTipo;

	@Column(name="intervento_cui")
	private String interventoCui;

	@Column(name="intervento_cup")
	private String interventoCup;

	@Column(name="intervento_descrizione_acquisto")
	private String interventoDescrizioneAcquisto;

	@Column(name="intervento_durata_mesi")
	private Integer interventoDurataMesi;

	@Column(name="intervento_id")
	private UUID interventoId;

	@Column(name="intervento_lotto_funzionale")
	private Boolean interventoLottoFunzionale;

	@Column(name="intervento_nuovo_affid")
	private Boolean interventoNuovoAffid;

	@Column(name="intervento_ricompreso_cui")
	private String interventoRicompresoCui;

	@Column(name="iva_anni_successivi")
	private BigDecimal ivaAnniSuccessivi;

	@Column(name="iva_primo_anno")
	private BigDecimal ivaPrimoAnno;

	@Column(name="iva_secondo_anno")
	private BigDecimal ivaSecondoAnno;

	@Column(name="iva_terzo_anno")
	private BigDecimal ivaTerzoAnno;

	@Column(name="modalita_affidamento")
	private String modalitaAffidamento;

	@Column(name="motivazione_non_riproposto")
	private String motivazioneNonRiproposto;

	private String note;

	@Column(name="nuts_codice")
	private String nutsCodice;

	@Column(name="nuts_descrizione")
	private String nutsDescrizione;

	@Column(name="priorita_codice")
	private String prioritaCodice;

	@Column(name="priorita_descrizione")
	private String prioritaDescrizione;

	@Column(name="programma_anno")
	private Integer programmaAnno;

	@Column(name="programma_anno_fine")
	private Integer programmaAnnoFine;

	@Column(name="programma_id")
	private UUID programmaId;

	@Column(name="referente_cognome")
	private String referenteCognome;

	@Column(name="referente_nome")
	private String referenteNome;

	@Column(name="ricompreso_tipo_codice")
	private String ricompresoTipoCodice;

	@Column(name="ricompreso_tipo_conteggio_importi")
	private Boolean ricompresoTipoConteggioImporti;

	@Column(name="risorsa_tag_trasmissione")
	private String risorsaTagTrasmissione;

	@Column(name="rup_utente_codice_fiscale")
	private String rupUtenteCodiceFiscale;

	@Column(name="rup_utente_cognome")
	private String rupUtenteCognome;

	@Column(name="rup_utente_nome")
	private String rupUtenteNome;

	@Column(name="settore_interventi_codice")
	private String settoreInterventiCodice;

	@Column(name="settore_interventi_descrizione")
	private String settoreInterventiDescrizione;

	@Column(name="tipologia_risorsa")
	private String tipologiaRisorsa;

	public CpassTPbaAcquistiDaTrasmettere() {
	}

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

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return acquistiDaTrasmettereId;
	}

	@Override
	public void setId(Integer id) {
		acquistiDaTrasmettereId = id;
	}

}
