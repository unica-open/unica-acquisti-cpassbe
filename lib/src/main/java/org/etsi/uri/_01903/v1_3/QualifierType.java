
package org.etsi.uri._01903.v1_3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per QualifierType.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="QualifierType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OIDAsURI"/&gt;
 *     &lt;enumeration value="OIDAsURN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 *
 */
@XmlType(name = "QualifierType")
@XmlEnum
public enum QualifierType {

    @XmlEnumValue("OIDAsURI")
    OID_AS_URI("OIDAsURI"),
    @XmlEnumValue("OIDAsURN")
    OID_AS_URN("OIDAsURN");
    private final String value;

    QualifierType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QualifierType fromValue(String v) {
        for (QualifierType c: QualifierType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
