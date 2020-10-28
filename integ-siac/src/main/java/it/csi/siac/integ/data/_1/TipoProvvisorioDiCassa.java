
package it.csi.siac.integ.data._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per tipoProvvisorioDiCassa.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="tipoProvvisorioDiCassa"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="ENTRATA"/&amp;gt;
 *     &amp;lt;enumeration value="SPESA"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "tipoProvvisorioDiCassa")
@XmlEnum
public enum TipoProvvisorioDiCassa {

    ENTRATA,
    SPESA;

    public String value() {
        return name();
    }

    public static TipoProvvisorioDiCassa fromValue(String v) {
        return valueOf(v);
    }

}
