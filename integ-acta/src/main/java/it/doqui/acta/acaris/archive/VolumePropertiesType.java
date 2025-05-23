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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per VolumePropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="VolumePropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}AggregazionePropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="intervalloNumericoDa" type="{archive.acaris.acta.doqui.it}IntervalloNumericoDaType"/&amp;gt;
 *         &amp;lt;element name="intervalloNumericoA" type="{archive.acaris.acta.doqui.it}IntervalloNumericoAType"/&amp;gt;
 *         &amp;lt;element name="stato" type="{archive.acaris.acta.doqui.it}enumVolumeStatoType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VolumePropertiesType", propOrder = {
    "intervalloNumericoDa",
    "intervalloNumericoA",
    "stato"
})
@XmlSeeAlso({
    VolumeSerieFascicoliPropertiesType.class,
    VolumeSerieTipologicaDocumentiPropertiesType.class,
    VolumeFascicoliPropertiesType.class,
    VolumeSottofascicoliPropertiesType.class
})
public abstract class VolumePropertiesType
    extends AggregazionePropertiesType
{

    @XmlElement(required = true)
    protected String intervalloNumericoDa;
    @XmlElement(required = true)
    protected String intervalloNumericoA;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumVolumeStatoType stato;

    /**
     * Recupera il valore della proprietà intervalloNumericoDa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntervalloNumericoDa() {
        return intervalloNumericoDa;
    }

    /**
     * Imposta il valore della proprietà intervalloNumericoDa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntervalloNumericoDa(String value) {
        this.intervalloNumericoDa = value;
    }

    /**
     * Recupera il valore della proprietà intervalloNumericoA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntervalloNumericoA() {
        return intervalloNumericoA;
    }

    /**
     * Imposta il valore della proprietà intervalloNumericoA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntervalloNumericoA(String value) {
        this.intervalloNumericoA = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link EnumVolumeStatoType }
     *     
     */
    public EnumVolumeStatoType getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietà stato.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumVolumeStatoType }
     *     
     */
    public void setStato(EnumVolumeStatoType value) {
        this.stato = value;
    }

}
