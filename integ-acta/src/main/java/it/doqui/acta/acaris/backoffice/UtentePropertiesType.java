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

package it.doqui.acta.acaris.backoffice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.doqui.acta.acaris.common.CodiceFiscaleType;
import it.doqui.acta.acaris.common.ObjectIdType;


/**
 * &lt;p&gt;Classe Java per UtentePropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="UtentePropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{backoffice.acaris.acta.doqui.it}BackOfficePropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="parentId" type="{common.acaris.acta.doqui.it}ObjectIdType"/&amp;gt;
 *         &amp;lt;element name="parentIdInChiaro" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="nome" type="{backoffice.acaris.acta.doqui.it}NomeType"/&amp;gt;
 *         &amp;lt;element name="cognome" type="{backoffice.acaris.acta.doqui.it}CognomeType"/&amp;gt;
 *         &amp;lt;element name="codiceFiscale" type="{common.acaris.acta.doqui.it}CodiceFiscaleType"/&amp;gt;
 *         &amp;lt;element name="email" type="{backoffice.acaris.acta.doqui.it}EmailType"/&amp;gt;
 *         &amp;lt;element name="matricola" type="{backoffice.acaris.acta.doqui.it}MatricolaType"/&amp;gt;
 *         &amp;lt;element name="note" type="{backoffice.acaris.acta.doqui.it}NoteType"/&amp;gt;
 *         &amp;lt;element name="codiceIdentitaList" type="{backoffice.acaris.acta.doqui.it}CodiceIdentitaType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataInizioValidita" type="{backoffice.acaris.acta.doqui.it}DataValiditaType"/&amp;gt;
 *         &amp;lt;element name="dataFineValidita" type="{backoffice.acaris.acta.doqui.it}DataValiditaType"/&amp;gt;
 *         &amp;lt;element name="valido" type="{backoffice.acaris.acta.doqui.it}ValidoType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UtentePropertiesType", propOrder = {
    "parentId",
    "parentIdInChiaro",
    "nome",
    "cognome",
    "codiceFiscale",
    "email",
    "matricola",
    "note",
    "codiceIdentitaList",
    "dataInizioValidita",
    "dataFineValidita",
    "valido"
})
public class UtentePropertiesType
    extends BackOfficePropertiesType
{

    @XmlElement(required = true)
    protected ObjectIdType parentId;
    @XmlElement(required = true)
    protected String parentIdInChiaro;
    @XmlElement(required = true)
    protected String nome;
    @XmlElement(required = true)
    protected String cognome;
    @XmlElement(required = true)
    protected CodiceFiscaleType codiceFiscale;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String matricola;
    @XmlElement(required = true)
    protected String note;
    protected List<String> codiceIdentitaList;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioValidita;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineValidita;
    protected boolean valido;

    /**
     * Recupera il valore della proprietà parentId.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdType }
     *     
     */
    public ObjectIdType getParentId() {
        return parentId;
    }

    /**
     * Imposta il valore della proprietà parentId.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdType }
     *     
     */
    public void setParentId(ObjectIdType value) {
        this.parentId = value;
    }

    /**
     * Recupera il valore della proprietà parentIdInChiaro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentIdInChiaro() {
        return parentIdInChiaro;
    }

    /**
     * Imposta il valore della proprietà parentIdInChiaro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentIdInChiaro(String value) {
        this.parentIdInChiaro = value;
    }

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Recupera il valore della proprietà cognome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il valore della proprietà cognome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscale.
     * 
     * @return
     *     possible object is
     *     {@link CodiceFiscaleType }
     *     
     */
    public CodiceFiscaleType getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il valore della proprietà codiceFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiceFiscaleType }
     *     
     */
    public void setCodiceFiscale(CodiceFiscaleType value) {
        this.codiceFiscale = value;
    }

    /**
     * Recupera il valore della proprietà email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta il valore della proprietà email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Recupera il valore della proprietà matricola.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     * Imposta il valore della proprietà matricola.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatricola(String value) {
        this.matricola = value;
    }

    /**
     * Recupera il valore della proprietà note.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta il valore della proprietà note.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the codiceIdentitaList property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the codiceIdentitaList property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getCodiceIdentitaList().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCodiceIdentitaList() {
        if (codiceIdentitaList == null) {
            codiceIdentitaList = new ArrayList<String>();
        }
        return this.codiceIdentitaList;
    }

    /**
     * Recupera il valore della proprietà dataInizioValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Imposta il valore della proprietà dataInizioValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioValidita(XMLGregorianCalendar value) {
        this.dataInizioValidita = value;
    }

    /**
     * Recupera il valore della proprietà dataFineValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della proprietà dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

    /**
     * Recupera il valore della proprietà valido.
     * 
     */
    public boolean isValido() {
        return valido;
    }

    /**
     * Imposta il valore della proprietà valido.
     * 
     */
    public void setValido(boolean value) {
        this.valido = value;
    }

}
