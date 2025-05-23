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
 * &lt;p&gt;Classe Java per classificatoreGenerico complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="classificatoreGenerico"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{urn:FdeWSAppjRicercaGateway}entitaCodificataBase"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="tipoCodifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "classificatoreGenerico", propOrder = {
    "tipoCodifica"
})
public class ClassificatoreGenerico
    extends EntitaCodificataBase
{

    protected String tipoCodifica;

    /**
     * Recupera il valore della proprietà tipoCodifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCodifica() {
        return tipoCodifica;
    }

    /**
     * Imposta il valore della proprietà tipoCodifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCodifica(String value) {
        this.tipoCodifica = value;
    }

}
