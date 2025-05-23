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

package it.rer.intercenter.notier.services._1_0.notificamdn;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per EsitoTrasmissione_Type complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EsitoTrasmissione_Type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodiceEsito" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}CodiceEsito_Type"/&gt;
 *         &lt;element name="DescrizioneErrore" type="{http://notier.intercenter.rer.it/services/1.0/notificaMDN}DescrizioneErrore_Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EsitoTrasmissione_Type", propOrder = {
    "codiceEsito",
    "descrizioneErrore"
})
public class EsitoTrasmissioneType {

    @XmlElement(name = "CodiceEsito", required = true)
    @XmlSchemaType(name = "string")
    protected CodiceEsitoType codiceEsito;
    @XmlElement(name = "DescrizioneErrore")
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
