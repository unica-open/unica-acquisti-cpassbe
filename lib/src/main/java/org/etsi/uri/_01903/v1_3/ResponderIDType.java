
package org.etsi.uri._01903.v1_3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ResponderIDType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="ResponderIDType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="ByName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ByKey" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponderIDType", propOrder = {
    "byName",
    "byKey"
})
public class ResponderIDType {

    @XmlElement(name = "ByName")
    protected String byName;
    @XmlElement(name = "ByKey")
    protected byte[] byKey;

    /**
     * Recupera il valore della proprietà byName.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getByName() {
        return byName;
    }

    /**
     * Imposta il valore della proprietà byName.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setByName(String value) {
        this.byName = value;
    }

    /**
     * Recupera il valore della proprietà byKey.
     *
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getByKey() {
        return byKey;
    }

    /**
     * Imposta il valore della proprietà byKey.
     *
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setByKey(byte[] value) {
        this.byKey = value;
    }

}
