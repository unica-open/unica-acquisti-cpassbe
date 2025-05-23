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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Classe Java per soggetto complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="soggetto"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{urn:FdeWSAppjRicercaGateway}entitaBase"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiceFiscaleEstero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiciSoggettiCollegatiPrecedenti" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="codiciSoggettiCollegatiSuccessivi" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="cognome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="contatti" type="{urn:FdeWSAppjRicercaGateway}contatti" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="dataNascita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="elencoModalitaPagamento" type="{urn:FdeWSAppjRicercaGateway}modalitaPagamento" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="elencoSedi" type="{urn:FdeWSAppjRicercaGateway}sede" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="indirizzoPrincipale" type="{urn:FdeWSAppjRicercaGateway}recapito" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="naturaGiuridica" type="{urn:FdeWSAppjRicercaGateway}naturaGiuridica" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="partitaIva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ragioneSociale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="sesso" type="{urn:FdeWSAppjRicercaGateway}sesso" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "soggetto", propOrder = {
    "codiceFiscale",
    "codiceFiscaleEstero",
    "codiciSoggettiCollegatiPrecedenti",
    "codiciSoggettiCollegatiSuccessivi",
    "cognome",
    "contatti",
    "dataNascita",
    "elencoModalitaPagamento",
    "elencoSedi",
    "indirizzoPrincipale",
    "naturaGiuridica",
    "nome",
    "partitaIva",
    "ragioneSociale",
    "sesso"
})
public class Soggetto
    extends EntitaBase
{

    protected String codiceFiscale;
    protected String codiceFiscaleEstero;
    @XmlElement(nillable = true)
    protected List<String> codiciSoggettiCollegatiPrecedenti;
    @XmlElement(nillable = true)
    protected List<String> codiciSoggettiCollegatiSuccessivi;
    protected String cognome;
    protected Contatti contatti;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataNascita;
    @XmlElement(nillable = true)
    protected List<ModalitaPagamento> elencoModalitaPagamento;
    @XmlElement(nillable = true)
    protected List<Sede> elencoSedi;
    protected Recapito indirizzoPrincipale;
    protected NaturaGiuridica naturaGiuridica;
    protected String nome;
    protected String partitaIva;
    protected String ragioneSociale;
    @XmlSchemaType(name = "string")
    protected Sesso sesso;

    /**
     * Recupera il valore della proprietà codiceFiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il valore della proprietà codiceFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscaleEstero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleEstero() {
        return codiceFiscaleEstero;
    }

    /**
     * Imposta il valore della proprietà codiceFiscaleEstero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleEstero(String value) {
        this.codiceFiscaleEstero = value;
    }

    /**
     * Gets the value of the codiciSoggettiCollegatiPrecedenti property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the codiciSoggettiCollegatiPrecedenti property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getCodiciSoggettiCollegatiPrecedenti().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCodiciSoggettiCollegatiPrecedenti() {
        if (codiciSoggettiCollegatiPrecedenti == null) {
            codiciSoggettiCollegatiPrecedenti = new ArrayList<String>();
        }
        return this.codiciSoggettiCollegatiPrecedenti;
    }

    /**
     * Gets the value of the codiciSoggettiCollegatiSuccessivi property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the codiciSoggettiCollegatiSuccessivi property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getCodiciSoggettiCollegatiSuccessivi().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCodiciSoggettiCollegatiSuccessivi() {
        if (codiciSoggettiCollegatiSuccessivi == null) {
            codiciSoggettiCollegatiSuccessivi = new ArrayList<String>();
        }
        return this.codiciSoggettiCollegatiSuccessivi;
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
     * Recupera il valore della proprietà contatti.
     * 
     * @return
     *     possible object is
     *     {@link Contatti }
     *     
     */
    public Contatti getContatti() {
        return contatti;
    }

    /**
     * Imposta il valore della proprietà contatti.
     * 
     * @param value
     *     allowed object is
     *     {@link Contatti }
     *     
     */
    public void setContatti(Contatti value) {
        this.contatti = value;
    }

    /**
     * Recupera il valore della proprietà dataNascita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta il valore della proprietà dataNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataNascita(XMLGregorianCalendar value) {
        this.dataNascita = value;
    }

    /**
     * Gets the value of the elencoModalitaPagamento property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the elencoModalitaPagamento property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getElencoModalitaPagamento().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link ModalitaPagamento }
     * 
     * 
     */
    public List<ModalitaPagamento> getElencoModalitaPagamento() {
        if (elencoModalitaPagamento == null) {
            elencoModalitaPagamento = new ArrayList<ModalitaPagamento>();
        }
        return this.elencoModalitaPagamento;
    }

    /**
     * Gets the value of the elencoSedi property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the elencoSedi property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getElencoSedi().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Sede }
     * 
     * 
     */
    public List<Sede> getElencoSedi() {
        if (elencoSedi == null) {
            elencoSedi = new ArrayList<Sede>();
        }
        return this.elencoSedi;
    }

    /**
     * Recupera il valore della proprietà indirizzoPrincipale.
     * 
     * @return
     *     possible object is
     *     {@link Recapito }
     *     
     */
    public Recapito getIndirizzoPrincipale() {
        return indirizzoPrincipale;
    }

    /**
     * Imposta il valore della proprietà indirizzoPrincipale.
     * 
     * @param value
     *     allowed object is
     *     {@link Recapito }
     *     
     */
    public void setIndirizzoPrincipale(Recapito value) {
        this.indirizzoPrincipale = value;
    }

    /**
     * Recupera il valore della proprietà naturaGiuridica.
     * 
     * @return
     *     possible object is
     *     {@link NaturaGiuridica }
     *     
     */
    public NaturaGiuridica getNaturaGiuridica() {
        return naturaGiuridica;
    }

    /**
     * Imposta il valore della proprietà naturaGiuridica.
     * 
     * @param value
     *     allowed object is
     *     {@link NaturaGiuridica }
     *     
     */
    public void setNaturaGiuridica(NaturaGiuridica value) {
        this.naturaGiuridica = value;
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
     * Recupera il valore della proprietà partitaIva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitaIva() {
        return partitaIva;
    }

    /**
     * Imposta il valore della proprietà partitaIva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitaIva(String value) {
        this.partitaIva = value;
    }

    /**
     * Recupera il valore della proprietà ragioneSociale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * Imposta il valore della proprietà ragioneSociale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRagioneSociale(String value) {
        this.ragioneSociale = value;
    }

    /**
     * Recupera il valore della proprietà sesso.
     * 
     * @return
     *     possible object is
     *     {@link Sesso }
     *     
     */
    public Sesso getSesso() {
        return sesso;
    }

    /**
     * Imposta il valore della proprietà sesso.
     * 
     * @param value
     *     allowed object is
     *     {@link Sesso }
     *     
     */
    public void setSesso(Sesso value) {
        this.sesso = value;
    }

}
