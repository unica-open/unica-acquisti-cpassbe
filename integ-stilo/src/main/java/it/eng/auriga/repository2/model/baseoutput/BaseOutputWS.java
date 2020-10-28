//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.2 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.05.26 alle 03:23:28 PM CEST 
//


package it.eng.auriga.repository2.model.baseoutput;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="WSResult" type="{}WSResultType"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="WSError"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;extension base="{}WSErrorType"&gt;
 *                 &lt;/extension&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="WarningMessage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;/choice&gt;
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
    "wsResult",
    "wsError",
    "warningMessage"
})
@XmlRootElement(name = "BaseOutput_WS")
public class BaseOutputWS {

    @XmlElement(name = "WSResult", required = true)
    protected String wsResult;
    @XmlElement(name = "WSError")
    protected BaseOutputWS.WSError wsError;
    @XmlElement(name = "WarningMessage")
    protected String warningMessage;

    /**
     * Recupera il valore della proprietà wsResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWSResult() {
        return wsResult;
    }

    /**
     * Imposta il valore della proprietà wsResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWSResult(String value) {
        this.wsResult = value;
    }

    /**
     * Recupera il valore della proprietà wsError.
     * 
     * @return
     *     possible object is
     *     {@link BaseOutputWS.WSError }
     *     
     */
    public BaseOutputWS.WSError getWSError() {
        return wsError;
    }

    /**
     * Imposta il valore della proprietà wsError.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseOutputWS.WSError }
     *     
     */
    public void setWSError(BaseOutputWS.WSError value) {
        this.wsError = value;
    }

    /**
     * Recupera il valore della proprietà warningMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarningMessage() {
        return warningMessage;
    }

    /**
     * Imposta il valore della proprietà warningMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarningMessage(String value) {
        this.warningMessage = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{}WSErrorType"&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class WSError
        extends WSErrorType
    {


    }

}
