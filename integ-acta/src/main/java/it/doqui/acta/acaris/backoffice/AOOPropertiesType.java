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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per AOOPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="AOOPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{backoffice.acaris.acta.doqui.it}OrganizationUnitPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="idRaggruppamentoAOO" type="{backoffice.acaris.acta.doqui.it}IdRaggruppamentoAOOType"/&amp;gt;
 *         &amp;lt;element name="idStrutturaList" type="{common.acaris.acta.doqui.it}ObjectIdType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="idUtenteResponsabile" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AOOPropertiesType", propOrder = {
    "idRaggruppamentoAOO",
    "idStrutturaList",
    "idUtenteResponsabile"
})
public class AOOPropertiesType
    extends OrganizationUnitPropertiesType
{

    @XmlElement(required = true)
    protected String idRaggruppamentoAOO;
    protected List<ObjectIdType> idStrutturaList;
    @XmlElement(required = true)
    protected ObjectIdType idUtenteResponsabile;

    /**
     * Recupera il valore della proprietà idRaggruppamentoAOO.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdRaggruppamentoAOO() {
        return idRaggruppamentoAOO;
    }

    /**
     * Imposta il valore della proprietà idRaggruppamentoAOO.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdRaggruppamentoAOO(String value) {
        this.idRaggruppamentoAOO = value;
    }

    /**
     * Gets the value of the idStrutturaList property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the idStrutturaList property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getIdStrutturaList().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectIdType }
     * 
     * 
     */
    public List<ObjectIdType> getIdStrutturaList() {
        if (idStrutturaList == null) {
            idStrutturaList = new ArrayList<ObjectIdType>();
        }
        return this.idStrutturaList;
    }

    /**
     * Recupera il valore della proprietà idUtenteResponsabile.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getIdUtenteResponsabile() {
        return idUtenteResponsabile;
    }

    /**
     * Imposta il valore della proprietà idUtenteResponsabile.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setIdUtenteResponsabile(ObjectIdType value) {
        this.idUtenteResponsabile = value;
    }

}
