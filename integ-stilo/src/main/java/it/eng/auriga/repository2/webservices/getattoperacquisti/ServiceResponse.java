
package it.eng.auriga.repository2.webservices.getattoperacquisti;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &amp;lt;element name="serviceReturn" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
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
    "serviceReturn"
})
@XmlRootElement(name = "serviceResponse")
public class ServiceResponse {

    @XmlElement(required = true)
    protected String serviceReturn;

    /**
     * Recupera il valore della proprietà serviceReturn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceReturn() {
        return serviceReturn;
    }

    /**
     * Imposta il valore della proprietà serviceReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceReturn(String value) {
        this.serviceReturn = value;
    }

}
