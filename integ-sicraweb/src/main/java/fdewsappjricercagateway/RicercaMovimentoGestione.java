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

package fdewsappjricercagateway;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per ricercaMovimentoGestione complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ricercaMovimentoGestione"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{urn:FdeWSAppjRicercaGateway}ricercaPaginataRequest"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="annoProvvedimento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiceStruttura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiceTipoProvvedimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiceTipoStruttura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroProvvedimento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaMovimentoGestione", propOrder = {
    "annoProvvedimento",
    "codiceStruttura",
    "codiceTipoProvvedimento",
    "codiceTipoStruttura",
    "numeroProvvedimento"
})
@XmlSeeAlso({
    RicercaImpegno.class
})
public class RicercaMovimentoGestione
    extends RicercaPaginataRequest
{

    protected Integer annoProvvedimento;
    protected String codiceStruttura;
    protected String codiceTipoProvvedimento;
    protected String codiceTipoStruttura;
    protected Integer numeroProvvedimento;

    /**
     * Recupera il valore della proprietà annoProvvedimento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoProvvedimento() {
        return annoProvvedimento;
    }

    /**
     * Imposta il valore della proprietà annoProvvedimento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoProvvedimento(Integer value) {
        this.annoProvvedimento = value;
    }

    /**
     * Recupera il valore della proprietà codiceStruttura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceStruttura() {
        return codiceStruttura;
    }

    /**
     * Imposta il valore della proprietà codiceStruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceStruttura(String value) {
        this.codiceStruttura = value;
    }

    /**
     * Recupera il valore della proprietà codiceTipoProvvedimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipoProvvedimento() {
        return codiceTipoProvvedimento;
    }

    /**
     * Imposta il valore della proprietà codiceTipoProvvedimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipoProvvedimento(String value) {
        this.codiceTipoProvvedimento = value;
    }

    /**
     * Recupera il valore della proprietà codiceTipoStruttura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipoStruttura() {
        return codiceTipoStruttura;
    }

    /**
     * Imposta il valore della proprietà codiceTipoStruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipoStruttura(String value) {
        this.codiceTipoStruttura = value;
    }

    /**
     * Recupera il valore della proprietà numeroProvvedimento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroProvvedimento() {
        return numeroProvvedimento;
    }

    /**
     * Imposta il valore della proprietà numeroProvvedimento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroProvvedimento(Integer value) {
        this.numeroProvvedimento = value;
    }

}
