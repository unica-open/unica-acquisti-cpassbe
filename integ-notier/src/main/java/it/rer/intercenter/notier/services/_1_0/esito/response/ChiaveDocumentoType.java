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

package it.rer.intercenter.notier.services._1_0.esito.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per ChiaveDocumentoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ChiaveDocumentoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Mittente" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}TokenNVMax50Type"/&gt;
 *         &lt;element name="TipoMittente" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}TipoMittenteType"/&gt;
 *         &lt;element name="Anno" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}IntMax4DgtType"/&gt;
 *         &lt;element name="Numero" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}TokenNVMax70Type"/&gt;
 *         &lt;element name="TipoDocumento" type="{http://notier.intercenter.rer.it/services/1.0/esito/response}TipoDocumentoType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChiaveDocumentoType", propOrder = {
    "mittente",
    "tipoMittente",
    "anno",
    "numero",
    "tipoDocumento"
})
public class ChiaveDocumentoType {

    @XmlElement(name = "Mittente", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String mittente;
    @XmlElement(name = "TipoMittente", required = true)
    @XmlSchemaType(name = "NMTOKEN")
    protected TipoMittenteType tipoMittente;
    @XmlElement(name = "Anno")
    @XmlSchemaType(name = "integer")
    protected int anno;
    @XmlElement(name = "Numero", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String numero;
    @XmlElement(name = "TipoDocumento", required = true)
    @XmlSchemaType(name = "NMTOKEN")
    protected TipoDocumentoType tipoDocumento;

    /**
     * Recupera il valore della proprietà mittente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMittente() {
        return mittente;
    }

    /**
     * Imposta il valore della proprietà mittente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMittente(String value) {
        this.mittente = value;
    }

    /**
     * Recupera il valore della proprietà tipoMittente.
     * 
     * @return
     *     possible object is
     *     {@link TipoMittenteType }
     *     
     */
    public TipoMittenteType getTipoMittente() {
        return tipoMittente;
    }

    /**
     * Imposta il valore della proprietà tipoMittente.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoMittenteType }
     *     
     */
    public void setTipoMittente(TipoMittenteType value) {
        this.tipoMittente = value;
    }

    /**
     * Recupera il valore della proprietà anno.
     * 
     */
    public int getAnno() {
        return anno;
    }

    /**
     * Imposta il valore della proprietà anno.
     * 
     */
    public void setAnno(int value) {
        this.anno = value;
    }

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
     * Recupera il valore della proprietà tipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumentoType }
     *     
     */
    public TipoDocumentoType getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Imposta il valore della proprietà tipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumentoType }
     *     
     */
    public void setTipoDocumento(TipoDocumentoType value) {
        this.tipoDocumento = value;
    }

}
