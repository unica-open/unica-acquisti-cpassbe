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
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per modalitaPagamento complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="modalitaPagamento"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{urn:FdeWSAppjRicercaGateway}entitaCodificataBase"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="codiceTipoAccredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="descrizioneTipoAccredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="sede" type="{urn:FdeWSAppjRicercaGateway}sede" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modalitaPagamento", propOrder = {
    "codiceTipoAccredito",
    "descrizioneTipoAccredito",
    "sede"
})
public class ModalitaPagamento
    extends EntitaCodificataBase
{

    protected String codiceTipoAccredito;
    protected String descrizioneTipoAccredito;
    protected Sede sede;

    /**
     * Recupera il valore della proprietà codiceTipoAccredito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipoAccredito() {
        return codiceTipoAccredito;
    }

    /**
     * Imposta il valore della proprietà codiceTipoAccredito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipoAccredito(String value) {
        this.codiceTipoAccredito = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneTipoAccredito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTipoAccredito() {
        return descrizioneTipoAccredito;
    }

    /**
     * Imposta il valore della proprietà descrizioneTipoAccredito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTipoAccredito(String value) {
        this.descrizioneTipoAccredito = value;
    }

    /**
     * Recupera il valore della proprietà sede.
     * 
     * @return
     *     possible object is
     *     {@link Sede }
     *     
     */
    public Sede getSede() {
        return sede;
    }

    /**
     * Imposta il valore della proprietà sede.
     * 
     * @param value
     *     allowed object is
     *     {@link Sede }
     *     
     */
    public void setSede(Sede value) {
        this.sede = value;
    }

}
