
package com.oracle.xmlns.apps.per.soaprovider.plsql.cuscsi_soa_giunta_regionale.get_tipologia_org_ente;

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
 *         &amp;lt;element name="CUSCSI_SOA_GIUNTA_REGIONALE-24G" type="{http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/}APPS.CUSCSI_SOA_GIUNTX5117459X5X1" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_COD_TIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DES_TIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_INIZIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_DATA_FINE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="OP_MSG_DATA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
    "cuscsisoagiuntaregionale24G",
    "opcodtipologia",
    "opdestipologia",
    "opdatainizio",
    "opdatafine",
    "opmsgdata"
})
@XmlRootElement(name = "OutputParameters")
public class OutputParameters {

    @XmlElementRef(name = "CUSCSI_SOA_GIUNTA_REGIONALE-24G", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<APPSCUSCSISOAGIUNTX5117459X5X1> cuscsisoagiuntaregionale24G;
    @XmlElementRef(name = "OP_COD_TIPOLOGIA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opcodtipologia;
    @XmlElementRef(name = "OP_DES_TIPOLOGIA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdestipologia;
    @XmlElementRef(name = "OP_DATA_INIZIO", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdatainizio;
    @XmlElementRef(name = "OP_DATA_FINE", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opdatafine;
    @XmlElementRef(name = "OP_MSG_DATA", namespace = "http://xmlns.oracle.com/apps/per/soaprovider/plsql/cuscsi_soa_giunta_regionale/get_tipologia_org_ente/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> opmsgdata;

    /**
     * Recupera il valore della proprietà cuscsisoagiuntaregionale24G.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link APPSCUSCSISOAGIUNTX5117459X5X1 }{@code >}
     *     
     */
    public JAXBElement<APPSCUSCSISOAGIUNTX5117459X5X1> getCUSCSISOAGIUNTAREGIONALE24G() {
        return cuscsisoagiuntaregionale24G;
    }

    /**
     * Imposta il valore della proprietà cuscsisoagiuntaregionale24G.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link APPSCUSCSISOAGIUNTX5117459X5X1 }{@code >}
     *     
     */
    public void setCUSCSISOAGIUNTAREGIONALE24G(JAXBElement<APPSCUSCSISOAGIUNTX5117459X5X1> value) {
        this.cuscsisoagiuntaregionale24G = value;
    }

    /**
     * Recupera il valore della proprietà opcodtipologia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPCODTIPOLOGIA() {
        return opcodtipologia;
    }

    /**
     * Imposta il valore della proprietà opcodtipologia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPCODTIPOLOGIA(JAXBElement<String> value) {
        this.opcodtipologia = value;
    }

    /**
     * Recupera il valore della proprietà opdestipologia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDESTIPOLOGIA() {
        return opdestipologia;
    }

    /**
     * Imposta il valore della proprietà opdestipologia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDESTIPOLOGIA(JAXBElement<String> value) {
        this.opdestipologia = value;
    }

    /**
     * Recupera il valore della proprietà opdatainizio.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDATAINIZIO() {
        return opdatainizio;
    }

    /**
     * Imposta il valore della proprietà opdatainizio.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDATAINIZIO(JAXBElement<String> value) {
        this.opdatainizio = value;
    }

    /**
     * Recupera il valore della proprietà opdatafine.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPDATAFINE() {
        return opdatafine;
    }

    /**
     * Imposta il valore della proprietà opdatafine.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPDATAFINE(JAXBElement<String> value) {
        this.opdatafine = value;
    }

    /**
     * Recupera il valore della proprietà opmsgdata.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOPMSGDATA() {
        return opmsgdata;
    }

    /**
     * Imposta il valore della proprietà opmsgdata.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOPMSGDATA(JAXBElement<String> value) {
        this.opmsgdata = value;
    }

}
