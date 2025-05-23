/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - ACTA
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.doqui.acta.acaris.subjectregistryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per InfoComuniCreazioneSoggetto complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="InfoComuniCreazioneSoggetto"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="denominazione" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="cognome" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="nome" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="soggettoInterno" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="codice" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="emailPEC" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="uuidSoggettoPadre" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="chiaveEsterna" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="provenienzaInpa" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="forzareSePresentiSoggettiSimili" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="idAOOSeSoggettoInterno" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idTipoSoggettoAppartenenza" type="{common.acaris.acta.doqui.it}IDDBType"/&amp;gt;
 *         &amp;lt;element name="idTipoSoggettoOrigine" type="{common.acaris.acta.doqui.it}IDDBType"/&amp;gt;
 *         &amp;lt;element name="pubblicaAmministrazione" type="{subjectregistryservice.acaris.acta.doqui.it}enumPA"/&amp;gt;
 *         &amp;lt;element name="codiceTipoSoggetto" type="{subjectregistryservice.acaris.acta.doqui.it}enumPFPGUL"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoComuniCreazioneSoggetto", propOrder = {
    "denominazione",
    "cognome",
    "nome",
    "soggettoInterno",
    "codice",
    "emailPEC",
    "uuidSoggettoPadre",
    "chiaveEsterna",
    "provenienzaInpa",
    "forzareSePresentiSoggettiSimili",
    "idAOOSeSoggettoInterno",
    "idTipoSoggettoAppartenenza",
    "idTipoSoggettoOrigine",
    "pubblicaAmministrazione",
    "codiceTipoSoggetto"
})
public class InfoComuniCreazioneSoggetto {

    @XmlElement(required = true)
    protected String denominazione;
    @XmlElement(required = true)
    protected String cognome;
    @XmlElement(required = true)
    protected String nome;
    protected boolean soggettoInterno;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String emailPEC;
    @XmlElement(required = true)
    protected ObjectIdType uuidSoggettoPadre;
    @XmlElement(required = true)
    protected String chiaveEsterna;
    protected boolean provenienzaInpa;
    protected boolean forzareSePresentiSoggettiSimili;
    @XmlElement(required = true)
    protected ObjectIdType idAOOSeSoggettoInterno;
    protected long idTipoSoggettoAppartenenza;
    protected long idTipoSoggettoOrigine;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumPA pubblicaAmministrazione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumPFPGUL codiceTipoSoggetto;

    /**
     * Recupera il valore della proprietà denominazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * Imposta il valore della proprietà denominazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazione(String value) {
        this.denominazione = value;
    }

    /**
     * Recupera il valore della proprietà cognome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il valore della proprietà cognome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Recupera il valore della proprietà soggettoInterno.
     * 
     */
    public boolean isSoggettoInterno() {
        return soggettoInterno;
    }

    /**
     * Imposta il valore della proprietà soggettoInterno.
     * 
     */
    public void setSoggettoInterno(boolean value) {
        this.soggettoInterno = value;
    }

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprietà emailPEC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailPEC() {
        return emailPEC;
    }

    /**
     * Imposta il valore della proprietà emailPEC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailPEC(String value) {
        this.emailPEC = value;
    }

    /**
     * Recupera il valore della proprietà uuidSoggettoPadre.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getUuidSoggettoPadre() {
        return uuidSoggettoPadre;
    }

    /**
     * Imposta il valore della proprietà uuidSoggettoPadre.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setUuidSoggettoPadre(ObjectIdType value) {
        this.uuidSoggettoPadre = value;
    }

    /**
     * Recupera il valore della proprietà chiaveEsterna.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiaveEsterna() {
        return chiaveEsterna;
    }

    /**
     * Imposta il valore della proprietà chiaveEsterna.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiaveEsterna(String value) {
        this.chiaveEsterna = value;
    }

    /**
     * Recupera il valore della proprietà provenienzaInpa.
     * 
     */
    public boolean isProvenienzaInpa() {
        return provenienzaInpa;
    }

    /**
     * Imposta il valore della proprietà provenienzaInpa.
     * 
     */
    public void setProvenienzaInpa(boolean value) {
        this.provenienzaInpa = value;
    }

    /**
     * Recupera il valore della proprietà forzareSePresentiSoggettiSimili.
     * 
     */
    public boolean isForzareSePresentiSoggettiSimili() {
        return forzareSePresentiSoggettiSimili;
    }

    /**
     * Imposta il valore della proprietà forzareSePresentiSoggettiSimili.
     * 
     */
    public void setForzareSePresentiSoggettiSimili(boolean value) {
        this.forzareSePresentiSoggettiSimili = value;
    }

    /**
     * Recupera il valore della proprietà idAOOSeSoggettoInterno.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdAOOSeSoggettoInterno() {
        return idAOOSeSoggettoInterno;
    }

    /**
     * Imposta il valore della proprietà idAOOSeSoggettoInterno.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdAOOSeSoggettoInterno(ObjectIdType value) {
        this.idAOOSeSoggettoInterno = value;
    }

    /**
     * Recupera il valore della proprietà idTipoSoggettoAppartenenza.
     * 
     */
    public long getIdTipoSoggettoAppartenenza() {
        return idTipoSoggettoAppartenenza;
    }

    /**
     * Imposta il valore della proprietà idTipoSoggettoAppartenenza.
     * 
     */
    public void setIdTipoSoggettoAppartenenza(long value) {
        this.idTipoSoggettoAppartenenza = value;
    }

    /**
     * Recupera il valore della proprietà idTipoSoggettoOrigine.
     * 
     */
    public long getIdTipoSoggettoOrigine() {
        return idTipoSoggettoOrigine;
    }

    /**
     * Imposta il valore della proprietà idTipoSoggettoOrigine.
     * 
     */
    public void setIdTipoSoggettoOrigine(long value) {
        this.idTipoSoggettoOrigine = value;
    }

    /**
     * Recupera il valore della proprietà pubblicaAmministrazione.
     * 
     * @return
     *     possible object is
     *     {@link EnumPA }
     *     
     */
    public EnumPA getPubblicaAmministrazione() {
        return pubblicaAmministrazione;
    }

    /**
     * Imposta il valore della proprietà pubblicaAmministrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumPA }
     *     
     */
    public void setPubblicaAmministrazione(EnumPA value) {
        this.pubblicaAmministrazione = value;
    }

    /**
     * Recupera il valore della proprietà codiceTipoSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link EnumPFPGUL }
     *     
     */
    public EnumPFPGUL getCodiceTipoSoggetto() {
        return codiceTipoSoggetto;
    }

    /**
     * Imposta il valore della proprietà codiceTipoSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumPFPGUL }
     *     
     */
    public void setCodiceTipoSoggetto(EnumPFPGUL value) {
        this.codiceTipoSoggetto = value;
    }

}
