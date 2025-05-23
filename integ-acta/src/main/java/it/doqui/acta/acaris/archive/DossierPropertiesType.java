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


/**
 * &lt;p&gt;Classe Java per DossierPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="DossierPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}AggregazionePropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="creazioneFascicoli" type="{archive.acaris.acta.doqui.it}CreazioneFascicoliType"/&amp;gt;
 *         &amp;lt;element name="riclassificazioneFascicoli" type="{archive.acaris.acta.doqui.it}RiclassificazioneFascicoliType"/&amp;gt;
 *         &amp;lt;element name="inserimentoDocumenti" type="{archive.acaris.acta.doqui.it}InserimentoDocumentiType"/&amp;gt;
 *         &amp;lt;element name="aggiuntaOriClassificazioneDocumenti" type="{archive.acaris.acta.doqui.it}AggiuntaOriClassificazioneDocumentiType"/&amp;gt;
 *         &amp;lt;element name="stato" type="{archive.acaris.acta.doqui.it}enumDossierStatoType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DossierPropertiesType", propOrder = {
    "creazioneFascicoli",
    "riclassificazioneFascicoli",
    "inserimentoDocumenti",
    "aggiuntaOriClassificazioneDocumenti",
    "stato"
})
public class DossierPropertiesType
    extends AggregazionePropertiesType
{

    protected boolean creazioneFascicoli;
    protected boolean riclassificazioneFascicoli;
    protected boolean inserimentoDocumenti;
    protected boolean aggiuntaOriClassificazioneDocumenti;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnumDossierStatoType stato;

    /**
     * Recupera il valore della proprietà creazioneFascicoli.
     * 
     */
    public boolean isCreazioneFascicoli() {
        return creazioneFascicoli;
    }

    /**
     * Imposta il valore della proprietà creazioneFascicoli.
     * 
     */
    public void setCreazioneFascicoli(boolean value) {
        this.creazioneFascicoli = value;
    }

    /**
     * Recupera il valore della proprietà riclassificazioneFascicoli.
     * 
     */
    public boolean isRiclassificazioneFascicoli() {
        return riclassificazioneFascicoli;
    }

    /**
     * Imposta il valore della proprietà riclassificazioneFascicoli.
     * 
     */
    public void setRiclassificazioneFascicoli(boolean value) {
        this.riclassificazioneFascicoli = value;
    }

    /**
     * Recupera il valore della proprietà inserimentoDocumenti.
     * 
     */
    public boolean isInserimentoDocumenti() {
        return inserimentoDocumenti;
    }

    /**
     * Imposta il valore della proprietà inserimentoDocumenti.
     * 
     */
    public void setInserimentoDocumenti(boolean value) {
        this.inserimentoDocumenti = value;
    }

    /**
     * Recupera il valore della proprietà aggiuntaOriClassificazioneDocumenti.
     * 
     */
    public boolean isAggiuntaOriClassificazioneDocumenti() {
        return aggiuntaOriClassificazioneDocumenti;
    }

    /**
     * Imposta il valore della proprietà aggiuntaOriClassificazioneDocumenti.
     * 
     */
    public void setAggiuntaOriClassificazioneDocumenti(boolean value) {
        this.aggiuntaOriClassificazioneDocumenti = value;
    }

    /**
     * Recupera il valore della proprietà stato.
     * 
     * @return
     *     possible object is
     *     {@link EnumDossierStatoType }
     *     
     */
    public EnumDossierStatoType getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietà stato.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumDossierStatoType }
     *     
     */
    public void setStato(EnumDossierStatoType value) {
        this.stato = value;
    }

}
