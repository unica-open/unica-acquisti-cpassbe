
package it.eng.auriga.repository2.model.outputdatiud;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per AssegnazioneInternaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AssegnazioneInternaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}SoggettoInternoType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DataOraAssegnazione" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="FlagPerConoscenza" type="{}FlagSiNoType"/&gt;
 *         &lt;element name="LivelloPriorita" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="ALTA"/&gt;
 *               &lt;enumeration value="MEDIA"/&gt;
 *               &lt;enumeration value="BASSA"/&gt;
 *               &lt;enumeration value=""/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MotivoAssegnazione" type="{}OggDiTabDiSistemaType" minOccurs="0"/&gt;
 *         &lt;element name="MessaggioAssegnazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DecorrenzaAssegnazione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="RichiestaPresaInCaricoEntroGiorniNro" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="RichiestaRispostaEntroGiorniNro" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="RichiestaNotificaMail" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="IndirizzoEmailNotifica" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *                   &lt;element name="NotificaPer"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="PresaInCarico"/&gt;
 *                         &lt;enumeration value="PresaVisione"/&gt;
 *                         &lt;enumeration value="Entrambe"/&gt;
 *                         &lt;enumeration value=""/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="FlagPrimaUltima"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="P"/&gt;
 *                         &lt;enumeration value="U"/&gt;
 *                         &lt;enumeration value=""/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssegnazioneInternaType", propOrder = {
    "dataOraAssegnazione",
    "flagPerConoscenza",
    "livelloPriorita",
    "motivoAssegnazione",
    "messaggioAssegnazione",
    "decorrenzaAssegnazione",
    "richiestaPresaInCaricoEntroGiorniNro",
    "richiestaRispostaEntroGiorniNro",
    "richiestaNotificaMail"
})
public class AssegnazioneInternaType
    extends SoggettoInternoType
{

    @XmlElement(name = "DataOraAssegnazione", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraAssegnazione;
    @XmlElement(name = "FlagPerConoscenza", required = true, defaultValue = "0")
    protected String flagPerConoscenza;
    @XmlElement(name = "LivelloPriorita")
    protected String livelloPriorita;
    @XmlElement(name = "MotivoAssegnazione")
    protected OggDiTabDiSistemaType motivoAssegnazione;
    @XmlElement(name = "MessaggioAssegnazione")
    protected String messaggioAssegnazione;
    @XmlElement(name = "DecorrenzaAssegnazione")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar decorrenzaAssegnazione;
    @XmlElement(name = "RichiestaPresaInCaricoEntroGiorniNro")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger richiestaPresaInCaricoEntroGiorniNro;
    @XmlElement(name = "RichiestaRispostaEntroGiorniNro")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger richiestaRispostaEntroGiorniNro;
    @XmlElement(name = "RichiestaNotificaMail")
    protected AssegnazioneInternaType.RichiestaNotificaMail richiestaNotificaMail;

    /**
     * Recupera il valore della proprietà dataOraAssegnazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraAssegnazione() {
        return dataOraAssegnazione;
    }

    /**
     * Imposta il valore della proprietà dataOraAssegnazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraAssegnazione(XMLGregorianCalendar value) {
        this.dataOraAssegnazione = value;
    }

    /**
     * Recupera il valore della proprietà flagPerConoscenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagPerConoscenza() {
        return flagPerConoscenza;
    }

    /**
     * Imposta il valore della proprietà flagPerConoscenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagPerConoscenza(String value) {
        this.flagPerConoscenza = value;
    }

    /**
     * Recupera il valore della proprietà livelloPriorita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLivelloPriorita() {
        return livelloPriorita;
    }

    /**
     * Imposta il valore della proprietà livelloPriorita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivelloPriorita(String value) {
        this.livelloPriorita = value;
    }

    /**
     * Recupera il valore della proprietà motivoAssegnazione.
     * 
     * @return
     *     possible object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public OggDiTabDiSistemaType getMotivoAssegnazione() {
        return motivoAssegnazione;
    }

    /**
     * Imposta il valore della proprietà motivoAssegnazione.
     * 
     * @param value
     *     allowed object is
     *     {@link OggDiTabDiSistemaType }
     *     
     */
    public void setMotivoAssegnazione(OggDiTabDiSistemaType value) {
        this.motivoAssegnazione = value;
    }

    /**
     * Recupera il valore della proprietà messaggioAssegnazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessaggioAssegnazione() {
        return messaggioAssegnazione;
    }

    /**
     * Imposta il valore della proprietà messaggioAssegnazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessaggioAssegnazione(String value) {
        this.messaggioAssegnazione = value;
    }

    /**
     * Recupera il valore della proprietà decorrenzaAssegnazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDecorrenzaAssegnazione() {
        return decorrenzaAssegnazione;
    }

    /**
     * Imposta il valore della proprietà decorrenzaAssegnazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDecorrenzaAssegnazione(XMLGregorianCalendar value) {
        this.decorrenzaAssegnazione = value;
    }

    /**
     * Recupera il valore della proprietà richiestaPresaInCaricoEntroGiorniNro.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRichiestaPresaInCaricoEntroGiorniNro() {
        return richiestaPresaInCaricoEntroGiorniNro;
    }

    /**
     * Imposta il valore della proprietà richiestaPresaInCaricoEntroGiorniNro.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRichiestaPresaInCaricoEntroGiorniNro(BigInteger value) {
        this.richiestaPresaInCaricoEntroGiorniNro = value;
    }

    /**
     * Recupera il valore della proprietà richiestaRispostaEntroGiorniNro.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRichiestaRispostaEntroGiorniNro() {
        return richiestaRispostaEntroGiorniNro;
    }

    /**
     * Imposta il valore della proprietà richiestaRispostaEntroGiorniNro.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRichiestaRispostaEntroGiorniNro(BigInteger value) {
        this.richiestaRispostaEntroGiorniNro = value;
    }

    /**
     * Recupera il valore della proprietà richiestaNotificaMail.
     * 
     * @return
     *     possible object is
     *     {@link AssegnazioneInternaType.RichiestaNotificaMail }
     *     
     */
    public AssegnazioneInternaType.RichiestaNotificaMail getRichiestaNotificaMail() {
        return richiestaNotificaMail;
    }

    /**
     * Imposta il valore della proprietà richiestaNotificaMail.
     * 
     * @param value
     *     allowed object is
     *     {@link AssegnazioneInternaType.RichiestaNotificaMail }
     *     
     */
    public void setRichiestaNotificaMail(AssegnazioneInternaType.RichiestaNotificaMail value) {
        this.richiestaNotificaMail = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="IndirizzoEmailNotifica" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
     *         &lt;element name="NotificaPer"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="PresaInCarico"/&gt;
     *               &lt;enumeration value="PresaVisione"/&gt;
     *               &lt;enumeration value="Entrambe"/&gt;
     *               &lt;enumeration value=""/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="FlagPrimaUltima"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="P"/&gt;
     *               &lt;enumeration value="U"/&gt;
     *               &lt;enumeration value=""/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "indirizzoEmailNotifica",
        "notificaPer",
        "flagPrimaUltima"
    })
    public static class RichiestaNotificaMail {

        @XmlElement(name = "IndirizzoEmailNotifica", required = true)
        protected List<String> indirizzoEmailNotifica;
        @XmlElement(name = "NotificaPer", required = true)
        protected String notificaPer;
        @XmlElement(name = "FlagPrimaUltima", required = true, defaultValue = "P")
        protected String flagPrimaUltima;

        /**
         * Gets the value of the indirizzoEmailNotifica property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the indirizzoEmailNotifica property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIndirizzoEmailNotifica().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getIndirizzoEmailNotifica() {
            if (indirizzoEmailNotifica == null) {
                indirizzoEmailNotifica = new ArrayList<String>();
            }
            return this.indirizzoEmailNotifica;
        }

        /**
         * Recupera il valore della proprietà notificaPer.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNotificaPer() {
            return notificaPer;
        }

        /**
         * Imposta il valore della proprietà notificaPer.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNotificaPer(String value) {
            this.notificaPer = value;
        }

        /**
         * Recupera il valore della proprietà flagPrimaUltima.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFlagPrimaUltima() {
            return flagPrimaUltima;
        }

        /**
         * Imposta il valore della proprietà flagPrimaUltima.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFlagPrimaUltima(String value) {
            this.flagPrimaUltima = value;
        }

    }

}
