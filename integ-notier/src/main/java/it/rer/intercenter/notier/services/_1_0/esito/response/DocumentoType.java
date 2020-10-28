/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package it.rer.intercenter.notier.services._1_0.esito.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per DocumentoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DocumentoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Destinatario" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}TokenNVMax50Type" minOccurs="0"/&gt;
 *         &lt;element name="Urn" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}TokenNVMax1024Type"/&gt;
 *         &lt;element name="DataRicezioneNotier" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="UrnCollegato" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}TokenNVMax1024Type" minOccurs="0"/&gt;
 *         &lt;element name="StatoGiacenza" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}StatoGiacenzaType" minOccurs="0"/&gt;
 *         &lt;element name="StatoConservazione" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}StatoConservazioneType" minOccurs="0"/&gt;
 *         &lt;element name="Chiave" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}ChiaveDocumentoType"/&gt;
 *         &lt;element name="Rappresentazione" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}RappresentazioneType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoType", propOrder = {
    "destinatario",
    "urn",
    "dataRicezioneNotier",
    "urnCollegato",
    "statoGiacenza",
    "statoConservazione",
    "chiave",
    "rappresentazione"
})
public class DocumentoType {

    @XmlElement(name = "Destinatario")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String destinatario;
    @XmlElement(name = "Urn", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String urn;
    @XmlElement(name = "DataRicezioneNotier", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRicezioneNotier;
    @XmlElement(name = "UrnCollegato")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String urnCollegato;
    @XmlElement(name = "StatoGiacenza")
    @XmlSchemaType(name = "NMTOKEN")
    protected StatoGiacenzaType statoGiacenza;
    @XmlElement(name = "StatoConservazione")
    @XmlSchemaType(name = "NMTOKEN")
    protected StatoConservazioneType statoConservazione;
    @XmlElement(name = "Chiave", required = true)
    protected ChiaveDocumentoType chiave;
    @XmlElement(name = "Rappresentazione", required = true)
    protected RappresentazioneType rappresentazione;

    /**
     * Recupera il valore della proprietà destinatario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * Imposta il valore della proprietà destinatario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatario(String value) {
        this.destinatario = value;
    }

    /**
     * Recupera il valore della proprietà urn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrn() {
        return urn;
    }

    /**
     * Imposta il valore della proprietà urn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrn(String value) {
        this.urn = value;
    }

    /**
     * Recupera il valore della proprietà dataRicezioneNotier.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRicezioneNotier() {
        return dataRicezioneNotier;
    }

    /**
     * Imposta il valore della proprietà dataRicezioneNotier.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRicezioneNotier(XMLGregorianCalendar value) {
        this.dataRicezioneNotier = value;
    }

    /**
     * Recupera il valore della proprietà urnCollegato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrnCollegato() {
        return urnCollegato;
    }

    /**
     * Imposta il valore della proprietà urnCollegato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrnCollegato(String value) {
        this.urnCollegato = value;
    }

    /**
     * Recupera il valore della proprietà statoGiacenza.
     * 
     * @return
     *     possible object is
     *     {@link StatoGiacenzaType }
     *     
     */
    public StatoGiacenzaType getStatoGiacenza() {
        return statoGiacenza;
    }

    /**
     * Imposta il valore della proprietà statoGiacenza.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoGiacenzaType }
     *     
     */
    public void setStatoGiacenza(StatoGiacenzaType value) {
        this.statoGiacenza = value;
    }

    /**
     * Recupera il valore della proprietà statoConservazione.
     * 
     * @return
     *     possible object is
     *     {@link StatoConservazioneType }
     *     
     */
    public StatoConservazioneType getStatoConservazione() {
        return statoConservazione;
    }

    /**
     * Imposta il valore della proprietà statoConservazione.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoConservazioneType }
     *     
     */
    public void setStatoConservazione(StatoConservazioneType value) {
        this.statoConservazione = value;
    }

    /**
     * Recupera il valore della proprietà chiave.
     * 
     * @return
     *     possible object is
     *     {@link ChiaveDocumentoType }
     *     
     */
    public ChiaveDocumentoType getChiave() {
        return chiave;
    }

    /**
     * Imposta il valore della proprietà chiave.
     * 
     * @param value
     *     allowed object is
     *     {@link ChiaveDocumentoType }
     *     
     */
    public void setChiave(ChiaveDocumentoType value) {
        this.chiave = value;
    }

    /**
     * Recupera il valore della proprietà rappresentazione.
     * 
     * @return
     *     possible object is
     *     {@link RappresentazioneType }
     *     
     */
    public RappresentazioneType getRappresentazione() {
        return rappresentazione;
    }

    /**
     * Imposta il valore della proprietà rappresentazione.
     * 
     * @param value
     *     allowed object is
     *     {@link RappresentazioneType }
     *     
     */
    public void setRappresentazione(RappresentazioneType value) {
        this.rappresentazione = value;
    }

}
