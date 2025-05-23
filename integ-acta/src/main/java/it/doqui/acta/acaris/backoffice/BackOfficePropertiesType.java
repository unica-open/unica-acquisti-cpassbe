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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.CommonPropertiesType;


/**
 * &lt;p&gt;Classe Java per BackOfficePropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="BackOfficePropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{common.acaris.acta.doqui.it}CommonPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="objectTypeId" type="{backoffice.acaris.acta.doqui.it}enumBackOfficeObjectType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BackOfficePropertiesType", propOrder = {
    "objectTypeId"
})
@XmlSeeAlso({
    UtentePropertiesType.class,
    OrganizationUnitPropertiesType.class
})
public abstract class BackOfficePropertiesType
    extends CommonPropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumBackOfficeObjectType objectTypeId;

    /**
     * Recupera il valore della proprietà objectTypeId.
     * 
     * @return
     *     possible object is
     *     {@link EnumBackOfficeObjectType }
     *     
     */
    public EnumBackOfficeObjectType getObjectTypeId() {
        return objectTypeId;
    }

    /**
     * Imposta il valore della proprietà objectTypeId.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumBackOfficeObjectType }
     *     
     */
    public void setObjectTypeId(EnumBackOfficeObjectType value) {
        this.objectTypeId = value;
    }

}
