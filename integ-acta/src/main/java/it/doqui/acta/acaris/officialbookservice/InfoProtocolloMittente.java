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
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Classe Java per InfoProtocolloMittente complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="InfoProtocolloMittente"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="numero" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="anno" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="data" type="{common.acaris.acta.doqui.it}date" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoProtocolloMittente", propOrder = {
    "numero",
    "anno",
    "data"
})
public class InfoProtocolloMittente {

    @XmlElement(required = true)
    protected String numero;
    @XmlElement(required = true)
    protected String anno;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar data;

    /**
     * Recupera il valore della proprietà numero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Imposta il valore della proprietà numero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Recupera il valore della proprietà anno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnno() {
        return anno;
    }

    /**
     * Imposta il valore della proprietà anno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnno(String value) {
        this.anno = value;
    }

    /**
     * Recupera il valore della proprietà data.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietà data.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setData(XMLGregorianCalendar value) {
        this.data = value;
    }

}
