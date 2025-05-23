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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.CommonPropertiesType;


/**
 * &lt;p&gt;Classe Java per OfficialBookPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="OfficialBookPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{common.acaris.acta.doqui.it}CommonPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="objectTypeId" type="{officialbookservice.acaris.acta.doqui.it}enumOfficialBookObjectType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OfficialBookPropertiesType", propOrder = {
    "objectTypeId"
})
@XmlSeeAlso({
    RegistroProtocolloPropertiesType.class,
    RegistrazionePropertiesType.class,
    CorrispondentePropertiesType.class,
    AnnotazioneOBPropertiesType.class,
    LogProtocolloPropertiesType.class,
    MessaggioPropertiesType.class,
    CorrispondenteMessaggioPropertiesType.class
})
public abstract class OfficialBookPropertiesType
    extends CommonPropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumOfficialBookObjectType objectTypeId;

    /**
     * Recupera il valore della proprietà objectTypeId.
     * 
     * @return
     *     possible object is
     *     {@link EnumOfficialBookObjectType }
     *     
     */
    public EnumOfficialBookObjectType getObjectTypeId() {
        return objectTypeId;
    }

    /**
     * Imposta il valore della proprietà objectTypeId.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumOfficialBookObjectType }
     *     
     */
    public void setObjectTypeId(EnumOfficialBookObjectType value) {
        this.objectTypeId = value;
    }

}
