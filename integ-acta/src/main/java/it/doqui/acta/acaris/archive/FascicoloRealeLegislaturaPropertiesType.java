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
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per FascicoloRealeLegislaturaPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="FascicoloRealeLegislaturaPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}FascicoloRealePropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="legislatura" type="{archive.acaris.acta.doqui.it}LegislaturaType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FascicoloRealeLegislaturaPropertiesType", propOrder = {
    "legislatura"
})
public class FascicoloRealeLegislaturaPropertiesType
    extends FascicoloRealePropertiesType
{

    @XmlElement(required = true)
    protected String legislatura;

    /**
     * Recupera il valore della proprietà legislatura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegislatura() {
        return legislatura;
    }

    /**
     * Imposta il valore della proprietà legislatura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegislatura(String value) {
        this.legislatura = value;
    }

}
