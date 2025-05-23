
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_consiglio_reg.get_organizzazioni_ente;

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
 *         &amp;lt;element name="P_COD_STRUTTURA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="P_NOME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
    "pcodstruttura",
    "pnome",
    "pdataestrazione"
})
@XmlRootElement(name = "InputParameters")
public class InputParameters {

    @XmlElementRef(name = "P_COD_STRUTTURA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pcodstruttura;
    @XmlElementRef(name = "P_NOME", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pnome;
    @XmlElementRef(name = "P_DATA_ESTRAZIONE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_consiglio_reg/get_organizzazioni_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pdataestrazione;

    /**
     * Recupera il valore della proprietà pcodstruttura.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPCODSTRUTTURA() {
        return pcodstruttura;
    }

    /**
     * Imposta il valore della proprietà pcodstruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPCODSTRUTTURA(JAXBElement<String> value) {
        this.pcodstruttura = value;
    }

    /**
     * Recupera il valore della proprietà pnome.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPNOME() {
        return pnome;
    }

    /**
     * Imposta il valore della proprietà pnome.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPNOME(JAXBElement<String> value) {
        this.pnome = value;
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
