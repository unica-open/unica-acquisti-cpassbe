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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Classe Java per SoggettoDefinitivo complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SoggettoDefinitivo"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{subjectregistryservice.acaris.acta.doqui.it}SoggettoRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="dataFineValidita" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="preferito" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="codiceFiscale" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="partitaIva" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="email" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="matricola" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="dataNascita" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="luogoNascita" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="note" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="infoComuniCreazioneSoggetto" type="{subjectregistryservice.acaris.acta.doqui.it}InfoComuniCreazioneSoggetto"/&amp;gt;
 *         &amp;lt;element name="categorieAnagrafiche" type="{subjectregistryservice.acaris.acta.doqui.it}CategoriaAnagraficaPropertiesType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeri" type="{subjectregistryservice.acaris.acta.doqui.it}Numero" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="indirizzi" type="{subjectregistryservice.acaris.acta.doqui.it}InfoCreazioneIndirizzo" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoDefinitivo", propOrder = {
    "dataFineValidita",
    "preferito",
    "codiceFiscale",
    "partitaIva",
    "email",
    "matricola",
    "dataNascita",
    "luogoNascita",
    "note",
    "infoComuniCreazioneSoggetto",
    "categorieAnagrafiche",
    "numeri",
    "indirizzi"
})
public class SoggettoDefinitivo
    extends SoggettoRequest
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineValidita;
    protected boolean preferito;
    @XmlElement(required = true)
    protected String codiceFiscale;
    @XmlElement(required = true)
    protected String partitaIva;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String matricola;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataNascita;
    @XmlElement(required = true)
    protected String luogoNascita;
    @XmlElement(required = true)
    protected String note;
    @XmlElement(required = true)
    protected InfoComuniCreazioneSoggetto infoComuniCreazioneSoggetto;
    protected List<CategoriaAnagraficaPropertiesType> categorieAnagrafiche;
    protected List<Numero> numeri;
    protected List<InfoCreazioneIndirizzo> indirizzi;

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
     * Recupera il valore della proprietà preferito.
     * 
     */
    public boolean isPreferito() {
        return preferito;
    }

    /**
     * Imposta il valore della proprietà preferito.
     * 
     */
    public void setPreferito(boolean value) {
        this.preferito = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il valore della proprietà codiceFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Recupera il valore della proprietà partitaIva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitaIva() {
        return partitaIva;
    }

    /**
     * Imposta il valore della proprietà partitaIva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitaIva(String value) {
        this.partitaIva = value;
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
     * Recupera il valore della proprietà matricola.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     * Imposta il valore della proprietà matricola.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatricola(String value) {
        this.matricola = value;
    }

    /**
     * Recupera il valore della proprietà dataNascita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta il valore della proprietà dataNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataNascita(XMLGregorianCalendar value) {
        this.dataNascita = value;
    }

    /**
     * Recupera il valore della proprietà luogoNascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLuogoNascita() {
        return luogoNascita;
    }

    /**
     * Imposta il valore della proprietà luogoNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLuogoNascita(String value) {
        this.luogoNascita = value;
    }

    /**
     * Recupera il valore della proprietà note.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta il valore della proprietà note.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Recupera il valore della proprietà infoComuniCreazioneSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link InfoComuniCreazioneSoggetto }
     *     
     */
    public InfoComuniCreazioneSoggetto getInfoComuniCreazioneSoggetto() {
        return infoComuniCreazioneSoggetto;
    }

    /**
     * Imposta il valore della proprietà infoComuniCreazioneSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoComuniCreazioneSoggetto }
     *     
     */
    public void setInfoComuniCreazioneSoggetto(InfoComuniCreazioneSoggetto value) {
        this.infoComuniCreazioneSoggetto = value;
    }

    /**
     * Gets the value of the categorieAnagrafiche property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the categorieAnagrafiche property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getCategorieAnagrafiche().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link CategoriaAnagraficaPropertiesType }
     * 
     * 
     */
    public List<CategoriaAnagraficaPropertiesType> getCategorieAnagrafiche() {
        if (categorieAnagrafiche == null) {
            categorieAnagrafiche = new ArrayList<CategoriaAnagraficaPropertiesType>();
        }
        return this.categorieAnagrafiche;
    }

    /**
     * Gets the value of the numeri property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the numeri property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getNumeri().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Numero }
     * 
     * 
     */
    public List<Numero> getNumeri() {
        if (numeri == null) {
            numeri = new ArrayList<Numero>();
        }
        return this.numeri;
    }

    /**
     * Gets the value of the indirizzi property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the indirizzi property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getIndirizzi().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link InfoCreazioneIndirizzo }
     * 
     * 
     */
    public List<InfoCreazioneIndirizzo> getIndirizzi() {
        if (indirizzi == null) {
            indirizzi = new ArrayList<InfoCreazioneIndirizzo>();
        }
        return this.indirizzi;
    }

}
