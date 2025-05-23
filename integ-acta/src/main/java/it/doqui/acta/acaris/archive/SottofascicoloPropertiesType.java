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
 * &lt;p&gt;Classe Java per SottofascicoloPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SottofascicoloPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}AggregazionePropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="creatoFascStd" type="{archive.acaris.acta.doqui.it}CreatoFascStdType"/&amp;gt;
 *         &amp;lt;element name="stato" type="{archive.acaris.acta.doqui.it}enumSottofascicoloStatoType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SottofascicoloPropertiesType", propOrder = {
    "creatoFascStd",
    "stato"
})
public class SottofascicoloPropertiesType
    extends AggregazionePropertiesType
{

    protected boolean creatoFascStd;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumSottofascicoloStatoType stato;

    /**
     * Recupera il valore della proprietà creatoFascStd.
     * 
     */
    public boolean isCreatoFascStd() {
        return creatoFascStd;
    }

    /**
     * Imposta il valore della proprietà creatoFascStd.
     * 
     */
    public void setCreatoFascStd(boolean value) {
        this.creatoFascStd = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link EnumSottofascicoloStatoType }
     *     
     */
    public EnumSottofascicoloStatoType getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietà stato.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumSottofascicoloStatoType }
     *     
     */
    public void setStato(EnumSottofascicoloStatoType value) {
        this.stato = value;
    }

}
