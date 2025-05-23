
package org.etsi.uri._01903.v1_3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per OCSPValuesType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="OCSPValuesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EncapsulatedOCSPValue" type="{http://uri.etsi.org/01903/v1.3.2#}EncapsulatedPKIDataType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OCSPValuesType", propOrder = {
    "encapsulatedOCSPValue"
})
public class OCSPValuesType {

    @XmlElement(name = "EncapsulatedOCSPValue", required = true)
    protected List<EncapsulatedPKIDataType> encapsulatedOCSPValue;

    /**
     * Gets the value of the encapsulatedOCSPValue property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the encapsulatedOCSPValue property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEncapsulatedOCSPValue().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EncapsulatedPKIDataType }
     *
     *
     */
    public List<EncapsulatedPKIDataType> getEncapsulatedOCSPValue() {
        if (encapsulatedOCSPValue == null) {
            encapsulatedOCSPValue = new ArrayList<>();
        }
        return this.encapsulatedOCSPValue;
    }

}
