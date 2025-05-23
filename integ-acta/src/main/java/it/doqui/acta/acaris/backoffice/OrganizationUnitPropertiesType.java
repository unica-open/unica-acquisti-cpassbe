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

package it.doqui.acta.acaris.backoffice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per OrganizationUnitPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="OrganizationUnitPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{backoffice.acaris.acta.doqui.it}BackOfficePropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="parentId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="parentIdInChiaro" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="codice" type="{backoffice.acaris.acta.doqui.it}CodiceType"/&amp;gt;
 *         &amp;lt;element name="denominazione" type="{backoffice.acaris.acta.doqui.it}DenominazioneType"/&amp;gt;
 *         &amp;lt;element name="indirizzo" type="{backoffice.acaris.acta.doqui.it}IndirizzoType"/&amp;gt;
 *         &amp;lt;element name="citta" type="{backoffice.acaris.acta.doqui.it}CittaType"/&amp;gt;
 *         &amp;lt;element name="email" type="{backoffice.acaris.acta.doqui.it}EmailType"/&amp;gt;
 *         &amp;lt;element name="emailPEC" type="{backoffice.acaris.acta.doqui.it}EmailType"/&amp;gt;
 *         &amp;lt;element name="sitoWeb" type="{backoffice.acaris.acta.doqui.it}SitoWebType"/&amp;gt;
 *         &amp;lt;element name="telefono" type="{backoffice.acaris.acta.doqui.it}TelefonoType"/&amp;gt;
 *         &amp;lt;element name="fax" type="{backoffice.acaris.acta.doqui.it}FaxType"/&amp;gt;
 *         &amp;lt;element name="dataInizioValidita" type="{backoffice.acaris.acta.doqui.it}DataValiditaType"/&amp;gt;
 *         &amp;lt;element name="dataFineValidita" type="{backoffice.acaris.acta.doqui.it}DataValiditaType"/&amp;gt;
 *         &amp;lt;element name="valido" type="{backoffice.acaris.acta.doqui.it}ValidoType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganizationUnitPropertiesType", propOrder = {
    "parentId",
    "parentIdInChiaro",
    "codice",
    "denominazione",
    "indirizzo",
    "citta",
    "email",
    "emailPEC",
    "sitoWeb",
    "telefono",
    "fax",
    "dataInizioValidita",
    "dataFineValidita",
    "valido"
})
@XmlSeeAlso({
    AOOPropertiesType.class,
    EntePropertiesType.class,
    StrutturaPropertiesType.class,
    NodoPropertiesType.class
})
public abstract class OrganizationUnitPropertiesType
    extends BackOfficePropertiesType
{

    @XmlElement(required = true)
    protected ObjectIdType parentId;
    @XmlElement(required = true)
    protected String parentIdInChiaro;
    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String denominazione;
    @XmlElement(required = true)
    protected String indirizzo;
    @XmlElement(required = true)
    protected String citta;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String emailPEC;
    @XmlElement(required = true)
    protected String sitoWeb;
    @XmlElement(required = true)
    protected String telefono;
    @XmlElement(required = true)
    protected String fax;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioValidita;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineValidita;
    protected boolean valido;

    /**
     * Recupera il valore della proprietà parentId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getParentId() {
        return parentId;
    }

    /**
     * Imposta il valore della proprietà parentId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setParentId(ObjectIdType value) {
        this.parentId = value;
    }

    /**
     * Recupera il valore della proprietà parentIdInChiaro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentIdInChiaro() {
        return parentIdInChiaro;
    }

    /**
     * Imposta il valore della proprietà parentIdInChiaro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentIdInChiaro(String value) {
        this.parentIdInChiaro = value;
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
     * Recupera il valore della proprietà indirizzo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta il valore della proprietà indirizzo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzo(String value) {
        this.indirizzo = value;
    }

    /**
     * Recupera il valore della proprietà citta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitta() {
        return citta;
    }

    /**
     * Imposta il valore della proprietà citta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitta(String value) {
        this.citta = value;
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
     * Recupera il valore della proprietà sitoWeb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSitoWeb() {
        return sitoWeb;
    }

    /**
     * Imposta il valore della proprietà sitoWeb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSitoWeb(String value) {
        this.sitoWeb = value;
    }

    /**
     * Recupera il valore della proprietà telefono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Imposta il valore della proprietà telefono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Recupera il valore della proprietà fax.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Imposta il valore della proprietà fax.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Recupera il valore della proprietà dataInizioValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Imposta il valore della proprietà dataInizioValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioValidita(XMLGregorianCalendar value) {
        this.dataInizioValidita = value;
    }

    /**
     * Recupera il valore della proprietà dataFineValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della proprietà dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

    /**
     * Recupera il valore della proprietà valido.
     * 
     */
    public boolean isValido() {
        return valido;
    }

    /**
     * Imposta il valore della proprietà valido.
     * 
     */
    public void setValido(boolean value) {
        this.valido = value;
    }

}
