
package it.csi.siac.integ.data._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per siNoIndifferenteEnum.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="siNoIndifferenteEnum"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="SI"/&amp;gt;
 *     &amp;lt;enumeration value="NO"/&amp;gt;
 *     &amp;lt;enumeration value="INDIFFERENTE"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "siNoIndifferenteEnum")
@XmlEnum
public enum SiNoIndifferenteEnum {

    SI,
    NO,
    INDIFFERENTE;

    public String value() {
        return name();
    }

    public static SiNoIndifferenteEnum fromValue(String v) {
        return valueOf(v);
    }

}
