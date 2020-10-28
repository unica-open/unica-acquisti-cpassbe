/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - NotiER
 * %%
 * Copyright (C) 2019 - 2020 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package org.etsi.uri._01903.v1_4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.etsi.uri._01903.v1_3.CertificateValuesType;
import org.etsi.uri._01903.v1_3.RevocationValuesType;


/**
 * <p>Classe Java per ValidationDataType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ValidationDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}CertificateValues" minOccurs="0"/&gt;
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}RevocationValues" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="UR" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationDataType", propOrder = {
    "certificateValues",
    "revocationValues"
})
public class ValidationDataType {

    @XmlElement(name = "CertificateValues", namespace = "http://uri.etsi.org/01903/v1.3.2#")
    protected CertificateValuesType certificateValues;
    @XmlElement(name = "RevocationValues", namespace = "http://uri.etsi.org/01903/v1.3.2#")
    protected RevocationValuesType revocationValues;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "UR")
    @XmlSchemaType(name = "anyURI")
    protected String ur;

    /**
     * Recupera il valore della proprietà certificateValues.
     * 
     * @return
     *     possible object is
     *     {@link CertificateValuesType }
     *     
     */
    public CertificateValuesType getCertificateValues() {
        return certificateValues;
    }

    /**
     * Imposta il valore della proprietà certificateValues.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateValuesType }
     *     
     */
    public void setCertificateValues(CertificateValuesType value) {
        this.certificateValues = value;
    }

    /**
     * Recupera il valore della proprietà revocationValues.
     * 
     * @return
     *     possible object is
     *     {@link RevocationValuesType }
     *     
     */
    public RevocationValuesType getRevocationValues() {
        return revocationValues;
    }

    /**
     * Imposta il valore della proprietà revocationValues.
     * 
     * @param value
     *     allowed object is
     *     {@link RevocationValuesType }
     *     
     */
    public void setRevocationValues(RevocationValuesType value) {
        this.revocationValues = value;
    }

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà ur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUR() {
        return ur;
    }

    /**
     * Imposta il valore della proprietà ur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUR(String value) {
        this.ur = value;
    }

}
