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
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per DatiCertificatoType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DatiCertificatoType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;choice&amp;gt;
 *         &amp;lt;element name="DatiCertificatoMarcaTemporale" type="{archive.acaris.acta.doqui.it}DatiCertificatoMarcaTemporaleXMLType"/&amp;gt;
 *         &amp;lt;element name="DatiCertificatoFirmaDigitale" type="{archive.acaris.acta.doqui.it}DatiCertificatoFirmaDigitaleXMLType"/&amp;gt;
 *       &amp;lt;/choice&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiCertificatoType", propOrder = {
    "datiCertificatoMarcaTemporale",
    "datiCertificatoFirmaDigitale"
})
public class DatiCertificatoType {

    @XmlElement(name = "DatiCertificatoMarcaTemporale")
    protected String datiCertificatoMarcaTemporale;
    @XmlElement(name = "DatiCertificatoFirmaDigitale")
    protected String datiCertificatoFirmaDigitale;

    /**
     * Recupera il valore della proprietà datiCertificatoMarcaTemporale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatiCertificatoMarcaTemporale() {
        return datiCertificatoMarcaTemporale;
    }

    /**
     * Imposta il valore della proprietà datiCertificatoMarcaTemporale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatiCertificatoMarcaTemporale(String value) {
        this.datiCertificatoMarcaTemporale = value;
    }

    /**
     * Recupera il valore della proprietà datiCertificatoFirmaDigitale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatiCertificatoFirmaDigitale() {
        return datiCertificatoFirmaDigitale;
    }

    /**
     * Imposta il valore della proprietà datiCertificatoFirmaDigitale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatiCertificatoFirmaDigitale(String value) {
        this.datiCertificatoFirmaDigitale = value;
    }

}
