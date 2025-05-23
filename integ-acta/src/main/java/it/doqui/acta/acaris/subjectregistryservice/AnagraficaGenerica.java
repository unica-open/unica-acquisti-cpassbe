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
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per AnagraficaGenerica complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="AnagraficaGenerica"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="tipoAnagrafica" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="codiceFonte" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="descrizioneFonte" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="chiaveEsterna" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="objectId" type="{common.acaris.acta.doqui.it}ObjectIdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dbKey" type="{common.acaris.acta.doqui.it}IDDBType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiceFiscale" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="nome" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="cognome" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="partitaIva" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="denominazione" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="idStatoSoggetto" type="{common.acaris.acta.doqui.it}IDDBType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="descStatoSoggetto" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnagraficaGenerica", propOrder = {
    "tipoAnagrafica",
    "codiceFonte",
    "descrizioneFonte",
    "chiaveEsterna",
    "objectId",
    "dbKey",
    "codiceFiscale",
    "nome",
    "cognome",
    "partitaIva",
    "denominazione",
    "idStatoSoggetto",
    "descStatoSoggetto"
})
public class AnagraficaGenerica {

    @XmlElement(required = true)
    protected String tipoAnagrafica;
    @XmlElement(required = true)
    protected String codiceFonte;
    @XmlElement(required = true)
    protected String descrizioneFonte;
    protected String chiaveEsterna;
    protected ObjectIdType objectId;
    protected Long dbKey;
    protected String codiceFiscale;
    protected String nome;
    protected String cognome;
    protected String partitaIva;
    protected String denominazione;
    protected Long idStatoSoggetto;
    protected String descStatoSoggetto;

    /**
     * Recupera il valore della proprietà tipoAnagrafica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAnagrafica() {
        return tipoAnagrafica;
    }

    /**
     * Imposta il valore della proprietà tipoAnagrafica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAnagrafica(String value) {
        this.tipoAnagrafica = value;
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
     * Recupera il valore della proprietà objectId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getObjectId() {
        return objectId;
    }

    /**
     * Imposta il valore della proprietà objectId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setObjectId(ObjectIdType value) {
        this.objectId = value;
    }

    /**
     * Recupera il valore della proprietà dbKey.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDbKey() {
        return dbKey;
    }

    /**
     * Imposta il valore della proprietà dbKey.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDbKey(Long value) {
        this.dbKey = value;
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
     * Recupera il valore della proprietà idStatoSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdStatoSoggetto() {
        return idStatoSoggetto;
    }

    /**
     * Imposta il valore della proprietà idStatoSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdStatoSoggetto(Long value) {
        this.idStatoSoggetto = value;
    }

    /**
     * Recupera il valore della proprietà descStatoSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescStatoSoggetto() {
        return descStatoSoggetto;
    }

    /**
     * Imposta il valore della proprietà descStatoSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescStatoSoggetto(String value) {
        this.descStatoSoggetto = value;
    }

}
