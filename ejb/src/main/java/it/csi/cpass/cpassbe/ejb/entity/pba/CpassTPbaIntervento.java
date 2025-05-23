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
package it.csi.cpass.cpassbe.ejb.entity.pba;

import java.io.Serializable;
import java.util.Date;
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

import it.csi.cpass.cpassbe.ejb.entity.CpassDCpv;
import it.csi.cpass.cpassbe.ejb.entity.CpassDStato;
import it.csi.cpass.cpassbe.ejb.entity.CpassTSettore;
import it.csi.cpass.cpassbe.ejb.entity.CpassTUtente;
import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;

/**
 * The persistent class for the cpass_t_intervento database table.
 *
 */
@Entity
@Table(name="cpass_t_pba_intervento")
@NamedQuery(name="CpassTPbaIntervento.findAll", query="SELECT c FROM CpassTPbaIntervento c")
public class CpassTPbaIntervento extends BaseAuditedEntity<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  The UUID namespace. */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_intervento");

	/** The intervento id. */
	@Id
	@Column(name="intervento_id", unique=true, nullable=false)
	private UUID interventoId;

	@Column(name="esente_cup")
	private Boolean esenteCup;

	/** The intervento anno avvio. */
	@Column(name="intervento_anno_avvio", nullable=false)
	private Integer interventoAnnoAvvio;

	/** The intervento cui. */
	@Column(name="intervento_cui", nullable=false, length=50)
	private String interventoCui;

	/** The intervento cup. */
	@Column(name="intervento_cup", length=15)
	private String interventoCup;

	/** The intervento descrizione acquisto. */
	@Column(name="intervento_descrizione_acquisto", nullable=false, length=500)
	private String interventoDescrizioneAcquisto;

	/** The intervento durata mesi. */
	@Column(name="intervento_durata_mesi", nullable=false)
	private Integer interventoDurataMesi;

	/** The intervento lotto funzionale. */
	@Column(name="intervento_lotto_funzionale", nullable=false)
	private Boolean interventoLottoFunzionale;

	/** The intervento nuovo affid. */
	@Column(name="intervento_nuovo_affid", nullable=false)
	private Boolean interventoNuovoAffid;

	/** The cpass D cpv. */
	//bi-directional many-to-one association to CpassDCpv
	@ManyToOne
	@JoinColumn(name="cpv_id", nullable=false)
	private CpassDCpv cpassDCpv;

	/** The cpass D mod affidamento. */
	//bi-directional many-to-one association to CpassDPbaModAffidamento
	@ManyToOne
	@JoinColumn(name="mod_affidamento_id", nullable=false)
	private CpassDPbaModAffidamento cpassDPbaModAffidamento;

	/** The cpass D tipo procedura. */
	//bi-directional many-to-one association to CpassDPbaModAffidamento
	@ManyToOne
	@JoinColumn(name="tipo_procedura_id")
	private CpassDPbaTipoProcedura cpassDPbaTipoProcedura;

	/** The cpass D nut. */
	//bi-directional many-to-one association to CpassDPbaNuts
	@ManyToOne
	@JoinColumn(name="nuts_id", nullable=false)
	private CpassDPbaNuts cpassDPbaNuts;

	/** The cpass D priorita. */
	//bi-directional many-to-one association to CpassDPbaPriorita
	@ManyToOne
	@JoinColumn(name="priorita_id", nullable=false)
	private CpassDPbaPriorita cpassDPbaPriorita;

	/** The cpass D settore interventi. */
	//bi-directional many-to-one association to CpassDPbaSettoreInterventi
	@ManyToOne
	@JoinColumn(name="settore_interventi_id", nullable=false)
	private CpassDPbaSettoreInterventi cpassDPbaSettoreInterventi;

	/** The cpass D stato. */
	//bi-directional many-to-one association to CpassDStato
	@ManyToOne
	@JoinColumn(name="stato_id", nullable=false)
	private CpassDStato cpassDStato;

	/** The cpass T programma. */
	//bi-directional many-to-one association to CpassTPbaProgramma
	@ManyToOne
	@JoinColumn(name="programma_id", nullable=false)
	private CpassTPbaProgramma cpassTPbaProgramma;

	/** The cpass T intervento importis. */
	//bi-directional many-to-one association to CpassTPbaInterventoImporti
	@OneToMany(mappedBy="cpassTPbaIntervento")
	private List<CpassTPbaInterventoImporti> cpassTPbaInterventoImportis;

	/** The cpass T intervento importis. */
	//bi-directional many-to-one association to CpassTPbaInterventoAltriDati
	@OneToMany(mappedBy="cpassTPbaIntervento")
	private List<CpassTPbaInterventoAltriDati> cpassTPbaInterventoAltriDatis;

	/** The cpass T intervento ricompreso. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	//@ManyToOne
	//@JoinColumn(name="intervento_ricompreso_id")
	//private CpassTPbaIntervento cpassTPbaInterventoRicompreso;

	@Column(name="intervento_ricompreso_cui")
	private String interventoRicompresoCui;

	/** The cpass T intervento ricompreso. */
	//bi-directional many-to-one association to CpassTPbaIntervento
	@ManyToOne
	@JoinColumn(name="intervento_copia_id")
	private CpassTPbaIntervento cpassTPbaInterventoCopia;

	/** The cpass D ausa. */
	//bi-directional many-to-one association to CpassDPbaAusa
	@ManyToOne
	@JoinColumn(name="ausa_id")
	private CpassDPbaAusa cpassDPbaAusa;

	/** The cpass D acquisto variato. */
	//bi-directional many-to-one association to CpassDPbaAcquistoVariato
	@ManyToOne
	@JoinColumn(name="acquisto_variato_id")
	private CpassDPbaAcquistoVariato cpassDPbaAcquistoVariato;

	/** The cpass D ricompreso tipo. */
	//bi-directional many-to-one association to CpassDRicompreso
	@ManyToOne
	@JoinColumn(name="ricompreso_tipo_id")
	private CpassDPbaRicompresoTipo cpassDPbaRicompresoTipo;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_rup_id")
	private CpassTUtente cpassTUtenteRup;

	@Column(name="intervento_copia_tipo")
	private String interventoCopiaTipo;

	@Column(name="intervento_importi_copia_tipo")
	private String interventoImportiCopiaTipo;

	@Column(name="flag_cui_non_generato")
	private Boolean flagCuiNonGenerato;

	@Column(name="motivazione_non_riproposto")
	private String motivazioneNonRiproposto;

	@Column(name="data_visto")
	private Date dataVisto;

	@ManyToOne
	@JoinColumn(name="utente_visto_id")
	private CpassTUtente cpassTUtenteVisto;

	@Column(name="data_validazione")
	private Date dataValidazione;

	@ManyToOne
	@JoinColumn(name="utente_validazione_id")
	private CpassTUtente cpassTUtenteValidazione;

	@Column(name="data_rifiuto")
	private Date dataRifiuto;

	@ManyToOne
	@JoinColumn(name="utente_rifiuto_id")
	private CpassTUtente cpassTUtenteRifiuto;

	/** The cpass D settore interventi. */
	//bi-directional many-to-one association to CpassTSettore
	@ManyToOne
	@JoinColumn(name="settore_id", nullable=false)
	private CpassTSettore cpassTSettore;


	//bi-directional many-to-one association to CpassRInterventoCpv
	@OneToMany(mappedBy="cpassTPbaIntervento")
	private List<CpassRInterventoCpv> cpassRInterventoCpvs;

	private Boolean capofila;

	@Column(name="data_visto_ragioneria")
	private Date dataVistoRagioneria;

	@Column(name="data_avviato")
	private Date dataAvviato;

	@Column(name="avviato")
	private Boolean avviato;

	@Column(name="versione_definitiva")
	private Boolean versioneDefinitiva;

	@Column(name="visto_ragioneria")
	private Boolean vistoRagioneria;

	//bi-directional many-to-one association to CpassRPbaStatiIntervento
	@OneToMany(mappedBy="cpassTPbaIntervento")
	private List<CpassRPbaStatiIntervento> cpassRPbaStatiInterventos;

	//bi-directional many-to-one association to CpassRPbaStoricoInterventoRup
	@OneToMany(mappedBy="cpassTPbaIntervento")
	private List<CpassRPbaStoricoInterventoRup> cpassRPbaStoricoInterventoRups;

	//bi-directional many-to-one association to CpassTPbaIntervento
	@ManyToOne
	@JoinColumn(name="intervento_capofila_id")
	private CpassTPbaIntervento cpassTPbaInterventoCapofila;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_visto_ragioneria_id")
	private CpassTUtente cpassTUtenteVistoRagioneria;

	//bi-directional many-to-one association to CpassTUtente
	@ManyToOne
	@JoinColumn(name="utente_avviato_id")
	private CpassTUtente cpassTUtenteAvviato;

	//bi-directional many-to-one association to CpassTPbaInterventoCig
	@OneToMany(mappedBy="cpassTPbaIntervento")
	private List<CpassTPbaInterventoCig> cpassTPbaInterventoCigs;

	@Column(name="esente_iva")
	private String esenteIva;

	/**
	 * Gets the intervento id.
	 *
	 * @return the intervento id
	 */
	public UUID getInterventoId() {
		return this.interventoId;
	}

	public String getEsenteIva() {
		return esenteIva;
	}

	public void setEsenteIva(String esenteIva) {
		this.esenteIva = esenteIva;
	}

	/**
	 * Sets the intervento id.
	 *
	 * @param interventoId the new intervento id
	 */
	public void setInterventoId(UUID interventoId) {
		this.interventoId = interventoId;
	}

	/**
	 * Gets the intervento anno avvio.
	 *
	 * @return the intervento anno avvio
	 */
	public Integer getInterventoAnnoAvvio() {
		return this.interventoAnnoAvvio;
	}

	/**
	 * Sets the intervento anno avvio.
	 *
	 * @param interventoAnnoAvvio the new intervento anno avvio
	 */
	public void setInterventoAnnoAvvio(Integer interventoAnnoAvvio) {
		this.interventoAnnoAvvio = interventoAnnoAvvio;
	}

	/**
	 * Gets the intervento cui.
	 *
	 * @return the intervento cui
	 */
	public String getInterventoCui() {
		return this.interventoCui;
	}

	/**
	 * Sets the intervento cui.
	 *
	 * @param interventoCui the new intervento cui
	 */
	public void setInterventoCui(String interventoCui) {
		this.interventoCui = interventoCui;
	}

	/**
	 * Gets the intervento cup.
	 *
	 * @return the intervento cup
	 */
	public String getInterventoCup() {
		return this.interventoCup;
	}

	/**
	 * Sets the intervento cup.
	 *
	 * @param interventoCup the new intervento cup
	 */
	public void setInterventoCup(String interventoCup) {
		this.interventoCup = interventoCup;
	}

	/**
	 * Gets the intervento descrizione acquisto.
	 *
	 * @return the intervento descrizione acquisto
	 */
	public String getInterventoDescrizioneAcquisto() {
		return this.interventoDescrizioneAcquisto;
	}

	/**
	 * Sets the intervento descrizione acquisto.
	 *
	 * @param interventoDescrizioneAcquisto the new intervento descrizione acquisto
	 */
	public void setInterventoDescrizioneAcquisto(String interventoDescrizioneAcquisto) {
		this.interventoDescrizioneAcquisto = interventoDescrizioneAcquisto;
	}

	/**
	 * Gets the intervento durata mesi.
	 *
	 * @return the intervento durata mesi
	 */
	public Integer getInterventoDurataMesi() {
		return this.interventoDurataMesi;
	}

	/**
	 * Sets the intervento durata mesi.
	 *
	 * @param interventoDurataMesi the new intervento durata mesi
	 */
	public void setInterventoDurataMesi(Integer interventoDurataMesi) {
		this.interventoDurataMesi = interventoDurataMesi;
	}

	/**
	 * Gets the intervento lotto funzionale.
	 *
	 * @return the intervento lotto funzionale
	 */
	public Boolean getInterventoLottoFunzionale() {
		return this.interventoLottoFunzionale;
	}

	/**
	 * Sets the intervento lotto funzionale.
	 *
	 * @param interventoLottoFunzionale the new intervento lotto funzionale
	 */
	public void setInterventoLottoFunzionale(Boolean interventoLottoFunzionale) {
		this.interventoLottoFunzionale = interventoLottoFunzionale;
	}

	/**
	 * Gets the intervento nuovo affid.
	 *
	 * @return the intervento nuovo affid
	 */
	public Boolean getInterventoNuovoAffid() {
		return this.interventoNuovoAffid;
	}

	/**
	 * Sets the intervento nuovo affid.
	 *
	 * @param interventoNuovoAffid the new intervento nuovo affid
	 */
	public void setInterventoNuovoAffid(Boolean interventoNuovoAffid) {
		this.interventoNuovoAffid = interventoNuovoAffid;
	}

	/**
	 * Gets the cpass D cpv.
	 *
	 * @return the cpass D cpv
	 */
	public CpassDCpv getCpassDCpv() {
		return this.cpassDCpv;
	}

	/**
	 * Sets the cpass D cpv.
	 *
	 * @param cpassDCpv the new cpass D cpv
	 */
	public void setCpassDCpv(CpassDCpv cpassDCpv) {
		this.cpassDCpv = cpassDCpv;
	}

	/**
	 * Gets the cpass D mod affidamento.
	 *
	 * @return the cpass D mod affidamento
	 */
	public CpassDPbaModAffidamento getCpassDPbaModAffidamento() {
		return this.cpassDPbaModAffidamento;
	}

	/**
	 * Sets the cpass D mod affidamento.
	 *
	 * @param cpassDPbaModAffidamento the new cpass D mod affidamento
	 */
	public void setCpassDPbaModAffidamento(CpassDPbaModAffidamento cpassDPbaModAffidamento) {
		this.cpassDPbaModAffidamento = cpassDPbaModAffidamento;
	}

	/**
	 * Gets the cpass D nut.
	 *
	 * @return the cpass D nut
	 */
	public CpassDPbaNuts getCpassDPbaNut() {
		return this.cpassDPbaNuts;
	}

	/**
	 * Sets the cpass D nut.
	 *
	 * @param cpassDPbaNuts the new cpass D nut
	 */
	public void setCpassDPbaNut(CpassDPbaNuts cpassDPbaNuts) {
		this.cpassDPbaNuts = cpassDPbaNuts;
	}

	/**
	 * Gets the cpass D priorita.
	 *
	 * @return the cpass D priorita
	 */
	public CpassDPbaPriorita getCpassDPbaPriorita() {
		return this.cpassDPbaPriorita;
	}

	/**
	 * Sets the cpass D priorita.
	 *
	 * @param cpassDPbaPriorita the new cpass D priorita
	 */
	public void setCpassDPbaPriorita(CpassDPbaPriorita cpassDPbaPriorita) {
		this.cpassDPbaPriorita = cpassDPbaPriorita;
	}

	/**
	 * Gets the cpass D settore interventi.
	 *
	 * @return the cpass D settore interventi
	 */
	public CpassDPbaSettoreInterventi getCpassDPbaSettoreInterventi() {
		return this.cpassDPbaSettoreInterventi;
	}

	/**
	 * Sets the cpass D settore interventi.
	 *
	 * @param cpassDPbaSettoreInterventi the new cpass D settore interventi
	 */
	public void setCpassDPbaSettoreInterventi(CpassDPbaSettoreInterventi cpassDPbaSettoreInterventi) {
		this.cpassDPbaSettoreInterventi = cpassDPbaSettoreInterventi;
	}

	/**
	 * Gets the cpass D stato.
	 *
	 * @return the cpass D stato
	 */
	public CpassDStato getCpassDStato() {
		return this.cpassDStato;
	}

	/**
	 * Sets the cpass D stato.
	 *
	 * @param cpassDStato the new cpass D stato
	 */
	public void setCpassDStato(CpassDStato cpassDStato) {
		this.cpassDStato = cpassDStato;
	}

	/**
	 * Gets the cpass T programma.
	 *
	 * @return the cpass T programma
	 */
	public CpassTPbaProgramma getCpassTPbaProgramma() {
		return this.cpassTPbaProgramma;
	}

	/**
	 * Sets the cpass T programma.
	 *
	 * @param cpassTPbaProgramma the new cpass T programma
	 */
	public void setCpassTPbaProgramma(CpassTPbaProgramma cpassTPbaProgramma) {
		this.cpassTPbaProgramma = cpassTPbaProgramma;
	}

	/**
	 * Gets the cpass T intervento importis.
	 *
	 * @return the cpass T intervento importis
	 */
	public List<CpassTPbaInterventoImporti> getCpassTPbaInterventoImportis() {
		return this.cpassTPbaInterventoImportis;
	}

	/**
	 * Sets the cpass T intervento importis.
	 *
	 * @param cpassTPbaInterventoImportis the new cpass T intervento importis
	 */
	public void setCpassTPbaInterventoImportis(List<CpassTPbaInterventoImporti> cpassTPbaInterventoImportis) {
		this.cpassTPbaInterventoImportis = cpassTPbaInterventoImportis;
	}

	/**
	 * @return the cpasslistCpv
	 */
	/*
	public List<CpassDCpv> getCpasslistCpv() {
		List<CpassDCpv> ris = new ArrayList<CpassDCpv>();
		List<CpassRInterventoCpv> rIntcpv = getCpassRInterventoCpvs();
		if (!rIntcpv.isEmpty()) {
			for(int i=0;i< rIntcpv.size();i++) {
				ris.add(rIntcpv.get(i).getCpassDCpv());
			}
		}
		return ris;
	}
	 */
	/**
	 * @param cpasslistCpv the cpasslistCpv to set
	 */
	//public void setCpasslistCpv(List<CpassDCpv> cpasslistCpv) {
	//this.cpasslistCpv = cpasslistCpv;
	//}


	/**
	 * Adds the cpass T intervento importi.
	 *
	 * @param cpassTPbaInterventoImporti the cpass T intervento importi
	 * @return the cpass T intervento importi
	 */
	public CpassTPbaInterventoImporti addCpassTPbaInterventoImporti(CpassTPbaInterventoImporti cpassTPbaInterventoImporti) {
		getCpassTPbaInterventoImportis().add(cpassTPbaInterventoImporti);
		cpassTPbaInterventoImporti.setCpassTPbaIntervento(this);

		return cpassTPbaInterventoImporti;
	}

	/**
	 * Removes the cpass T intervento importi.
	 *
	 * @param cpassTPbaInterventoImporti the cpass T intervento importi
	 * @return the cpass T intervento importi
	 */
	public CpassTPbaInterventoImporti removeCpassTPbaInterventoImporti(CpassTPbaInterventoImporti cpassTPbaInterventoImporti) {
		getCpassTPbaInterventoImportis().remove(cpassTPbaInterventoImporti);
		cpassTPbaInterventoImporti.setCpassTPbaIntervento(null);

		return cpassTPbaInterventoImporti;
	}
	/*
	public CpassTPbaIntervento getCpassTPbaInterventoRicompreso() {
		return this.cpassTPbaInterventoRicompreso;
	}
	public void setCpassTPbaInterventoRicompreso(CpassTPbaIntervento cpassTPbaInterventoRicompreso) {
		this.cpassTPbaInterventoRicompreso = cpassTPbaInterventoRicompreso;
	}
	 */

	/**
	 * Gets the cpass D ausa.
	 *
	 * @return the cpass D ausa
	 */
	public CpassDPbaAusa getCpassDPbaAusa() {
		return cpassDPbaAusa;
	}

	/**
	 * Sets the cpass D ausa.
	 *
	 * @param cpassDPbaAusa the new cpass D ausa
	 */
	public void setCpassDPbaAusa(CpassDPbaAusa cpassDPbaAusa) {
		this.cpassDPbaAusa = cpassDPbaAusa;
	}

	/**
	 * Gets the cpass D acquisto variato.
	 *
	 * @return the cpass D acquisto variato
	 */
	public CpassDPbaAcquistoVariato getCpassDPbaAcquistoVariato() {
		return cpassDPbaAcquistoVariato;
	}

	/**
	 * Sets the cpass D acquisto variato.
	 *
	 * @param cpassDPbaAcquistoVariato the new cpass D acquisto variato
	 */
	public void setCpassDPbaAcquistoVariato(CpassDPbaAcquistoVariato cpassDPbaAcquistoVariato) {
		this.cpassDPbaAcquistoVariato = cpassDPbaAcquistoVariato;
	}

	/**
	 * Gets the cpass D ricompreso tipo.
	 *
	 * @return the cpass D ricompreso tipo
	 */
	public CpassDPbaRicompresoTipo getCpassDPbaRicompresoTipo() {
		return cpassDPbaRicompresoTipo;
	}

	/**
	 * Sets the cpass D ricompreso tipo.
	 *
	 * @param cpassDPbaRicompresoTipo the new cpass D ricompreso tipo
	 */
	public void setCpassDPbaRicompresoTipo(CpassDPbaRicompresoTipo cpassDPbaRicompresoTipo) {
		this.cpassDPbaRicompresoTipo = cpassDPbaRicompresoTipo;
	}


	/**
	 * @return the cpassTUtenteRup
	 */
	public CpassTUtente getCpassTUtenteRup() {
		return cpassTUtenteRup;
	}

	/**
	 * @param cpassTUtenteRup the cpassTUtenteRup to set
	 */
	public void setCpassTUtenteRup(CpassTUtente cpassTUtenteRup) {
		this.cpassTUtenteRup = cpassTUtenteRup;
	}


	/**
	 * @return the cpassDPbaNuts
	 */
	public CpassDPbaNuts getCpassDPbaNuts() {
		return cpassDPbaNuts;
	}

	/**
	 * @param cpassDPbaNuts the cpassDPbaNuts to set
	 */
	public void setCpassDPbaNuts(CpassDPbaNuts cpassDPbaNuts) {
		this.cpassDPbaNuts = cpassDPbaNuts;
	}

	/**
	 * @return the cpassTPbaInterventoCopia
	 */
	public CpassTPbaIntervento getCpassTPbaInterventoCopia() {
		return cpassTPbaInterventoCopia;
	}

	/**
	 * @param cpassTPbaInterventoCopia the cpassTPbaInterventoCopia to set
	 */
	public void setCpassTPbaInterventoCopia(CpassTPbaIntervento cpassTPbaInterventoCopia) {
		this.cpassTPbaInterventoCopia = cpassTPbaInterventoCopia;
	}

	/**
	 * @return the flagCuiNonGenerato
	 */
	public Boolean getFlagCuiNonGenerato() {
		return flagCuiNonGenerato;
	}

	/**
	 * @param flagCuiNonGenerato the flagCuiNonGenerato to set
	 */
	public void setFlagCuiNonGenerato(Boolean flagCuiNonGenerato) {
		this.flagCuiNonGenerato = flagCuiNonGenerato;
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
	 * @return the interventoCopiaTipo
	 */
	public String getInterventoCopiaTipo() {
		return interventoCopiaTipo;
	}

	/**
	 * @param interventoCopiaTipo the interventoCopiaTipo to set
	 */
	public void setInterventoCopiaTipo(String interventoCopiaTipo) {
		this.interventoCopiaTipo = interventoCopiaTipo;
	}


	/**
	 * @return the interventoImportiCopiaTipo
	 */
	public String getInterventoImportiCopiaTipo() {
		return interventoImportiCopiaTipo;
	}

	/**
	 * @param interventoImportiCopiaTipo the interventoImportiCopiaTipo to set
	 */
	public void setInterventoImportiCopiaTipo(String interventoImportiCopiaTipo) {
		this.interventoImportiCopiaTipo = interventoImportiCopiaTipo;
	}

	/**
	 * @return the cpassTSettore
	 */
	public CpassTSettore getCpassTSettore() {
		return cpassTSettore;
	}

	/**
	 * @param cpassTSettore the cpassTSettore to set
	 */
	public void setCpassTSettore(CpassTSettore cpassTSettore) {
		this.cpassTSettore = cpassTSettore;
	}


	/**
	 * @return the cpassTPbaInterventoAltriDatis
	 */
	public List<CpassTPbaInterventoAltriDati> getCpassTPbaInterventoAltriDatis() {
		return cpassTPbaInterventoAltriDatis;
	}

	/**
	 * @param cpassTPbaInterventoAltriDatis the cpassTPbaInterventoAltriDatis to set
	 */
	public void setCpassTPbaInterventoAltriDatis(List<CpassTPbaInterventoAltriDati> cpassTPbaInterventoAltriDatis) {
		this.cpassTPbaInterventoAltriDatis = cpassTPbaInterventoAltriDatis;
	}

	public List<CpassRInterventoCpv> getCpassRInterventoCpvs() {
		return this.cpassRInterventoCpvs;
	}

	public void setCpassRInterventoCpvs(List<CpassRInterventoCpv> cpassRInterventoCpvs) {
		this.cpassRInterventoCpvs = cpassRInterventoCpvs;
	}

	public CpassRInterventoCpv addCpassRInterventoCpv(CpassRInterventoCpv cpassRInterventoCpv) {
		getCpassRInterventoCpvs().add(cpassRInterventoCpv);
		cpassRInterventoCpv.setCpassTPbaIntervento(this);

		return cpassRInterventoCpv;
	}

	public CpassRInterventoCpv removeCpassRInterventoCpv(CpassRInterventoCpv cpassRInterventoCpv) {
		getCpassRInterventoCpvs().remove(cpassRInterventoCpv);
		cpassRInterventoCpv.setCpassTPbaIntervento(null);

		return cpassRInterventoCpv;
	}

	/**
	 * @return the esenteCup
	 */
	public Boolean getEsenteCup() {
		return esenteCup;
	}

	/**
	 * @param esenteCup the esenteCup to set
	 */
	public void setEsenteCup(Boolean esenteCup) {
		this.esenteCup = esenteCup;
	}


	/**
	 * @return the dataVisto
	 */
	public Date getDataVisto() {
		return dataVisto;
	}

	/**
	 * @param dataVisto the dataVisto to set
	 */
	public void setDataVisto(Date dataVisto) {
		this.dataVisto = dataVisto;
	}

	/**
	 * @return the cpassTUtenteVisto
	 */
	public CpassTUtente getCpassTUtenteVisto() {
		return cpassTUtenteVisto;
	}

	/**
	 * @param cpassTUtenteVisto the cpassTUtenteVisto to set
	 */
	public void setCpassTUtenteVisto(CpassTUtente cpassTUtenteVisto) {
		this.cpassTUtenteVisto = cpassTUtenteVisto;
	}

	/**
	 * @return the dataValidazione
	 */
	public Date getDataValidazione() {
		return dataValidazione;
	}

	/**
	 * @param dataValidazione the dataValidazione to set
	 */
	public void setDataValidazione(Date dataValidazione) {
		this.dataValidazione = dataValidazione;
	}

	/**
	 * @return the cpassTUtenteValidazione
	 */
	public CpassTUtente getCpassTUtenteValidazione() {
		return cpassTUtenteValidazione;
	}

	/**
	 * @param cpassTUtenteValidazione the cpassTUtenteValidazione to set
	 */
	public void setCpassTUtenteValidazione(CpassTUtente cpassTUtenteValidazione) {
		this.cpassTUtenteValidazione = cpassTUtenteValidazione;
	}

	/**
	 * @return the dataRifiuto
	 */
	public Date getDataRifiuto() {
		return dataRifiuto;
	}

	/**
	 * @param dataRifiuto the dataRifiuto to set
	 */
	public void setDataRifiuto(Date dataRifiuto) {
		this.dataRifiuto = dataRifiuto;
	}

	/**
	 * @return the cpassTUtenteRifiuto
	 */
	public CpassTUtente getCpassTUtenteRifiuto() {
		return cpassTUtenteRifiuto;
	}

	/**
	 * @param cpassTUtenteRifiuto the cpassTUtenteRifiuto to set
	 */
	public void setCpassTUtenteRifiuto(CpassTUtente cpassTUtenteRifiuto) {
		this.cpassTUtenteRifiuto = cpassTUtenteRifiuto;
	}


	/**
	 * @return the interventoRicompresoCui
	 */
	public String getInterventoRicompresoCui() {
		return interventoRicompresoCui;
	}

	/**
	 * @param interventoRicompresoCui the interventoRicompresoCui to set
	 */
	public void setInterventoRicompresoCui(String interventoRicompresoCui) {
		this.interventoRicompresoCui = interventoRicompresoCui;
	}

	@Override
	public UUID getId() {
		return interventoId;
	}

	@Override
	public void setId(UUID id) {
		interventoId = id;
	}


	@Override
	public void initId() {
		final UUID id = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, cpassTPbaProgramma.getId() + "|" + interventoCui);
		this.interventoId = id;
	}
	//////////////////////////



	public List<CpassRPbaStatiIntervento> getCpassRPbaStatiInterventos() {
		return this.cpassRPbaStatiInterventos;
	}

	/**
	 * @return the dataAvviato
	 */
	public Date getDataAvviato() {
		return dataAvviato;
	}

	/**
	 * @param dataAvviato the dataAvviato to set
	 */
	public void setDataAvviato(Date dataAvviato) {
		this.dataAvviato = dataAvviato;
	}

	/**
	 * @return the avviato
	 */
	public Boolean getAvviato() {
		return avviato;
	}

	/**
	 * @param avviato the avviato to set
	 */
	public void setAvviato(Boolean avviato) {
		this.avviato = avviato;
	}

	/**
	 * @return the cpassTUtenteAvviato
	 */
	public CpassTUtente getCpassTUtenteAvviato() {
		return cpassTUtenteAvviato;
	}

	/**
	 * @param cpassTUtenteAvviato the cpassTUtenteAvviato to set
	 */
	public void setCpassTUtenteAvviato(CpassTUtente cpassTUtenteAvviato) {
		this.cpassTUtenteAvviato = cpassTUtenteAvviato;
	}

	public void setCpassRPbaStatiInterventos(List<CpassRPbaStatiIntervento> cpassRPbaStatiInterventos) {
		this.cpassRPbaStatiInterventos = cpassRPbaStatiInterventos;
	}

	public CpassRPbaStatiIntervento addCpassRPbaStatiIntervento(CpassRPbaStatiIntervento cpassRPbaStatiIntervento) {
		getCpassRPbaStatiInterventos().add(cpassRPbaStatiIntervento);
		cpassRPbaStatiIntervento.setCpassTPbaIntervento(this);

		return cpassRPbaStatiIntervento;
	}

	public CpassRPbaStatiIntervento removeCpassRPbaStatiIntervento(CpassRPbaStatiIntervento cpassRPbaStatiIntervento) {
		getCpassRPbaStatiInterventos().remove(cpassRPbaStatiIntervento);
		cpassRPbaStatiIntervento.setCpassTPbaIntervento(null);

		return cpassRPbaStatiIntervento;
	}

	/**
	 * @return the capofila
	 */
	public Boolean getCapofila() {
		return capofila;
	}

	/**
	 * @param capofila the capofila to set
	 */
	public void setCapofila(Boolean capofila) {
		this.capofila = capofila;
	}

	/**
	 * @return the dataVistoRagioneria
	 */
	public Date getDataVistoRagioneria() {
		return dataVistoRagioneria;
	}

	/**
	 * @param dataVistoRagioneria the dataVistoRagioneria to set
	 */
	public void setDataVistoRagioneria(Date dataVistoRagioneria) {
		this.dataVistoRagioneria = dataVistoRagioneria;
	}

	/**
	 * @return the versioneDefinitiva
	 */
	public Boolean getVersioneDefinitiva() {
		return versioneDefinitiva;
	}

	/**
	 * @param versioneDefinitiva the versioneDefinitiva to set
	 */
	public void setVersioneDefinitiva(Boolean versioneDefinitiva) {
		this.versioneDefinitiva = versioneDefinitiva;
	}

	/**
	 * @return the vistoRagioneria
	 */
	public Boolean getVistoRagioneria() {
		return vistoRagioneria;
	}

	/**
	 * @param vistoRagioneria the vistoRagioneria to set
	 */
	public void setVistoRagioneria(Boolean vistoRagioneria) {
		this.vistoRagioneria = vistoRagioneria;
	}

	/**
	 * @return the cpassRPbaStoricoInterventoRups
	 */
	public List<CpassRPbaStoricoInterventoRup> getCpassRPbaStoricoInterventoRups() {
		return cpassRPbaStoricoInterventoRups;
	}

	/**
	 * @param cpassRPbaStoricoInterventoRups the cpassRPbaStoricoInterventoRups to set
	 */
	public void setCpassRPbaStoricoInterventoRups(List<CpassRPbaStoricoInterventoRup> cpassRPbaStoricoInterventoRups) {
		this.cpassRPbaStoricoInterventoRups = cpassRPbaStoricoInterventoRups;
	}

	/**


	/**
	 * @return the cpassTUtenteVistoRagioneria
	 */
	public CpassTUtente getCpassTUtenteVistoRagioneria() {
		return cpassTUtenteVistoRagioneria;
	}

	/**
	 * @return the cpassTPbaInterventoCapofila
	 */
	public CpassTPbaIntervento getCpassTPbaInterventoCapofila() {
		return cpassTPbaInterventoCapofila;
	}

	/**
	 * @param cpassTPbaInterventoCapofila the cpassTPbaInterventoCapofila to set
	 */
	public void setCpassTPbaInterventoCapofila(CpassTPbaIntervento cpassTPbaInterventoCapofila) {
		this.cpassTPbaInterventoCapofila = cpassTPbaInterventoCapofila;
	}

	/**
	 * @param cpassTUtenteVistoRagioneria the cpassTUtenteVistoRagioneria to set
	 */
	public void setCpassTUtenteVistoRagioneria(CpassTUtente cpassTUtenteVistoRagioneria) {
		this.cpassTUtenteVistoRagioneria = cpassTUtenteVistoRagioneria;
	}

	/**
	 * @return the cpassDPbaTipoProcedura
	 */
	public CpassDPbaTipoProcedura getCpassDPbaTipoProcedura() {
		return cpassDPbaTipoProcedura;
	}

	/**
	 * @param cpassDPbaTipoProcedura the cpassDPbaTipoProcedura to set
	 */
	public void setCpassDPbaTipoProcedura(CpassDPbaTipoProcedura cpassDPbaTipoProcedura) {
		this.cpassDPbaTipoProcedura = cpassDPbaTipoProcedura;
	}

	/**
	 * @return the cpassTPbaInterventoCigs
	 */
	public List<CpassTPbaInterventoCig> getCpassTPbaInterventoCigs() {
		return cpassTPbaInterventoCigs;
	}

	/**
	 * @param cpassTPbaInterventoCigs the cpassTPbaInterventoCigs to set
	 */
	public void setCpassTPbaInterventoCigs(List<CpassTPbaInterventoCig> cpassTPbaInterventoCigs) {
		this.cpassTPbaInterventoCigs = cpassTPbaInterventoCigs;
	}



}
