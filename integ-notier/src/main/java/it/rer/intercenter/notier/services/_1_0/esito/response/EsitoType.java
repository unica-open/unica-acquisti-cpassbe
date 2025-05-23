/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2021 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2021 | CSI Piemonte
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


/**
 * <p>Classe Java per EsitoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EsitoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodiceEsito" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}CodiceEsitoType"/&gt;
 *         &lt;element name="CodiceErrore" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}CodiceErrore" minOccurs="0"/&gt;
 *         &lt;element name="DescrizioneErrore" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}TokenNVMax4096Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EsitoType", propOrder = {
    "codiceEsito",
    "codiceErrore",
    "descrizioneErrore"
})
public class EsitoType {

    @XmlElement(name = "CodiceEsito", required = true)
    @XmlSchemaType(name = "NMTOKEN")
    protected CodiceEsitoType codiceEsito;
    @XmlElement(name = "CodiceErrore")
    @XmlSchemaType(name = "NMTOKEN")
    protected CodiceErrore codiceErrore;
    @XmlElement(name = "DescrizioneErrore")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String descrizioneErrore;

    /**
     * Recupera il valore della proprietà codiceEsito.
     * 
     * @return
     *     possible object is
     *     {@link CodiceEsitoType }
     *     
     */
    public CodiceEsitoType getCodiceEsito() {
        return codiceEsito;
    }

    /**
     * Imposta il valore della proprietà codiceEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiceEsitoType }
     *     
     */
    public void setCodiceEsito(CodiceEsitoType value) {
        this.codiceEsito = value;
    }

    /**
     * Recupera il valore della proprietà codiceErrore.
     * 
     * @return
     *     possible object is
     *     {@link CodiceErrore }
     *     
     */
    public CodiceErrore getCodiceErrore() {
        return codiceErrore;
    }

    /**
     * Imposta il valore della proprietà codiceErrore.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiceErrore }
     *     
     */
    public void setCodiceErrore(CodiceErrore value) {
        this.codiceErrore = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneErrore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneErrore() {
        return descrizioneErrore;
    }

    /**
     * Imposta il valore della proprietà descrizioneErrore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneErrore(String value) {
        this.descrizioneErrore = value;
    }

}
