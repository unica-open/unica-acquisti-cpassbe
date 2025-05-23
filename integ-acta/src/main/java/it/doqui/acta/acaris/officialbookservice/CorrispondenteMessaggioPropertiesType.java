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

package it.doqui.acta.acaris.officialbookservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per CorrispondenteMessaggioPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="CorrispondenteMessaggioPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{officialbookservice.acaris.acta.doqui.it}OfficialBookPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="tipoMD" type="{officialbookservice.acaris.acta.doqui.it}enumTipoCorrispondente"/&amp;gt;
 *         &amp;lt;element name="email" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="corrispondente" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="protocollo" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="dataProtocollo" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="denominazioneAmministrazione" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="denominazioneAOO" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="primaRegistrazione" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="competenza" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="richiestaConferma" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="confermaInviata" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="idMessaggio" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="idSoggetto" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrispondenteMessaggioPropertiesType", propOrder = {
    "tipoMD",
    "email",
    "corrispondente",
    "protocollo",
    "dataProtocollo",
    "denominazioneAmministrazione",
    "denominazioneAOO",
    "primaRegistrazione",
    "competenza",
    "richiestaConferma",
    "confermaInviata",
    "idMessaggio",
    "idSoggetto"
})
public class CorrispondenteMessaggioPropertiesType
    extends OfficialBookPropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipoCorrispondente tipoMD;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String corrispondente;
    @XmlElement(required = true)
    protected String protocollo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataProtocollo;
    @XmlElement(required = true)
    protected String denominazioneAmministrazione;
    @XmlElement(required = true)
    protected String denominazioneAOO;
    protected boolean primaRegistrazione;
    protected boolean competenza;
    protected boolean richiestaConferma;
    protected boolean confermaInviata;
    @XmlElement(required = true)
    protected ObjectIdType idMessaggio;
    @XmlElement(required = true)
    protected ObjectIdType idSoggetto;

    /**
     * Recupera il valore della proprietà tipoMD.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoCorrispondente }
     *     
     */
    public EnumTipoCorrispondente getTipoMD() {
        return tipoMD;
    }

    /**
     * Imposta il valore della proprietà tipoMD.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoCorrispondente }
     *     
     */
    public void setTipoMD(EnumTipoCorrispondente value) {
        this.tipoMD = value;
    }

    /**
     * Recupera il valore della proprietà email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta il valore della proprietà email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Recupera il valore della proprietà corrispondente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrispondente() {
        return corrispondente;
    }

    /**
     * Imposta il valore della proprietà corrispondente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrispondente(String value) {
        this.corrispondente = value;
    }

    /**
     * Recupera il valore della proprietà protocollo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocollo() {
        return protocollo;
    }

    /**
     * Imposta il valore della proprietà protocollo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocollo(String value) {
        this.protocollo = value;
    }

    /**
     * Recupera il valore della proprietà dataProtocollo.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataProtocollo() {
        return dataProtocollo;
    }

    /**
     * Imposta il valore della proprietà dataProtocollo.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataProtocollo(XMLGregorianCalendar value) {
        this.dataProtocollo = value;
    }

    /**
     * Recupera il valore della proprietà denominazioneAmministrazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazioneAmministrazione() {
        return denominazioneAmministrazione;
    }

    /**
     * Imposta il valore della proprietà denominazioneAmministrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazioneAmministrazione(String value) {
        this.denominazioneAmministrazione = value;
    }

    /**
     * Recupera il valore della proprietà denominazioneAOO.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazioneAOO() {
        return denominazioneAOO;
    }

    /**
     * Imposta il valore della proprietà denominazioneAOO.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazioneAOO(String value) {
        this.denominazioneAOO = value;
    }

    /**
     * Recupera il valore della proprietà primaRegistrazione.
     * 
     */
    public boolean isPrimaRegistrazione() {
        return primaRegistrazione;
    }

    /**
     * Imposta il valore della proprietà primaRegistrazione.
     * 
     */
    public void setPrimaRegistrazione(boolean value) {
        this.primaRegistrazione = value;
    }

    /**
     * Recupera il valore della proprietà competenza.
     * 
     */
    public boolean isCompetenza() {
        return competenza;
    }

    /**
     * Imposta il valore della proprietà competenza.
     * 
     */
    public void setCompetenza(boolean value) {
        this.competenza = value;
    }

    /**
     * Recupera il valore della proprietà richiestaConferma.
     * 
     */
    public boolean isRichiestaConferma() {
        return richiestaConferma;
    }

    /**
     * Imposta il valore della proprietà richiestaConferma.
     * 
     */
    public void setRichiestaConferma(boolean value) {
        this.richiestaConferma = value;
    }

    /**
     * Recupera il valore della proprietà confermaInviata.
     * 
     */
    public boolean isConfermaInviata() {
        return confermaInviata;
    }

    /**
     * Imposta il valore della proprietà confermaInviata.
     * 
     */
    public void setConfermaInviata(boolean value) {
        this.confermaInviata = value;
    }

    /**
     * Recupera il valore della proprietà idMessaggio.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdMessaggio() {
        return idMessaggio;
    }

    /**
     * Imposta il valore della proprietà idMessaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdMessaggio(ObjectIdType value) {
        this.idMessaggio = value;
    }

    /**
     * Recupera il valore della proprietà idSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdSoggetto() {
        return idSoggetto;
    }

    /**
     * Imposta il valore della proprietà idSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdSoggetto(ObjectIdType value) {
        this.idSoggetto = value;
    }

}
