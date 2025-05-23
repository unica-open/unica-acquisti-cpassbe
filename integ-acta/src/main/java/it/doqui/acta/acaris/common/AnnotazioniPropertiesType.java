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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Classe Java per AnnotazioniPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="AnnotazioniPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{common.acaris.acta.doqui.it}CommonPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="objectTypeId" type="{common.acaris.acta.doqui.it}enumCommonObjectType"/&amp;gt;
 *         &amp;lt;element name="descrizione" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="idUtente" type="{common.acaris.acta.doqui.it}IdUtenteType"/&amp;gt;
 *         &amp;lt;element name="data" type="{common.acaris.acta.doqui.it}date"/&amp;gt;
 *         &amp;lt;element name="annotazioneFormale" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnnotazioniPropertiesType", propOrder = {
    "objectTypeId",
    "descrizione",
    "idUtente",
    "data",
    "annotazioneFormale"
})
public class AnnotazioniPropertiesType
    extends CommonPropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumCommonObjectType objectTypeId;
    @XmlElement(required = true)
    protected String descrizione;
    @XmlElement(required = true)
    protected IdUtenteType idUtente;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar data;
    protected boolean annotazioneFormale;

    /**
     * Recupera il valore della proprietà objectTypeId.
     * 
     * @return
     *     possible object is
     *     {@link EnumCommonObjectType }
     *     
     */
    public EnumCommonObjectType getObjectTypeId() {
        return objectTypeId;
    }

    /**
     * Imposta il valore della proprietà objectTypeId.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumCommonObjectType }
     *     
     */
    public void setObjectTypeId(EnumCommonObjectType value) {
        this.objectTypeId = value;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietà idUtente.
     * 
     * @return
     *     possible object is
     *     {@link IdUtenteType }
     *     
     */
    public IdUtenteType getIdUtente() {
        return idUtente;
    }

    /**
     * Imposta il valore della proprietà idUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link IdUtenteType }
     *     
     */
    public void setIdUtente(IdUtenteType value) {
        this.idUtente = value;
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

    /**
     * Recupera il valore della proprietà annotazioneFormale.
     * 
     */
    public boolean isAnnotazioneFormale() {
        return annotazioneFormale;
    }

    /**
     * Imposta il valore della proprietà annotazioneFormale.
     * 
     */
    public void setAnnotazioneFormale(boolean value) {
        this.annotazioneFormale = value;
    }

}
