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


/**
 * &lt;p&gt;Classe Java per SerieFascicoliPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SerieFascicoliPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}SeriePropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="obbligoFascStand" type="{archive.acaris.acta.doqui.it}ObbligoFascStandType"/&amp;gt;
 *         &amp;lt;element name="tipologiaNumerazione" type="{archive.acaris.acta.doqui.it}enumTipologiaNumerazioneType"/&amp;gt;
 *         &amp;lt;element name="idFascicoloStandard" type="{archive.acaris.acta.doqui.it}IdFascicoloStandardType"/&amp;gt;
 *         &amp;lt;element name="stato" type="{archive.acaris.acta.doqui.it}enumSerieFascicoliStatoType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SerieFascicoliPropertiesType", propOrder = {
    "obbligoFascStand",
    "tipologiaNumerazione",
    "idFascicoloStandard",
    "stato"
})
public class SerieFascicoliPropertiesType
    extends SeriePropertiesType
{

    protected boolean obbligoFascStand;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipologiaNumerazioneType tipologiaNumerazione;
    @XmlElement(required = true)
    protected IdFascicoloStandardType idFascicoloStandard;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumSerieFascicoliStatoType stato;

    /**
     * Recupera il valore della proprietà obbligoFascStand.
     * 
     */
    public boolean isObbligoFascStand() {
        return obbligoFascStand;
    }

    /**
     * Imposta il valore della proprietà obbligoFascStand.
     * 
     */
    public void setObbligoFascStand(boolean value) {
        this.obbligoFascStand = value;
    }

    /**
     * Recupera il valore della proprietà tipologiaNumerazione.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipologiaNumerazioneType }
     *     
     */
    public EnumTipologiaNumerazioneType getTipologiaNumerazione() {
        return tipologiaNumerazione;
    }

    /**
     * Imposta il valore della proprietà tipologiaNumerazione.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipologiaNumerazioneType }
     *     
     */
    public void setTipologiaNumerazione(EnumTipologiaNumerazioneType value) {
        this.tipologiaNumerazione = value;
    }

    /**
     * Recupera il valore della proprietà idFascicoloStandard.
     * 
     * @return
     *     possible object is
     *     {@link IdFascicoloStandardType }
     *     
     */
    public IdFascicoloStandardType getIdFascicoloStandard() {
        return idFascicoloStandard;
    }

    /**
     * Imposta il valore della proprietà idFascicoloStandard.
     * 
     * @param value
     *     allowed object is
     *     {@link IdFascicoloStandardType }
     *     
     */
    public void setIdFascicoloStandard(IdFascicoloStandardType value) {
        this.idFascicoloStandard = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link EnumSerieFascicoliStatoType }
     *     
     */
    public EnumSerieFascicoliStatoType getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietà stato.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumSerieFascicoliStatoType }
     *     
     */
    public void setStato(EnumSerieFascicoliStatoType value) {
        this.stato = value;
    }

}
