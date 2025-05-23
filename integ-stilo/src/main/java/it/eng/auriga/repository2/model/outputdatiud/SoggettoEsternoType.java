
package it.eng.auriga.repository2.model.outputdatiud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Soggetto (persona fisica o giuridica) esterno all'AOO
 * 
 * <p>Classe Java per SoggettoEsternoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SoggettoEsternoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodIdInAnagrafe" type="{}IdInSistemaEsternoType" minOccurs="0"/&gt;
 *         &lt;element name="FlagFisica" type="{}FlagSiNoType"/&gt;
 *         &lt;element name="Denominazione_Cognome" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodiceFiscale" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="16"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PartitaIva" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="11"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Sesso" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="M|F|-"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DataNascitaIstituzione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="ComuneNascita" type="{}ComuneItalianoType" minOccurs="0"/&gt;
 *         &lt;element name="StatoNascita" type="{}StatoNazionaleType" minOccurs="0"/&gt;
 *         &lt;element name="StatoCittadinanza" type="{}StatoNazionaleType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoEsternoType", propOrder = {
    "codIdInAnagrafe",
    "flagFisica",
    "denominazioneCognome",
    "nome",
    "codiceFiscale",
    "partitaIva",
    "sesso",
    "dataNascitaIstituzione",
    "comuneNascita",
    "statoNascita",
    "statoCittadinanza"
})
@XmlSeeAlso({
    SoggettoEstEstesoType.class,
    DestinatarioEsternoType.class
})
public class SoggettoEsternoType {

    @XmlElement(name = "CodIdInAnagrafe")
    protected String codIdInAnagrafe;
    @XmlElement(name = "FlagFisica", required = true)
    protected String flagFisica;
    @XmlElement(name = "Denominazione_Cognome", required = true)
    protected String denominazioneCognome;
    @XmlElement(name = "Nome")
    protected String nome;
    @XmlElement(name = "CodiceFiscale")
    protected String codiceFiscale;
    @XmlElement(name = "PartitaIva")
    protected String partitaIva;
    @XmlElement(name = "Sesso")
    protected String sesso;
    @XmlElement(name = "DataNascitaIstituzione")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataNascitaIstituzione;
    @XmlElement(name = "ComuneNascita")
    protected ComuneItalianoType comuneNascita;
    @XmlElement(name = "StatoNascita")
    protected StatoNazionaleType statoNascita;
    @XmlElement(name = "StatoCittadinanza")
    protected StatoNazionaleType statoCittadinanza;

    /**
     * Recupera il valore della proprietà codIdInAnagrafe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodIdInAnagrafe() {
        return codIdInAnagrafe;
    }

    /**
     * Imposta il valore della proprietà codIdInAnagrafe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodIdInAnagrafe(String value) {
        this.codIdInAnagrafe = value;
    }

    /**
     * Recupera il valore della proprietà flagFisica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagFisica() {
        return flagFisica;
    }

    /**
     * Imposta il valore della proprietà flagFisica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagFisica(String value) {
        this.flagFisica = value;
    }

    /**
     * Recupera il valore della proprietà denominazioneCognome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazioneCognome() {
        return denominazioneCognome;
    }

    /**
     * Imposta il valore della proprietà denominazioneCognome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazioneCognome(String value) {
        this.denominazioneCognome = value;
    }

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il valore della proprietà codiceFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Recupera il valore della proprietà partitaIva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitaIva() {
        return partitaIva;
    }

    /**
     * Imposta il valore della proprietà partitaIva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitaIva(String value) {
        this.partitaIva = value;
    }

    /**
     * Recupera il valore della proprietà sesso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSesso() {
        return sesso;
    }

    /**
     * Imposta il valore della proprietà sesso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSesso(String value) {
        this.sesso = value;
    }

    /**
     * Recupera il valore della proprietà dataNascitaIstituzione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataNascitaIstituzione() {
        return dataNascitaIstituzione;
    }

    /**
     * Imposta il valore della proprietà dataNascitaIstituzione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataNascitaIstituzione(XMLGregorianCalendar value) {
        this.dataNascitaIstituzione = value;
    }

    /**
     * Recupera il valore della proprietà comuneNascita.
     * 
     * @return
     *     possible object is
     *     {@link ComuneItalianoType }
     *     
     */
    public ComuneItalianoType getComuneNascita() {
        return comuneNascita;
    }

    /**
     * Imposta il valore della proprietà comuneNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link ComuneItalianoType }
     *     
     */
    public void setComuneNascita(ComuneItalianoType value) {
        this.comuneNascita = value;
    }

    /**
     * Recupera il valore della proprietà statoNascita.
     * 
     * @return
     *     possible object is
     *     {@link StatoNazionaleType }
     *     
     */
    public StatoNazionaleType getStatoNascita() {
        return statoNascita;
    }

    /**
     * Imposta il valore della proprietà statoNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoNazionaleType }
     *     
     */
    public void setStatoNascita(StatoNazionaleType value) {
        this.statoNascita = value;
    }

    /**
     * Recupera il valore della proprietà statoCittadinanza.
     * 
     * @return
     *     possible object is
     *     {@link StatoNazionaleType }
     *     
     */
    public StatoNazionaleType getStatoCittadinanza() {
        return statoCittadinanza;
    }

    /**
     * Imposta il valore della proprietà statoCittadinanza.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoNazionaleType }
     *     
     */
    public void setStatoCittadinanza(StatoNazionaleType value) {
        this.statoCittadinanza = value;
    }

}
