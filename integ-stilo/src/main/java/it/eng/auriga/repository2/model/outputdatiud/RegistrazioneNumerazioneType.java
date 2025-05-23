
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per RegistrazioneNumerazioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RegistrazioneNumerazioneType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="CategoriaReg"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="PG"/&gt;
 *               &lt;enumeration value="PP"/&gt;
 *               &lt;enumeration value="R"/&gt;
 *               &lt;enumeration value="E"/&gt;
 *               &lt;enumeration value="A"/&gt;
 *               &lt;enumeration value="I"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SiglaReg" type="{}SiglaRegNumType"/&gt;
 *         &lt;element name="AnnoReg" type="{}AnnoType" minOccurs="0"/&gt;
 *         &lt;element name="NumReg" type="{}NumRegType"/&gt;
 *         &lt;element name="DataOraReg" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="UOReg" type="{}UOType" minOccurs="0"/&gt;
 *         &lt;element name="UserReg" type="{}UserType" minOccurs="0"/&gt;
 *         &lt;element name="FlagRegAnnullata" type="{}FlagSiNoType" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *       &lt;attribute name="IdRegNum" type="{}IdInSistemaEsternoType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistrazioneNumerazioneType", propOrder = {

})
public class RegistrazioneNumerazioneType {

    @XmlElement(name = "CategoriaReg", required = true)
    protected String categoriaReg;
    @XmlElement(name = "SiglaReg", required = true)
    protected String siglaReg;
    @XmlElement(name = "AnnoReg")
    @XmlSchemaType(name = "integer")
    protected Integer annoReg;
    @XmlElement(name = "NumReg")
    @XmlSchemaType(name = "integer")
    protected int numReg;
    @XmlElement(name = "DataOraReg")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraReg;
    @XmlElement(name = "UOReg")
    protected UOType uoReg;
    @XmlElement(name = "UserReg")
    protected UserType userReg;
    @XmlElement(name = "FlagRegAnnullata")
    protected String flagRegAnnullata;
    @XmlAttribute(name = "IdRegNum")
    protected String idRegNum;

    /**
     * Recupera il valore della proprietà categoriaReg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoriaReg() {
        return categoriaReg;
    }

    /**
     * Imposta il valore della proprietà categoriaReg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoriaReg(String value) {
        this.categoriaReg = value;
    }

    /**
     * Recupera il valore della proprietà siglaReg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaReg() {
        return siglaReg;
    }

    /**
     * Imposta il valore della proprietà siglaReg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaReg(String value) {
        this.siglaReg = value;
    }

    /**
     * Recupera il valore della proprietà annoReg.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoReg() {
        return annoReg;
    }

    /**
     * Imposta il valore della proprietà annoReg.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoReg(Integer value) {
        this.annoReg = value;
    }

    /**
     * Recupera il valore della proprietà numReg.
     * 
     */
    public int getNumReg() {
        return numReg;
    }

    /**
     * Imposta il valore della proprietà numReg.
     * 
     */
    public void setNumReg(int value) {
        this.numReg = value;
    }

    /**
     * Recupera il valore della proprietà dataOraReg.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraReg() {
        return dataOraReg;
    }

    /**
     * Imposta il valore della proprietà dataOraReg.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraReg(XMLGregorianCalendar value) {
        this.dataOraReg = value;
    }

    /**
     * Recupera il valore della proprietà uoReg.
     * 
     * @return
     *     possible object is
     *     {@link UOType }
     *     
     */
    public UOType getUOReg() {
        return uoReg;
    }

    /**
     * Imposta il valore della proprietà uoReg.
     * 
     * @param value
     *     allowed object is
     *     {@link UOType }
     *     
     */
    public void setUOReg(UOType value) {
        this.uoReg = value;
    }

    /**
     * Recupera il valore della proprietà userReg.
     * 
     * @return
     *     possible object is
     *     {@link UserType }
     *     
     */
    public UserType getUserReg() {
        return userReg;
    }

    /**
     * Imposta il valore della proprietà userReg.
     * 
     * @param value
     *     allowed object is
     *     {@link UserType }
     *     
     */
    public void setUserReg(UserType value) {
        this.userReg = value;
    }

    /**
     * Recupera il valore della proprietà flagRegAnnullata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagRegAnnullata() {
        return flagRegAnnullata;
    }

    /**
     * Imposta il valore della proprietà flagRegAnnullata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagRegAnnullata(String value) {
        this.flagRegAnnullata = value;
    }

    /**
     * Recupera il valore della proprietà idRegNum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdRegNum() {
        return idRegNum;
    }

    /**
     * Imposta il valore della proprietà idRegNum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdRegNum(String value) {
        this.idRegNum = value;
    }

}
