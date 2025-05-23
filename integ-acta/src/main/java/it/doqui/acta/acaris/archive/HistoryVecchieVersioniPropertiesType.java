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

package it.doqui.acta.acaris.archive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Classe Java per HistoryVecchieVersioniPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="HistoryVecchieVersioniPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}RelationshipPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="numeroVersione" type="{archive.acaris.acta.doqui.it}NumeroVersioneType"/&amp;gt;
 *         &amp;lt;element name="dataVersione" type="{archive.acaris.acta.doqui.it}DataVersioneType"/&amp;gt;
 *         &amp;lt;element name="motivazioneVersione" type="{archive.acaris.acta.doqui.it}MotivazioneVersioneType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HistoryVecchieVersioniPropertiesType", propOrder = {
    "numeroVersione",
    "dataVersione",
    "motivazioneVersione"
})
public class HistoryVecchieVersioniPropertiesType
    extends RelationshipPropertiesType
{

    protected int numeroVersione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataVersione;
    @XmlElement(required = true)
    protected String motivazioneVersione;

    /**
     * Recupera il valore della proprietà numeroVersione.
     * 
     */
    public int getNumeroVersione() {
        return numeroVersione;
    }

    /**
     * Imposta il valore della proprietà numeroVersione.
     * 
     */
    public void setNumeroVersione(int value) {
        this.numeroVersione = value;
    }

    /**
     * Recupera il valore della proprietà dataVersione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataVersione() {
        return dataVersione;
    }

    /**
     * Imposta il valore della proprietà dataVersione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataVersione(XMLGregorianCalendar value) {
        this.dataVersione = value;
    }

    /**
     * Recupera il valore della proprietà motivazioneVersione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivazioneVersione() {
        return motivazioneVersione;
    }

    /**
     * Imposta il valore della proprietà motivazioneVersione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivazioneVersione(String value) {
        this.motivazioneVersione = value;
    }

}
