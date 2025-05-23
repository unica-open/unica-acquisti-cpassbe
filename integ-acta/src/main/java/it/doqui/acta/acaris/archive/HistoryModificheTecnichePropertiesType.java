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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Classe Java per HistoryModificheTecnichePropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="HistoryModificheTecnichePropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}RelationshipPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="dataTrasformazione" type="{archive.acaris.acta.doqui.it}DataTrasformazioneType"/&amp;gt;
 *         &amp;lt;element name="motivazioneTrasformazione" type="{archive.acaris.acta.doqui.it}enumMotivazioneTrasformazioneType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HistoryModificheTecnichePropertiesType", propOrder = {
    "dataTrasformazione",
    "motivazioneTrasformazione"
})
public class HistoryModificheTecnichePropertiesType
    extends RelationshipPropertiesType
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataTrasformazione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumMotivazioneTrasformazioneType motivazioneTrasformazione;

    /**
     * Recupera il valore della proprietà dataTrasformazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTrasformazione() {
        return dataTrasformazione;
    }

    /**
     * Imposta il valore della proprietà dataTrasformazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTrasformazione(XMLGregorianCalendar value) {
        this.dataTrasformazione = value;
    }

    /**
     * Recupera il valore della proprietà motivazioneTrasformazione.
     * 
     * @return
     *     possible object is
     *     {@link EnumMotivazioneTrasformazioneType }
     *     
     */
    public EnumMotivazioneTrasformazioneType getMotivazioneTrasformazione() {
        return motivazioneTrasformazione;
    }

    /**
     * Imposta il valore della proprietà motivazioneTrasformazione.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumMotivazioneTrasformazioneType }
     *     
     */
    public void setMotivazioneTrasformazione(EnumMotivazioneTrasformazioneType value) {
        this.motivazioneTrasformazione = value;
    }

}
