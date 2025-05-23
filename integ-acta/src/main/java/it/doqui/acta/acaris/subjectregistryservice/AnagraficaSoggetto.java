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


/**
 * &lt;p&gt;Classe Java per AnagraficaSoggetto complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="AnagraficaSoggetto"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="codiceFiscale" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="partitaIva" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="denominazione" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="tipoAnagrafica" type="{subjectregistryservice.acaris.acta.doqui.it}enumPFPG"/&amp;gt;
 *         &amp;lt;element name="cognome" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="nome" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="sesso" type="{subjectregistryservice.acaris.acta.doqui.it}enumSesso" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="statoAnagrafico" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiceFonte" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="descrizioneFonte" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataNascita" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="luogoNascita" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="provvisoria" type="{common.acaris.acta.doqui.it}boolean" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="indirizzi" type="{subjectregistryservice.acaris.acta.doqui.it}IndirizzoFonteEsterna" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnagraficaSoggetto", propOrder = {
    "codiceFiscale",
    "partitaIva",
    "denominazione",
    "tipoAnagrafica",
    "cognome",
    "nome",
    "sesso",
    "statoAnagrafico",
    "codiceFonte",
    "descrizioneFonte",
    "dataNascita",
    "luogoNascita",
    "provvisoria",
    "indirizzi"
})
public class AnagraficaSoggetto {

    protected String codiceFiscale;
    protected String partitaIva;
    protected String denominazione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumPFPG tipoAnagrafica;
    protected String cognome;
    protected String nome;
    @XmlSchemaType(name = "string")
    protected EnumSesso sesso;
    protected String statoAnagrafico;
    protected String codiceFonte;
    protected String descrizioneFonte;
    protected String dataNascita;
    protected String luogoNascita;
    protected Boolean provvisoria;
    protected List<IndirizzoFonteEsterna> indirizzi;

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
     * Recupera il valore della proprietà tipoAnagrafica.
     * 
     * @return
     *     possible object is
     *     {@link EnumPFPG }
     *     
     */
    public EnumPFPG getTipoAnagrafica() {
        return tipoAnagrafica;
    }

    /**
     * Imposta il valore della proprietà tipoAnagrafica.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumPFPG }
     *     
     */
    public void setTipoAnagrafica(EnumPFPG value) {
        this.tipoAnagrafica = value;
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
     * Recupera il valore della proprietà sesso.
     * 
     * @return
     *     possible object is
     *     {@link EnumSesso }
     *     
     */
    public EnumSesso getSesso() {
        return sesso;
    }

    /**
     * Imposta il valore della proprietà sesso.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumSesso }
     *     
     */
    public void setSesso(EnumSesso value) {
        this.sesso = value;
    }

    /**
     * Recupera il valore della proprietà statoAnagrafico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoAnagrafico() {
        return statoAnagrafico;
    }

    /**
     * Imposta il valore della proprietà statoAnagrafico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoAnagrafico(String value) {
        this.statoAnagrafico = value;
    }

    /**
     * Recupera il valore della proprietà codiceFonte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFonte() {
        return codiceFonte;
    }

    /**
     * Imposta il valore della proprietà codiceFonte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFonte(String value) {
        this.codiceFonte = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneFonte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneFonte() {
        return descrizioneFonte;
    }

    /**
     * Imposta il valore della proprietà descrizioneFonte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneFonte(String value) {
        this.descrizioneFonte = value;
    }

    /**
     * Recupera il valore della proprietà dataNascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta il valore della proprietà dataNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataNascita(String value) {
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
     * Recupera il valore della proprietà provvisoria.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProvvisoria() {
        return provvisoria;
    }

    /**
     * Imposta il valore della proprietà provvisoria.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProvvisoria(Boolean value) {
        this.provvisoria = value;
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
     * {@link IndirizzoFonteEsterna }
     * 
     * 
     */
    public List<IndirizzoFonteEsterna> getIndirizzi() {
        if (indirizzi == null) {
            indirizzi = new ArrayList<IndirizzoFonteEsterna>();
        }
        return this.indirizzi;
    }

}
