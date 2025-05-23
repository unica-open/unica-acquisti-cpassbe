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

package it.doqui.acta.acaris.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per ComplexPropertyType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ComplexPropertyType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{common.acaris.acta.doqui.it}PropertyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="nested" type="{common.acaris.acta.doqui.it}ComplexPropertyType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexPropertyType", propOrder = {
    "nested"
})
public class ComplexPropertyType
    extends PropertyType
{

    @XmlElement(required = true)
    protected ComplexPropertyType nested;

    /**
     * Recupera il valore della proprietà nested.
     * 
     * @return
     *     possible object is
     *     {@link ComplexPropertyType }
     *     
     */
    public ComplexPropertyType getNested() {
        return nested;
    }

    /**
     * Imposta il valore della proprietà nested.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexPropertyType }
     *     
     */
    public void setNested(ComplexPropertyType value) {
        this.nested = value;
    }

}
