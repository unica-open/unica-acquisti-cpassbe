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

import it.doqui.acta.acaris.common.prt.EnumPFPGUL;


/**
 * &lt;p&gt;Classe Java per SoggettoEsterno complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SoggettoEsterno"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{officialbookservice.acaris.acta.doqui.it}InfoSoggettoAssociato"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="chiaveEsterna" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="idPFPGUL" type="{prt.common.acaris.acta.doqui.it}enumPFPGUL"/&amp;gt;
 *         &amp;lt;element name="codiceTipoSoggetto" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="codiceFonte" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoEsterno", propOrder = {
    "chiaveEsterna",
    "idPFPGUL",
    "codiceTipoSoggetto",
    "codiceFonte"
})
public class SoggettoEsterno
    extends InfoSoggettoAssociato
{

    @XmlElement(required = true)
    protected String chiaveEsterna;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumPFPGUL idPFPGUL;
    @XmlElement(required = true)
    protected String codiceTipoSoggetto;
    @XmlElement(required = true)
    protected String codiceFonte;

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
     * Recupera il valore della proprietà idPFPGUL.
     * 
     * @return
     *     possible object is
     *     {@link EnumPFPGUL }
     *     
     */
    public EnumPFPGUL getIdPFPGUL() {
        return idPFPGUL;
    }

    /**
     * Imposta il valore della proprietà idPFPGUL.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumPFPGUL }
     *     
     */
    public void setIdPFPGUL(EnumPFPGUL value) {
        this.idPFPGUL = value;
    }

    /**
     * Recupera il valore della proprietà codiceTipoSoggetto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipoSoggetto() {
        return codiceTipoSoggetto;
    }

    /**
     * Imposta il valore della proprietà codiceTipoSoggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipoSoggetto(String value) {
        this.codiceTipoSoggetto = value;
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

}
