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

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per importoCapitoloUscitaGestione complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="importoCapitoloUscitaGestione"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{urn:FdeWSAppjRicercaGateway}importo"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="disponibilitaImpegnare" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="stanziamento" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="stanziamentoCassa" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="stanziamentoResiduo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "importoCapitoloUscitaGestione", propOrder = {
    "disponibilitaImpegnare",
    "stanziamento",
    "stanziamentoCassa",
    "stanziamentoResiduo"
})
public class ImportoCapitoloUscitaGestione
    extends Importo
{

    protected BigDecimal disponibilitaImpegnare;
    protected BigDecimal stanziamento;
    protected BigDecimal stanziamentoCassa;
    protected BigDecimal stanziamentoResiduo;

    /**
     * Recupera il valore della proprietà disponibilitaImpegnare.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDisponibilitaImpegnare() {
        return disponibilitaImpegnare;
    }

    /**
     * Imposta il valore della proprietà disponibilitaImpegnare.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDisponibilitaImpegnare(BigDecimal value) {
        this.disponibilitaImpegnare = value;
    }

    /**
     * Recupera il valore della proprietà stanziamento.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStanziamento() {
        return stanziamento;
    }

    /**
     * Imposta il valore della proprietà stanziamento.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStanziamento(BigDecimal value) {
        this.stanziamento = value;
    }

    /**
     * Recupera il valore della proprietà stanziamentoCassa.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStanziamentoCassa() {
        return stanziamentoCassa;
    }

    /**
     * Imposta il valore della proprietà stanziamentoCassa.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStanziamentoCassa(BigDecimal value) {
        this.stanziamentoCassa = value;
    }

    /**
     * Recupera il valore della proprietà stanziamentoResiduo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStanziamentoResiduo() {
        return stanziamentoResiduo;
    }

    /**
     * Imposta il valore della proprietà stanziamentoResiduo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStanziamentoResiduo(BigDecimal value) {
        this.stanziamentoResiduo = value;
    }

}
