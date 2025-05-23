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
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per ContenutoFisicoPropertiesType complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ContenutoFisicoPropertiesType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{archive.acaris.acta.doqui.it}DocumentPropertiesType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="numeroVersione" type="{archive.acaris.acta.doqui.it}NumeroVersioneType"/&amp;gt;
 *         &amp;lt;element name="modificheTecniche" type="{archive.acaris.acta.doqui.it}ModificheTecnicheType"/&amp;gt;
 *         &amp;lt;element name="workingCopy" type="{archive.acaris.acta.doqui.it}WorkingCopyType"/&amp;gt;
 *         &amp;lt;element name="infoFormato" type="{archive.acaris.acta.doqui.it}InfoFormatoXMLType"/&amp;gt;
 *         &amp;lt;element name="rapportoVerifica" type="{archive.acaris.acta.doqui.it}RapportoVerificaXMLType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="datiCertificato" type="{archive.acaris.acta.doqui.it}DatiCertificatoType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="sbustamento" type="{archive.acaris.acta.doqui.it}SbustamentoType"/&amp;gt;
 *         &amp;lt;element name="modificabile" type="{archive.acaris.acta.doqui.it}ModificabileType"/&amp;gt;
 *         &amp;lt;element name="docPrimario" type="{archive.acaris.acta.doqui.it}DocPrimarioType"/&amp;gt;
 *         &amp;lt;element name="mimeTypeBusta" type="{archive.acaris.acta.doqui.it}MIMETypeBustaType"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContenutoFisicoPropertiesType", propOrder = {
    "numeroVersione",
    "modificheTecniche",
    "workingCopy",
    "infoFormato",
    "rapportoVerifica",
    "datiCertificato",
    "sbustamento",
    "modificabile",
    "docPrimario",
    "mimeTypeBusta"
})
public class ContenutoFisicoPropertiesType
    extends DocumentPropertiesType
{

    protected int numeroVersione;
    @XmlElement(required = true)
    protected String modificheTecniche;
    protected boolean workingCopy;
    @XmlElement(required = true)
    protected String infoFormato;
    protected List<String> rapportoVerifica;
    protected List<DatiCertificatoType> datiCertificato;
    protected boolean sbustamento;
    protected boolean modificabile;
    protected boolean docPrimario;
    @XmlElement(required = true)
    protected String mimeTypeBusta;

    /**
     * Recupera il valore della proprietà numeroVersione.
     * 
     */
    public int getNumeroVersione() {
        return numeroVersione;
    }

    /**
     * Imposta il valore della proprietà numeroVersione.
     * 
     */
    public void setNumeroVersione(int value) {
        this.numeroVersione = value;
    }

    /**
     * Recupera il valore della proprietà modificheTecniche.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModificheTecniche() {
        return modificheTecniche;
    }

    /**
     * Imposta il valore della proprietà modificheTecniche.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModificheTecniche(String value) {
        this.modificheTecniche = value;
    }

    /**
     * Recupera il valore della proprietà workingCopy.
     * 
     */
    public boolean isWorkingCopy() {
        return workingCopy;
    }

    /**
     * Imposta il valore della proprietà workingCopy.
     * 
     */
    public void setWorkingCopy(boolean value) {
        this.workingCopy = value;
    }

    /**
     * Recupera il valore della proprietà infoFormato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfoFormato() {
        return infoFormato;
    }

    /**
     * Imposta il valore della proprietà infoFormato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfoFormato(String value) {
        this.infoFormato = value;
    }

    /**
     * Gets the value of the rapportoVerifica property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the rapportoVerifica property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getRapportoVerifica().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRapportoVerifica() {
        if (rapportoVerifica == null) {
            rapportoVerifica = new ArrayList<String>();
        }
        return this.rapportoVerifica;
    }

    /**
     * Gets the value of the datiCertificato property.
     * 
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the datiCertificato property.
     * 
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     *    getDatiCertificato().add(newItem);
     * &lt;/pre&gt;
     * 
     * 
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link DatiCertificatoType }
     * 
     * 
     */
    public List<DatiCertificatoType> getDatiCertificato() {
        if (datiCertificato == null) {
            datiCertificato = new ArrayList<DatiCertificatoType>();
        }
        return this.datiCertificato;
    }

    /**
     * Recupera il valore della proprietà sbustamento.
     * 
     */
    public boolean isSbustamento() {
        return sbustamento;
    }

    /**
     * Imposta il valore della proprietà sbustamento.
     * 
     */
    public void setSbustamento(boolean value) {
        this.sbustamento = value;
    }

    /**
     * Recupera il valore della proprietà modificabile.
     * 
     */
    public boolean isModificabile() {
        return modificabile;
    }

    /**
     * Imposta il valore della proprietà modificabile.
     * 
     */
    public void setModificabile(boolean value) {
        this.modificabile = value;
    }

    /**
     * Recupera il valore della proprietà docPrimario.
     * 
     */
    public boolean isDocPrimario() {
        return docPrimario;
    }

    /**
     * Imposta il valore della proprietà docPrimario.
     * 
     */
    public void setDocPrimario(boolean value) {
        this.docPrimario = value;
    }

    /**
     * Recupera il valore della proprietà mimeTypeBusta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeTypeBusta() {
        return mimeTypeBusta;
    }

    /**
     * Imposta il valore della proprietà mimeTypeBusta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeTypeBusta(String value) {
        this.mimeTypeBusta = value;
    }

}
