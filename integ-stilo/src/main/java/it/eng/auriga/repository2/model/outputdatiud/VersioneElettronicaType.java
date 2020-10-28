//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.05.26 alle 03:23:29 PM CEST 
//


package it.eng.auriga.repository2.model.outputdatiud;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Contiene le informazioni di un file associato all'unità documentaria
 * 
 * <p>Classe Java per VersioneElettronicaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="VersioneElettronicaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NomeFile" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="NroUltimaVersione" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="DifferenteDaAttuale" type="{}FlagSiNoType" /&gt;
 *       &lt;attribute name="ModificatoAlTsRich" type="{}FlagSiNoType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VersioneElettronicaType", propOrder = {
    "nomeFile",
    "nroUltimaVersione"
})
public class VersioneElettronicaType {

    @XmlElement(name = "NomeFile", required = true)
    protected String nomeFile;
    @XmlElement(name = "NroUltimaVersione")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger nroUltimaVersione;
    @XmlAttribute(name = "DifferenteDaAttuale")
    protected String differenteDaAttuale;
    @XmlAttribute(name = "ModificatoAlTsRich")
    protected String modificatoAlTsRich;

    /**
     * Recupera il valore della proprietà nomeFile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeFile() {
        return nomeFile;
    }

    /**
     * Imposta il valore della proprietà nomeFile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeFile(String value) {
        this.nomeFile = value;
    }

    /**
     * Recupera il valore della proprietà nroUltimaVersione.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNroUltimaVersione() {
        return nroUltimaVersione;
    }

    /**
     * Imposta il valore della proprietà nroUltimaVersione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNroUltimaVersione(BigInteger value) {
        this.nroUltimaVersione = value;
    }

    /**
     * Recupera il valore della proprietà differenteDaAttuale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDifferenteDaAttuale() {
        return differenteDaAttuale;
    }

    /**
     * Imposta il valore della proprietà differenteDaAttuale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDifferenteDaAttuale(String value) {
        this.differenteDaAttuale = value;
    }

    /**
     * Recupera il valore della proprietà modificatoAlTsRich.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModificatoAlTsRich() {
        return modificatoAlTsRich;
    }

    /**
     * Imposta il valore della proprietà modificatoAlTsRich.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModificatoAlTsRich(String value) {
        this.modificatoAlTsRich = value;
    }

}
