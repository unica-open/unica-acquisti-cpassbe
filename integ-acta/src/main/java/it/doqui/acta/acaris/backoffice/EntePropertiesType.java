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

package it.doqui.acta.acaris.backoffice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per EntePropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="EntePropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{backoffice.acaris.acta.doqui.it}OrganizationUnitPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="abilitato" type="{backoffice.acaris.acta.doqui.it}AbilitatoType"/&amp;gt;
 *         &amp;lt;element name="tipologiaEnte" type="{backoffice.acaris.acta.doqui.it}enumTipologiaEnteType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntePropertiesType", propOrder = {
    "abilitato",
    "tipologiaEnte"
})
public class EntePropertiesType
    extends OrganizationUnitPropertiesType
{

    protected boolean abilitato;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTipologiaEnteType tipologiaEnte;

    /**
     * Recupera il valore della proprietà abilitato.
     * 
     */
    public boolean isAbilitato() {
        return abilitato;
    }

    /**
     * Imposta il valore della proprietà abilitato.
     * 
     */
    public void setAbilitato(boolean value) {
        this.abilitato = value;
    }

    /**
     * Recupera il valore della proprietà tipologiaEnte.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipologiaEnteType }
     *     
     */
    public EnumTipologiaEnteType getTipologiaEnte() {
        return tipologiaEnte;
    }

    /**
     * Imposta il valore della proprietà tipologiaEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipologiaEnteType }
     *     
     */
    public void setTipologiaEnte(EnumTipologiaEnteType value) {
        this.tipologiaEnte = value;
    }

}
