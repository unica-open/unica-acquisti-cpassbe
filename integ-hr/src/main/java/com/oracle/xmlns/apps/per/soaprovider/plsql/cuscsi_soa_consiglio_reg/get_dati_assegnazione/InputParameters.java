
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_consiglio_reg.get_dati_assegnazione;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per anonymous complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="P_MATRICOLA_HR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="P_CODICE_FISCALE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="P_DATA_ESTRAZIONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pmatricolahr",
    "pcodicefiscale",
    "pdataestrazione"
})
@XmlRootElement(name = "InputParameters")
public class InputParameters {

    @XmlElementRef(name = "P_MATRICOLA_HR", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pmatricolahr;
    @XmlElementRef(name = "P_CODICE_FISCALE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pcodicefiscale;
    @XmlElementRef(name = "P_DATA_ESTRAZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_dati_assegnazione/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pdataestrazione;

    /**
     * Recupera il valore della proprietà pmatricolahr.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPMATRICOLAHR() {
        return pmatricolahr;
    }

    /**
     * Imposta il valore della proprietà pmatricolahr.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPMATRICOLAHR(JAXBElement<String> value) {
        this.pmatricolahr = value;
    }

    /**
     * Recupera il valore della proprietà pcodicefiscale.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPCODICEFISCALE() {
        return pcodicefiscale;
    }

    /**
     * Imposta il valore della proprietà pcodicefiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPCODICEFISCALE(JAXBElement<String> value) {
        this.pcodicefiscale = value;
    }

    /**
     * Recupera il valore della proprietà pdataestrazione.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPDATAESTRAZIONE() {
        return pdataestrazione;
    }

    /**
     * Imposta il valore della proprietà pdataestrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPDATAESTRAZIONE(JAXBElement<String> value) {
        this.pdataestrazione = value;
    }

}
