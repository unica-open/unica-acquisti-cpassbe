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

package it.doqui.acta.acaris.officialbookservice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Classe Java per InfoCreazioneRegistrazione complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="InfoCreazioneRegistrazione"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="numeroRegistrazionePrecedente" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="annoRegistrazionePrecedente" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="registrazioneRiservata" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="oggetto" type="{common.acaris.acta.doqui.it}string"/&amp;gt;
 *         &amp;lt;element name="collocazioneCartacea" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="mezzoTrasmissivoId" type="{common.acaris.acta.doqui.it}IDDBType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="forzareSeRegistrazioneSimile" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="documentoRiservato" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="classificazioneProposta" type="{common.acaris.acta.doqui.it}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="forzareSeRegistrazionePropostaInvalida" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="dataDocumento" type="{common.acaris.acta.doqui.it}date" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="descrizioneAllegato" type="{common.acaris.acta.doqui.it}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="mittenteInterno" type="{officialbookservice.acaris.acta.doqui.it}MittenteInterno" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="destinatarioInterno" type="{officialbookservice.acaris.acta.doqui.it}DestinatarioInterno" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="destinatarioEsterno" type="{officialbookservice.acaris.acta.doqui.it}DestinatarioEsterno" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="protocollante" type="{officialbookservice.acaris.acta.doqui.it}IdentificazioneProtocollante" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="annotazione" type="{officialbookservice.acaris.acta.doqui.it}InfoCreazioneAnnotazioneOB" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="forzareSePresenzaInviti" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *         &amp;lt;element name="forzareSePresenzaDaInoltrare" type="{common.acaris.acta.doqui.it}boolean"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoCreazioneRegistrazione", propOrder = {
    "numeroRegistrazionePrecedente",
    "annoRegistrazionePrecedente",
    "registrazioneRiservata",
    "oggetto",
    "collocazioneCartacea",
    "mezzoTrasmissivoId",
    "forzareSeRegistrazioneSimile",
    "documentoRiservato",
    "classificazioneProposta",
    "forzareSeRegistrazionePropostaInvalida",
    "dataDocumento",
    "descrizioneAllegato",
    "mittenteInterno",
    "destinatarioInterno",
    "destinatarioEsterno",
    "protocollante",
    "annotazione",
    "forzareSePresenzaInviti",
    "forzareSePresenzaDaInoltrare"
})
public class InfoCreazioneRegistrazione {

    protected String numeroRegistrazionePrecedente;
    protected String annoRegistrazionePrecedente;
    protected boolean registrazioneRiservata;
    @XmlElement(required = true)
    protected String oggetto;
    protected String collocazioneCartacea;
    protected Long mezzoTrasmissivoId;
    protected boolean forzareSeRegistrazioneSimile;
    protected boolean documentoRiservato;
    protected String classificazioneProposta;
    protected boolean forzareSeRegistrazionePropostaInvalida;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataDocumento;
    protected List<String> descrizioneAllegato;
    protected List<MittenteInterno> mittenteInterno;
    protected List<DestinatarioInterno> destinatarioInterno;
    protected List<DestinatarioEsterno> destinatarioEsterno;
    protected IdentificazioneProtocollante protocollante;
    protected InfoCreazioneAnnotazioneOB annotazione;
    protected boolean forzareSePresenzaInviti;
    protected boolean forzareSePresenzaDaInoltrare;

    /**
     * Recupera il valore della proprietà numeroRegistrazionePrecedente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRegistrazionePrecedente() {
        return numeroRegistrazionePrecedente;
    }

    /**
     * Imposta il valore della proprietà numeroRegistrazionePrecedente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRegistrazionePrecedente(String value) {
        this.numeroRegistrazionePrecedente = value;
    }

    /**
     * Recupera il valore della proprietà annoRegistrazionePrecedente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnoRegistrazionePrecedente() {
        return annoRegistrazionePrecedente;
    }

    /**
     * Imposta il valore della proprietà annoRegistrazionePrecedente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnoRegistrazionePrecedente(String value) {
        this.annoRegistrazionePrecedente = value;
    }

    /**
     * Recupera il valore della proprietà registrazioneRiservata.
     * 
     */
    public boolean isRegistrazioneRiservata() {
        return registrazioneRiservata;
    }

    /**
     * Imposta il valore della proprietà registrazioneRiservata.
     * 
     */
    public void setRegistrazioneRiservata(boolean value) {
        this.registrazioneRiservata = value;
    }

    /**
     * Recupera il valore della proprietà oggetto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Imposta il valore della proprietà oggetto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOggetto(String value) {
        this.oggetto = value;
    }

    /**
     * Recupera il valore della proprietà collocazioneCartacea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollocazioneCartacea() {
        return collocazioneCartacea;
    }

    /**
     * Imposta il valore della proprietà collocazioneCartacea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollocazioneCartacea(String value) {
        this.collocazioneCartacea = value;
    }

    /**
     * Recupera il valore della proprietà mezzoTrasmissivoId.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMezzoTrasmissivoId() {
        return mezzoTrasmissivoId;
    }

    /**
     * Imposta il valore della proprietà mezzoTrasmissivoId.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMezzoTrasmissivoId(Long value) {
        this.mezzoTrasmissivoId = value;
    }

    /**
     * Recupera il valore della proprietà forzareSeRegistrazioneSimile.
     * 
     */
    public boolean isForzareSeRegistrazioneSimile() {
        return forzareSeRegistrazioneSimile;
    }

    /**
     * Imposta il valore della proprietà forzareSeRegistrazioneSimile.
     * 
     */
    public void setForzareSeRegistrazioneSimile(boolean value) {
        this.forzareSeRegistrazioneSimile = value;
    }

    /**
     * Recupera il valore della proprietà documentoRiservato.
     * 
     */
    public boolean isDocumentoRiservato() {
        return documentoRiservato;
    }

    /**
     * Imposta il valore della proprietà documentoRiservato.
     * 
     */
    public void setDocumentoRiservato(boolean value) {
        this.documentoRiservato = value;
    }

    /**
     * Recupera il valore della proprietà classificazioneProposta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificazioneProposta() {
        return classificazioneProposta;
    }

    /**
     * Imposta il valore della proprietà classificazioneProposta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassificazioneProposta(String value) {
        this.classificazioneProposta = value;
    }

    /**
     * Recupera il valore della proprietà forzareSeRegistrazionePropostaInvalida.
     * 
     */
    public boolean isForzareSeRegistrazionePropostaInvalida() {
        return forzareSeRegistrazionePropostaInvalida;
    }

    /**
     * Imposta il valore della proprietà forzareSeRegistrazionePropostaInvalida.
     * 
     */
    public void setForzareSeRegistrazionePropostaInvalida(boolean value) {
        this.forzareSeRegistrazionePropostaInvalida = value;
    }

    /**
     * Recupera il valore della proprietà dataDocumento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDocumento() {
        return dataDocumento;
    }

    /**
     * Imposta il valore della proprietà dataDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDocumento(XMLGregorianCalendar value) {
        this.dataDocumento = value;
    }

    /**
     * Gets the value of the descrizioneAllegato property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the descrizioneAllegato property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDescrizioneAllegato().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDescrizioneAllegato() {
        if (descrizioneAllegato == null) {
            descrizioneAllegato = new ArrayList<String>();
        }
        return this.descrizioneAllegato;
    }

    /**
     * Gets the value of the mittenteInterno property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the mittenteInterno property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getMittenteInterno().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link MittenteInterno }
     * 
     * 
     */
    public List<MittenteInterno> getMittenteInterno() {
        if (mittenteInterno == null) {
            mittenteInterno = new ArrayList<MittenteInterno>();
        }
        return this.mittenteInterno;
    }

    /**
     * Gets the value of the destinatarioInterno property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the destinatarioInterno property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDestinatarioInterno().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link DestinatarioInterno }
     * 
     * 
     */
    public List<DestinatarioInterno> getDestinatarioInterno() {
        if (destinatarioInterno == null) {
            destinatarioInterno = new ArrayList<DestinatarioInterno>();
        }
        return this.destinatarioInterno;
    }

    /**
     * Gets the value of the destinatarioEsterno property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the destinatarioEsterno property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDestinatarioEsterno().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link DestinatarioEsterno }
     * 
     * 
     */
    public List<DestinatarioEsterno> getDestinatarioEsterno() {
        if (destinatarioEsterno == null) {
            destinatarioEsterno = new ArrayList<DestinatarioEsterno>();
        }
        return this.destinatarioEsterno;
    }

    /**
     * Recupera il valore della proprietà protocollante.
     * 
     * @return
     *     possible object is
     *     {@link IdentificazioneProtocollante }
     *     
     */
    public IdentificazioneProtocollante getProtocollante() {
        return protocollante;
    }

    /**
     * Imposta il valore della proprietà protocollante.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentificazioneProtocollante }
     *     
     */
    public void setProtocollante(IdentificazioneProtocollante value) {
        this.protocollante = value;
    }

    /**
     * Recupera il valore della proprietà annotazione.
     * 
     * @return
     *     possible object is
     *     {@link InfoCreazioneAnnotazioneOB }
     *     
     */
    public InfoCreazioneAnnotazioneOB getAnnotazione() {
        return annotazione;
    }

    /**
     * Imposta il valore della proprietà annotazione.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoCreazioneAnnotazioneOB }
     *     
     */
    public void setAnnotazione(InfoCreazioneAnnotazioneOB value) {
        this.annotazione = value;
    }

    /**
     * Recupera il valore della proprietà forzareSePresenzaInviti.
     * 
     */
    public boolean isForzareSePresenzaInviti() {
        return forzareSePresenzaInviti;
    }

    /**
     * Imposta il valore della proprietà forzareSePresenzaInviti.
     * 
     */
    public void setForzareSePresenzaInviti(boolean value) {
        this.forzareSePresenzaInviti = value;
    }

    /**
     * Recupera il valore della proprietà forzareSePresenzaDaInoltrare.
     * 
     */
    public boolean isForzareSePresenzaDaInoltrare() {
        return forzareSePresenzaDaInoltrare;
    }

    /**
     * Imposta il valore della proprietà forzareSePresenzaDaInoltrare.
     * 
     */
    public void setForzareSePresenzaDaInoltrare(boolean value) {
        this.forzareSePresenzaDaInoltrare = value;
    }

}
