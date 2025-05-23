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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.doqui.acta.acaris.common.IdProvvedimentoAutorizzatType;


/**
 * &lt;p&gt;Classe Java per TitolarioPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="TitolarioPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}FolderPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="codice" type="{archive.acaris.acta.doqui.it}CodiceType"/&amp;gt;
 *         &amp;lt;element name="descrizione" type="{archive.acaris.acta.doqui.it}DescrizioneType"/&amp;gt;
 *         &amp;lt;element name="stato" type="{archive.acaris.acta.doqui.it}enumTitolarioStatoType"/&amp;gt;
 *         &amp;lt;element name="idRaggruppamentoAOO" type="{archive.acaris.acta.doqui.it}IdRaggruppamentoAOOType"/&amp;gt;
 *         &amp;lt;element name="dataCreaz" type="{archive.acaris.acta.doqui.it}DataCreazType"/&amp;gt;
 *         &amp;lt;element name="dataInizio" type="{archive.acaris.acta.doqui.it}DataInizioType"/&amp;gt;
 *         &amp;lt;element name="dataFine" type="{archive.acaris.acta.doqui.it}DataFineType"/&amp;gt;
 *         &amp;lt;element name="numeroMaxLivelli" type="{archive.acaris.acta.doqui.it}NumeroMaxlivelliType"/&amp;gt;
 *         &amp;lt;element name="numeroLivSottofascicoliAmmessi" type="{archive.acaris.acta.doqui.it}NumeroLivSottofascicoliAmmessiType"/&amp;gt;
 *         &amp;lt;element name="creaAutomaPrimaSerie" type="{archive.acaris.acta.doqui.it}CreaAutomaPrimaSerieType"/&amp;gt;
 *         &amp;lt;element name="serieMultipleVoce" type="{archive.acaris.acta.doqui.it}SerieMultipleVoceType"/&amp;gt;
 *         &amp;lt;element name="insertContenutiInVociConSottovoci" type="{archive.acaris.acta.doqui.it}InsertContenutIinVociConSottovociType"/&amp;gt;
 *         &amp;lt;element name="insertDocInFascConSottofasc" type="{archive.acaris.acta.doqui.it}InsertDocInFascConSottofascType"/&amp;gt;
 *         &amp;lt;element name="insertFuoriVolumi" type="{archive.acaris.acta.doqui.it}InsertFuoriVolumiType"/&amp;gt;
 *         &amp;lt;element name="scartoSoloConVolumi" type="{archive.acaris.acta.doqui.it}ScartoSoloConVolumiType"/&amp;gt;
 *         &amp;lt;element name="idProvvedimentoAutorizzatList" type="{common.acaris.acta.doqui.it}IdProvvedimentoAutorizzatType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TitolarioPropertiesType", propOrder = {
    "codice",
    "descrizione",
    "stato",
    "idRaggruppamentoAOO",
    "dataCreaz",
    "dataInizio",
    "dataFine",
    "numeroMaxLivelli",
    "numeroLivSottofascicoliAmmessi",
    "creaAutomaPrimaSerie",
    "serieMultipleVoce",
    "insertContenutiInVociConSottovoci",
    "insertDocInFascConSottofasc",
    "insertFuoriVolumi",
    "scartoSoloConVolumi",
    "idProvvedimentoAutorizzatList"
})
public class TitolarioPropertiesType
    extends FolderPropertiesType
{

    @XmlElement(required = true)
    protected String codice;
    @XmlElement(required = true)
    protected String descrizione;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumTitolarioStatoType stato;
    @XmlElement(required = true)
    protected String idRaggruppamentoAOO;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataCreaz;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFine;
    protected int numeroMaxLivelli;
    protected int numeroLivSottofascicoliAmmessi;
    protected boolean creaAutomaPrimaSerie;
    protected boolean serieMultipleVoce;
    protected boolean insertContenutiInVociConSottovoci;
    protected boolean insertDocInFascConSottofasc;
    protected boolean insertFuoriVolumi;
    protected boolean scartoSoloConVolumi;
    protected List<IdProvvedimentoAutorizzatType> idProvvedimentoAutorizzatList;

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

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
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link EnumTitolarioStatoType }
     *     
     */
    public EnumTitolarioStatoType getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietà stato.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTitolarioStatoType }
     *     
     */
    public void setStato(EnumTitolarioStatoType value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della proprietà idRaggruppamentoAOO.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdRaggruppamentoAOO() {
        return idRaggruppamentoAOO;
    }

    /**
     * Imposta il valore della proprietà idRaggruppamentoAOO.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdRaggruppamentoAOO(String value) {
        this.idRaggruppamentoAOO = value;
    }

    /**
     * Recupera il valore della proprietà dataCreaz.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCreaz() {
        return dataCreaz;
    }

    /**
     * Imposta il valore della proprietà dataCreaz.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCreaz(XMLGregorianCalendar value) {
        this.dataCreaz = value;
    }

    /**
     * Recupera il valore della proprietà dataInizio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizio() {
        return dataInizio;
    }

    /**
     * Imposta il valore della proprietà dataInizio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizio(XMLGregorianCalendar value) {
        this.dataInizio = value;
    }

    /**
     * Recupera il valore della proprietà dataFine.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFine() {
        return dataFine;
    }

    /**
     * Imposta il valore della proprietà dataFine.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFine(XMLGregorianCalendar value) {
        this.dataFine = value;
    }

    /**
     * Recupera il valore della proprietà numeroMaxLivelli.
     * 
     */
    public int getNumeroMaxLivelli() {
        return numeroMaxLivelli;
    }

    /**
     * Imposta il valore della proprietà numeroMaxLivelli.
     * 
     */
    public void setNumeroMaxLivelli(int value) {
        this.numeroMaxLivelli = value;
    }

    /**
     * Recupera il valore della proprietà numeroLivSottofascicoliAmmessi.
     * 
     */
    public int getNumeroLivSottofascicoliAmmessi() {
        return numeroLivSottofascicoliAmmessi;
    }

    /**
     * Imposta il valore della proprietà numeroLivSottofascicoliAmmessi.
     * 
     */
    public void setNumeroLivSottofascicoliAmmessi(int value) {
        this.numeroLivSottofascicoliAmmessi = value;
    }

    /**
     * Recupera il valore della proprietà creaAutomaPrimaSerie.
     * 
     */
    public boolean isCreaAutomaPrimaSerie() {
        return creaAutomaPrimaSerie;
    }

    /**
     * Imposta il valore della proprietà creaAutomaPrimaSerie.
     * 
     */
    public void setCreaAutomaPrimaSerie(boolean value) {
        this.creaAutomaPrimaSerie = value;
    }

    /**
     * Recupera il valore della proprietà serieMultipleVoce.
     * 
     */
    public boolean isSerieMultipleVoce() {
        return serieMultipleVoce;
    }

    /**
     * Imposta il valore della proprietà serieMultipleVoce.
     * 
     */
    public void setSerieMultipleVoce(boolean value) {
        this.serieMultipleVoce = value;
    }

    /**
     * Recupera il valore della proprietà insertContenutiInVociConSottovoci.
     * 
     */
    public boolean isInsertContenutiInVociConSottovoci() {
        return insertContenutiInVociConSottovoci;
    }

    /**
     * Imposta il valore della proprietà insertContenutiInVociConSottovoci.
     * 
     */
    public void setInsertContenutiInVociConSottovoci(boolean value) {
        this.insertContenutiInVociConSottovoci = value;
    }

    /**
     * Recupera il valore della proprietà insertDocInFascConSottofasc.
     * 
     */
    public boolean isInsertDocInFascConSottofasc() {
        return insertDocInFascConSottofasc;
    }

    /**
     * Imposta il valore della proprietà insertDocInFascConSottofasc.
     * 
     */
    public void setInsertDocInFascConSottofasc(boolean value) {
        this.insertDocInFascConSottofasc = value;
    }

    /**
     * Recupera il valore della proprietà insertFuoriVolumi.
     * 
     */
    public boolean isInsertFuoriVolumi() {
        return insertFuoriVolumi;
    }

    /**
     * Imposta il valore della proprietà insertFuoriVolumi.
     * 
     */
    public void setInsertFuoriVolumi(boolean value) {
        this.insertFuoriVolumi = value;
    }

    /**
     * Recupera il valore della proprietà scartoSoloConVolumi.
     * 
     */
    public boolean isScartoSoloConVolumi() {
        return scartoSoloConVolumi;
    }

    /**
     * Imposta il valore della proprietà scartoSoloConVolumi.
     * 
     */
    public void setScartoSoloConVolumi(boolean value) {
        this.scartoSoloConVolumi = value;
    }

    /**
     * Gets the value of the idProvvedimentoAutorizzatList property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the idProvvedimentoAutorizzatList property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getIdProvvedimentoAutorizzatList().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link IdProvvedimentoAutorizzatType }
     * 
     * 
     */
    public List<IdProvvedimentoAutorizzatType> getIdProvvedimentoAutorizzatList() {
        if (idProvvedimentoAutorizzatList == null) {
            idProvvedimentoAutorizzatList = new ArrayList<IdProvvedimentoAutorizzatType>();
        }
        return this.idProvvedimentoAutorizzatList;
    }

}
