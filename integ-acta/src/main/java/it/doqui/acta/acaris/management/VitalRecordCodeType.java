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

package it.doqui.acta.acaris.management;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.IdVitalRecordCodeType;


/**
 * &lt;p&gt;Classe Java per VitalRecordCodeType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="VitalRecordCodeType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="idVitalRecordCode" type="{common.acaris.acta.doqui.it}IdVitalRecordCodeType"/&amp;gt;
 *         &amp;lt;element name="descrizione" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="tempoDiVitalita" type="{common.acaris.acta.doqui.it}integer"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VitalRecordCodeType", propOrder = {
    "idVitalRecordCode",
    "descrizione",
    "tempoDiVitalita"
})
public class VitalRecordCodeType {

    @XmlElement(required = true)
    protected IdVitalRecordCodeType idVitalRecordCode;
    @XmlElement(required = true)
    protected String descrizione;
    protected int tempoDiVitalita;

    /**
     * Recupera il valore della proprietà idVitalRecordCode.
     * 
     * @return
     *     possible object is
     *     {@link IdVitalRecordCodeType }
     *     
     */
    public IdVitalRecordCodeType getIdVitalRecordCode() {
        return idVitalRecordCode;
    }

    /**
     * Imposta il valore della proprietà idVitalRecordCode.
     * 
     * @param value
     *     allowed object is
     *     {@link IdVitalRecordCodeType }
     *     
     */
    public void setIdVitalRecordCode(IdVitalRecordCodeType value) {
        this.idVitalRecordCode = value;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietà tempoDiVitalita.
     * 
     */
    public int getTempoDiVitalita() {
        return tempoDiVitalita;
    }

    /**
     * Imposta il valore della proprietà tempoDiVitalita.
     * 
     */
    public void setTempoDiVitalita(int value) {
        this.tempoDiVitalita = value;
    }

}
