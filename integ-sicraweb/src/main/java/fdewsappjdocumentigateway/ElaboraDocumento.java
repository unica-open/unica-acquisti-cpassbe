/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - INTEGRATION submodule - SICRAWEB
 * %%
 * Copyright (C) 2019 - 2023 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2023 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */

package fdewsappjdocumentigateway;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per elaboraDocumento complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="elaboraDocumento"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{urn:FdeWSAppjDocumentiGateway}baseRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="codiceTipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="contenutoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="messaggio" type="{urn:FdeWSAppjDocumentiGateway}messaggio" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elaboraDocumento", propOrder = {
    "codiceTipoDocumento",
    "contenutoDocumento",
    "messaggio"
})
public class ElaboraDocumento
    extends BaseRequest
{

    protected String codiceTipoDocumento;
    protected String contenutoDocumento;
    @XmlElementRef(name = "messaggio", type = JAXBElement.class, required = false)
    protected JAXBElement<Messaggio> messaggio;

    /**
     * Recupera il valore della proprietà codiceTipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipoDocumento() {
        return codiceTipoDocumento;
    }

    /**
     * Imposta il valore della proprietà codiceTipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipoDocumento(String value) {
        this.codiceTipoDocumento = value;
    }

    /**
     * Recupera il valore della proprietà contenutoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContenutoDocumento() {
        return contenutoDocumento;
    }

    /**
     * Imposta il valore della proprietà contenutoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContenutoDocumento(String value) {
        this.contenutoDocumento = value;
    }

    /**
     * Recupera il valore della proprietà messaggio.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Messaggio }{@code >}
     *     
     */
    public JAXBElement<Messaggio> getMessaggio() {
        return messaggio;
    }

    /**
     * Imposta il valore della proprietà messaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Messaggio }{@code >}
     *     
     */
    public void setMessaggio(JAXBElement<Messaggio> value) {
        this.messaggio = value;
    }

}
