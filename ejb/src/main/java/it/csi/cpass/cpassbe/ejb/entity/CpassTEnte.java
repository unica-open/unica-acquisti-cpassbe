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
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.cpass.cpassbe.ejb.entity.base.BaseAuditedEntity;
import it.csi.cpass.cpassbe.ejb.entity.mag.CpassTMagMagazzino;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassDOrdTipoProcedura;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTFornitore;
import it.csi.cpass.cpassbe.ejb.entity.ord.CpassTOrdSezione;
import it.csi.cpass.cpassbe.ejb.entity.ord.evasione.CpassDOrdCausaleSospensioneEvasione;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdRigaRda;
import it.csi.cpass.cpassbe.ejb.entity.ord.rda.CpassTOrdTestataRda;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassDPbaTipoProcedura;
import it.csi.cpass.cpassbe.ejb.entity.pba.CpassTPbaProgramma;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRegoleSmistamentoRms;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsRigaRms;
import it.csi.cpass.cpassbe.ejb.entity.rms.CpassTRmsTestataRms;
import it.csi.cpass.cpassbe.lib.util.uuid.UuidUtils;


/**
 * The persistent class for the cpass_t_ente database table.
 *
 */
@Entity
@Table(name="cpass_t_ente")
@NamedQuery(name="CpassTEnte.findAll", query="SELECT c FROM CpassTEnte c")
public class CpassTEnte extends BaseAuditedEntity<UUID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The UUID namespace */
	public static final UUID NAMESPACE = UuidUtils.generateUUIDv5FromNamespaceAndString(UuidUtils.NAMESPACE_OID, "cpass_t_ente");

	/** The ente id. */
	@Id
	@Column(name="ente_id", unique=true, nullable=false)
	private UUID enteId;

	@Column(name="ente_codice", nullable=false, length=50)
	private String enteCodice;

	@Column(name="ente_codice_fiscale", nullable=false, length=16)
	private String enteCodiceFiscale;

	@Column(name="ente_denominazione", nullable=false, length=500)
	private String enteDenominazione;

	@Column(name="codice_ipa_amministrazione", length=200)
	private String codiceIpaAmministrazione;

	@Column(name="dipartimento", length=200)
	private String dipartimento;

	@Column(name="ufficio", length=200)
	private String ufficio;

	@Column(name="regione", length=200)
	private String regione;

	@Column(name="provincia", length=200)
	private String provincia;

	@Column(name="indirizzo", length=200)
	private String indirizzo;

	@Column(name="telefono", length=200)
	private String telefono;

	@Column(name="email", length=200)
	private String email;

	//@Column(name="emailPEC", length=200)
	//private String emailPEC;

	@Column(name="path_logo", length=200)
	private String pathLogo;

	@Column(name="link", length=200)
	private String link;

	@Column(name="emailpec")
	private String emailpec;


	//bi-directional many-to-one association to CpassDOggettiSpesa
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassDOggettiSpesa> cpassDOggettiSpesas;

	//bi-directional many-to-one association to CpassDOrdCausaleSospensioneEvasione
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassDOrdCausaleSospensioneEvasione> cpassDOrdCausaleSospensioneEvasiones;

	//bi-directional many-to-one association to CpassDOrdTipoProcedura
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassDOrdTipoProcedura> cpassDOrdTipoProceduras;

	//bi-directional many-to-one association to CpassDPbaTipoProcedura
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassDPbaTipoProcedura> cpassDPbaTipoProceduras;

	//bi-directional many-to-one association to CpassDProvvedimentoTipo
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassDProvvedimentoTipo> cpassDProvvedimentoTipos;

	//bi-directional many-to-one association to CpassDTipoSettore
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassDTipoSettore> cpassDTipoSettores;

	//bi-directional many-to-one association to CpassRRuoloModulo
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassRRuoloModulo> cpassRRuoloModulos;

	//bi-directional many-to-one association to CpassTElaborazione
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTElaborazione> cpassTElaboraziones;

	//bi-directional many-to-one association to CpassTFornitore
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTFornitore> cpassTFornitores;

	//bi-directional many-to-one association to CpassTGestioneCampo
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTGestioneCampo> cpassTGestioneCampos;

	//bi-directional many-to-one association to CpassTImpegno
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTImpegno> cpassTImpegnos;

	//bi-directional many-to-one association to CpassTMagMagazzino
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTMagMagazzino> cpassTMagMagazzinos;

	//bi-directional many-to-one association to CpassTOrdRigaRda
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTOrdRigaRda> cpassTOrdRigaRdas;

	//bi-directional many-to-one association to CpassTOrdSezione
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTOrdSezione> cpassTOrdSeziones;

	//bi-directional many-to-one association to CpassTOrdTestataRda
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTOrdTestataRda> cpassTOrdTestataRdas;

	//bi-directional many-to-one association to CpassTParametro
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTParametro> cpassTParametros;

	//bi-directional many-to-one association to CpassTPbaProgramma
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTPbaProgramma> cpassTPbaProgrammas;

	//bi-directional many-to-one association to CpassTProgressivo
	// commentata per jpa errore nel collegamento con la cpassTProgressivo.( cpassTProgressivo ha un id composto anche dall'ente id)
	//	@OneToMany(mappedBy="cpassTEnte")
	//	private List<CpassTProgressivo> cpassTProgressivos;

	//bi-directional many-to-one association to CpassTProvvedimento
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTProvvedimento> cpassTProvvedimentos;

	//bi-directional many-to-one association to CpassTRegoleSmistamentoRm
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTRegoleSmistamentoRms> cpassTRegoleSmistamentoRms;

	//bi-directional many-to-one association to CpassTRmsRigaRm
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTRmsRigaRms> cpassTRmsRigaRms;

	//bi-directional many-to-one association to CpassTRmsTestataRm
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTRmsTestataRms> cpassTRmsTestataRms;

	//bi-directional many-to-one association to CpassTSchedulazioneBatch
	//@OneToMany(mappedBy="cpassTEnte")
	//private List<CpassTSchedulazioneBatch> cpassTSchedulazioneBatches;

	//bi-directional many-to-one association to CpassTSettore
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTSettore> cpassTSettores;

	//bi-directional many-to-one association to CpassTSettoreStorico
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTSettoreStorico> cpassTSettoreStoricos;

	//bi-directional many-to-one association to CpassTSubimpegno
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTSubimpegno> cpassTSubimpegnos;

	//bi-directional many-to-one association to CpassTUfficio
	@OneToMany(mappedBy="cpassTEnte")
	private List<CpassTUfficio> cpassTUfficios;








	/**
	 * Gets the ente id.
	 *
	 * @return the ente id
	 */
	public UUID getEnteId() {
		return this.enteId;
	}

	/**
	 * Sets the ente id.
	 *
	 * @param enteId the new ente id
	 */
	public void setEnteId(UUID enteId) {
		this.enteId = enteId;
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
	 * @return the codiceIpaAmministrazione
	 */
	public String getCodiceIpaAmministrazione() {
		return codiceIpaAmministrazione;
	}

	/**
	 * @param codiceIpaAmministrazione the codiceIpaAmministrazione to set
	 */
	public void setCodiceIpaAmministrazione(String codiceIpaAmministrazione) {
		this.codiceIpaAmministrazione = codiceIpaAmministrazione;
	}

	/**
	 * @return the dipartimento
	 */
	public String getDipartimento() {
		return dipartimento;
	}

	/**
	 * @param dipartimento the dipartimento to set
	 */
	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
	}

	/**
	 * @return the ufficio
	 */
	public String getUfficio() {
		return ufficio;
	}

	/**
	 * @param ufficio the ufficio to set
	 */
	public void setUfficio(String ufficio) {
		this.ufficio = ufficio;
	}

	/**
	 * @return the regione
	 */
	public String getRegione() {
		return regione;
	}

	/**
	 * @param regione the regione to set
	 */
	public void setRegione(String regione) {
		this.regione = regione;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the enteCodice
	 */
	public String getEnteCodice() {
		return enteCodice;
	}

	/**
	 * @param enteCodice the enteCodice to set
	 */
	public void setEnteCodice(String enteCodice) {
		this.enteCodice = enteCodice;
	}

	/**
	 * Gets the cpass T programmas.
	 *
	 * @return the cpass T programmas
	 */
	public List<CpassTPbaProgramma> getCpassTPbaProgrammas() {
		return this.cpassTPbaProgrammas;
	}

	/**
	 * Sets the cpass T programmas.
	 *
	 * @param cpassTPbaProgrammas the new cpass T programmas
	 */
	public void setCpassTPbaProgrammas(List<CpassTPbaProgramma> cpassTPbaProgrammas) {
		this.cpassTPbaProgrammas = cpassTPbaProgrammas;
	}

	/**
	 * Adds the cpass T programma.
	 *
	 * @param cpassTPbaProgramma the cpass T programma
	 * @return the cpass T programma
	 */
	public CpassTPbaProgramma addCpassTPbaProgramma(CpassTPbaProgramma cpassTPbaProgramma) {
		getCpassTPbaProgrammas().add(cpassTPbaProgramma);
		cpassTPbaProgramma.setCpassTEnte(this);

		return cpassTPbaProgramma;
	}

	/**
	 * Removes the cpass T programma.
	 *
	 * @param cpassTPbaProgramma the cpass T programma
	 * @return the cpass T programma
	 */
	public CpassTPbaProgramma removeCpassTPbaProgramma(CpassTPbaProgramma cpassTPbaProgramma) {
		getCpassTPbaProgrammas().remove(cpassTPbaProgramma);
		cpassTPbaProgramma.setCpassTEnte(null);

		return cpassTPbaProgramma;
	}

	/**
	 * Gets the cpass T settores.
	 *
	 * @return the cpass T settores
	 */
	public List<CpassTSettore> getCpassTSettores() {
		return this.cpassTSettores;
	}

	/**
	 * Sets the cpass T settores.
	 *
	 * @param cpassTSettores the new cpass T settores
	 */
	public void setCpassTSettores(List<CpassTSettore> cpassTSettores) {
		this.cpassTSettores = cpassTSettores;
	}

	/**
	 * Adds the cpass T settore.
	 *
	 * @param cpassTSettore the cpass T settore
	 * @return the cpass T settore
	 */
	public CpassTSettore addCpassTSettore(CpassTSettore cpassTSettore) {
		getCpassTSettores().add(cpassTSettore);
		cpassTSettore.setCpassTEnte(this);

		return cpassTSettore;
	}

	/**
	 * Removes the cpass T settore.
	 *
	 * @param cpassTSettore the cpass T settore
	 * @return the cpass T settore
	 */
	public CpassTSettore removeCpassTSettore(CpassTSettore cpassTSettore) {
		getCpassTSettores().remove(cpassTSettore);
		cpassTSettore.setCpassTEnte(null);

		return cpassTSettore;
	}

	public String getPathLogo() {
		return pathLogo;
	}

	public void setPathLogo(String pathLogo) {
		this.pathLogo = pathLogo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	//NUOVI
	/**
	 * @return the emailpec
	 */
	public String getEmailpec() {
		return emailpec;
	}

	/**
	 * @param emailpec the emailpec to set
	 */
	public void setEmailpec(String emailpec) {
		this.emailpec = emailpec;
	}

	/**
	 * @return the cpassDOggettiSpesas
	 */
	public List<CpassDOggettiSpesa> getCpassDOggettiSpesas() {
		return cpassDOggettiSpesas;
	}

	/**
	 * @param cpassDOggettiSpesas the cpassDOggettiSpesas to set
	 */
	public void setCpassDOggettiSpesas(List<CpassDOggettiSpesa> cpassDOggettiSpesas) {
		this.cpassDOggettiSpesas = cpassDOggettiSpesas;
	}

	/**
	 * @return the cpassDOrdCausaleSospensioneEvasiones
	 */
	public List<CpassDOrdCausaleSospensioneEvasione> getCpassDOrdCausaleSospensioneEvasiones() {
		return cpassDOrdCausaleSospensioneEvasiones;
	}

	/**
	 * @param cpassDOrdCausaleSospensioneEvasiones the cpassDOrdCausaleSospensioneEvasiones to set
	 */
	public void setCpassDOrdCausaleSospensioneEvasiones(
			List<CpassDOrdCausaleSospensioneEvasione> cpassDOrdCausaleSospensioneEvasiones) {
		this.cpassDOrdCausaleSospensioneEvasiones = cpassDOrdCausaleSospensioneEvasiones;
	}

	/**
	 * @return the cpassDOrdTipoProceduras
	 */
	public List<CpassDOrdTipoProcedura> getCpassDOrdTipoProceduras() {
		return cpassDOrdTipoProceduras;
	}

	/**
	 * @param cpassDOrdTipoProceduras the cpassDOrdTipoProceduras to set
	 */
	public void setCpassDOrdTipoProceduras(List<CpassDOrdTipoProcedura> cpassDOrdTipoProceduras) {
		this.cpassDOrdTipoProceduras = cpassDOrdTipoProceduras;
	}

	/**
	 * @return the cpassDPbaTipoProceduras
	 */
	public List<CpassDPbaTipoProcedura> getCpassDPbaTipoProceduras() {
		return cpassDPbaTipoProceduras;
	}

	/**
	 * @param cpassDPbaTipoProceduras the cpassDPbaTipoProceduras to set
	 */
	public void setCpassDPbaTipoProceduras(List<CpassDPbaTipoProcedura> cpassDPbaTipoProceduras) {
		this.cpassDPbaTipoProceduras = cpassDPbaTipoProceduras;
	}

	/**
	 * @return the cpassDProvvedimentoTipos
	 */
	public List<CpassDProvvedimentoTipo> getCpassDProvvedimentoTipos() {
		return cpassDProvvedimentoTipos;
	}

	/**
	 * @param cpassDProvvedimentoTipos the cpassDProvvedimentoTipos to set
	 */
	public void setCpassDProvvedimentoTipos(List<CpassDProvvedimentoTipo> cpassDProvvedimentoTipos) {
		this.cpassDProvvedimentoTipos = cpassDProvvedimentoTipos;
	}

	/**
	 * @return the cpassDTipoSettores
	 */
	public List<CpassDTipoSettore> getCpassDTipoSettores() {
		return cpassDTipoSettores;
	}

	/**
	 * @param cpassDTipoSettores the cpassDTipoSettores to set
	 */
	public void setCpassDTipoSettores(List<CpassDTipoSettore> cpassDTipoSettores) {
		this.cpassDTipoSettores = cpassDTipoSettores;
	}

	/**
	 * @return the cpassRRuoloModulos
	 */
	public List<CpassRRuoloModulo> getCpassRRuoloModulos() {
		return cpassRRuoloModulos;
	}

	/**
	 * @param cpassRRuoloModulos the cpassRRuoloModulos to set
	 */
	public void setCpassRRuoloModulos(List<CpassRRuoloModulo> cpassRRuoloModulos) {
		this.cpassRRuoloModulos = cpassRRuoloModulos;
	}

	/**
	 * @return the cpassTElaboraziones
	 */
	public List<CpassTElaborazione> getCpassTElaboraziones() {
		return cpassTElaboraziones;
	}

	/**
	 * @param cpassTElaboraziones the cpassTElaboraziones to set
	 */
	public void setCpassTElaboraziones(List<CpassTElaborazione> cpassTElaboraziones) {
		this.cpassTElaboraziones = cpassTElaboraziones;
	}

	/**
	 * @return the cpassTFornitores
	 */
	public List<CpassTFornitore> getCpassTFornitores() {
		return cpassTFornitores;
	}

	/**
	 * @param cpassTFornitores the cpassTFornitores to set
	 */
	public void setCpassTFornitores(List<CpassTFornitore> cpassTFornitores) {
		this.cpassTFornitores = cpassTFornitores;
	}

	/**
	 * @return the cpassTGestioneCampos
	 */
	public List<CpassTGestioneCampo> getCpassTGestioneCampos() {
		return cpassTGestioneCampos;
	}

	/**
	 * @param cpassTGestioneCampos the cpassTGestioneCampos to set
	 */
	public void setCpassTGestioneCampos(List<CpassTGestioneCampo> cpassTGestioneCampos) {
		this.cpassTGestioneCampos = cpassTGestioneCampos;
	}

	/**
	 * @return the cpassTImpegnos
	 */
	public List<CpassTImpegno> getCpassTImpegnos() {
		return cpassTImpegnos;
	}

	/**
	 * @param cpassTImpegnos the cpassTImpegnos to set
	 */
	public void setCpassTImpegnos(List<CpassTImpegno> cpassTImpegnos) {
		this.cpassTImpegnos = cpassTImpegnos;
	}

	/**
	 * @return the cpassTMagMagazzinos
	 */
	public List<CpassTMagMagazzino> getCpassTMagMagazzinos() {
		return cpassTMagMagazzinos;
	}

	/**
	 * @param cpassTMagMagazzinos the cpassTMagMagazzinos to set
	 */
	public void setCpassTMagMagazzinos(List<CpassTMagMagazzino> cpassTMagMagazzinos) {
		this.cpassTMagMagazzinos = cpassTMagMagazzinos;
	}

	/**
	 * @return the cpassTOrdRigaRdas
	 */
	public List<CpassTOrdRigaRda> getCpassTOrdRigaRdas() {
		return cpassTOrdRigaRdas;
	}

	/**
	 * @param cpassTOrdRigaRdas the cpassTOrdRigaRdas to set
	 */
	public void setCpassTOrdRigaRdas(List<CpassTOrdRigaRda> cpassTOrdRigaRdas) {
		this.cpassTOrdRigaRdas = cpassTOrdRigaRdas;
	}

	/**
	 * @return the cpassTOrdSeziones
	 */
	public List<CpassTOrdSezione> getCpassTOrdSeziones() {
		return cpassTOrdSeziones;
	}

	/**
	 * @param cpassTOrdSeziones the cpassTOrdSeziones to set
	 */
	public void setCpassTOrdSeziones(List<CpassTOrdSezione> cpassTOrdSeziones) {
		this.cpassTOrdSeziones = cpassTOrdSeziones;
	}

	/**
	 * @return the cpassTOrdTestataRdas
	 */
	public List<CpassTOrdTestataRda> getCpassTOrdTestataRdas() {
		return cpassTOrdTestataRdas;
	}

	/**
	 * @param cpassTOrdTestataRdas the cpassTOrdTestataRdas to set
	 */
	public void setCpassTOrdTestataRdas(List<CpassTOrdTestataRda> cpassTOrdTestataRdas) {
		this.cpassTOrdTestataRdas = cpassTOrdTestataRdas;
	}

	/**
	 * @return the cpassTParametros
	 */
	public List<CpassTParametro> getCpassTParametros() {
		return cpassTParametros;
	}

	/**
	 * @param cpassTParametros the cpassTParametros to set
	 */
	public void setCpassTParametros(List<CpassTParametro> cpassTParametros) {
		this.cpassTParametros = cpassTParametros;
	}

	/**
	 * @return the cpassTProvvedimentos
	 */
	public List<CpassTProvvedimento> getCpassTProvvedimentos() {
		return cpassTProvvedimentos;
	}

	/**
	 * @param cpassTProvvedimentos the cpassTProvvedimentos to set
	 */
	public void setCpassTProvvedimentos(List<CpassTProvvedimento> cpassTProvvedimentos) {
		this.cpassTProvvedimentos = cpassTProvvedimentos;
	}

	/**
	 * @return the cpassTRegoleSmistamentoRms
	 */
	public List<CpassTRegoleSmistamentoRms> getCpassTRegoleSmistamentoRms() {
		return cpassTRegoleSmistamentoRms;
	}

	/**
	 * @param cpassTRegoleSmistamentoRms the cpassTRegoleSmistamentoRms to set
	 */
	public void setCpassTRegoleSmistamentoRms(List<CpassTRegoleSmistamentoRms> cpassTRegoleSmistamentoRms) {
		this.cpassTRegoleSmistamentoRms = cpassTRegoleSmistamentoRms;
	}

	/**
	 * @return the cpassTRmsRigaRms
	 */
	public List<CpassTRmsRigaRms> getCpassTRmsRigaRms() {
		return cpassTRmsRigaRms;
	}

	/**
	 * @param cpassTRmsRigaRms the cpassTRmsRigaRms to set
	 */
	public void setCpassTRmsRigaRms(List<CpassTRmsRigaRms> cpassTRmsRigaRms) {
		this.cpassTRmsRigaRms = cpassTRmsRigaRms;
	}

	/**
	 * @return the cpassTRmsTestataRms
	 */
	public List<CpassTRmsTestataRms> getCpassTRmsTestataRms() {
		return cpassTRmsTestataRms;
	}

	/**
	 * @param cpassTRmsTestataRms the cpassTRmsTestataRms to set
	 */
	public void setCpassTRmsTestataRms(List<CpassTRmsTestataRms> cpassTRmsTestataRms) {
		this.cpassTRmsTestataRms = cpassTRmsTestataRms;
	}

	/**
	 * @return the cpassTSettoreStoricos
	 */
	public List<CpassTSettoreStorico> getCpassTSettoreStoricos() {
		return cpassTSettoreStoricos;
	}

	/**
	 * @param cpassTSettoreStoricos the cpassTSettoreStoricos to set
	 */
	public void setCpassTSettoreStoricos(List<CpassTSettoreStorico> cpassTSettoreStoricos) {
		this.cpassTSettoreStoricos = cpassTSettoreStoricos;
	}

	/**
	 * @return the cpassTSubimpegnos
	 */
	public List<CpassTSubimpegno> getCpassTSubimpegnos() {
		return cpassTSubimpegnos;
	}

	/**
	 * @param cpassTSubimpegnos the cpassTSubimpegnos to set
	 */
	public void setCpassTSubimpegnos(List<CpassTSubimpegno> cpassTSubimpegnos) {
		this.cpassTSubimpegnos = cpassTSubimpegnos;
	}

	/**
	 * @return the cpassTUfficios
	 */
	public List<CpassTUfficio> getCpassTUfficios() {
		return cpassTUfficios;
	}

	/**
	 * @param cpassTUfficios the cpassTUfficios to set
	 */
	public void setCpassTUfficios(List<CpassTUfficio> cpassTUfficios) {
		this.cpassTUfficios = cpassTUfficios;
	}

	@Override
	public UUID getId() {
		return enteId;
	}

	@Override
	public void setId(UUID id) {
		enteId = id;
	}

	@Override
	public void initId() {
		this.enteId = UuidUtils.generateUUIDv5FromNamespaceAndString(NAMESPACE, enteCodiceFiscale);
	}
}
