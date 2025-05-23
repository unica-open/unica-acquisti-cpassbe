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
 * &lt;p&gt;Classe Java per DocumentoFisicoPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DocumentoFisicoPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}FolderPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="descrizione" type="{archive.acaris.acta.doqui.it}DescrizioneType"/&amp;gt;
 *         &amp;lt;element name="progressivoPerDocumento" type="{archive.acaris.acta.doqui.it}ProgressivoPerDocumentoType"/&amp;gt;
 *         &amp;lt;element name="dataMemorizzazione" type="{archive.acaris.acta.doqui.it}DataMemorizzazioneType"/&amp;gt;
 *         &amp;lt;element name="docMimeTypes" type="{archive.acaris.acta.doqui.it}DocMimeTypesXMLType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoFisicoPropertiesType", propOrder = {
    "descrizione",
    "progressivoPerDocumento",
    "dataMemorizzazione",
    "docMimeTypes"
})
public class DocumentoFisicoPropertiesType
    extends FolderPropertiesType
{

    @XmlElement(required = true)
    protected String descrizione;
    protected int progressivoPerDocumento;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataMemorizzazione;
    @XmlElement(required = true)
    protected String docMimeTypes;

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
     * Recupera il valore della proprietà progressivoPerDocumento.
     * 
     */
    public int getProgressivoPerDocumento() {
        return progressivoPerDocumento;
    }

    /**
     * Imposta il valore della proprietà progressivoPerDocumento.
     * 
     */
    public void setProgressivoPerDocumento(int value) {
        this.progressivoPerDocumento = value;
    }

    /**
     * Recupera il valore della proprietà dataMemorizzazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMemorizzazione() {
        return dataMemorizzazione;
    }

    /**
     * Imposta il valore della proprietà dataMemorizzazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMemorizzazione(XMLGregorianCalendar value) {
        this.dataMemorizzazione = value;
    }

    /**
     * Recupera il valore della proprietà docMimeTypes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocMimeTypes() {
        return docMimeTypes;
    }

    /**
     * Imposta il valore della proprietà docMimeTypes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocMimeTypes(String value) {
        this.docMimeTypes = value;
    }

}
