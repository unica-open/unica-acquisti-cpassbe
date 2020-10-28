
package it.csi.siac.documenti.svc._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per statoElaborazione.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="statoElaborazione"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="AVVIATA"/&amp;gt;
 *     &amp;lt;enumeration value="CONCLUSA"/&amp;gt;
 *     &amp;lt;enumeration value="ERRORE"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "statoElaborazione")
@XmlEnum
public enum StatoElaborazione {

    AVVIATA,
    CONCLUSA,
    ERRORE;

    public String value() {
        return name();
    }

    public static StatoElaborazione fromValue(String v) {
        return valueOf(v);
    }

}
