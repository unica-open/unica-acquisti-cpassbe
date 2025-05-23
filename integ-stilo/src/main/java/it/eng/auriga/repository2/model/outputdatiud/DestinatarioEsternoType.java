
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per DestinatarioEsternoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DestinatarioEsternoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}SoggettoEsternoType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PerConoscenza" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="IndirizzoDest" type="{}IndirizzoType" minOccurs="0"/&gt;
 *         &lt;element name="IndirizzoEmailDest" type="{}EmailType" minOccurs="0"/&gt;
 *         &lt;element name="DataOraSpedAlDest" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="MezzoTrasmissioneAlDest" type="{}OggDiTabDiSistemaType" minOccurs="0"/&gt;
 *         &lt;element name="DataRaccomandataAlDest" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="NroRaccomandataAlDest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DestinatarioEsternoType", propOrder = {
    "perConoscenza",
    "indirizzoDest",
    "indirizzoEmailDest",
    "dataOraSpedAlDest",
    "mezzoTrasmissioneAlDest",
    "dataRaccomandataAlDest",
    "nroRaccomandataAlDest"
})
public class DestinatarioEsternoType
    extends SoggettoEsternoType
{

    @XmlElement(name = "PerConoscenza")
    protected Object perConoscenza;
    @XmlElement(name = "IndirizzoDest")
    protected IndirizzoType indirizzoDest;
    @XmlElement(name = "IndirizzoEmailDest")
    protected EmailType indirizzoEmailDest;
    @XmlElement(name = "DataOraSpedAlDest")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraSpedAlDest;
    @XmlElement(name = "MezzoTrasmissioneAlDest")
    protected OggDiTabDiSistemaType mezzoTrasmissioneAlDest;
    @XmlElement(name = "DataRaccomandataAlDest")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataRaccomandataAlDest;
    @XmlElement(name = "NroRaccomandataAlDest")
    protected String nroRaccomandataAlDest;

    /**
     * Recupera il valore della proprietà perConoscenza.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPerConoscenza() {
        return perConoscenza;
    }

    /**
     * Imposta il valore della proprietà perConoscenza.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPerConoscenza(Object value) {
        this.perConoscenza = value;
    }

    /**
     * Recupera il valore della proprietà indirizzoDest.
     * 
     * @return
     *     possible object is
     *     {@link IndirizzoType }
     *     
     */
    public IndirizzoType getIndirizzoDest() {
        return indirizzoDest;
    }

    /**
     * Imposta il valore della proprietà indirizzoDest.
     * 
     * @param value
     *     allowed object is
     *     {@link IndirizzoType }
     *     
     */
    public void setIndirizzoDest(IndirizzoType value) {
        this.indirizzoDest = value;
    }

    /**
     * Recupera il valore della proprietà indirizzoEmailDest.
     * 
     * @return
     *     possible object is
     *     {@link EmailType }
     *     
     */
    public EmailType getIndirizzoEmailDest() {
        return indirizzoEmailDest;
    }

    /**
     * Imposta il valore della proprietà indirizzoEmailDest.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailType }
     *     
     */
    public void setIndirizzoEmailDest(EmailType value) {
        this.indirizzoEmailDest = value;
    }

    /**
     * Recupera il valore della proprietà dataOraSpedAlDest.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraSpedAlDest() {
        return dataOraSpedAlDest;
    }

    /**
     * Imposta il valore della proprietà dataOraSpedAlDest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraSpedAlDest(XMLGregorianCalendar value) {
        this.dataOraSpedAlDest = value;
    }

    /**
     * Recupera il valore della proprietà mezzoTrasmissioneAlDest.
     * 
     * @return
     *     possible object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public OggDiTabDiSistemaType getMezzoTrasmissioneAlDest() {
        return mezzoTrasmissioneAlDest;
    }

    /**
     * Imposta il valore della proprietà mezzoTrasmissioneAlDest.
     * 
     * @param value
     *     allowed object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public void setMezzoTrasmissioneAlDest(OggDiTabDiSistemaType value) {
        this.mezzoTrasmissioneAlDest = value;
    }

    /**
     * Recupera il valore della proprietà dataRaccomandataAlDest.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRaccomandataAlDest() {
        return dataRaccomandataAlDest;
    }

    /**
     * Imposta il valore della proprietà dataRaccomandataAlDest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRaccomandataAlDest(XMLGregorianCalendar value) {
        this.dataRaccomandataAlDest = value;
    }

    /**
     * Recupera il valore della proprietà nroRaccomandataAlDest.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroRaccomandataAlDest() {
        return nroRaccomandataAlDest;
    }

    /**
     * Imposta il valore della proprietà nroRaccomandataAlDest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroRaccomandataAlDest(String value) {
        this.nroRaccomandataAlDest = value;
    }

}
